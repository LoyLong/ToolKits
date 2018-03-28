package org.loy.test2;

public class ScrumblePWD {

    public static void main(String[] args) {

        System.out.println(unScramblePwd("Asdf@1234"));

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
        return sbPwd.toString().trim();
    }

}
