package services;

import com.opencsv.CSVWriter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

/**
 * Created by martian on 2017/04/17.
 */

/* Converts CSV file data into JAVA lists/objects */
public class FileConverter {


    public FileConverter() {
    }

    public void areaFileConverter(){}

    public static void xls(String inputFile, String outputFile){

        // For storing data into CSV files
        StringBuffer data = new StringBuffer();

        try{
//            FileOutputStream fos = new FileOutputStream(outputFile);

            FileInputStream input_document = new FileInputStream(new File(inputFile));

            // Get the workbook object for XLS file
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(inputFile));

            // Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
//            Cell cell;
//            Row row;

            // Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            FileWriter my_csv = new FileWriter(outputFile);
            CSVWriter my_csv_output = new CSVWriter(my_csv);

            //Loop through rows.
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();
                int i=0;//String array
                //change this depending on the length of your sheet
                String[] csvdata = new String[2];
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()) {
                    Cell cell = cellIterator.next(); //Fetch CELL
                    switch(cell.getCellType()) { //Identify CELL type
                        //you need to add more code here based on
                        //your requirement / transformations
                        case Cell.CELL_TYPE_STRING:
                            csvdata[i]= cell.getStringCellValue();
                            break;
                    }
                    i=i+1;
                }
                my_csv_output.writeNext(csvdata);
            }

            my_csv_output.close(); //close the CSV file
            //we created our file..!!
            input_document.close(); //close xls

//            while (rowIterator.hasNext())
//            {
//                row = rowIterator.next();
//                // For each row, iterate through each columns
//                Iterator<Cell> cellIterator = row.cellIterator();
//                while (cellIterator.hasNext())
//                {
//                    cell = cellIterator.next();
//
//                    switch (cell.getCellType())
//                    {
//                        case Cell.CELL_TYPE_BOOLEAN:
//                            data.append(cell.getBooleanCellValue() + ",");
//                            break;
//
//                        case Cell.CELL_TYPE_NUMERIC:
//                            data.append(cell.getNumericCellValue() + ",");
//                            break;
//
//                        case Cell.CELL_TYPE_STRING:
//                            data.append(cell.getStringCellValue() + ",");
//                            break;
//
//                        case Cell.CELL_TYPE_BLANK:
//                            data.append("" + ",");
//                            break;
//
//                        default:
//                            data.append(cell + ",");
//                    }
//
//                    data.append('\n');
//                }
////                data.append('\n');
//            }
//
//            fos.write(data.toString().getBytes());
//            fos.close();


        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args){

        String path = System.getProperty("user.home") + "/Desktop/Areas.xlsx";
        String newPath = System.getProperty("user.home") + "/Desktop/Areas.csv";

//        File iFile = new File(path);
//        File oFile = new File(newPath);

        xls(path, newPath);
    }

}
