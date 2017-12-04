package homework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class DateUtilTest {
    Logger logger = Logger.getLogger("yll");
    DateUtil util = new DateUtil();
    //用来存放从Excel中读取到的每一行数据
    List<Row> rows = new ArrayList<>();
    List<Row> rows2 = new ArrayList<>();
    //用来存放处理之后的结果数据,包括输入和预期输出和实际输出
    List<TestResult> testResults = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        File file = new File("testcase/test-zyl.xlsx");
        ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(file);
        for (ArrayList<Object> arr : result) {
            for (Object obj : arr) {
                System.out.println(obj.toString());
            }
        }
        int height = result.size();

        for (int i = 0; i < height; ++i) {
            String in = result.get(i).get(0).toString().trim();
            String n = result.get(i).get(1).toString().trim();
            String out = result.get(i).get(2).toString().trim();

            try {
                int ner = 0;
                MyDate iner = util.strToMyDate(in);

                MyDate outer = util.strToMyDate(out);

                String pattern = "^(-?\\d+)(\\.\\d+)?$";
                if (Pattern.compile(pattern).matcher(n).find()) {
                    ner = (int) Double.parseDouble(n);
                } else {
                    iner = null;
                    outer = null;
                }

                Row row1 = new Row(iner, ner, outer);
                rows.add(row1);
                rows2.add(row1);

            } catch (NumberFormatException e) {
                e.printStackTrace();
                logger.info("where wrong:============> " + " in is " + in + " n is " + n + " out is " + out);
            }

        }
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(" *********** result info ****************");
        int countOfPass = 0;
        int countOfFailed = 0;
        int countOfTestCase = 0;
        List<TestResult> resultOfFailed = new ArrayList<>();
        for (TestResult result : testResults) {
            countOfTestCase ++;
            if (result.dateExpected==null && result.dateResult==null ) {
                countOfPass ++;
            }
            else if (result.dateExpected!=null&&(result.dateExpected.compareTo(result.dateResult) == 0))  {
                countOfPass++;
            }
            else {
                countOfFailed ++;
                resultOfFailed.add(result);
            }

        }
        System.out.println("测试用例通过率:" + countOfPass + "/" + countOfTestCase);
        System.out.println("失败的用例如下所示:");
        for (TestResult result : resultOfFailed) {
            System.out.println(result.toString());
        }
    }
    @Test
    public void nextNdays() throws Exception {

        for (Row row : rows) {
            MyDate thisDay = row.getInputDate();

            int n = row.getN();
            MyDate thatDay = row.getOutputDate();
            TestResult result = new TestResult();
            result.setDateInput(thisDay);
            result.setDateResult(util.nextNdays(thisDay, n));
            result.setDateExpected(thatDay);
            result.setN(n);
            testResults.add(result);
        }
        System.out.println();

    }

    class TestResult {
        private MyDate dateInput;
        private MyDate dateResult;
        private MyDate dateExpected;
        private int n;

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        //构造方法
        public TestResult(MyDate dateInput, MyDate dateResult, MyDate dateExpected) {
            this.dateInput = dateInput;
            this.dateResult = dateResult;
            this.dateExpected = dateExpected;
        }

        public MyDate getDateInput() {
            return dateInput;
        }

        public void setDateInput(MyDate dateInput) {
            this.dateInput = dateInput;
        }

        public TestResult() {

        }

        public MyDate getDateResult() {
            return dateResult;
        }

        public void setDateResult(MyDate dateResult) {
            this.dateResult = dateResult;
        }

        public MyDate getDateExpected() {
            return dateExpected;
        }

        public void setDateExpected(MyDate dateExpected) {
            this.dateExpected = dateExpected;
        }

        @Override
        public String toString() {
            Row row =new Row();
            return "dateInput is " + dateInput +
                    "\n dateResult is " + dateResult +
                    "\n dateExpected is " + dateExpected +
                    "\n the arg n is " + n;

        }
    }

}