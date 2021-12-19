package drools;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
public class JavaArraylist {

    public static void main(String[] args) {
        List workbookList=createlist();
        //System.out.println(workbookList);
        try {
            writeToXls(workbookList);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static List createlist(){
        List biaotou=new ArrayList();
        List resultList=new ArrayList();
        biaotou.add("name");
        biaotou.add("id");
        resultList.add(biaotou);
        List neirong=new ArrayList();
        neirong.add("A");
        neirong.add("AA");
        resultList.add(neirong);
        neirong=new ArrayList();//初始化一下，这样之前的值就不会加入
        neirong.add("b");
        neirong.add("bb");
        resultList.add(neirong);
        neirong=new ArrayList();
        neirong.add("C");
        neirong.add("CC");
        resultList.add(neirong);
        System.out.print(resultList);
        return resultList;
    }
    public static void writeToXls(List resultList )throws Exception{
        //创建一个EXCEL
        Workbook wb = new HSSFWorkbook();
        //创建一个SHEET
        Sheet sheet1 = wb.createSheet("报表1");
        if(resultList!=null){
            for (int i = 0; i < resultList.size(); i++) {
                //创建一行
                Row row = sheet1.createRow(i);
                List rowList=(List)resultList.get(i);
                for (int j = 0; j < rowList.size(); j++) {
                    Cell cell = row.createCell(j);
                    String cellLiString=(String)rowList.get(j);
                    cell.setCellValue(cellLiString );
                }
            }
        }
        FileOutputStream fileOut = new FileOutputStream("Y:\\testa.xls");
        wb.write(fileOut);
        fileOut.close();
    }
}