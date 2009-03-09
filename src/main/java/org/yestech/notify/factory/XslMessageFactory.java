/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.factory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yestech.notify.constant.MessageTypeEnum;
import org.yestech.notify.objectmodel.DefaultNotification;
import org.yestech.notify.objectmodel.IMessage;
import org.yestech.notify.objectmodel.INotification;
import org.yestech.notify.template.*;
import org.yestech.lib.xml.XslUtils;

/**
 * Factory to create a {@link org.yestech.notify.objectmodel.INotification} with XSL templating.
 *
 */
public class XslMessageFactory extends BaseMessageFactory
{
    final private static Logger logger = LoggerFactory.getLogger(XslMessageFactory.class);

    public XslMessageFactory() {
        super();
    }

    private MessageTypeEnum getMessageTypeFromXslFile() {
        MessageTypeEnum type = getMessageType();

        if (type == null) {
            try {
                XslTemplateLanguagePersistence templateData = (XslTemplateLanguagePersistence) getTemplateData();

                SAXBuilder builder = new SAXBuilder();
                Document doc = builder.build(XslUtils.locateFileInApp(templateData.getFilePath()));
                Namespace ns = Namespace.getNamespace("xsl", "http://www.w3.org/1999/XSL/Transform");
                Element root = doc.getRootElement();
                Element output = root.getChild("output", ns);
                type = MessageTypeEnum.valueOf(output.getAttributeValue("method"));
            } catch (Exception e) {
                logger.error("Error Locating Message Type", e);
                throw new RuntimeException("Error Locating Message Type", e);
            }
        }
        return type;
    }

    public INotification create() {
        DefaultNotification notification = new DefaultNotification();
        notification.setSender(getSender());

        IMessage message = createMessage();
        
        MessageTypeEnum type = getMessageTypeFromXslFile();
        message.setMessageType(type);
        notification.setMessage(message);

        notification.addRecipients(getRecipients());
        notification.addCopyRecipients(getCopyRecipients());
        notification.addBlindCopyRecipients(getBlindRecipients());

        notification.setTemplate(getTemplateLanguage());

        return notification;
    }
}
