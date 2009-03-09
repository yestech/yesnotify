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

/**
 * Represents the sender of a {@link INotification}.
 */
@XStreamAlias("sender")
public class Sender implements ISender
{
    @XStreamAsAttribute
    private String emailAddress;

    @XStreamAsAttribute
    private String replyAddress;

    @XStreamAsAttribute
    private String displayName;

    public Sender()
    {
        super();
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getReplyAddress()
    {
        return replyAddress;
    }

    public void setReplyAddress(String replyAddress)
    {
        this.replyAddress = replyAddress;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ISender)) return false;

        Sender sender = (Sender) o;

        if (displayName != null ? !displayName.equals(sender.displayName) : sender.displayName != null) return false;
        if (emailAddress != null ? !emailAddress.equals(sender.emailAddress) : sender.emailAddress != null)
            return false;
        if (replyAddress != null ? !replyAddress.equals(sender.replyAddress) : sender.replyAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = emailAddress != null ? emailAddress.hashCode() : 0;
        result = 31 * result + (replyAddress != null ? replyAddress.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        return result;
    }

    public int compareTo(ISender o)
    {
        return emailAddress.compareTo(o.getEmailAddress());
    }

    @Override
    public String toString() {
        return "Sender{" +
                "emailAddress='" + emailAddress + '\'' +
                ", replyAddress='" + replyAddress + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
