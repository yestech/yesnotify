/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.template;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yestech.notify.objectmodel.IMessage;

import java.io.Serializable;
import java.io.StringWriter;

/**
 * A non op template.  It preforms no customization, just returns the original message
 */
@XStreamAlias("velocityTemplate")
public class VelocityTemplateLanguage implements ITemplateLanguage {
    final private static Logger logger = LoggerFactory.getLogger(VelocityTemplateLanguage.class);
    private String templateFile;
    private VelocityEngine ve;
    private VelocityContext context;

    public VelocityTemplateLanguage() {
        super();
        ve = new VelocityEngine();
        context = new VelocityContext();
    }

    public VelocityEngine getVe() {
        return ve;
    }

    public void setVe(VelocityEngine ve) {
        this.ve = ve;
    }

    public VelocityContext getContext() {
        return context;
    }

    public void setContext(VelocityContext context) {
        this.context = context;
    }

    public String getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

    /**
     * Applies the Customization to the Message and returns the Custom Message.
     *
     * @param message Message to Customize
     * @return The custom message result
     */
    public String apply(IMessage message) {
        StringWriter writer = new StringWriter();
        try {
            context.put("notification.message", message);
            ve.setProperty("resource.loader","class"); 
            ve.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"); 
//            ve.setProperty(
//                    VelocityEngine.RUNTIME_LOG_LOGSYSTEM, logger);
            ve.init();
            Template t = ve.getTemplate(templateFile);
            t.merge(context, writer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

    /**
     * <b>ONLY</b> used for Deserialization!!!!!!!
     *
     * @param data Template Data to use.
     */
    public void setTemplateData(Serializable data) {
    }

    /**
     * <b>ONLY</b> used for Serialization!!!!!!!
     *
     * @return The Template Data to use.
     */
    public Serializable getTemplateData() {
        return "";
    }
}