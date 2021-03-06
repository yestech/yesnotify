/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

/*
 *
 * Original Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.client;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.yestech.notify.constant.MessageTypeEnum;
import org.yestech.notify.objectmodel.IMessage;
import org.yestech.notify.objectmodel.INotification;
import org.yestech.notify.objectmodel.INotificationJob;
import org.yestech.notify.objectmodel.ISender;
import static org.yestech.notify.factory.ObjectFactory.createRecipient;
import static org.yestech.notify.factory.ObjectFactory.createSender;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public class NotificationBuilderIntegrationTest {
  
    @Test
    public void testChainBuilder() {
        INotificationBuilder builder = NotificationBuilder.getBuilder();
        assertNotNull(builder);
        String subject = "test subject";
        String text = "this is the text";
        String attachment = "attch1";
        ISender sender = createSender();
        builder.setSubject(subject).setMessageType(MessageTypeEnum.HTML_EMAIL);
        builder.setText(text).setSender(sender).addRecipient(createRecipient("1")).addRecipient(createRecipient("2"));
        builder.addRecipient(createRecipient("3")).addCopyRecipient(createRecipient("4")).addCopyRecipient(createRecipient("5"));
        builder.addBlindRecipient(createRecipient("6")).addBlindRecipient(createRecipient("7")).addBlindRecipient(createRecipient("8"));
        builder.addBlindRecipient(createRecipient("9")).addAttachment(attachment);
        INotification notification = builder.createNotification();
        assertNotNull(notification);
        assertEquals(4, notification.blindRecipientSize());
        assertEquals(3, notification.recipientSize());
        assertEquals(2, notification.copyRecipientSize());
        IMessage message = notification.getMessage();
        assertEquals(1, message.getAttachments().size());
        assertEquals(attachment, message.getAttachments().iterator().next());
        assertEquals(text, message.getText());
        assertEquals(subject, message.getSubject());
        assertEquals(MessageTypeEnum.HTML_EMAIL, message.getMessageType());
    }

    @Test
    public void testBuilder() {
        INotificationBuilder builder = NotificationBuilder.getBuilder();
        assertNotNull(builder);
        String subject = "test subject";
        builder.setSubject(subject);
        builder.setMessageType(MessageTypeEnum.HTML_EMAIL);
        String text = "this is the text";
        builder.setText(text);
        ISender sender = createSender();
        builder.setSender(sender);
        builder.addRecipient(createRecipient("1"));
        builder.addRecipient(createRecipient("2"));
        builder.addRecipient(createRecipient("3"));
        builder.addCopyRecipient(createRecipient("4"));
        builder.addCopyRecipient(createRecipient("5"));
        builder.addBlindRecipient(createRecipient("6"));
        builder.addBlindRecipient(createRecipient("7"));
        builder.addBlindRecipient(createRecipient("8"));
        builder.addBlindRecipient(createRecipient("9"));
        String attachment = "attch1";
        builder.addAttachment(attachment);
        INotification notification = builder.createNotification();
        assertNotNull(notification);
        assertEquals(4, notification.blindRecipientSize());
        assertEquals(3, notification.recipientSize());
        assertEquals(2, notification.copyRecipientSize());
        IMessage message = notification.getMessage();
        assertEquals(1, message.getAttachments().size());
        assertEquals(attachment, message.getAttachments().iterator().next());
        assertEquals(text, message.getText());
        assertEquals(subject, message.getSubject());
        assertEquals(MessageTypeEnum.HTML_EMAIL, message.getMessageType());
    }

    @Test
    public void testReuseBuilder() {
        INotificationBuilder builder = NotificationBuilder.getBuilder();
        assertNotNull(builder);
        String subject = "test subject";
        builder.setSubject(subject);
        builder.setMessageType(MessageTypeEnum.TEXT_EMAIL);
        String text = "this is the text";
        builder.setText(text);
        ISender sender = createSender();
        builder.setSender(sender);
        builder.addRecipient(createRecipient("1"));
        builder.addRecipient(createRecipient("2"));
        builder.addRecipient(createRecipient("3"));
        builder.addRecipient(createRecipient("4"));
        builder.addCopyRecipient(createRecipient("1"));
        builder.addCopyRecipient(createRecipient("2"));
        builder.addBlindRecipient(createRecipient("1"));
        builder.addBlindRecipient(createRecipient("2"));
        builder.addBlindRecipient(createRecipient("3"));
        builder.addBlindRecipient(createRecipient("4"));
        String attachment = "attch1";
        builder.addAttachment(attachment);
        INotification notification = builder.createNotification();
        assertNotNull(notification);
        assertEquals(2, notification.copyRecipientSize());
        assertEquals(4, notification.blindRecipientSize());
        assertEquals(4, notification.recipientSize());
        IMessage message = notification.getMessage();
        assertEquals(1, message.getAttachments().size());
        assertEquals(attachment, message.getAttachments().iterator().next());
        assertEquals(text, message.getText());
        assertEquals(subject, message.getSubject());
        assertEquals(MessageTypeEnum.TEXT_EMAIL, message.getMessageType());

        builder.clearBlindRecipients();
        builder.clearCopyRecipients();
        builder.clearAttachments();
        builder.addRecipient(createRecipient("5"));
        builder.addRecipient(createRecipient("6"));
        builder.setMessageType(MessageTypeEnum.MULTIPART_EMAIL);
        INotification notification2 = builder.createNotification();
        assertNotNull(notification2);
        assertEquals(0, notification2.copyRecipientSize());
        assertEquals(0, notification2.blindRecipientSize());
        assertEquals(6, notification2.recipientSize());
        assertNotSame(notification, notification2);
        IMessage message2 = notification2.getMessage();
        assertEquals(0, message2.getAttachments().size());
        assertEquals(text, message2.getText());
        assertEquals(subject, message2.getSubject());
        assertEquals(MessageTypeEnum.MULTIPART_EMAIL, message2.getMessageType());
    }

    @Test
    public void testDefaultBuilder() {
        INotificationBuilder builder = NotificationBuilder.getBuilder();
        assertNotNull(builder);
        INotification notification = builder.createNotification();
        assertNotNull(notification);
        ISender sender = notification.getSender();
        assertNull(sender);
        assertNotNull(notification.getTemplate());
        assertTrue(notification.getRecipients().isEmpty());
        assertTrue(notification.getCopyRecipients().isEmpty());
        assertTrue(notification.getBlindCopyRecipients().isEmpty());
        INotificationJob job = notification.getJob();
        System.out.println(job);
        assertNull(job);
        IMessage message = notification.getMessage();
        assertNotNull(message);
        assertNull(message.getText());
        assertNull(message.getSubject());
        assertEquals(MessageTypeEnum.TEXT_EMAIL, message.getMessageType());
        assertTrue(message.isAttachmentEmpty());
    }


}
