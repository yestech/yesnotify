/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.objectmodel;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The AggregateNotification Job that gets sent and loaded.
 *
 */
public class AggregateNotificationJob implements IAggregateNotificationJob
{
    private DateTime sent;
    private String stage;
    private String sender;
    private String senderName;
    private String subject;
    private String plainPart;
    private String htmlPart;
    private Collection<IRecipient> recipients;
    private Collection<String> files;
    private DateTime created;
    private DateTime modified;
    private String countryCode;

    private long id;

    // some constants that map to enumerations in the database, this is here because the NotificationJob class is central to the mailer

    public AggregateNotificationJob() {
        super();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public DateTime getSent() {
        return sent;
    }

    public String getStage() {
        return stage;
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getPlainPart() {
        return plainPart;
    }

    public String getHtmlPart() {
        return htmlPart;
    }

    public Collection<IRecipient> getRecipients() {
        return recipients;
    }

    public Collection<String> getFiles() {
        return files;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public DateTime getCreated() {
        return created;
    }

    public long getId() {
        return id;
    }

    public DateTime getModified() {
        return modified;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public void setFiles(Collection<String> files) {
        this.files = files;
    }

    public void setHtmlPart(String htmlPart) {
        this.htmlPart = htmlPart;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setModified(DateTime modified) {
        this.modified = modified;
    }

    public void setPlainPart(String plainPart) {
        this.plainPart = plainPart;
    }

    public void setRecipients(Collection<IRecipient> recipients) {
        if (recipients != null) {
            this.recipients = recipients;
        }
    }

    public void clearRecipients() {
        recipients = new ArrayList<IRecipient>();
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setSent(DateTime sent) {
        this.sent = sent;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof IAggregateNotificationJob)) return false;

        AggregateNotificationJob that = (AggregateNotificationJob) o;

        if (id != that.id) return false;
        if (countryCode != null ? !countryCode.equals(that.countryCode) : that.countryCode != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (files != null ? !files.equals(that.files) : that.files != null) return false;
        if (htmlPart != null ? !htmlPart.equals(that.htmlPart) : that.htmlPart != null) return false;
        if (modified != null ? !modified.equals(that.modified) : that.modified != null) return false;
        if (plainPart != null ? !plainPart.equals(that.plainPart) : that.plainPart != null) return false;
        if (recipients != null ? !recipients.equals(that.recipients) : that.recipients != null) return false;
        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        if (senderName != null ? !senderName.equals(that.senderName) : that.senderName != null) return false;
        if (sent != null ? !sent.equals(that.sent) : that.sent != null) return false;
        if (stage != null ? !stage.equals(that.stage) : that.stage != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = sent != null ? sent.hashCode() : 0;
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (senderName != null ? senderName.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (plainPart != null ? plainPart.hashCode() : 0);
        result = 31 * result + (htmlPart != null ? htmlPart.hashCode() : 0);
        result = 31 * result + (recipients != null ? recipients.hashCode() : 0);
        result = 31 * result + (files != null ? files.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }

    /**
     * Deep Clone.
     *
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
