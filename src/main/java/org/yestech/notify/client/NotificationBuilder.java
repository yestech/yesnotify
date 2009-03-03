package org.yestech.notify.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yestech.notify.constant.FormatEnum;
import org.yestech.notify.constant.MimeTypeEnum;
import org.yestech.notify.constant.TemplateLanguageEnum;
import org.yestech.notify.model.BaseNotificationFactory;
import org.yestech.notify.model.DefaultNotificationFactory;
import org.yestech.notify.model.XslNotificationFactory;
import org.yestech.notify.objectmodel.INotification;
import org.yestech.notify.objectmodel.Recipient;
import org.yestech.notify.objectmodel.ISender;
import org.yestech.notify.objectmodel.IRecipient;
import org.yestech.notify.util.Clazz;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Builder used for creating concrete {@link org.yestech.notify.objectmodel.INotification}.
 *
 */
public class NotificationBuilder
{
    final private static Logger logger = LoggerFactory.getLogger(NotificationBuilder.class);

    private BaseNotificationFactory notificationFactory;

    private static Map<TemplateLanguageEnum, Class> notificationFactories;

    static {
        //load templates
        notificationFactories = new HashMap<TemplateLanguageEnum, Class>();

        notificationFactories.put(TemplateLanguageEnum.XSL_XML,
                XslNotificationFactory.class);

        notificationFactories.put(TemplateLanguageEnum.XSL_MAP,
                XslNotificationFactory.class);

        notificationFactories.put(TemplateLanguageEnum.NULL,
                DefaultNotificationFactory.class);
    }

    private NotificationBuilder() {
        super();
    }

    public void setSubject(String subject) {
        notificationFactory.setSubject(subject);
    }

    public void addAttachment(String attachment) {
        notificationFactory.addAttachment(attachment);
    }

    public void addAttachments(Collection<String> attachments) {
        notificationFactory.addAttachments(attachments);
    }

    public void clearAttachments() {
        notificationFactory.clearAttachments();
    }

    public void setNotificationType(FormatEnum notificationType) {
        notificationFactory.setNotificationType(notificationType);
    }

    public void setMimeType(MimeTypeEnum mimeType) {
        notificationFactory.setMimeType(mimeType);
    }

    public void setSender(ISender sender) {
        notificationFactory.setSender(sender);
    }

    public void addRecipient(Recipient recipient) {
        notificationFactory.addRecipient(recipient);
    }

    public void clearRecipients() {
        notificationFactory.clearRecipients();
    }

    public void addRecipients(Collection<IRecipient> recipients) {
        notificationFactory.addRecipients(recipients);
    }

    public void addCopyRecipient(Recipient copyRecipient) {
        notificationFactory.addCopyRecipient(copyRecipient);
    }

    public void addCopyRecipients(Collection<IRecipient> copyRecipients) {
        notificationFactory.addCopyRecipients(copyRecipients);
    }

    public void clearCopyRecipients() {
        notificationFactory.clearCopyRecipients();
    }


    public void addBlindRecipient(Recipient blindRecipient) {
        notificationFactory.addBlindRecipient(blindRecipient);
    }

    public void addBlindRecipients(Collection<IRecipient> blindRecipients) {
        notificationFactory.addBlindRecipients(blindRecipients);
    }

    public void clearBlindRecipients() {
        notificationFactory.clearBlindRecipients();
    }

    public void setText(String text) {
        notificationFactory.setText(text);
    }

    public void setTemplateData(Serializable templateData) {
        notificationFactory.setTemplateData(templateData);
    }

    /**
     * <b>Only</b> call this after setting all the other appriopriate attributes.
     * The current values will <b>Always</b> be used to create the message.  This
     * method can be reused to create additional {@link org.yestech.notify.objectmodel.INotification}, just reset
     * the appropriate attributes then call this method.
     *
     * @return The Message
     */
    public INotification createNotification() {
        return notificationFactory.create();
    }

    public static NotificationBuilder getBuilder(TemplateLanguageEnum templateLanguage) {
        NotificationBuilder builder = new NotificationBuilder();
        Class factoryClass = notificationFactories.get(templateLanguage);
        builder.notificationFactory = (BaseNotificationFactory) Clazz.instantiateClass(factoryClass);
        return builder;
    }
}
