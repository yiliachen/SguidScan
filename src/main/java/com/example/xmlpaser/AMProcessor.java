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

import com.example.model.AM;
import com.example.service.AMService;

@Scope("prototype")
@Service("amProcessor")
public class AMProcessor extends ProcessorBase {

	@Autowired
	AMService amservice;

	public AMProcessor() {
	}

	public AMProcessor(String pEntryName, Set pfes) {
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
			NodeList nl = doc.getElementsByTagName("AppModule");
			if (nl.getLength() > 0) {
				if (nl.item(0).getNodeType() == Element.ELEMENT_NODE) {
					Element el = (Element) nl.item(0);
					String name = el.getAttribute("Name");
					NodeList nlViewUsage = el.getElementsByTagName("ViewUsage");
					Iterator<AM> _iter = amservice.findByPath(getmEntryName()).iterator();
					while (_iter.hasNext()) {
						AM _am = _iter.next();
						amservice.delete(_am);
					}
					if (nlViewUsage.getLength() > 0) {
						for (int i = 0; i < nlViewUsage.getLength(); i++) {
							Node nVU = nlViewUsage.item(i);
							if (nVU.getNodeType() == Element.ELEMENT_NODE) {
								Element eVU = (Element) nVU;
								AM am = new AM(name, this.mEntryName, eVU.getAttribute("Name"),
										eVU.getAttribute("ViewObjectName"));
								amservice.add(am);
							}
						}
					} else {
						AM am = new AM(name, this.mEntryName, "NA", "NA");
						amservice.add(am);
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
