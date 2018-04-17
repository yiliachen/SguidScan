package com.example.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Stack;

public class CyclicDependenciesSameDirUsingGraph {
	public CyclicDependenciesSameDirUsingGraph(String configFile){
		Properties prop = new Properties();
    	InputStream input = null;
    	try {
			input = new FileInputStream(configFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			prop.load(input);
		    this.setBASE_DIR_SHELL_SCR(prop.getProperty("cyclic_parent_dir"));
		    this.setFETCH_TRANS_FILES_SHELL_SCR(prop.getProperty("cyclic_fetchFilesInTrans"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String _basePath = null;
	private String BASE_DIR_SHELL_SCR;
	
	public String getBASE_DIR_SHELL_SCR() {
		return BASE_DIR_SHELL_SCR;
	}

	public void setBASE_DIR_SHELL_SCR(String bASE_DIR_SHELL_SCR) {
		BASE_DIR_SHELL_SCR = bASE_DIR_SHELL_SCR;
	}

	public String getFETCH_TRANS_FILES_SHELL_SCR() {
		return FETCH_TRANS_FILES_SHELL_SCR;
	}

	public void setFETCH_TRANS_FILES_SHELL_SCR(String fETCH_TRANS_FILES_SHELL_SCR) {
		FETCH_TRANS_FILES_SHELL_SCR = fETCH_TRANS_FILES_SHELL_SCR;
	}

	private String FETCH_TRANS_FILES_SHELL_SCR;
	private static String FND_DIR = "FND";
	
	public class Graph{
		Integer vertices;
		int[] colorVertex=null; //1=White(Unvisited),2=Gray(Visiting),3=Black(Visited)
        Stack<String> stack = new Stack<String>(); 
        LinkedList<Stack<String>> listOfStacks = null;
        LinkedList<LinkedList<String>> adjLists; // Adjacency Lists for the Graph
        LinkedList<String> dependencyList = null; //Dependency List for the Graph
        LinkedList<String> allFileList = null; //Master List of all files
        
        //Constructor used to initialize the graph
        Graph(List<String> filePaths) throws FileNotFoundException
        {
        	Scanner sc = new Scanner(System.in);
        	File file;
        	String line;
            adjLists = new LinkedList<LinkedList<String>>();
            allFileList = new LinkedList<String>();
            stack.addAll(filePaths);
            String currentFile = null;
    		String filePathFromLine= null;
    		int startIndexForLine,endIndexForLine;
    		String pathType=null;
    		
            while(!stack.isEmpty()) {
            	currentFile = stack.peek();
            	if(!allFileList.contains(currentFile)) 
				{
	            	file = new File(stack.pop());
					sc = new Scanner(file);
					
					dependencyList = new LinkedList<String>();
					
					while (sc.hasNextLine())
				    {
					    line = sc.nextLine();
	
					    //Find the task references for a file and store those as a list of strings.
					    if(line.contains("task_reference")) 
					    {
					    	startIndexForLine=line.indexOf("te=\"")+("te=\"").length();
					    	endIndexForLine=line.indexOf("\"", startIndexForLine);
					    	pathType = line.substring(startIndexForLine, endIndexForLine);
					    	//FND directly explicitly skipped
					    	if(pathType.equalsIgnoreCase(FND_DIR))
					    		continue;
					    	startIndexForLine=line.indexOf("subdir=\"")+("subdir=\"").length();
					    	endIndexForLine=line.indexOf("\"", startIndexForLine);
							filePathFromLine=line.substring(startIndexForLine, endIndexForLine);
							startIndexForLine=line.indexOf("file_basename=\"")+("file_basename=\"").length();
							endIndexForLine=line.indexOf("\"", startIndexForLine);
							filePathFromLine+="/"+line.substring(startIndexForLine, endIndexForLine);
							filePathFromLine=filePathFromLine.replaceFirst(pathType, _basePath+"/"+pathType.toLowerCase()+"/db/data");
							if(!(new File(filePathFromLine)).isFile())
							{
								System.out.println("Invalid task reference: "+filePathFromLine + "in file "+currentFile);
							}
							else {
								dependencyList.add(filePathFromLine);
								stack.push(filePathFromLine);
							}
							
						}
					    
				    }
					allFileList.add(currentFile);
					adjLists.add(dependencyList);
					sc.close();
				
				}
            	else
            		stack.pop();
            }
            vertices = allFileList.size();
        }
        
        //Print the adjacency list for the graph
        void printGraph() {
        	if(vertices==0)
        	{
        		System.out.println("Graph is empty.");
        		return;
        	}
        	for(int i=0;i<vertices;i++) {
        		System.out.println(i+" "+allFileList.get(i)+" --> "+ adjLists.get(i));
        	}
        }
        
      //Find all the cycles in all the vertices of the graph
        boolean findCyclicDependencies() {
        	colorVertex = new int[vertices];
        	listOfStacks = new LinkedList<Stack<String>>();
        	
        	for(int i=0;i<vertices;i++)
        		colorVertex[i]=1;
        	
        	//Iterate for all the vertices in the graph. For every vertex, we check if there is a cycle.
        	for(int i=0;i<vertices;i++)
        	{
        		stack = new Stack<String>();
        		/*for(int j=0;j<vertices;j++)
        			colorVertex[j]=1;*/
        		//System.out.println("Current vertex: "+i+" "+allFileList.get(i));
        		stack.push(allFileList.get(i));
        		if(checkCyclicDependency(i))
        		{
        			//stack.pop();
        			if(stack.elementAt(0).equalsIgnoreCase(stack.peek()))
        				listOfStacks.add(stack);
        		}	
        	}
        	if(listOfStacks.isEmpty())
        		return false;
        	else
        		return true;
        }
        
        
        //Recursively find all the cycles in the graph
        boolean checkCyclicDependency(int i)
        {
        	colorVertex[i]=2; //In Process
            //String currentFile = allFileList.get(i);
            int k;
            for(int j=0;j<adjLists.get(i).size();j++) {
            	String filePath = adjLists.get(i).get(j);
            	//System.out.println(" Current Flow "+currentFile+" Current Iteration: "+filePath+" Current Stack: "+stack);
            	k=allFileList.indexOf(filePath);
            	stack.push(filePath);
            	if(colorVertex[k]==2)
            		return true;
            	if(colorVertex[k]==1 && checkCyclicDependency(k))
            		return true;
            	stack.pop();
            }
            
            colorVertex[i]=3; //Processed
            //stack.pop();
            return false;
        }
        
	}
	
	//Generate output file in HTML format
	void generateOutputFile(Graph newFileGraph) throws FileNotFoundException {
		String fileName = "CyclicDependencies_"+new Date().getTime()+".htm";
		File outputFile = new File(fileName);
		PrintWriter writer = new PrintWriter(outputFile);
    	writer.write("<html><head><title>Cyclic Dependencies for Input Files</title></head>");
		writer.write("<body>");
		writer.write("Following cyclic dependencies exist: <br>");
		writer.write("<ul>");
		
		//Check cyclic dependencies
    	if(newFileGraph.findCyclicDependencies()) {
    		for(Stack<String> mainStack : newFileGraph.listOfStacks) {
    			System.out.println("Cycle: "+mainStack);
    			writer.write("<li>"+mainStack.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", "-->")+"</li>");
    		}
    	}
    		
    	else {
    		System.out.println("No cycle");
    		writer.write("<li>No cycle</li>");
    	}
    	
    	writer.write("</ul>");
    	System.out.println("Output generated in "+fileName);
    	
    	writer.write("</body></html>");
    	writer.flush();
    	writer.close();
	}
	
    public static void main(String[] args) throws IOException {
    	if(args.length != 1){
    		System.err.println("Usage:CyclicDependencies configFilePath");
    		System.exit(-1);
    	}
    	String configFile = args[0];
    	CyclicDependenciesSameDirUsingGraph newObj = new CyclicDependenciesSameDirUsingGraph(configFile);
    	
    	//Graph newFileGraph = newObj.new Graph("/scratch/anidasgu/view_storage/anidasgu_hcmView1/fusionapps/hcm/hrx/db/data/HcmLocKWTop/HcmLocKWPaySetupTop/HcmLocKWPaySetupData/DIRCardDefinitionSD.xml");
    	//Graph newFileGraph = newObj.new Graph("C:/Users/anidasgu/Desktop/FileTest/SeedData.txt");
    	/*ArrayList<Graph> listOfGraphs = new ArrayList<Graph>();
    	for(String arg : args) {
    		listOfGraphs.add(newObj.new Graph(arg));
    	}*/

    	List<String> listOfInputPaths = null;
    	String inputFilePath;
    	
    	//Find base file path
    	String command = newObj.BASE_DIR_SHELL_SCR;  
    	Process process = Runtime.getRuntime().exec(command);                    
        BufferedReader reader = new BufferedReader(new InputStreamReader(        
            process.getInputStream()));                                          
        newObj._basePath = reader.readLine();
        
        //Find the list of files
    	command = newObj.FETCH_TRANS_FILES_SHELL_SCR;  
    	process = Runtime.getRuntime().exec(command);    
    	reader = new BufferedReader(new InputStreamReader(        
                process.getInputStream()));
    	inputFilePath=reader.readLine().replaceAll("fusionapps/hcm", newObj._basePath);
    	listOfInputPaths = Arrays.asList(inputFilePath.split(" "));
    	//listOfInputPaths = new ArrayList<String>();
    	//listOfInputPaths.add("C:/Users/anidasgu/Desktop/FileTest/SeedData.txt");
    	//listOfInputPaths.add("C:/Users/anidasgu/Desktop/FileTest/File1.txt");
    	
    	
    	//Initialize the graph
    	Graph newFileGraph = newObj.new Graph(listOfInputPaths);
    	
    	/*Graph newFileGraph = newObj.new Graph(new String[] {
    			"C:/Users/anidasgu/Desktop/FileTest/SeedData.txt",
    			"C:/Users/anidasgu/Desktop/FileTest/File1.txt"
    	});*/
    	
    	//newFileGraph.printGraph();
    	
    	//Check if the graph has cyclic dependencies and save the output into an HTML file
    	newObj.generateOutputFile(newFileGraph);	
    	
    }
}
