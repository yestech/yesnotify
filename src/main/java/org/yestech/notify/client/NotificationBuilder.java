/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yestech.notify.constant.MessageTypeEnum;
import org.yestech.notify.factory.DefaultMessageFactory;
import org.yestech.notify.factory.BaseMessageFactory;
import org.yestech.notify.factory.XslMessageFactory;
import org.yestech.notify.objectmodel.INotification;
import org.yestech.notify.objectmodel.IRecipient;
import org.yestech.notify.objectmodel.ISender;
import org.yestech.notify.template.ITemplateLanguage;
import org.yestech.notify.template.NullTemplateLanguage;
import org.yestech.notify.template.XslXmlTemplateLanguage;
import org.yestech.notify.template.XslMapTemplateLanguage;
import org.yestech.lib.lang.Clazz;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * Builder used for creating concrete {@link org.yestech.notify.objectmodel.INotification}.
 */
public class NotificationBuilder implements INotificationBuilder {
    final private static Logger logger = LoggerFactory.getLogger(NotificationBuilder.class);

    private BaseMessageFactory messageFactory;

    private static Map<Class<? extends ITemplateLanguage>, Class<? extends BaseMessageFactory>> notificationFactories = newHashMap();

    static {
        //load default custom templates
        notificationFactories = new HashMap<Class<? extends ITemplateLanguage>, Class<? extends BaseMessageFactory>>();

        notificationFactories.put(XslXmlTemplateLanguage.class,
                XslMessageFactory.class);

        notificationFactories.put(XslMapTemplateLanguage.class,
                XslMessageFactory.class);
    }

    public static Map<Class<? extends ITemplateLanguage>, Class<? extends BaseMessageFactory>> getNotificationFactories() {
        return notificationFactories;
    }

    public static void setNotificationFactories(Map<Class<? extends ITemplateLanguage>, Class<? extends BaseMessageFactory>> notificationFactories) {
        for (Map.Entry<Class<? extends ITemplateLanguage>, Class<? extends BaseMessageFactory>> classClassEntry : notificationFactories.entrySet()) {
            NotificationBuilder.notificationFactories.put(classClassEntry.getKey(),
                    classClassEntry.getValue());
        }
    }

    private NotificationBuilder() {
        super();
    }

    public INotificationBuilder setSubject(String subject) {
        messageFactory.setSubject(subject);
        return this;
    }

    public INotificationBuilder addAttachment(String attachment) {
        messageFactory.addAttachment(attachment);
        return this;
    }

    public INotificationBuilder addAttachments(Collection<String> attachments) {
        messageFactory.addAttachments(attachments);
        return this;
    }

    public INotificationBuilder clearAttachments() {
        messageFactory.clearAttachments();
        return this;
    }

    public INotificationBuilder setMessageType(MessageTypeEnum notificationType) {
        messageFactory.setNotificationType(notificationType);
        return this;
    }

    public INotificationBuilder setSender(ISender sender) {
        messageFactory.setSender(sender);
        return this;
    }

    public INotificationBuilder addRecipient(IRecipient recipient) {
        messageFactory.addRecipient(recipient);
        return this;
    }

    public INotificationBuilder clearRecipients() {
        messageFactory.clearRecipients();
        return this;
    }

    public INotificationBuilder addRecipients(Collection<IRecipient> recipients) {
        messageFactory.addRecipients(recipients);
        return this;
    }

    public INotificationBuilder addCopyRecipient(IRecipient copyRecipient) {
        messageFactory.addCopyRecipient(copyRecipient);
        return this;
    }

    public INotificationBuilder addCopyRecipients(Collection<IRecipient> copyRecipients) {
        messageFactory.addCopyRecipients(copyRecipients);
        return this;
    }

    public INotificationBuilder clearCopyRecipients() {
        messageFactory.clearCopyRecipients();
        return this;
    }


    public INotificationBuilder addBlindRecipient(IRecipient blindRecipient) {
        messageFactory.addBlindRecipient(blindRecipient);
        return this;
    }

    public INotificationBuilder addBlindRecipients(Collection<IRecipient> blindRecipients) {
        messageFactory.addBlindRecipients(blindRecipients);
        return this;
    }

    public INotificationBuilder clearBlindRecipients() {
        messageFactory.clearBlindRecipients();
        return this;
    }

    public INotificationBuilder setText(String text) {
        messageFactory.setText(text);
        return this;
    }

    public INotificationBuilder setTemplateData(Serializable templateData) {
        messageFactory.setTemplateData(templateData);
        return this;
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

    public static INotificationBuilder getBuilder() {
        return getBuilder(new NullTemplateLanguage());
    }

    public static INotificationBuilder getBuilder(ITemplateLanguage templateLanguage) {
        NotificationBuilder builder = new NotificationBuilder();
        Class<? extends BaseMessageFactory> factoryClass = notificationFactories.get(templateLanguage.getClass());
        if (factoryClass == null) {
            factoryClass = DefaultMessageFactory.class;
        }
        builder.messageFactory = Clazz.instantiateClass(factoryClass);
        builder.messageFactory.setTemplateLanguage(templateLanguage);
        return builder;
    }
}
