package org.loy.test2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b> Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class Test implements Serializable {

    public static final String NBSP = "\u00A0";
    static {
        System.out.println("static");
    }
    {
        System.out.println("block");
    }

    Test() {
        System.out.println("this");
    }

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public static void main(final String[] args) {

        // String filePath =
        // "C:\\WorkSpace\\EclipseWorkspace\\LightExecutiveService\\lesf-ns-core\\src\\main";
        String filePath = "C:\\WorkSpace\\BSD";

        Test t = new Test();
        // int totalCount = t.updatePackage(filePath, 0);

        String test = "SMSLang_E";
        System.out.println("SMSLang_E.subString = " + test.substring(test.length() - 1));

        // System.out.println("Total updated files count = " + totalCount);
        // t.updateFileName1();
        // t.test39();
        System.out.println(StringUtils.leftPad("1", 4, "0"));
    }

    public void updateFileName1() {
        String filePath = "C:\\WorkSpace\\BSD\\RAD8.5_Was\\AppiaToolkitGFIXTestClient4Future\\dist\\MockMessages\\testcases";

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File " + filePath + " doesn't exist!");
            return;
        }

        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (null == children) {
                System.out.println(filePath + " is empty folder!");
                return;
            }

            for (File subFile : children) {
                String subFilePreFix = subFile.getName().substring(0, 5);
                System.out.println("subFilePreFix = " + subFilePreFix);
                String subVersion = subFilePreFix.substring(3).trim();
                subVersion = subVersion.length() == 1 ? "0" + subVersion : subVersion;
                System.out.println("subVersion = " + subVersion);
                String subFilePreFixUpdated = subFilePreFix.substring(0, 3) + subVersion + " ";
                System.out.println("subFilePreFixUpdated = " + subFilePreFixUpdated);
                String subFilePath = subFile.getAbsolutePath();
                System.out.println("subFilePath = " + subFilePath);
                subFilePath = subFilePath.replaceAll(subFilePreFix, subFilePreFixUpdated);
                System.out.println("updated subFilePath = " + subFilePath);
                subFile.renameTo(new File(subFilePath));
            }
            return;
        }
    }

    public void updateFileName2() {
        String filePath = "C:\\WorkSpace\\BSD\\RAD8.5_Was\\AppiaToolkitGFIXTestClient4Future\\dist\\MockMessages\\testcases";
        Map<String, String> nameList = new LinkedHashMap<String, String>();
        nameList.put("OESGFixMsgNewOrderForMultiLeg", "NewMulti");
        nameList.put("OESGFixMsgNewOrder", "New");
        nameList.put("OESGFixMsgModifyOrderForMultiLeg", "ModifyMulti");
        nameList.put("OESGFixMsgModifyOrder", "Modify");
        nameList.put("OESGFixMsgCancelOrder", "Cancel");
        nameList.put("OESGFixMsgQuoteRequest", "Quote");
        nameList.put("OESGFixMsgTradSessionStatus", "TradSession");

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File " + filePath + " doesn't exist!");
            return;
        }

        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (null == children) {
                System.out.println(filePath + " is empty folder!");
                return;
            }

            for (File subFile : children) {
                String subFileName = subFile.getName();
                System.out.println("subFileName = " + subFileName);
                String subFilePath = subFile.getAbsolutePath();
                System.out.println("subFilePath = " + subFilePath);
                for (Iterator<Map.Entry<String, String>> mapItr = nameList.entrySet().iterator(); mapItr.hasNext();) {
                    Map.Entry<String, String> item = mapItr.next();
                    if (subFileName.contains(item.getKey())) {
                        subFilePath = subFilePath.replaceAll(item.getKey(), item.getValue());
                        System.out.println("updated subFilePath = " + subFilePath);
                        subFile.renameTo(new File(subFilePath));
                    }
                }

            }
            return;
        }
    }


    public int updatePackage(String filePath, int count) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File " + filePath + " doesn't exist!");
            return count;
        }

        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (null == children) {
                System.out.println(filePath + " is empty folder!");
                return count;
            }

            int subCount = count;
            for (File subFile : children) {
                subCount = updatePackage(subFile.getAbsolutePath(), subCount);
            }
            return subCount;
        } else {

            try {
                Path path = Paths.get(filePath);
                // Charset charset = StandardCharsets.UTF_8;
                // String content = new String(Files.readAllBytes(path),
                // charset);
                String content = new String(Files.readAllBytes(path));
                content = content.replaceAll("comp", "comp");
                // Files.write(path, content.getBytes(charset));
                Files.write(path, content.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return count + 1;
        }
    }

    public void testMapRemove() {
        Map<String, String> map = new HashMap<String, String>();

        map.put("A", "a");
        map.put("B", "b");
        map.put("C", "c");

        System.out.println(map.remove("A"));
    }

    public void testBeak() {
        String sLogMsg = new StringBuilder().append("sOrdSts=").append("execDetail.m_sOrdStatus").append(", sExeType=")
            .append("execDetail.m_sExecType").append(", sMsgType=").append("execDetail.m_sMsgType").append(", sExecTransType=")
            .append("execDetail.m_sExecTransType").append(", CltOrdID=").append("execDetail.m_sCltOrdID").append(", MsgSeqNum=")
            .append("execDetail.m_sMsgSeqNum").append(", ExecID=").append("execDetail.m_sExecID").append("Process complete after ")
            .append(46).append(" times retry.").toString();

        System.out.println(sLogMsg);

        if (true) {
            System.out.println("1a");
            if (true) {
                System.out.println("2a");
                if (true) {
                    System.out.println("3a");
                    // break;
                }
                System.out.println("2b");
            }
            System.out.println("1b");
        }
        System.out.println("4");
    }

    public void readLargeFile2() {

        String lastNo = null;
        try {
            File f = new File("C:\\Workspace\\Temp\\LargeFile.test");
            FileReader fr = new FileReader(f);
            FileInputStream fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-16");

            BufferedReader br = new BufferedReader(isr);

            String line;
            do {
                line = br.readLine();
                // line = readLine(fr);
                // System.out.println("line = " + line);
                if (null != line && line.length() > 0) {
                    lastNo = line;
                }
            } while (null != line);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("the lastNo = " + lastNo);
        }
    }

    private String readLine(FileReader fr) {

        StringBuffer sb = new StringBuffer();
        char[] ca = new char[1];
        int b = 0;
        try {
            while (13 != b && 10 != b) {
                b = fr.read();
                // System.out.println("b = " + b);
                sb.append(Character.toString((char) b));
                // sb.append(ca[0]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public void readLargeFile() {

        String lastNo = null;
        try {
            File f = new File("C:\\Workspace\\Temp\\LargeFile.test");
            InputStreamReader isr = new InputStreamReader(new FileInputStream(f), "unicode");
            BufferedReader br = new BufferedReader(isr);

            String line;
            do {
                line = br.readLine();
                if (null != line && line.length() > 0) {
                    lastNo = line.substring(0, 8);
                }
            } while (null != line);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("the lastNo = " + lastNo);
        }
    }

    public void test42() {
        try {
            File f = new File("C:\\Workspace\\Temp\\LargeFile2.test");
            f.createNewFile();

            // OutputStream io = new FileOutputStream(f);
            // FileWriter fw = new FileWriter(f);

            OutputStreamWriter isr = new OutputStreamWriter(new FileOutputStream(f), "unicode");
            BufferedWriter br = new BufferedWriter(isr);

            for (int i = 0; i < 5000000; i++) {
                StringBuffer sb = new StringBuffer();
                String indx = String.valueOf(i);
                while (indx.length() < 6) {
                    indx = "0" + indx;
                }
                sb.append(indx);
                sb.append("Content:");
                for (int j = 0; j < 10; j++) {
                    sb.append(indx);
                }
                sb.append("\n");
                br.write(sb.toString());
            }
            br.flush();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test41() {
        String test = "GBPÂ Â PRU[br/]31814743Â Â (Joint)[br/] Abcfd GLBTOOLS,  SITGtasfvs SITGtasfvsdafhh,  SITGTasv SITGTasvbsbnd,  SITGTasvv SITGTasvvwer &  SITGTjs SITGTjsdjgb[br/]  ";

        test = test.trim();
        System.out.println("test = " + test.substring(test.length() - 5));
        if ("[br/]".equals(test.substring(test.length() - 5))) {
            test = test.substring(0, test.length() - 5);
        }

        System.out.println("test = " + test);
    }

    public void test40() {
        String format = "###-######-###";

        String acctNumber = "633210086";
        String formattedAccountNumber = acctNumber;

        if (StringUtils.isNotBlank(format)) {
            try {
                char[] cArr = new char[format.length()];
                int index = 0;
                boolean hasError = false;
                if (format.length() >= 1) {
                    for (int i = 0; i < format.length(); i++) {

                        if (index <= (acctNumber.length() - 1)) {

                            if ('#' == format.charAt(i)) {
                                cArr[i] = acctNumber.charAt(index);
                                index++;
                            } else {

                                if ('?' == format.charAt(i)) {
                                    cArr[i] = ' ';
                                    index++;
                                } else {
                                    cArr[i] = format.charAt(i);
                                }
                            }

                        } else {
                            hasError = true;
                            break;
                        }
                    }
                    if (!hasError) {
                        formattedAccountNumber = String.valueOf(cArr).trim();
                    } else {
                        formattedAccountNumber = acctNumber;
                    }
                } else {
                    formattedAccountNumber = acctNumber;
                }

            } catch (Exception e) {
            }
        }

        System.out.println("format = " + format);
        System.out.println("acctNumber = " + acctNumber);
        System.out.println("formattedAccountNumber = " + formattedAccountNumber);
    }

    public void test39() {
        List<String> custOwnerNameList = new ArrayList<String>();

        Locale defaultLocale = Locale.getDefault();
        Locale cnLocale = new Locale("zh", "CN");
        Locale twLocale = new Locale("zh", "TW");
        Locale hkLocale = new Locale("zh", "HK");
        Locale testLocale = Locale.CHINA;


        custOwnerNameList.add("æ�Žä¿Š");
        custOwnerNameList.add("æ�Žå˜‰");
        custOwnerNameList.add("å‘¨æ�°");
        /*
         * String[] test = new String[]{"æ�Žä¿Š","æ�Žå˜‰","å‘¨æ�°"};
         * 
         * Arrays.sort(test); System.out.println(Arrays.toString(test));
         */

        Collections.sort(custOwnerNameList);
        System.out.println("Sort by en US");
        System.out.println(custOwnerNameList);

        System.out.println("Sort by zh CN");
        Collator collator = Collator.getInstance(cnLocale);
        Collections.sort(custOwnerNameList, collator);
        System.out.println(custOwnerNameList);

        System.out.println("Sort by zh TW");
        collator = Collator.getInstance(twLocale);
        Collections.sort(custOwnerNameList, collator);
        System.out.println(custOwnerNameList);

        System.out.println("Sort by zh HK");
        collator = Collator.getInstance(hkLocale);
        Collections.sort(custOwnerNameList, collator);
        System.out.println(custOwnerNameList);
    }

    public void test38() {
        int i = 7 % 3;
        System.out.println(mod(7, 3));
    }

    private int mod(int x, int y) {
        int result = x % y;
        if (result > 0) {
            result += y;
        }
        return result;
    }

    public void test37() {
        List<int[]> comboCell = new ArrayList<int[]>();

        comboCell.add(new int[] {2, 3});
        comboCell.add(new int[] {3, 2});
        comboCell.add(new int[] {4, 5});

        System.out.println(comboCell.contains(new int[] {2, 3}));
        System.out.println(comboCell.contains(new int[] {5, 3}));
    }

    public void test36() {

        String catnameHasBlankStr = "true";
        String catnameWrapIndStr = "5";
        System.out.println("catnameHasBlankStr = " + catnameHasBlankStr);
        System.out.println("catnameWrapIndStr = " + catnameWrapIndStr);

        String wrapCategoryName = "12345 23456 123 56   12345678 01234567890123";
        System.out.println("wrapCategoryName = " + wrapCategoryName);

        if (StringUtils.isBlank(wrapCategoryName)) {
            System.out.println("StringUtils.isBlank(wrapCategoryName)");
            return;
        }

        if (StringUtils.isNotBlank(catnameWrapIndStr)) {
            int catnameWrapInd = Integer.parseInt(catnameWrapIndStr);
            if (wrapCategoryName.length() < catnameWrapInd) {
                System.out.println("wrapCategoryName.length() < catnameWrapInd");
                return;
            }

            boolean catnameHasBlank = Boolean.parseBoolean(catnameHasBlankStr);

            System.out.println("catnameHasBlank = " + catnameHasBlank);
            StringBuilder strBld = new StringBuilder();

            String rawSplitStr = wrapCategoryName;
            System.out.println("rawSplitStr = " + rawSplitStr);
            while (rawSplitStr.length() > catnameWrapInd) {
                String[] splitStrRslt = insetBreakInString(rawSplitStr, catnameWrapInd, catnameHasBlank);
                strBld.append(splitStrRslt[0]).append("\r\n");
                rawSplitStr = StringUtils.trim(splitStrRslt[1]);
            }
            strBld.append(rawSplitStr);

            wrapCategoryName = strBld.toString();
        }

        System.out.println("wrapCategoryName = " + wrapCategoryName);
    }

    private String[] insetBreakInString(final String rawStr, final int insertPosition, final boolean containBlank) {

        System.out.println("insetBreakInString.in");
        System.out.println("rawStr = " + rawStr);

        boolean catnameHasBlank = StringUtils.contains(rawStr, " ");
        System.out.println("catnameHasBlank = " + catnameHasBlank);
        if (!containBlank || !catnameHasBlank) {
            return new String[] {rawStr.substring(0, insertPosition), rawStr.substring(insertPosition)};
        }

        int blankCharInd = insertPosition;
        String blank = null;
        blank = rawStr.substring(insertPosition, insertPosition + 1);

        if (StringUtils.isBlank(blank)) {
            return new String[] {rawStr.substring(0, insertPosition), rawStr.substring(insertPosition)};
        }

        blank = null;

        do {
            blankCharInd--;
            blank = rawStr.substring(blankCharInd, blankCharInd + 1);
            System.out.println("blankCharInd = " + blankCharInd);
            System.out.println("blank = " + blank);
        } while (StringUtils.isNotBlank(blank) && blankCharInd > 0);

        if (blankCharInd < 1) {
            blankCharInd = insertPosition;
        } else {
            blankCharInd++;
        }

        return new String[] {rawStr.substring(0, blankCharInd), rawStr.substring(blankCharInd)};
    }

    public void test35() {

        int i = 0;

        Boolean.parseBoolean("true");
        System.out.println(Boolean.parseBoolean("true"));
        System.out.println(i++);
        System.out.println(++i);
        System.out.println(i + 1);
    }

    public void test34() {
        int i = 0xFFFFFFF1;
        int j = ~i;
        System.out.println(i);
        System.out.println(j);
        i = 0x00000000;
        System.out.println(i);
        i = 0xFFFFFFFF;
        System.out.println(i);
        i = 0x10000000;
        System.out.println(i);
        i = 0x80000000;
        System.out.println(i);

        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-2147483648));
        System.out.println(Integer.toBinaryString(-0));
        System.out.println(Integer.toBinaryString(0));
        System.out.println(Integer.toBinaryString(2147483647));

        System.out.println(Integer.toBinaryString(-1 << 31));
        System.out.println(Integer.toBinaryString(-1 << 32));
    }

    public void test33() {
        double amount = 200.0;
        Locale locale = new Locale("fr", "FR");
        Currency currency = Currency.getInstance(locale);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        currencyFormatter.setCurrency(currency);
        System.out.println(currency.getCurrencyCode());
        System.out.println(currencyFormatter.format(amount));
    }

    public void test32() {
        String a = "[Strong]The page cannot be loaded at this time due to a system error.  Apologies for any inconvenience, please try again later. [/Strong]";
        a = a.replaceAll("\\[Strong\\]", "");
        a = a.replaceAll("\\[/Strong\\]", "");
        System.out.println(a);
    }

    public void test31() {
        String a = "1234135346757686797808912345678901";
        System.out.println(a.substring(a.length() - 11));
    }

    public void test30() {

        String format = "####-####-####-####";
        String formattedAccountNumber = "223456789012456";
        String acctNumber = formattedAccountNumber;

        if (true) {
            System.out.println("format = " + format);
        }

        if (StringUtils.isNotBlank(format)) {
            try {
                char[] cArr = new char[format.length()];
                int index = 0;
                boolean hasError = false;
                if (format.length() >= 1) {
                    for (int i = 0; i < format.length(); i++) {

                        if (index <= (acctNumber.length() - 1)) {

                            if ('#' == format.charAt(i)) {
                                cArr[i] = acctNumber.charAt(index);
                                index++;
                            } else {

                                if ('?' == format.charAt(i)) {
                                    cArr[i] = ' ';
                                    index++;
                                } else {
                                    cArr[i] = format.charAt(i);
                                }
                            }

                        } else {
                            hasError = true;
                            break;
                        }
                    }
                    if (!hasError) {
                        formattedAccountNumber = String.valueOf(cArr).trim();
                    } else {
                        formattedAccountNumber = acctNumber;
                    }
                } else {
                    formattedAccountNumber = acctNumber;
                }

            } catch (Exception e) {
                System.out.println("Account formatting has exception.");
                System.out.println("The account number to be formatted is [" + acctNumber + "]");
                System.out.println("The account format is [" + format + "]");
            }
        }

        System.out.println("formattedAccountNumber = " + formattedAccountNumber);
    }

    public void test29() {

        char a = '7';
        Integer b = new Integer(55);
        Long l = new Long(55);
        Double c = new Double(55);

        switch (a) {
        case 55:
            System.out.println("a = 55");
            break;
        default:
            System.out.println("a != 55");
            break;
        }

    }

    public void test28() {
        char a = 55;
        System.out.println(a);
    }

    public void test27() {
        String a = "028005197781";
        System.out.println(a.substring(5));
        System.out.println(a.substring(6));
    }

    public void test26() {

        Double d = new Double(2) + 3;
        Float f = 2 + new Float(3);
        Integer i = new Integer(2) + 3;

        Double d2 = new Integer(2) + new Double(3);
        Double d3 = new Integer(2) + new Double(3);

    }

    public class test2 {
        public void test() {};
    }
    class test3 {
        public final int num;
        {
            num = 3;
        }

        public void test(test3... arg) {}

        @Override
        public void finalize() throws Throwable {
            System.out.println("test3.finalize.in");
            throw new RuntimeException();
        }
    }

    public static void call() throws IOException {
        File file = new File("test.dat");
        file.createNewFile();
        throw new IllegalArgumentException();
    }

    public interface testI {
        int x = 0;
    }

    static final int[] a;
    static {
        a = new int[2];
        a[0] = 100;
        a[1] = 200;
    }


    public void test25() {
        float f = 1;
        // long l =42e1;
        f = 0x123;
        System.out.println("f = " + f);
        int index = 1;
        int foo[] = new int[3];
        int bar = foo[index];
        int baz = bar + index;
        System.out.println("baz = " + baz);
    }

    public void test24() throws Exception {

        try {
            // System.exit(1);
            throw new Exception();
        } finally {
            System.out.println("finally");
        }

    }

    public void test23() {
        String temp = "000123,456,789.00000";

        System.out.println(temp);

        temp = temp.replaceAll(",", "");
        System.out.println((new BigDecimal(temp)).stripTrailingZeros().toString());
        System.out.println((new BigDecimal(temp)).toPlainString());

    }


    public void test22() {
        Integer i = null;
        BigDecimal b = null;
        Float f = null;
        Double d = null;

        double m = f + i + d + 12;

    }

    public void test21() {
        String a = "A", b = "B", c = a + b, d = a + b;
        System.out.print(((a + b) == (a + b)) + ",");
        System.out.print((c == d) + ",");
        System.out.print(c.equals(d) + ",");
    }

    public void test20() {
        float fl[], f2[];
        fl = new float[10];
        f2 = fl;
        System.out.println("f2[0]= " + f2[0]);
    }

    public void test19() {
        int i = 0xFFFFFFF1;
        int j = ~i;
        System.out.println("i=" + i);
        System.out.println("j=" + j);
    }

    public void test18() {
        Test t = new Test();

        // t.test17();

        t.new test3();

        t = null;
        System.gc();
    }

    public void test17() {
        int a = 0;
        int b[] = new int[5];
        int c = 3;
        int d = 5;
        b[a] = a = c = d;
        for (int i = 0; i < 5; i++)
            System.out.println(b[i]);
    }

    public void test16() {
        String folderPath = "D:\\tools\\emotions1\\png";

        File emtFold = new File(folderPath);

        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        System.out.println("<palette name=\"kiro\">");
        for (File emtFile : emtFold.listFiles()) {
            String fileName = emtFile.getName();
            int subFixInd = fileName.indexOf(".");
            String pureName = fileName.substring(0, subFixInd);

            System.out.println("<item><name><![CDATA[");
            System.out.println(pureName);
            System.out.println("]]></name><imgfile>");
            System.out.println(fileName);
            System.out.println("</imgfile><keyboard><![CDATA[");
            System.out.println(pureName);
            System.out.println("]]></keyboard><kbrdregexp><![CDATA[");
            System.out.println(pureName);
            System.out.println("]]></kbrdregexp></item>");
        }
        System.out.println("</palette>  ");

    }

    public void test15() {

        String a = "123456[br/]789";
        int indx = a.indexOf("[br/]") + "[br/]".length();
        a = a.substring(0, indx) + "test" + a.substring(indx);
        System.out.println(a);

        StringBuffer resultSb = new StringBuffer();
        resultSb.append(a);
        resultSb.insert(indx, "test");
        System.out.println(resultSb);
    }

    public void test14() {

        int i = 10;
        Object t[] = new Object[i];
        t[0] = new Integer(12);

        Object[] a = new Object[] {new Integer(12), new Boolean(true)};

        Arrays.sort(a);
    }

    public void test13() {
        String str = "D01,D02,D03,TD1,D31,D32,D33,D34,D35,D41,D42,D43,D44,D45,D51,D52,D53,D54,D55,D61,D62,D63,D64,D65,D09,D05,D06,D07,D08,D10,TD2,TD3,D04,D71,D72,D73,D74,D75,CA1,CA3,CA4,CA5,CA6,CA7,CSA,CSV,CUD,HEI,HEQ,MSV,PCI,PCN,SSI,SSM,SSP,SSV,SV1,SV2,SV3,SV4,SVO,NSV,SVR,CUE,CUO,MMD,CD,CTD,FTD,FTI,MIA,RNR,RST,STD,STI,TDI,TEF,TMA,TMD,TMP,TRO,TWK,CN2,CUA,CUR,RCA,D99,D01,D04,D09,D11,D14,D15,D16,D17,D18,D19,D20,D21,D22,D24,D25,D26,D27,D28,D29,D30,D31,D32,D33,D34,D35,D36,D37,D38,D91,D92,D93,D94,D95,D96,D97,D98,CHP,CUA,PGD,PRA,PCI,PC2,PC3,PC4,PSV,SSV,SCA,CUI,ALA,CH2,CN3,CA1,CSV,MSV,CDP,PDP,CVP,SV1,CA3,CD,OND,STD,TDI,TMD,TD1,TD2,TD3,TD4";

        List<String> strList = new ArrayList<String>();
        for (String temp : str.split(",")) {
            if (!strList.contains(temp)) {
                strList.add(temp);
            }
        }

        String result = "";
        for (String temp : strList) {
            result = result + "," + temp;
        }
        System.out.println(result.substring(1));
    }

    public void test12() {
        String tmp = Test.NBSP + Test.NBSP + "Securities     Account[br/]041-609587-090";
        System.out.println("|" + tmp + "|");

        while (tmp.contains(Test.NBSP + Test.NBSP)) {
            tmp = tmp.replace(Test.NBSP + Test.NBSP, Test.NBSP);
        }

        System.out.println("|" + tmp + "|");
        System.out.println("|" + tmp.trim() + "|");
        System.out.println("|" + StringUtils.trim(tmp) + "|");

        System.out.println(tmp.substring(0, 1));
        System.out.println(" " == tmp.substring(0, 1));

        while (Test.NBSP.equals(tmp.charAt(0))) {
            tmp = tmp.substring(1);
        }
        System.out.println("|" + tmp + "|");

    }

}
