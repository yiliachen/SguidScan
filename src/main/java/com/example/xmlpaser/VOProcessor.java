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

import com.example.model.ViewObject;
import com.example.service.ViewObjectService;
@Scope("prototype")
@Service("voProcessor")
public class VOProcessor extends ProcessorBase {

	public VOProcessor(String pEntryName, Set pfes) {
		super.setFes(pfes);
		super.setmEntryName(pEntryName);
		super.setmDebug(false);
	}
	
	public VOProcessor(){}

	@Autowired
	ViewObjectService voservice;

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
			NodeList nl = doc.getElementsByTagName("ViewObject");
			if (nl.getLength() > 0) {
				if (nl.item(0).getNodeType() == Element.ELEMENT_NODE) {
					Element el = (Element) nl.item(0);
					String name = el.getAttribute("Name");
					String IsEffectiveDated = el.getAttribute("IsEffectiveDated");
					String Extends = el.getAttribute("Extends");
					
					Iterator<ViewObject> _voiter = voservice.getViewObjectByPath(getmEntryName()).iterator();
					while(_voiter.hasNext()){
						voservice.delete(_voiter.next());
					}
					
					NodeList nlEntityUsage = el.getElementsByTagName("EntityUsage");
					if (nlEntityUsage.getLength() > 0) {
						for (int i = 0; i < nlEntityUsage.getLength(); i++) {
							Node nEU = nlEntityUsage.item(i);
							if (nEU.getNodeType() == Element.ELEMENT_NODE) {
								Element eEU = (Element) nEU;
								ViewObject vo = new ViewObject(name, this.mEntryName, eEU.getAttribute("Name"),
										eEU.getAttribute("Entity"), IsEffectiveDated,Extends);
								voservice.save(vo);
							}
						}
					} else {
						ViewObject vo = new ViewObject(name, this.mEntryName, "NA", "NA", IsEffectiveDated,Extends);
						voservice.save(vo);
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
