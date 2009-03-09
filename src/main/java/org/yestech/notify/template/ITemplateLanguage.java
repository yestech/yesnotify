/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.template;

import org.springframework.beans.factory.annotation.Required;
import org.yestech.notify.objectmodel.IMessage;

/**
 * A Template Language used to customize a {@link org.yestech.notify.objectmodel.Message}.
 */
public interface ITemplateLanguage<P extends ITemplateLanguagePersistence> {

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
     * @param persistence Template persistence to use.
     */
    @Required
    public void setPersistence(P persistence);

    /**
     * <b>ONLY</b> used for Serialization!!!!!!!
     *
     * @return The Template persistence to use.
     */
    public P getPersistence();
}
