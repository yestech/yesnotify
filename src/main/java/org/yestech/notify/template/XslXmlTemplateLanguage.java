/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.template;

import org.jdom.Document;
import org.jdom.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yestech.notify.objectmodel.IMessage;
import org.yestech.lib.xml.XslUtils;

import javax.xml.transform.stream.StreamSource;

import java.io.File;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;

/**
 * An XSL based Template.
 *
 */
public class XslXmlTemplateLanguage implements ITemplateLanguage {
    final private static Logger logger = LoggerFactory.getLogger(XslXmlTemplateLanguage.class);

    private XslTemplateData templateData;

    public XslXmlTemplateLanguage() {
        super();
    }

    public XslXmlTemplateLanguage(XslTemplateData templateData) {
        super();
        this.templateData = templateData;
    }

    /**
     * <b>ONLY</b> used for Deserialization!!!!!!!
     *
     * @param data Template Data to use.
     */
    public void setTemplateData(Serializable data) {
        templateData = (XslTemplateData) data;
    }

    /**
     * <b>ONLY</b> used for Serialization!!!!!!!
     *
     * @return The Template Data to use.
     */
    public Serializable getTemplateData() {
        return templateData;
    }

    /**
     * Applies the Customization to the Message and returns the Custom Message.
     *
     * @param message Message to Customize
     * @return The custom message result
     */
    public String apply(IMessage message) {
        String templateMessage = message.getText();

        Document doc = (Document) templateData.getData();
        String xslPath = templateData.getFilePath();
        File xslFile = XslUtils.locateFileInApp(xslPath);

        XMLOutputter xmlOut = new XMLOutputter();
        String document = xmlOut.outputString(doc);         //output the document to a string

        if (logger.isDebugEnabled()) {
            logger.debug("email xml content-->" + document);
        }
        Reader docReader = new StringReader(document);      //create a reader for that string
        //need to convert the jdom document into something usuable
        templateMessage = XslUtils.transformMessage(new StreamSource(docReader), new StreamSource(xslFile));
        return templateMessage;
    }

}
