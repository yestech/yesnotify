/*
 *
 * Original Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.objectmodel;

import org.yestech.notify.constant.StatusEnum;

import java.io.Serializable;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public interface IAggregateNotificationRecipient extends Serializable
{
    String getEmail();

    void setEmail(String email);

    long getNotificationJobId();

    long getUserId();

    StatusEnum getStatus();

    String getAttributes();

    void setAttributes(String attributes);

    void setNotificationJobId(long notificationJobId);

    void setStatus(StatusEnum status);

    void setUserId(long userId);
}
