import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadInExcel 
{
	public static void main(String[] args) 
	{
		try
		{
			FileInputStream file = new FileInputStream(new File("C:\\Users\\guptaksh\\Desktop\\Coding Practice\\XML-JAVA\\Book1.xlsx"));
			XSSFWorkbook xw = new XSSFWorkbook(file);
			XSSFSheet xs = xw.getSheetAt(0);
			Iterator<Row> rowIterator = xs.iterator();
			while (rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator =row.iterator();
				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					switch (cell.getCellType())
					{
						case Cell.CELL_TYPE_STRING:
							System.out.println(cell.getStringCellValue()+ "\n");
							break;
						case Cell.CELL_TYPE_NUMERIC:
							System.out.println(cell.getNumericCellValue()+"\n");
							break;
					}
				}
				//System.out.println("");
			}
//			int rowNumber= 0;
//			while (rowNumber<3)
//			XSSFRow row = xs.getRow(rowNumber);
//			
//			    
//
//			    for(int columnNumber = 0; columnNumber < row.getLastCellNum(); columnNumber++) {
//			        XSSFCell cell = row.getCell(columnNumber);
//			        if(cell != null) {
//			            // do something with the cell
//			        	System.out.println(cell.getStringCellValue());
//			        
//			}
//			
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
