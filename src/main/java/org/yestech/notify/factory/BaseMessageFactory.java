/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.factory;

import org.yestech.notify.constant.MessageTypeEnum;
import org.yestech.notify.objectmodel.*;
import org.yestech.notify.template.ITemplateLanguage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Base Class Used to Create {@link org.yestech.notify.objectmodel.INotification}s.
 *
 */
public abstract class BaseMessageFactory
{
    private ISender sender;
    private Collection<IRecipient> recipients;
    private Collection<IRecipient> copyRecipients;
    private Collection<IRecipient> blindRecipients;
    private String text;
    private Serializable templateData;
    private String subject;
    private Collection<String> attachments;
    private MessageTypeEnum messageType;
    private ITemplateLanguage templateLanguage;

    public BaseMessageFactory() {
        super();
        recipients = new ArrayList<IRecipient>();
        copyRecipients = new ArrayList<IRecipient>();
        blindRecipients = new ArrayList<IRecipient>();
        attachments = new ArrayList<String>();
    }

    public abstract INotification create();

    public void setSender(ISender sender) {
        this.sender = sender;
    }

    protected ISender getSender() {
        return sender;
    }

    public void addRecipient(IRecipient recipient) {
        if (recipient != null) {
            recipients.add(recipient);
        }
    }

    public void clearRecipients() {
        recipients.clear();
    }

    public void addRecipients(Collection<IRecipient> recipients) {
        if (recipients != null && !recipients.isEmpty()) {
            this.recipients.addAll(recipients);
        }
    }


    protected Collection<IRecipient> getRecipients() {
        return recipients;
    }

    public void addCopyRecipient(IRecipient copyRecipient) {
        if (copyRecipient != null) {
            copyRecipients.add(copyRecipient);
        }
    }

    public void addCopyRecipients(Collection<IRecipient> copyRecipients) {
        if (copyRecipients != null && !copyRecipients.isEmpty()) {
            this.copyRecipients.addAll(copyRecipients);
        }
    }

    protected Collection<IRecipient> getCopyRecipients() {
        return copyRecipients;
    }

    public void clearCopyRecipients() {
        copyRecipients.clear();
    }

    public void addBlindRecipient(IRecipient blindRecipient) {
        if (blindRecipient != null) {
            blindRecipients.add(blindRecipient);
        }
    }

    public void addBlindRecipients(Collection<IRecipient> blindRecipients) {
        if (blindRecipients != null && !blindRecipients.isEmpty()) {
            this.blindRecipients.addAll(blindRecipients);
        }
    }

    protected Collection<IRecipient> getBlindRecipients() {
        return blindRecipients;
    }

    public void clearBlindRecipients() {
        blindRecipients.clear();
    }

    public void setText(String text) {
        this.text = text;
    }

    protected String getText() {
        return text;
    }

    public void setTemplateData(Serializable templateData) {
        this.templateData = templateData;
    }

    protected Serializable getTemplateData() {
        return templateData;
    }

    protected String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void clearAttachments() {
        attachments.clear();
    }

    protected Collection<String> getAttachments() {
        return attachments;
    }

    public void addAttachment(String attachment) {
        if (attachment != null) {
            attachments.add(attachment);
        }
    }

    public void addAttachments(Collection<String> attachments) {
        if (attachments != null && !attachments.isEmpty()) {
            this.attachments.addAll(attachments);
        }
    }
    protected MessageTypeEnum getMessageType() {
        return messageType;
    }

    public void setNotificationType(MessageTypeEnum messageType) {
        this.messageType = messageType;
    }

    protected IMessage createMessage() {
        IMessage message = new Message();
        message.setText(getText());
        message.setSubject(getSubject());
        message.setMessageType(getMessageType());
        message.addAttachments(getAttachments());
        return message;
    }

    public void setTemplateLanguage(ITemplateLanguage templateLanguage) {
        this.templateLanguage = templateLanguage;
    }

    public ITemplateLanguage getTemplateLanguage() {
        return templateLanguage;
    }
}
