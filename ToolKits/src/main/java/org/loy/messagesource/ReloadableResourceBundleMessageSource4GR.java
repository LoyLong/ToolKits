package org.loy.messagesource;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;

public class ReloadableResourceBundleMessageSource4GR extends ReloadableResourceBundleMessageSource {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.context.support.
     * ReloadableResourceBundleMessageSource
     * #loadProperties(org.springframework.core.io.Resource, java.lang.String)
     */
    @Override
    protected Properties loadProperties(Resource resource, String filename) throws IOException {
        System.out.println("[ReloadableResourceBundleMessageSource4GR].[loadProperties].in");
        String resourceName = resource.getFilename();
        System.out.println("resourceName = " + resourceName);
        Properties props = null;
        if (("report_hierarchy.xml").equals(resourceName)) {
            System.out.println("caching report xml hierarchy starts");
            props = new Properties();
            props.put("report_hierarchy.xml", "report_hierarchy.xml");

            try {
                System.out.println("ReportLoadingService.getInstance().loadReportTreeFromXmlFile()");
            } catch (Exception e) {
                System.out.println("caching report xml hierarchy interrupts with exception:" + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("caching report xml hierarchy finishes successfully");

        } else {
            props = super.loadProperties(resource, filename);
        }
        return props;
    }

    /**
     * refresh properties.
     * 
     * @param fileName
     *            the file name
     * @return 0 no file reload need, 1 need to reload properties file
     */
    protected void refreshProperties(String fileName) {
        System.out.println("[ReloadableResourceBundleMessageSource4GR].[refreshProperties].in");
        PropertiesHolder holder = getProperties(fileName);
        super.refreshProperties(fileName, holder);
    }
}

