/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.objectmodel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yestech.notify.template.NullTemplateLanguage;
import org.yestech.notify.template.ITemplateLanguage;
import org.yestech.notify.util.Clazz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

/**
 * Default implementation of a {@link INotification}.
 *
 */
public class DefaultNotification implements INotification
{
    final private static Logger logger = LoggerFactory.getLogger(DefaultNotification.class);

    static final long serialVersionUID = 8897680828148707131L;

    private ISender sender;
    private UUID jobId;
    private Collection<IRecipient> recipients;
    private Collection<IRecipient> copyRecipients;
    private Collection<IRecipient> blindRecipients;
    private transient ITemplateLanguage template;
    private IMessage message;

    public DefaultNotification() {
        template = new NullTemplateLanguage();
        recipients = new HashSet<IRecipient>();
        copyRecipients = new HashSet<IRecipient>();
        blindRecipients = new HashSet<IRecipient>();
    }

    public void setJob(INotificationJob job) {
        if (job == null) {
            throw new NullPointerException("NotificationJob can't be null....");
        }
        this.jobId = job.getIdentifier();
    }

    /**
     * Return the {@link NotificationJob} this message is associated with.
     *
     * @return The INotificationJob
     */
    public INotificationJob getJob() {
        return (jobId != null) ? new LightWeightNotificationJob(jobId) : null;
    }

    public void setTemplate(ITemplateLanguage template) {
        if (template != null) {
            this.template = template;
        }
    }

    /**
     * Returns the {@link org.yestech.notify.template.ITemplateLanguage} to apply.
     *
     * @return The Template
     */
    public ITemplateLanguage getTemplate() {
        return template;
    }

    public void setSender(ISender sender) {
        this.sender = sender;
    }

    /**
     * Return the {@link Sender} of the Message
     *
     * @return The sender
     */
    public ISender getSender() {
        return sender;
    }

    /**
     * Return the {@link Recipient}s of the Message
     *
     * @return The Recipients
     */
    public Collection<IRecipient> getRecipients() {
        return Collections.unmodifiableCollection(recipients);
    }

    public void addRecipient(IRecipient recipient) {
        if (recipient != null) {
            recipients.add(recipient);
        }
    }

    public void addRecipients(Collection<IRecipient> recipients) {
        if (recipients != null && !recipients.isEmpty()) {
            this.recipients.addAll(recipients);
        }
    }


    /**
     * Return the {@link Recipient}s of the Message who are to be
     * copied
     *
     * @return The Recipients
     */
    public Collection<IRecipient> getCopyRecipients() {
        return Collections.unmodifiableCollection(copyRecipients);
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

    /**
     * Return the {@link Recipient}s of the Message who are to be
     * blindly copied
     *
     * @return The Recipients
     */
    public Collection<IRecipient> getBlindCopyRecipients() {
        return Collections.unmodifiableCollection(blindRecipients);
    }

    public void addBlindCopyRecipient(Recipient blindRecipient) {
        if (blindRecipient != null) {
            blindRecipients.add(blindRecipient);
        }
    }

    public void addBlindCopyRecipients(Collection<IRecipient> blindRecipients) {
        if (blindRecipients != null && !blindRecipients.isEmpty()) {
            this.blindRecipients.addAll(blindRecipients);
        }
    }

    /**
     * Return the number of {@link Recipient}s
     *
     * @return Count of recipients
     */
    public int recipientSize() {
        return recipients.size();
    }

    /**
     * Return the number of Copy {@link Recipient}s
     *
     * @return Count of copy recipients
     */
    public int copyRecipientSize() {
        return copyRecipients.size();
    }

    /**
     * Return the number of Blind Copy {@link Recipient}s
     *
     * @return Count of blind copy recipients
     */
    public int blindRecipientSize() {
        return blindRecipients.size();
    }

    /**
     * Return the number of Total {@link Recipient}s
     *
     * @return Count of total recipients
     */
    public int recipientTotalSize() {
        return (recipientSize() + copyRecipientSize() + blindRecipientSize());
    }

    /**
     * Set the Message.
     *
     * @param message
     */
    public void setMessage(IMessage message) {
        this.message = message;
    }

    /**
     * Return the Message to send.
     *
     * @return The message
     */
    public IMessage getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultNotification)) return false;

        DefaultNotification that = (DefaultNotification) o;

        if (blindRecipients != null ? !blindRecipients.equals(that.blindRecipients) : that.blindRecipients != null)
            return false;
        if (copyRecipients != null ? !copyRecipients.equals(that.copyRecipients) : that.copyRecipients != null)
            return false;
        if (jobId != null ? !jobId.equals(that.jobId) : that.jobId != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (recipients != null ? !recipients.equals(that.recipients) : that.recipients != null) return false;
        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        if (template != null ? !template.equals(that.template) : that.template != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sender != null ? sender.hashCode() : 0;
        result = 31 * result + (jobId != null ? jobId.hashCode() : 0);
        result = 31 * result + (recipients != null ? recipients.hashCode() : 0);
        result = 31 * result + (copyRecipients != null ? copyRecipients.hashCode() : 0);
        result = 31 * result + (blindRecipients != null ? blindRecipients.hashCode() : 0);
        result = 31 * result + (template != null ? template.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DefaultNotification{" +
                "sender=" + sender +
                ", jobId=" + jobId +
                ", recipients=" + recipients +
                ", copyRecipients=" + copyRecipients +
                ", blindRecipients=" + blindRecipients +
                ", template=" + template +
                ", message=" + message +
                '}';
    }

    /**
     * Override Serialization
     *
     * @param s
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream s)
            throws IOException {

        //write the default object
        s.defaultWriteObject();

        //add the class name of the Template language
        String className = template.getClass().getName();
        if (logger.isDebugEnabled()) {
            logger.debug("Writing template class name: " + className);
        }
        s.writeUTF(className);
        s.writeObject(template.getTemplateData());
    }

    /**
     * Override DeSerialization
     *
     * @param s
     * @throws IOException
     */
    private void readObject(ObjectInputStream s)
            throws IOException {

        try {
            s.defaultReadObject();
        } catch (Exception e) {
            logger.error("Error Resolving Class...", e);
            throw new RuntimeException("Error Resolving Class...", e);
        }

        //load template class
        String className = s.readUTF();
        Serializable templateData = null;
        try {
            templateData = (Serializable) s.readObject();
        } catch (Exception e) {
            logger.error("Error Restoring Template Data...", e);
            throw new RuntimeException("Error Restoring Template Data...", e);

        }
        if (logger.isDebugEnabled()) {
            logger.debug("Reading template class name: " + className);
        }
        template = (ITemplateLanguage) Clazz.instantiateClass(className);
        template.setTemplateData(templateData);
    }

    public int compareTo(INotification o)
    {
        return jobId.compareTo(o.getJob().getIdentifier());
    }
}
