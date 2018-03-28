package org.loy.xsl.holiday;

import java.util.GregorianCalendar;

public class TestCalendar {

    public static void main(String[] args) {

        int daysInMonth = new GregorianCalendar(2017, 0, 32).getActualMaximum(5);

        System.out.println(daysInMonth);
    }

}
