/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yestech.notify.constant.StageEnum;
import org.yestech.notify.constant.StatusEnum;
import org.yestech.notify.objectmodel.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class provides convienece methods.
 *
 */
final public class NotificationUtils
{
    final private static Logger logger =
            LoggerFactory.getLogger(NotificationUtils.class);

    private NotificationUtils() {
        super();
    }

    /**
     * Converts a String File Name to an {@link org.yestech.notify.objectmodel.IAttachment} object.
     *
     * @param origAttachments FileNames to convert
     * @return Collection of {@link org.yestech.notify.objectmodel.Attachment}
     */
    public static Collection<IAttachment> convertAttachments(Collection<String> origAttachments) {
        Collection<IAttachment> attachments = new ArrayList<IAttachment>();
        if (origAttachments != null) {
            for (String origAttachment : origAttachments)
            {
                Attachment attachment = new Attachment(origAttachment);
                attachments.add(attachment);
            }
        }
        return attachments;
    }

    /**
     * Converts a String email address to a {@link IRecipient} object.
     *
     * @param origRecipients Email Addresses to convert
     * @return Collection of {@link IRecipient}.
     */
    public static Collection<IRecipient> convertRecipients(Collection<String> origRecipients) {
        Collection<IRecipient> recipients = new ArrayList<IRecipient>();
        for (String origRecipient : origRecipients)
        {
            Recipient recipient = new Recipient();
            recipient.setEmailAddress(origRecipient);
            recipients.add(recipient);
        }
        return recipients;
    }

}
