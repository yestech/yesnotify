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
public interface IAttachment extends Serializable, Comparable<IAttachment>
{
    String getFileName();
}
