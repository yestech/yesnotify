/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.objectmodel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yestech.lib.lang.Clazz;
import org.yestech.notify.template.ITemplateLanguage;
import org.yestech.notify.template.NullTemplateLanguage;

import java.io.*;
import java.util.Collection;
import static java.util.Collections.unmodifiableCollection;
import java.util.HashSet;
import java.util.UUID;

/**
 * Default implementation of a {@link INotification}.
 */
@XStreamAlias("notification")
public class DefaultNotification implements INotification {
    final private static Logger logger = LoggerFactory.getLogger(DefaultNotification.class);

    static final long serialVersionUID = 8897680828148707131L;

    @XStreamAlias("sender")
    private ISender sender;

    @XStreamAlias("jobId")
    @XStreamAsAttribute
    private UUID jobId;

    @XStreamImplicit(itemFieldName = "recipient")
    private Collection<IRecipient> recipients;

    @XStreamImplicit(itemFieldName = "copyRecipient")
    private Collection<IRecipient> copyRecipients;

    @XStreamImplicit(itemFieldName = "blindRecipient")
    private Collection<IRecipient> blindRecipients;

    @XStreamAlias("template")
    @XStreamOmitField
    private transient ITemplateLanguage template;

    @XStreamAlias("message")
    private IMessage message;

    public DefaultNotification() {
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
        if (template == null) {
            return new NullTemplateLanguage();
        }
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
        if (recipients == null) {
            return unmodifiableCollection(new HashSet<IRecipient>());
        }
        return unmodifiableCollection(recipients);
    }

    public void addRecipient(IRecipient recipient) {
        if (recipient != null) {
            if (this.recipients == null) {
                this.recipients = new HashSet<IRecipient>();
            }
            recipients.add(recipient);
        }
    }

    public void addRecipients(Collection<IRecipient> recipients) {
        if (recipients != null && !recipients.isEmpty()) {
            if (this.recipients == null) {
                this.recipients = new HashSet<IRecipient>();
            }
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
        if (copyRecipients == null) {
            return unmodifiableCollection(new HashSet<IRecipient>());
        }
        return unmodifiableCollection(copyRecipients);
    }

    public void addCopyRecipient(IRecipient copyRecipient) {
        if (copyRecipient != null) {
            if (this.copyRecipients == null) {
                this.copyRecipients = new HashSet<IRecipient>();
            }
            copyRecipients.add(copyRecipient);
        }
    }

    public void addCopyRecipients(Collection<IRecipient> copyRecipients) {
        if (copyRecipients != null && !copyRecipients.isEmpty()) {
            if (this.copyRecipients == null) {
                this.copyRecipients = new HashSet<IRecipient>();
            }
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
        if (blindRecipients == null) {
            return unmodifiableCollection(new HashSet<IRecipient>());
        }
        return unmodifiableCollection(blindRecipients);
    }

    public void addBlindCopyRecipient(Recipient blindRecipient) {
        if (blindRecipient != null) {
            if (this.blindRecipients == null) {
                this.blindRecipients = new HashSet<IRecipient>();
            }
            blindRecipients.add(blindRecipient);
        }
    }

    public void addBlindCopyRecipients(Collection<IRecipient> blindRecipients) {
        if (blindRecipients != null && !blindRecipients.isEmpty()) {
            if (this.blindRecipients == null) {
                this.blindRecipients = new HashSet<IRecipient>();
            }
            this.blindRecipients.addAll(blindRecipients);
        }
    }

    /**
     * Return the number of {@link Recipient}s
     *
     * @return Count of recipients
     */
    public int recipientSize() {
        return recipients == null ? 0 : recipients.size();
    }

    /**
     * Return the number of Copy {@link Recipient}s
     *
     * @return Count of copy recipients
     */
    public int copyRecipientSize() {
        return copyRecipients == null ? 0 : copyRecipients.size();
    }

    /**
     * Return the number of Blind Copy {@link Recipient}s
     *
     * @return Count of blind copy recipients
     */
    public int blindRecipientSize() {
        return blindRecipients == null ? 0 : blindRecipients.size();
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
        ITemplateLanguage templateLanguage = getTemplate();
        String className = templateLanguage.getClass().getName();
        if (logger.isDebugEnabled()) {
            logger.debug("Writing template class name: " + className);
        }
        s.writeUTF(className);
        s.writeObject(templateLanguage.getTemplateData());
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

    public int compareTo(INotification o) {
        return jobId.compareTo(o.getJob().getIdentifier());
    }
}
