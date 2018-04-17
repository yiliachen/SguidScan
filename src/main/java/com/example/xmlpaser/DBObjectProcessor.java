package com.example.xmlpaser;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.model.DBObject;
import com.example.service.DBObjectService;

@Scope("prototype")
@Service("dbobjectProcessor")
public class DBObjectProcessor extends ProcessorBase {
	@Autowired
	DBObjectService dbobjectService;

	public DBObjectProcessor() {
	}

	public DBObjectProcessor(String pEntryName, Set pfes) {
		super.setFes(pfes);
		super.setmEntryName(pEntryName);
		super.setmDebug(false);
	}

	public void process() {
		try {
			File inputFile = new File(this.mEntryName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(false);
			dbf.setNamespaceAware(true);
			dbf.setFeature("http://xml.org/sax/features/namespaces", false);
			dbf.setFeature("http://xml.org/sax/features/validation", false);
			dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
			dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			DocumentBuilder dBuilder = dbf.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			String _type = "TABLE";
			NodeList _tabNode = doc.getElementsByTagName("TABLE");
			if (_tabNode.getLength() == 0) {
				_tabNode = doc.getElementsByTagName("VIEW");
				_type = "VIEW";
			}

			if (_tabNode.getLength() > 0) {
				// goes into the table node find name tag.
				String _objName = "";
				NodeList _tabChild = _tabNode.item(0).getChildNodes();
				Node _objNode = _tabNode.item(0);
				for (int i = 0; i < _tabChild.getLength(); i++) {
					if (_tabChild.item(i) != null && _tabChild.item(i).getNodeType() == Element.ELEMENT_NODE) {
						Node nd = _tabChild.item(i);
						if (nd != null && nd.getNodeType() == Element.ELEMENT_NODE && nd.getNodeName().equals("NAME")) {
							_objName = nd.getTextContent();
							break;
						}
					}
				}
				Iterator<DBObject> _dbobj = dbobjectService.getDBObjectByPath(this.getmEntryName()).iterator();
				while (_dbobj.hasNext()) {
					dbobjectService.remove(_dbobj.next());
				}
				//get all the columns' tags
				NodeList _colList = ((Element) _objNode).getElementsByTagName("COL_LIST_ITEM");
				String _colName = "";
				for (int i = 0; i < _colList.getLength(); i++) {
					Node _col = (Node) _colList.item(i);
					if (_col != null && _col.hasChildNodes()) {
						NodeList _colChild = _col.getChildNodes();
						//get the name of the column
						for (int i1 = 0; i1 < _colChild.getLength(); i1++) {
							Node nd = (Node) _colChild.item(i1);
							if (nd != null && nd.getNodeType() == Element.ELEMENT_NODE
									&& nd.getNodeName().equals("NAME")) {
								_colName = ((Element) nd).getTextContent();
								DBObject _dbObj = new DBObject(_objName, this.mEntryName, _colName, _type);
								dbobjectService.save(_dbObj);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.process();
	}
}
