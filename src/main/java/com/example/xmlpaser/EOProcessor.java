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
import org.w3c.dom.NodeList;

import com.example.model.EntityObject;
import com.example.service.EOService;

@Scope("prototype")
@Service("eoProcessor")
public class EOProcessor extends ProcessorBase 
{
	public EOProcessor(String pEntryName, Set pfes){
		super.setFes(pfes);
		super.setmEntryName(pEntryName);
		super.setmDebug(false);
	}
	
	@Autowired
	EOService eoservice;
	
	public void process(){
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
			NodeList nl = doc.getElementsByTagName("Entity");
			if(nl.getLength()>0){
				if(nl.item(0).getNodeType()==Element.ELEMENT_NODE){
					Element el = (Element)nl.item(0);
					Iterator<EntityObject> _eoiter = eoservice.getEntityByPath(getmEntryName()).iterator();
					while(_eoiter.hasNext()){
						eoservice.remove(_eoiter.next());
					}
					EntityObject eo = new EntityObject(el.getAttribute("Name"), 
							this.mEntryName, 
							el.getAttribute("DBObjectType"), 
							el.getAttribute("DBObjectName"),
							el.getAttribute("EffectiveDateType"));
					eoservice.save(eo);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public EOProcessor(){}

	@Override
	public void run() {
		this.process();
	}
}
