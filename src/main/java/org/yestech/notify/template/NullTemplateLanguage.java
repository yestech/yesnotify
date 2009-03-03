package org.yestech.notify.template;

import org.yestech.notify.objectmodel.IMessage;

import java.io.Serializable;

/**
 * A non op template.  It preforms no customization, just returns the original message
 *
 */
public class NullTemplateLanguage implements TemplateLanguage {
    public NullTemplateLanguage() {
        super();
    }

    /**
     * Applies the Customization to the Message and returns the Custom Message.
     *
     * @param message Message to Customize
     * @return The custom message result
     */
    public String apply(IMessage message) {
        return message.getText();
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
