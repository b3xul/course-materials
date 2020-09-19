package it.polito.po.fileexamples;

public class TemplateCSV {
	
	
	
	
	private List<YourJavaItem> processInputFile(String inputFilePath) {
		
		
	    List<YourJavaItem> inputList = new ArrayList<YourJavaItem>();
	    
	    try{
	      File inputF = new File(inputFilePath);
		  InputStream inputFS = new FileInputStream(inputF);
	      BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
	      //OR
		  //BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
		  
		  
		  
		  // skip or process the header of the csv
	      inputList = br.lines().skip(1).map(processLine).collect(Collectors.toList());
		  //OR
		  //String[] headers = br.remove(0);
		  //processHeaders(headers);
		  //inputList = br.lines().map(processLine).collect(Collectors.toList());
		  
	      br.close();
	    } catch (FileNotFoundException|IOException e) {
	      ....
	    }
	    return inputList ;
	}
	



	private Function<String, YourJavaItem> processLine = (line) -> {
	
	  //String[] p = line.split(COMMA);// a CSV has comma separated values
		String[] p = line.split(",");
		YourJavaItem item = new YourJavaItem();
	  
	  item.doSomethingOnTheFirstValue(p[0]);//<-- this is the first column in the csv file
	  
	  //do something on an optional value
	  if (p[3] != null && p[3].trim().length() > 0) {
		item.setSomeProperty(p[3]);
	  }
	  //more operations on values go here
	  //..
	  return item;
	}
	
	
	private void processHeaders(){
	
	//...
	
	}
	
	
	
}
