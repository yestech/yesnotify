/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.objectmodel;


import org.yestech.notify.constant.StatusEnum;


/**
 * This is a Recipient of a {@link AggregateNotificationJob}.
 *
 */
@Deprecated
public class AggregateNotificationRecipient implements IAggregateNotificationRecipient
{

	private long mailJobId;
	private long userId;
	private String email;
	private StatusEnum status;
	private String attributes;  // this actually stores the name, but since the db table calls this an attribute we do here too

	/**
	 *
	 */
	public AggregateNotificationRecipient() {
		super();
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public long getNotificationJobId(){
		return mailJobId;
	}

	public long getUserId(){
		return userId;
	}

	public StatusEnum getStatus(){
		return status;
	}

	public String getAttributes(){
		return attributes;
	}

	/**
	 * @param attributes
	 */
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	/**
	 * @param notificationJobId
	 */
	public void setNotificationJobId(long notificationJobId) {
		this.mailJobId = notificationJobId;
	}

	/**
	 * @param status
	 */
	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	/**
	 * @param userId
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof IAggregateNotificationRecipient)) return false;

        AggregateNotificationRecipient that = (AggregateNotificationRecipient) o;

        if (mailJobId != that.mailJobId) return false;
        if (userId != that.userId) return false;
        if (attributes != null ? !attributes.equals(that.attributes) : that.attributes != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (status != that.status) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (mailJobId ^ (mailJobId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "AggregateNotificationRecipient{" +
               "mailJobId=" + mailJobId +
               ", userId=" + userId +
               ", email='" + email + '\'' +
               ", status=" + status +
               ", attributes='" + attributes + '\'' +
               '}';
    }

    /**
     * Factory method to create itself.
     *
     * @param mailJobId the {@link AggregateNotificationJob}
     * @param userId the User id
     * @param email The Email Address
     * @param status The {@link org.yestech.notify.constant.StatusEnum} of the Send
     * @param attributes The attributes saved.  This is usually the full name
     * @return
     */
    public static IAggregateNotificationRecipient create(long mailJobId, long userId, String email,
                                            StatusEnum status, String attributes) {
        AggregateNotificationRecipient recipient = new AggregateNotificationRecipient();
        recipient.mailJobId = mailJobId;
	    recipient.userId = userId;
	    recipient.email = email;
	    recipient.status = status;
	    recipient.attributes = attributes;

        return recipient;
    }

}
