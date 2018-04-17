package com.example.xmlpaser;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.model.FileEntry;

public class AttributeProcessor implements Processor 
{
	private String mEntryName = null;
	private boolean mDebug = false;
	private Set<FileEntry> fes = null;
	private String startPath = null;
	public String getmEntryName() {
		return mEntryName;
	}
	private Hashtable<String,String> sguidSet = new Hashtable<String,String>();
	private Hashtable<String,String> mkeyset = new Hashtable<String, String>();
	private boolean mEndFound = false;
	private boolean mDe = false;


	public void setmEntryName(String mEntryName) {
		this.mEntryName = mEntryName;
	}


	public Set<FileEntry> getFes() {
		return fes;
	}


	public void setFes(Set<FileEntry> fes) {
		this.fes = fes;
	}


	public AttributeProcessor(String pEntryName, Set<FileEntry> pfes){
		this.mEntryName = pEntryName;
		this.fes = pfes;
	}
	
	
	@Autowired
//	private FileEntryService fes;
	
	public void process(){
		try {
			File inputFile = new File(this.mEntryName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			this.iterDocs(doc.getDocumentElement());
			Iterator<String> iter = this.getVOKeyCount().keySet().iterator();
			System.out.print(this.mEntryName+"|");
			while (iter.hasNext()) {
				String l_key = iter.next();
				FileEntry fe = new FileEntry();
				fe.setFilename(this.mEntryName);
				
				if (l_key.indexOf("_NULL") > 0 ) {
					System.out.print(l_key + ":" + this.getVOKeyCount().get(l_key));
					fe.setEntryName(l_key);
					fe.setType("SGUID isNull='true' case");
					fe.setOccourance(this.getVOKeyCount().get(l_key));
				}
				else if(l_key.indexOf("_DUPSGUID_") >= 0){
					System.out.print("DUP_"+l_key + ":" + this.getVOKeyCount().get(l_key));
					fe.setEntryName(l_key);
					fe.setType("Duplicated SGUID");
					fe.setOccourance(this.getVOKeyCount().get(l_key));
				}
				else if(l_key.indexOf("DERecord") >= 0 ){
					System.out.print("DerecDUP_"+l_key + ":" + this.getVOKeyCount().get(l_key));
					fe.setEntryName(l_key);
					fe.setType("Dateeffective Records");
					fe.setOccourance(this.getVOKeyCount().get(l_key));
				}
				else if(l_key.indexOf("rowkey") >= 0){
					System.out.print(l_key + ":" + this.getVOKeyCount().get(l_key));
					fe.setEntryName(l_key);
					fe.setType("RowKey Counts");
					fe.setOccourance(this.getVOKeyCount().get(l_key));
				}
				else if (l_key.indexOf("SGUID") < 0 && l_key.indexOf("NoSguidTag_") <0) {
					System.out.print(l_key + ":" + this.getVOKeyCount().get(l_key));
					fe.setEntryName(l_key);
					fe.setType("This VO's row count");
					fe.setOccourance(this.getVOKeyCount().get(l_key));
				}
				else if (l_key.indexOf("NoSguidTag_") >= 0) {
					System.out.print(l_key + ":" + this.getVOKeyCount().get(l_key));
					fe.setEntryName(l_key);
					fe.setType("No Sguid Tag");
					fe.setOccourance(this.getVOKeyCount().get(l_key));
				}
				else if (this.mDebug) {
					System.out.println(l_key + ":" + this.getVOKeyCount().get(l_key));
				} else {
					continue;
				}
				System.out.print("|");
				fe.setStartPath(this.getStartPath());
				fes.add(fe);
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	Map<String, Integer> VOKeyCount = new HashMap<String, Integer>();

	public void selfIncrease(String p_Key) {
		if (this.VOKeyCount.containsKey(p_Key)) {
			this.VOKeyCount.put(p_Key, this.VOKeyCount.get(p_Key) + 1);
		} else {
			this.VOKeyCount.put(p_Key, 1);
		}
	}
	
	public void checkDebegin(Element pEle){
		if (pEle.hasAttribute("debegin")) {
			this.mDe = true;
			if(pEle.hasAttribute("deend")){
				this.mEndFound = true;
				this.mDe = false;
			}
		}
	}
	
	public void checkDeend(Element pEle) {
		if (pEle.hasAttribute("deend") && this.mEndFound == false) {
			this.mDe = false;
			System.out.println("!!!File: " + this.mEntryName + "contains Records have different SGUID within DE:!!!");
			if(this.mkeyset.keySet().size() > 1){
				Iterator<Entry<String, String>> liter = this.mkeyset.entrySet().iterator();
				while (liter.hasNext()) {
					Entry<String, String> lentry = liter.next();
					this.selfIncrease("DERecord"+lentry.getKey() + "_" + lentry.getValue());
				}
			}
			this.mkeyset.clear();
		}
	}

	public void iterDocs(Node pNode) {
		//Iterate all the nodes of the XML
		if (pNode.getNodeType() == Node.ELEMENT_NODE) {
			Element l_ele = (Element) pNode;
			if (l_ele.hasAttribute("rowkey")) {
				selfIncrease("View_"+l_ele.getNodeName());
				selfIncrease("rowkey");
				NodeList sguidlist = l_ele.getElementsByTagName("SGUID");
				if(sguidlist.getLength()==0){
					selfIncrease("NoSguidTag_"+l_ele.getNodeName()+"_"+l_ele.getAttribute("rowkey"));
				}
			}
		}
		NodeList nl = pNode.getChildNodes();
		for (int iter = 0; iter < nl.getLength(); iter++) {
			Node l_node = nl.item(iter);
			
			if (l_node.getNodeName().equals("SGUID")) {
				Node lParentNode = l_node.getParentNode();
				if (lParentNode.getNodeType() == Node.ELEMENT_NODE) {
					Element lParentElem = (Element) lParentNode;
					this.checkDebegin(lParentElem);
					this.selfIncrease(l_node.getParentNode().getNodeName() + "_" + l_node.getNodeName());
					if (l_node.getTextContent().length() != 0 && !this.mDe) {
						String lrowkey = lParentElem.getAttribute("rowkey");
						if(this.sguidSet.containsKey(l_node.getTextContent()))
						{
							//rowkey and sguid are not identical
							if(!this.sguidSet.get(l_node.getTextContent()).equals(lrowkey)){
								selfIncrease(lrowkey+"_DUPSGUID_"+l_node.getTextContent());
							}
						}else{
							this.sguidSet.put(l_node.getTextContent(), lrowkey);
						}
						selfIncrease(l_node.getParentNode().getNodeName() + "_" + l_node.getNodeName() + "_"
								+ l_node.getTextContent());
					} else if(l_node.getTextContent().length() != 0 && this.mDe) {
					    this.selfIncrease(l_node.getParentNode().getNodeName()+"_"+l_node.getTextContent()+"_DERecord");
					    this.mkeyset.put(l_node.getTextContent(),lParentElem.getAttribute("rowkey"));
					}else if(l_node.getTextContent().length() == 0){
						selfIncrease(l_node.getParentNode().getNodeName() + "_" + l_node.getNodeName() + lParentElem.getAttribute("rowkey")+"_NULL");
					}
					this.checkDeend(lParentElem);
				}
			}

			if (l_node.hasChildNodes()) {
				iterDocs(l_node);
			}
		}
	}

	public Map<String, Integer> getVOKeyCount() {
		return VOKeyCount;
	}
	public boolean getmDebug() {
		return mDebug;
	}

	public void setmDebug(boolean mDebug) {
		this.mDebug = mDebug;
	}


	@Override
	public void run() {
		this.process();
	}
	
	public String getStartPath() {
		return startPath;
	}
	
	public void setStartPath(String startPath) {
		this.startPath = startPath;
	}

}
