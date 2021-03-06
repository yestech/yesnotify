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
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yestech.notify.objectmodel.IMessage;
import org.yestech.notify.util.Sl4jLogChute;

import java.io.StringWriter;
import java.util.Map;

/**
 * A template language that using velocity templates.
 */
@XStreamAlias("velocityTemplate")
public class VelocityTemplateLanguage implements ITemplateLanguage<VelocityTemplateLanguagePersistence> {
    final private static Logger logger = LoggerFactory.getLogger(VelocityTemplateLanguage.class);
    private VelocityEngine ve;
    private VelocityContext context;
    private VelocityTemplateLanguagePersistence templateData;

    public VelocityTemplateLanguage() throws Exception {
        super();
        ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM, new Sl4jLogChute());
        ve.setProperty("resource.loader", "class");
        ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ve.init();
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

    /**
     * Applies the Customization to the Message and returns the Custom Message.
     * <br/>
     * The raw {@link IMessage} is set to key: yesnotify_notification_message
     * <br/>
     * each entry in the template data is set to the key supplied.
     *
     * @param message Message to Customize
     * @return The custom message result
     */
    public String apply(IMessage message) {
        StringWriter writer = new StringWriter();
        try {
            context.put("yesnotify_notification_message", message);
            for (Map.Entry<String, Object> entry : templateData.getData().entrySet()) {
                context.put(entry.getKey(), entry.getValue());
            }
            ve.init();
            Template t = ve.getTemplate(templateData.getFilePath());
            t.merge(context, writer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

    @Override
    public void setPersistence(VelocityTemplateLanguagePersistence data) {
        this.templateData = data;
    }

    @Override
    public VelocityTemplateLanguagePersistence getPersistence() {
        return templateData;
    }
}