import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Objects;

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

public class GetAllChildNodes 
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
		    Row row1 = sheet.getRow(1);
		    int it2=0;
		   // System.out.println (row.getLastCellNum());
		    NodeList tagParameterList = doc.getElementsByTagName("parameter");
		    System.out.println(doc.getElementsByTagName("value").item(1).getTextContent());
			NodeList tagParameterList1 = doc.getElementsByTagName("value");
			System.out.println(tagParameterList1.getLength());
			System.out.println(tagParameterList.getLength());
			
			for (int i=0; i<tagParameterList1.getLength(); i++)
			{
				Node node = tagParameterList.item(i);
				System.out.println(node.getNodeName());
				System.out.println(doc.getElementsByTagName("value").item(i).getTextContent());
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}