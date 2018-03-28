package org.loy.general;

import java.util.Arrays;
import java.util.List;


public class UnitTest {

    public static void main(String[] args) {
        System.out.println("05".contains("05"));
        testSplitString("05");
        testSplitString("02,03");

        unScramblePwd("Wardley1");
    }


    static public String scramblePwd(String sInPasswd) {
        // System.out.println("The input passwd:"+inPasswd);
        StringBuffer sbPwd = new StringBuffer();
        char cP;

        for (int i = 0; i < sInPasswd.length(); i++) {
            cP = (char) (sInPasswd.charAt(i) + 1);
            if (cP == ':') {
                cP = '0';
            } else if (cP == '{') {
                cP = 'a';
            } else if (cP == '[') {
                cP = 'A';
            }
            sbPwd.append(cP);
        }
        System.out.println("dealingModeList = " + sbPwd.toString().trim());
        return sbPwd.toString().trim();
    }

    static public String unScramblePwd(String sRawPasswd) {
        StringBuffer sbPwd = new StringBuffer();
        char cP;

        for (int i = 0; i < sRawPasswd.length(); i++) {
            cP = (char) (sRawPasswd.charAt(i) - 1);
            if (cP == '/') {
                cP = '9';
            } else if (cP == '`') {
                cP = 'z';
            } else if (cP == '@') {
                cP = 'Z';
            }
            sbPwd.append(cP);
        }
        System.out.println("dealingModeList = " + sbPwd.toString().trim());
        return sbPwd.toString().trim();
    }

    public static void testSplitString(String m_sDealingMode) {
        List<String> dealingModeList = Arrays.asList(m_sDealingMode.split(","));
        System.out.println("dealingModeList = " + dealingModeList);
    }

    public static void testParseMsgObj2Str() {

        GeneralMessage msgObj = new GeneralMessage();

        msgObj.setMessageId(Constants.MESSAGE_ID_GRINFO);

        Segment seg = new Segment();
        seg.setFieldKey("CUSTOMER_NUMBER");
        seg.setFieldType("STRING");
        seg.setFieldValue("100001");
        msgObj.addSegment(seg);

        seg = new Segment();
        seg.setFieldKey("ACCOUNT_NUMBER");
        seg.setFieldType("STRING");
        seg.setFieldValue("1420616299");
        msgObj.addSegment(seg);

        seg = new Segment();
        seg.setFieldKey("COUNTRY_CODE");
        seg.setFieldType("STRING");
        seg.setFieldValue("HK");
        msgObj.addSegment(seg);

        seg = new Segment();
        seg.setFieldKey("GROUP_MEMBER");
        seg.setFieldType("STRING");
        seg.setFieldValue("HCBS");
        msgObj.addSegment(seg);

        System.out.println(MessageParserUtil.parseMsgObj2Str(msgObj));
    }

    public static void parseMsgStr2Obj() {
        try {
            String msgStr = "210GRINFO                     0004CUSTOMER_NUMBER               STRING    0006100001ACCOUNT_NUMBER                STRING    0016HKxxxx1420616299COUNTRY_CODE                  STRING    0002HKGROUP_MEMBER                  STRING    0004HCBS";
            System.out.println(MessageParserUtil.parseMsgStr2Obj(msgStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
