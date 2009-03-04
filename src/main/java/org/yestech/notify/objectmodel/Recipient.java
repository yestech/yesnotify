/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.objectmodel;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * Represents a Recipient of a Notification.
 *
 */
@XStreamAlias("recipient")
public class Recipient implements IRecipient
{

    @XStreamAsAttribute
    private String emailAddress;

    @XStreamAsAttribute
    private String displayName;

	public Recipient() {
		super();
	}

    /**
     * Return the Email Address
     *
     * @return
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Set the Email Address
     *
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Get the Name of the Recipient
     *
     * @return
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Set the Name of the Recipient.
     *
     * @param displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof IRecipient)) return false;

        Recipient recipient = (Recipient) o;

        if (displayName != null ? !displayName.equals(recipient.displayName) : recipient.displayName != null)
            return false;
        if (emailAddress != null ? !emailAddress.equals(recipient.emailAddress) : recipient.emailAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = emailAddress != null ? emailAddress.hashCode() : 0;
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Recipient{" +
               "emailAddress='" + emailAddress + '\'' +
               ", displayName='" + displayName + '\'' +
               '}';
    }

    public int compareTo(IRecipient o)
    {
        return emailAddress.compareTo(o.getEmailAddress());
    }
}




