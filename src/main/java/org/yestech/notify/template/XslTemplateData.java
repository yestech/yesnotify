package org.yestech.notify.template;

import java.io.Serializable;

/**
 * A wrapper for the TemplateData needed for XSL processing
 *
 */
final public class XslTemplateData implements Serializable {

    private Object data;
    private String filePath;

    private XslTemplateData() {
        super();
    }

    public Object getData() {
        return data;
    }

    public String getFilePath() {
        return filePath;
    }

    /**
     * Create the Template Data
     *
     * @param data Data to use in template
     * @param filePath Path to the xsl file
     * @return
     */
    public static XslTemplateData create(Object data, String filePath) {
        if (data == null) {
            throw new NullPointerException("Data can't be null");
        }
        if (filePath == null) {
            throw new NullPointerException("File Path can't be null or empty");
        }
        XslTemplateData templateData = new XslTemplateData();
        templateData.data = data;
        templateData.filePath = filePath;
        return templateData;
    }
}
