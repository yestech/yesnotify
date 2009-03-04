/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.template;

import org.jdom.Document;
import org.jdom.Element;
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
import java.util.Iterator;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * A {@link java.util.Map} based Template.
 *
 */
@XStreamAlias("xslMapTemplate")
public class XslMapTemplateLanguage implements ITemplateLanguage {
    final private static Logger logger = LoggerFactory.getLogger(XslMapTemplateLanguage.class);

    private XslTemplateData templateData;

    public XslMapTemplateLanguage() {
        super();
    }

    public XslMapTemplateLanguage(XslTemplateData templateData) {
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

        Map nameValues = (Map) templateData.getData();
        String xslPath = templateData.getFilePath();
        File xslFile = XslUtils.locateFileInApp(xslPath);

        Element element = new Element("email");

        for (Iterator i = nameValues.keySet().iterator(); i.hasNext();) {
            String key = (String) i.next();
            String value = (String) nameValues.get(key);
            element = element.setAttribute(key, (value == null) ? "" : value);
        }

        //add the element to a document
        Document doc = new Document(element);

        //need to convert the jdom document into something usuable
        XMLOutputter xmlOut = new XMLOutputter();
        String document = xmlOut.outputString(doc);         //output the document to a string
        Reader docReader = new StringReader(document);      //create a reader for that string
        templateMessage = XslUtils.transformMessage(new StreamSource(docReader), new StreamSource(xslFile));
        return templateMessage;
    }
}
