/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

/*
 *
 * Original Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.deliver;

import org.yestech.notify.constant.MessageTypeEnum;
import org.yestech.notify.objectmodel.INotification;
import org.yestech.notify.objectmodel.IRecipient;
import org.yestech.notify.objectmodel.ISender;
import org.yestech.notify.template.ITemplateLanguage;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.EmailException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.google.common.collect.Lists.newArrayList;

/**
 * @author $Author: $
 * @version $Revision: $
 */
@DeliverableMessageType({MessageTypeEnum.MULTIPART_EMAIL})
public class MultiPartEmailDelivery extends EmailDelivery {
    final private static Logger logger = LoggerFactory.getLogger(MultiPartEmailDelivery.class);

    protected void sendMessage(INotification notification, IRecipient recipient) throws EmailException {
        // Create the email message
        HtmlEmail email = new HtmlEmail();
        enableAuthenticator(email);
        email.setHostName(getEmailHost());
        ISender sender = notification.getSender();
        email.setFrom(sender.getEmailAddress(), sender.getDisplayName());
        if (StringUtils.isNotBlank(sender.getReplyAddress())) {
            email.addReplyTo(sender.getReplyAddress());
        }
        email.setSubject(notification.getMessage().getSubject());
        email.addTo(recipient.getEmailAddress(), recipient.getDisplayName());
        ITemplateLanguage template = notification.getTemplate();
        String appliedMessage = template.apply(notification.getMessage());
        email.setHtmlMsg(appliedMessage);
        email.send();
    }
}