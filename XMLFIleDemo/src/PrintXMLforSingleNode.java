import java.io.File;
import java.io.StringWriter;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class PrintXMLforSingleNode 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try
		{
			String filepath = "C:\\Users\\guptaksh\\Desktop\\Coding Practice\\XML-JAVA\\c7b57990-78b5-4b00-a107-eeddca279613_params.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			
		//printing the node value 
		//reference https://dzone.com/articles/java-dom-printing-content-node
			TransformerFactory tranFactory = TransformerFactory.newInstance();
			Transformer trans = tranFactory.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		    trans.setOutputProperty(OutputKeys.INDENT, "yes");
		    
		    StringWriter sw = new StringWriter();
		 //for file editing
		    //StreamResult sr = new StreamResult(new File(filepath));
		 //for printing the result on console.
		    StreamResult sr = new StreamResult(sw);
		   //DOMSource ds = new DOMSource(parameter1);
		   // trans.transform(ds, sr);
//			    String xmlString = sw.toString();
//			    System.out.println(xmlString);
			
		    System.out.println("_________________________________");
			
			NodeList tagParameterList = doc.getElementsByTagName("parameter");
			
			for (int j=1;j<tagParameterList.getLength(); j++)
			{
				Node parameter1 = tagParameterList.item(j);
				NodeList nl = parameter1.getChildNodes();
				
				for (int i=0; i<nl.getLength(); i++)
				{
					Node node = nl.item(i);
					if ("value".equals(node.getNodeName()))
						node.setTextContent("Akshit");
				}
				//Node parameter2 = doc.getElementsByTagName("parameter").item(2);
				 DOMSource ds2 = new DOMSource(parameter1);
				 trans.transform(ds2, sr);
			}
		
		   
		    String xml = sw.toString();
		    System.out.println(xml);
	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
