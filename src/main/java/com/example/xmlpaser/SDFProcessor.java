package com.example.xmlpaser;

import java.io.File;
import java.util.HashSet;
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

import com.example.model.SDF;
import com.example.service.SDFService;

@Scope("prototype")
@Service("sdfProcessor")
public class SDFProcessor extends ProcessorBase {
	@Autowired
	SDFService sdfService;
	
	public SDFProcessor(String pEntryName, Set pfes) {
		super.setFes(pfes);
		super.setmEntryName(pEntryName);
		super.setmDebug(false);
	}
	
	private Set<String> tagset = new HashSet<String>();
	
	public void iterNodes(Node node){
		if(node.getNodeType()==Element.ELEMENT_NODE){
			Element ele = (Element)node;
			if(ele.hasAttribute("rowkey")){
				tagset.add(ele.getTagName());
			}
		}
		
		if(node.hasChildNodes()){
			NodeList nl = node.getChildNodes();
			for(int i=0;i<nl.getLength();i++){
				iterNodes(nl.item(i));
			}
		}
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

			NodeList nl = doc.getElementsByTagName("SEEDDATA");
			if (nl.getLength() > 0) {
				if (nl.item(0).getNodeType() == Element.ELEMENT_NODE) {
					Element el = (Element) nl.item(0);
					String am = el.getAttribute("am");
					String vo = el.getAttribute("vo");
					String effective_dated = el.getAttribute("effective_dated");
					String ModuleId = el.getAttribute("partitionkey_value");
					iterNodes(el);
					Iterator<SDF> _iter = sdfService.finbyPath(getmEntryName()).iterator();
					while(_iter.hasNext()){
						SDF _sdf = _iter.next();
						sdfService.remove(_sdf);
					}
					Iterator<String> _tagiter = tagset.iterator();
					while(_tagiter.hasNext()){
						String tag = _tagiter.next();
						SDF sdf = new SDF(this.mEntryName,am,ModuleId,tag,effective_dated);
						sdfService.save(sdf);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SDFProcessor(){}

	@Override
	public void run() {
		this.process();
	}
}
