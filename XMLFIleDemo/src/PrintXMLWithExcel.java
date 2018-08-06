import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.ss.format.CellElapsedFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PrintXMLWithExcel 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try
		{
		//opening the XML File
			String filepathXML = "C:\\Users\\guptaksh\\Desktop\\Coding Practice\\XML-JAVA\\c7b57990-78b5-4b00-a107-eeddca279613_params.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepathXML);
			
		//opening the Excel File
			String filepathExcel = "C:\\Users\\guptaksh\\Desktop\\Coding Practice\\XML-JAVA\\Book1.xlsx";
			FileInputStream fls = new FileInputStream(new File(filepathExcel));
			XSSFWorkbook workbook = new XSSFWorkbook(fls);
			XSSFSheet sheet = workbook.getSheetAt(0);
		
		//reference https://dzone.com/articles/java-dom-printing-content-node
			TransformerFactory tranFactory = TransformerFactory.newInstance();
			Transformer trans = tranFactory.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		    trans.setOutputProperty(OutputKeys.INDENT, "yes");
		    
		//for printing in console
		    StringWriter sw = new StringWriter();
		    //for file editing
		    //StreamResult sr = new StreamResult(new File(filepath));
		    //for printing the result on console.
		    StreamResult sr = new StreamResult(sw);
		   
		//getting the first row of the excel
		    Row row = sheet.getRow(0);
		    Iterator<Cell> iterator = row.cellIterator();
		//getting the value from the document with tag named as parameters.
		    NodeList tagParameterList = doc.getElementsByTagName("parameters");
		    	
		    while (iterator.hasNext())
		    {
		    	Cell cell = iterator.next();
		    //printing the cell value obtained from excel sheet
		    	System.out.println(cell.getStringCellValue());

		    //tagParameterList = nodes based on parameters
				for (int j=0;j<tagParameterList.getLength(); j++)
				{
			//individual node taken from nodelist
					Node parameter1 = tagParameterList.item(j);
			//getting all the child nodes
					NodeList nl = parameter1.getChildNodes();
					
			//iterating through every child node
					for (int i=0; i<nl.getLength(); i++)
					{
			//creating individaul node for every child node ie parameter.
						Node node = nl.item(i);
						
						//System.out.println(cell.getStringCellValue());
						//System.out.println(node.getTextContent());
						
			//created one list for all the nodes with name tag.
						NodeList nodeNameList = doc.getElementsByTagName("name");
			//iterating through all the name nodes where name matches.
						for (int nodeNameListVariable = 0; nodeNameListVariable<nodeNameList.getLength(); nodeNameListVariable++)
						{
							Node nodeName = nodeNameList.item(nodeNameListVariable);
							Element nodeNameElement = (Element) nodeName;
							//System.out.println(nodeNameElement.getElementsByTagName("value").item(0));
							//System.out.println(nodeName.getTextContent());
//							if (nodeName.getTextContent().equalsIgnoreCase("name"))
//							{
//								//System.out.println(nodeName.getTextContent());
//								System.out.println("Inside the name tag");
//							}
							
								if (cell.getStringCellValue().equals(nodeName.getTextContent()))
									//node.setTextContent("Akshit");
									System.out.println("Inside the replacement string");
						}
						
							
					}
					//Node parameter2 = doc.getElementsByTagName("parameter").item(2);
				//edit for checking the final code
					 DOMSource ds2 = new DOMSource(parameter1);
					 trans.transform(ds2, sr);
				}
				
			   
			   
		    	
		    }
//		    DOMSource ds2 = new DOMSource(parameter1);
//			 trans.transform(ds2, sr);
		
		    String xml = sw.toString();
		  //  System.out.println(xml);
			
			
					
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
