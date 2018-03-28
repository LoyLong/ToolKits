package org.loy.messagesource;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class TestMessageSource extends Thread {

    ReloadableResourceBundleMessageSource reloadableMsgSrc = null;
    String[] basenames = null;

    public TestMessageSource() {
        // reloadableMsgSrc = new ReloadableResourceBundleMessageSource();
        reloadableMsgSrc = new ReloadableResourceBundleMessageSource4GR();
        // String fileName = "log4j";
        String fileName = "report_hierarchy";
        basenames = new String[2];
        String baseFolder = "file:C:\\WorkSpace\\GitRepository\\ToolKit\\ToolKit\\TestToolKit\\src\\main\\resources\\config\\";
        basenames[0] = baseFolder + "report_hierarchy";
        basenames[1] = baseFolder + "log4j";
        reloadableMsgSrc.setCacheSeconds(3);
        reloadableMsgSrc.setBasenames(basenames);
    }

    public void print() {
        try {
            while (true) {
                ((ReloadableResourceBundleMessageSource4GR) reloadableMsgSrc).refreshProperties(basenames[0]);
                System.out.println("testing = " + reloadableMsgSrc.getMessage("testing", null, null));
                Thread.sleep(2 * 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // config/log4j.properties

        new TestMessageSource().print();
    }

}
