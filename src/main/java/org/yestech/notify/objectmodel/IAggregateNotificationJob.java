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
package org.yestech.notify.objectmodel;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public interface IAggregateNotificationJob extends Serializable, Cloneable
{
    String getCountryCode();

    void setCountryCode(String countryCode);

    String getSenderName();

    void setSenderName(String senderName);

    DateTime getSent();

    String getStage();

    String getSender();

    String getSubject();

    String getPlainPart();

    String getHtmlPart();

    Collection<IRecipient> getRecipients();

    Collection<String> getFiles();

    void setStage(String stage);

    DateTime getCreated();

    long getId();

    DateTime getModified();

    void setCreated(DateTime created);

    void setFiles(Collection<String> files);

    void setHtmlPart(String htmlPart);

    void setId(long id);

    void setModified(DateTime modified);

    void setPlainPart(String plainPart);

    void setRecipients(Collection<IRecipient> recipients);

    void clearRecipients();

    void setSender(String sender);

    void setSent(DateTime sent);

    void setSubject(String subject);
}
