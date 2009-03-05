/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.objectmodel;

import org.yestech.notify.constant.MessageTypeEnum;
import org.yestech.lib.lang.ArraySet;

import java.util.Collection;
import java.util.Collections;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * A Physical Message that wants to be send.  It is contained in a {@link INotification}.
 *
 */
@XStreamAlias("message")
public class Message implements IMessage
{
    private String text;

    @XStreamImplicit(itemFieldName = "attachment")
    private Collection<Attachment> attachments;


    @XStreamAlias("messageType")
    @XStreamAsAttribute
    private MessageTypeEnum messageType;

    @XStreamAsAttribute
    private String subject;

    public Message() {
        super();
        attachments = new ArraySet<Attachment>();
        messageType = MessageTypeEnum.TEXT_EMAIL;
    }

    public void addAttachments(Collection attachments) {
        if (attachments != null && !attachments.isEmpty()) {
            this.attachments.addAll(attachments);
        }
    }

    public void addAttachment(Attachment attachment) {
        if (attachment != null) {
            attachments.add(attachment);
        }
    }

    /**
     * Clears the Attachments.
     */
    public void clearAttachment() {
        attachments.clear();
    }
    /**
     * Return the whether pr not there are attachments in the message.
     *
     * @return True if Attachments, else false.
     */
    public boolean isAttachmentEmpty() {
        return attachments.isEmpty();
    }

    /**
     * Return the number of Attachments
     *
     * @return Count of attachments
     */
    public int attachementSize() {
        return attachments.size();
    }

    /**
     * Return an Immutable Collection of Attachments
     *
     * @return The Attachments
     */
    public Collection getAttachments() {
        return Collections.unmodifiableCollection(attachments);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageTypeEnum getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageTypeEnum messageType) {
        if (messageType != null) {
            this.messageType = messageType;
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof IMessage)) return false;

        Message that = (Message) o;

        if (attachments != null ? !attachments.equals(that.attachments) : that.attachments != null) return false;
        if (messageType != that.messageType) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (attachments != null ? attachments.hashCode() : 0);
        result = 31 * result + (messageType != null ? messageType.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Message{" +
               "text='" + text + '\'' +
               ", attachments=" + attachments +
               ", messageType=" + messageType +
               ", subject='" + subject + '\'' +
               '}';
    }
}
