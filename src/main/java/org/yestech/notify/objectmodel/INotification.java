package org.yestech.notify.objectmodel;

import org.yestech.notify.template.ITemplateLanguage;

import java.io.Serializable;
import java.util.Collection;

/**
 * Represents a Persistable Message to be delivered by Mail.
 *
 */
public interface INotification extends Serializable, Comparable<INotification> {

    static final long serialVersionUID = 8897680828148707131L;

    /**
     * Returns the {@link org.yestech.notify.template.ITemplateLanguage} to apply.
     *
     * @return The Template
     */
    public ITemplateLanguage getTemplate();

    /**
     * Return the {@link NotificationJob} this message is associated with.
     *
     * @return The MailJob
     */
    public INotificationJob getJob();

    /**
     * Return the {@link Sender} of the Message
     *
     * @return The sender
     */
    public ISender getSender();

    /**
     * Return the {@link Recipient}s of the Message
     *
     * @return The Recipients
     */
    public Collection<IRecipient> getRecipients();

    /**
     * Return the {@link Recipient}s of the Message who are to be
     * copied
     *
     * @return The Recipients
     */
    public Collection<IRecipient> getCopyRecipients();

    /**
     * Return the {@link Recipient}s of the Message who are to be
     * blindly copied
     *
     * @return The Recipients
     */
    public Collection<IRecipient> getBlindCopyRecipients();

    /**
     * Return the number of {@link Recipient}s
     *
     * @return Count of recipients
     */
    public int recipientSize();

    /**
     * Return the number of Copy {@link Recipient}s
     *
     * @return Count of copy recipients
     */
    public int copyRecipientSize();

    /**
     * Return the number of Blind Copy {@link Recipient}s
     *
     * @return Count of blind copy recipients
     */
    public int blindRecipientSize();

    /**
     * Return the number of Total {@link Recipient}s
     *
     * @return Count of total recipients
     */
    public int recipientTotalSize();

    /**
     * Return the Message to send.
     *
     * @return The message
     */
    public IMessage getMessage();

}
