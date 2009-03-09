/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

/*
 *
 * Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.client;

import org.yestech.notify.constant.MessageTypeEnum;
import org.yestech.notify.objectmodel.ISender;
import org.yestech.notify.objectmodel.IRecipient;
import org.yestech.notify.objectmodel.INotification;

import java.util.Collection;
import java.io.Serializable;

/**
 * @author Artie Copeland
 * @version $Revision: $
 */
public interface INotificationBuilder {
    INotificationBuilder setSubject(String subject);

    INotificationBuilder addAttachment(String attachment);

    INotificationBuilder addAttachments(Collection<String> attachments);

    INotificationBuilder clearAttachments();

    INotificationBuilder setMessageType(MessageTypeEnum notificationType);

    INotificationBuilder setSender(ISender sender);

    INotificationBuilder addRecipient(IRecipient recipient);

    INotificationBuilder clearRecipients();

    INotificationBuilder addRecipients(Collection<IRecipient> recipients);

    INotificationBuilder addCopyRecipient(IRecipient copyRecipient);

    INotificationBuilder addCopyRecipients(Collection<IRecipient> copyRecipients);

    INotificationBuilder clearCopyRecipients();

    INotificationBuilder addBlindRecipient(IRecipient blindRecipient);

    INotificationBuilder addBlindRecipients(Collection<IRecipient> blindRecipients);

    INotificationBuilder clearBlindRecipients();

    INotificationBuilder setText(String text);

    INotificationBuilder setTemplateData(Serializable templateData);

    INotification createNotification();

    void clear();
}
