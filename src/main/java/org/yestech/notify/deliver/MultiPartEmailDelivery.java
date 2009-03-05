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
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author $Author: $
 * @version $Revision: $
 */
@DeliverableMessageType({MessageTypeEnum.MULTIPART_EMAIL})
public class MultiPartEmailDelivery extends EmailDelivery {
    final private static Logger logger = LoggerFactory.getLogger(MultiPartEmailDelivery.class);

    public void deliver(INotification notification) {
        try {
            // Create the email message
            HtmlEmail email = new HtmlEmail();
            email.setHostName("mail.myserver.com");
            email.addTo("jdoe@somewhere.org", "John Doe");
            email.setFrom("me@apache.org", "Me");
            email.setSubject("");

            // embed the image and get the content id
//        URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
//        String cid = email.embed(url, "Apache logo");

            // set the html message
//        email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");

            // set the alternative message
//        email.setTextMsg("Your email client does not support HTML messages");

            // send the email
            email.send();
        } catch (EmailException e) {
            logger.error(e.getMessage(), e);
        }
    }
}