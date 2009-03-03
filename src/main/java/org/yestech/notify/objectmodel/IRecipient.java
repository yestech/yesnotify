/*
 *
 * Original Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.objectmodel;

import java.io.Serializable;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public interface IRecipient extends Serializable, Comparable<IRecipient>
{
    String getEmailAddress();

    void setEmailAddress(String emailAddress);

    String getDisplayName();

    void setDisplayName(String displayName);
}
