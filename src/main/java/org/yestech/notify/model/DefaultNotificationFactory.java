package org.yestech.notify.model;

import org.yestech.notify.objectmodel.INotification;
import org.yestech.notify.objectmodel.DefaultNotification;
import org.yestech.notify.template.NullTemplateLanguage;

/**
 * Factory to create a {@link org.yestech.notify.objectmodel.INotification} with no op templating.
 *
 */
public class DefaultNotificationFactory extends BaseNotificationFactory
{

    public DefaultNotificationFactory() {
        super();
    }

    public INotification create() {
        DefaultNotification notification = new DefaultNotification();
        notification.setSender(getSender());

        notification.setMessage(createNotification());

        notification.addRecipients(getRecipients());
        notification.addCopyRecipients(getCopyRecipients());
        notification.addBlindCopyRecipients(getBlindRecipients());

        notification.setTemplate(new NullTemplateLanguage());

        return notification;
    }
}
