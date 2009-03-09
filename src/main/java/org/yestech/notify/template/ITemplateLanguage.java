/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.template;

import org.yestech.notify.objectmodel.IMessage;
import org.springframework.beans.factory.annotation.Required;

import java.io.Serializable;
import java.io.Externalizable;

/**
 * A Template Language used to customize a {@link org.yestech.notify.objectmodel.Message}.
 *
 */
public interface ITemplateLanguage<D extends Serializable> {

    /**
     * Applies the Customization to the Message and returns the Custom Message.
     *
     * @param message Message to Customize
     * @return The custom message result
     */
    public String apply(IMessage message);

    /**
     * <b>ONLY</b> used for Deserialization!!!!!!!
     *
     * @param data Template Data to use.
     */
    @Required
    public void setTemplateData(D data);

    /**
     * <b>ONLY</b> used for Serialization!!!!!!!
     *
     * @return The Template Data to use.
     */
    public D getTemplateData();
}
