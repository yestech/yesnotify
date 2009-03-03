package org.yestech.notify.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yestech.notify.constant.StageEnum;
import org.yestech.notify.constant.StatusEnum;
import org.yestech.notify.objectmodel.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class provides convienece methods for validating a
 * {@link org.yestech.notify.objectmodel.IAggregateNotificationJob}.
 *
 */
final public class NotificationUtils
{
    final private static Logger logger =
            LoggerFactory.getLogger(NotificationUtils.class);

    private NotificationUtils() {
        super();
    }

    /**
     * Converts a String File Name to an {@link org.yestech.notify.objectmodel.IAttachment} object.
     *
     * @param origAttachments FileNames to convert
     * @return Collection of {@link org.yestech.notify.objectmodel.Attachment}
     */
    public static Collection<IAttachment> convertAttachments(Collection<String> origAttachments) {
        Collection<IAttachment> attachments = new ArrayList<IAttachment>();
        if (origAttachments != null) {
            for (String origAttachment : origAttachments)
            {
                Attachment attachment = new Attachment(origAttachment);
                attachments.add(attachment);
            }
        }
        return attachments;
    }

    /**
     * Converts a String email address to a {@link IRecipient} object.
     *
     * @param origRecipients Email Addresses to convert
     * @return Collection of {@link IRecipient}.
     */
    public static Collection<IRecipient> convertRecipients(Collection<String> origRecipients) {
        Collection<IRecipient> recipients = new ArrayList<IRecipient>();
        for (String origRecipient : origRecipients)
        {
            Recipient recipient = new Recipient();
            recipient.setEmailAddress(origRecipient);
            recipients.add(recipient);
        }
        return recipients;
    }

    public static void initialCheck(IAggregateNotificationJob job) {
        if (job == null
                || job.getSender() == null
                || job.getSubject() == null
                || ((job.getPlainPart() == null || job.getPlainPart().equals(""))
                && (job.getHtmlPart() == null || job.getHtmlPart().equals("")))) {
            logger.error("notification job [" + job + "] not in a valid state to notify");
            throw new RuntimeException(
                    "notification job [" + job + "] not in a valid state to notify");
        }

        // if the job has been delivered, bail out of sending again
        if (job.getStage().equals(StageEnum.DELIVER)) {
            logger.error(
                    "job was already sent once to the real delivery list ["
                    + job
                    + "]");
            throw new RuntimeException(
                    "job was already sent once to the real delivery list ["
                    + job
                    + "]");
        }
    }


    /**
     * Converts a Collection of {@link IRecipient} to {@link IAggregateNotificationRecipient} so they can be blasted
     *
     * @param recipients IRecipients to convert
     * @return A Collection of IAggregateNotificationRecipient
     */
    public static Collection<IAggregateNotificationRecipient> convertRecipientToAggregateNotificationRecipient(Collection<IRecipient> recipients, long jobId) {
        ArrayList<IAggregateNotificationRecipient> convertedRecipients = new ArrayList<IAggregateNotificationRecipient>();
        if (recipients == null) {
            logger.error("Recipients can be null...");
            throw new NullPointerException("Recipients can be null...");
        }

        for (IRecipient recipient : recipients)
        {
            AggregateNotificationRecipient aggregateNotificationRecipient = new AggregateNotificationRecipient();
            aggregateNotificationRecipient.setUserId(-1L);
            aggregateNotificationRecipient.setNotificationJobId(jobId);
            aggregateNotificationRecipient.setEmail(recipient.getEmailAddress());
            aggregateNotificationRecipient.setStatus(StatusEnum.SENT);
            aggregateNotificationRecipient.setAttributes("A tester");
            convertedRecipients.add(aggregateNotificationRecipient);
        }

        return convertedRecipients;
    }
}
