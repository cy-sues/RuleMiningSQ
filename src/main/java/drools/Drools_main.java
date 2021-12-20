package drools;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.*;
import java.net.ServerSocket;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Drools_main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // 获取drools实现的 KieServices 实例
        KieServices ks = KieServices.Factory.get();
        // kieServices默认加载 classpath:META-INF/kmodule.xml 得到 KieContainer
        KieContainer kContainer = ks.getKieClasspathContainer();
        // 通过 kContainer获取 kmodule.xml 中定义的 ksession
        KieSession kSession = kContainer.newKieSession("shangqi-rules");
//        List<Long> time=new ArrayList<Long>();//记录时间
//        List<Integer> size=new ArrayList<Integer>();//记录生成规则数
//        List<Integer> newTriple=new ArrayList<Integer>();//记录生成新三元组数
        List min1=new ArrayList();
        List resultList= new ArrayList();
        min1.add("newTriple");
        min1.add("Time");
        min1.add("NewRole");
        resultList.add(min1);
        min1=new ArrayList();//初始化
        long newT;
        // 读取实例数据
        for(int i=1;i<=590;i++){

            BufferedReader dataReader = new BufferedReader(new FileReader(new File(Drools_main.class.getResource("/data/"+ i +".nt").toURI())));

            String dataLine = null;
            while((dataLine = dataReader.readLine()) != null){
                if(dataLine.isEmpty())
                    continue;
                else{
                    String[] lineArray = dataLine.split(" ");
                    // 向WorkingMemory插入三元组
                    kSession.insert(new Triple(lineArray[0], lineArray[1], lineArray[2]));
//                System.out.println(lineArray[0]+"  "+lineArray[1]+"  "+lineArray[2]);
                }
            }
            // 输出推理前的三元组及数量
//            System.out.println("Triples num before reasoning: " + kSession.getObjects().toArray().length);
//            System.out.println("Triples Before Reasoning:");
//        outpuztTriples(kSession);
            ArrayList<String> triples1=new ArrayList<String>();
            Object[] a =kSession.getObjects().toArray();
            long k=kSession.getObjects().toArray().length;
            for (Object o:a)
            {
                String r=o.toString();
                triples1.add(r);
//                System.out.println(r);
            }
            long startTime = System.currentTimeMillis();

            System.out.println("new new Execute..."+i+"******************************************************");
            // 使规则引擎执行规则
            kSession.fireAllRules();
            long endTime = System.currentTimeMillis();
            long runningTime = endTime - startTime;
            long newTriple = kSession.getObjects().toArray().length-k;

            // 输出推理后的三元组及数量
            System.out.println("Facts num after reasoning: " + newTriple);
            System.out.println("Facts After Reasoning:");
            min1.add(newTriple);//
            ArrayList<String> triples2=new ArrayList<String>();
            Object[] b =kSession.getObjects().toArray();
            for (Object o:b)
            {
                String r=o.toString();
                triples2.add(r);
            }

            // 输出推理时间
            System.out.println("Total time cost: " + runningTime + "ms");
            min1.add(runningTime);
            triples2.removeAll(triples1);
            min1.add(triples2.size());
            resultList.add(min1);
            min1=new ArrayList();
            System.out.println("The number of diagnosed problem is " + triples2.size());
            int n=0;
            for(String s:triples2)
            {
                System.out.println(++n+": "+s);
            }

        }
        //创建一个EXCEL
        Workbook wb = new HSSFWorkbook();
        //创建一个SHEET
        Sheet sheet1 = wb.createSheet("报表");
        if(resultList!=null){
            for (int i = 0; i < resultList.size(); i++) {
                //创建一行
                Row row = sheet1.createRow(i);
                List rowList=(List)resultList.get(i);
                for (int j = 0; j < rowList.size(); j++) {
                    Cell cell = row.createCell(j);
                    String t1=rowList.get(j).toString();
//                    String cellLiString=(String)rowList.get(t1);
                    cell.setCellValue(t1);
                }
            }
        }
        FileOutputStream fileOut = new FileOutputStream("Y:\\data6.xls");
        wb.write(fileOut);
        fileOut.close();
    }
        }

        /**
         * 用于输出KieSession中的所有三元组
         * @param kSession
         */
//        public static void outputTriples(KieSession kSession) {
//            Object[] array2 = kSession.getObjects().toArray();
//            for(int i = 0; i < array2.length; i++){
//                System.out.println(1+i + ": " + array2[i]);
//            }



//}
