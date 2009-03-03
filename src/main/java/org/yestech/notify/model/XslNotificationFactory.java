package org.yestech.notify.model;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yestech.notify.constant.FormatEnum;
import org.yestech.notify.constant.MimeTypeEnum;
import org.yestech.notify.objectmodel.DefaultNotification;
import org.yestech.notify.objectmodel.IMessage;
import org.yestech.notify.objectmodel.INotification;
import org.yestech.notify.template.*;
import org.yestech.notify.util.XSLUtils;

import java.util.Map;

/**
 * Factory to create a {@link org.yestech.notify.objectmodel.INotification} with XSL templating.
 *
 */
public class XslNotificationFactory extends BaseNotificationFactory
{
    final private static Logger logger = LoggerFactory.getLogger(XslNotificationFactory.class);

    public XslNotificationFactory() {
        super();
    }

    private FormatEnum getMessageTypeFromXslFile() {
        FormatEnum type = getMessageType();

        if (type == null) {
            try {
                XslTemplateData templateData = (XslTemplateData) getTemplateData();

                SAXBuilder builder = new SAXBuilder();
                Document doc = builder.build(XSLUtils.locateFileInApp(templateData.getFilePath()));
                Namespace ns = Namespace.getNamespace("xsl", "http://www.w3.org/1999/XSL/Transform");
                Element root = doc.getRootElement();
                Element output = root.getChild("output", ns);
                type = FormatEnum.valueOf(output.getAttributeValue("method"));
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

        IMessage message = createNotification();
        
        FormatEnum type = getMessageTypeFromXslFile();
        message.setMessageType(type);
        MimeTypeEnum mimeType = FormatEnum.HTML.equals(type) ? MimeTypeEnum.HTML : MimeTypeEnum.TEXT;

        message.setMimeType(mimeType);

        notification.setMessage(message);

        notification.addRecipients(getRecipients());
        notification.addCopyRecipients(getCopyRecipients());
        notification.addBlindCopyRecipients(getBlindRecipients());

        notification.setTemplate(getLanguage());

        return notification;
    }

    private TemplateLanguage getLanguage() {
        TemplateLanguage language = new NullTemplateLanguage();
        XslTemplateData templateData = (XslTemplateData) getTemplateData();

        if (templateData.getData() instanceof Map) {
            language = new XslMapTemplateLanguage(templateData);
        } else {
            language = new XslXmlTemplateLanguage(templateData);
        }

        return language;
    }
}
