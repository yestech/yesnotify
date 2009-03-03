package org.yestech.notify.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yestech.notify.constant.MessageTypeEnum;
import org.yestech.notify.constant.MimeTypeEnum;
import org.yestech.notify.constant.TemplateLanguageEnum;
import org.yestech.notify.model.DefaultMessageFactory;
import org.yestech.notify.model.BaseMessageFactory;
import org.yestech.notify.model.XslMessageFactory;
import org.yestech.notify.objectmodel.INotification;
import org.yestech.notify.objectmodel.IRecipient;
import org.yestech.notify.objectmodel.ISender;
import org.yestech.notify.util.Clazz;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Builder used for creating concrete {@link org.yestech.notify.objectmodel.INotification}.
 */
public class NotificationBuilder {
    final private static Logger logger = LoggerFactory.getLogger(NotificationBuilder.class);

    private BaseMessageFactory messageFactory;

    private static Map<TemplateLanguageEnum, Class> notificationFactories;

    static {
        //load templates
        notificationFactories = new HashMap<TemplateLanguageEnum, Class>();

        notificationFactories.put(TemplateLanguageEnum.XSL_XML,
                XslMessageFactory.class);

        notificationFactories.put(TemplateLanguageEnum.XSL_MAP,
                XslMessageFactory.class);

        notificationFactories.put(TemplateLanguageEnum.NULL,
                DefaultMessageFactory.class);
    }

    private NotificationBuilder() {
        super();
    }

    public void setSubject(String subject) {
        messageFactory.setSubject(subject);
    }

    public void addAttachment(String attachment) {
        messageFactory.addAttachment(attachment);
    }

    public void addAttachments(Collection<String> attachments) {
        messageFactory.addAttachments(attachments);
    }

    public void clearAttachments() {
        messageFactory.clearAttachments();
    }

    public void setMessageType(MessageTypeEnum notificationType) {
        messageFactory.setNotificationType(notificationType);
    }

    public void setMimeType(MimeTypeEnum mimeType) {
        messageFactory.setMimeType(mimeType);
    }

    public void setSender(ISender sender) {
        messageFactory.setSender(sender);
    }

    public void addRecipient(IRecipient recipient) {
        messageFactory.addRecipient(recipient);
    }

    public void clearRecipients() {
        messageFactory.clearRecipients();
    }

    public void addRecipients(Collection<IRecipient> recipients) {
        messageFactory.addRecipients(recipients);
    }

    public void addCopyRecipient(IRecipient copyRecipient) {
        messageFactory.addCopyRecipient(copyRecipient);
    }

    public void addCopyRecipients(Collection<IRecipient> copyRecipients) {
        messageFactory.addCopyRecipients(copyRecipients);
    }

    public void clearCopyRecipients() {
        messageFactory.clearCopyRecipients();
    }


    public void addBlindRecipient(IRecipient blindRecipient) {
        messageFactory.addBlindRecipient(blindRecipient);
    }

    public void addBlindRecipients(Collection<IRecipient> blindRecipients) {
        messageFactory.addBlindRecipients(blindRecipients);
    }

    public void clearBlindRecipients() {
        messageFactory.clearBlindRecipients();
    }

    public void setText(String text) {
        messageFactory.setText(text);
    }

    public void setTemplateData(Serializable templateData) {
        messageFactory.setTemplateData(templateData);
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
        return messageFactory.create();
    }

    /**
     * Clears the attachments, all recipients, text
     */
    public void clear() {
        messageFactory.clearAttachments();
        messageFactory.clearRecipients();
        messageFactory.clearBlindRecipients();
        messageFactory.clearCopyRecipients();
        messageFactory.setText("");
    }

    public static NotificationBuilder getBuilder() {
        return getBuilder(TemplateLanguageEnum.NULL);
    }

    public static NotificationBuilder getBuilder(TemplateLanguageEnum templateLanguage) {
        NotificationBuilder builder = new NotificationBuilder();
        Class factoryClass = notificationFactories.get(templateLanguage);
        builder.messageFactory = (BaseMessageFactory) Clazz.instantiateClass(factoryClass);
        return builder;
    }
}
