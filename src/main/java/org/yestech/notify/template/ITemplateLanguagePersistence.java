/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

/*
 *
 * Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.template;

import java.io.Serializable;

/**
 * Used to persist the internal of {@link ITemplateLanguage}
 *
 * @author Artie Copeland
 * @version $Revision: $
 */
public interface ITemplateLanguagePersistence<D extends Serializable> extends Serializable {

    /**
     * Set some data to use when applying a message
     *
     * @param data
     */
    public void setData(D data);

    /**
     * Return the data to use when applying a message
     * @return data to use when applying a message
     */
    public D getData();
}
