/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.factory;

import org.yestech.notify.objectmodel.INotification;
import org.yestech.notify.objectmodel.DefaultNotification;
import org.yestech.notify.template.ITemplateLanguage;

/**
 * Factory to create a {@link org.yestech.notify.objectmodel.INotification} with no op templating.
 *
 */
public class DefaultMessageFactory extends BaseMessageFactory
{

    public DefaultMessageFactory() {
        super();
    }

    public INotification create() {
        DefaultNotification notification = new DefaultNotification();
        notification.setSender(getSender());

        notification.setMessage(createMessage());

        notification.addRecipients(getRecipients());
        notification.addCopyRecipients(getCopyRecipients());
        notification.addBlindCopyRecipients(getBlindRecipients());

        ITemplateLanguage template = getTemplateLanguage();
        template.getPersistence().setData(getTemplateData());
        notification.setTemplate(template);

        return notification;
    }
}
