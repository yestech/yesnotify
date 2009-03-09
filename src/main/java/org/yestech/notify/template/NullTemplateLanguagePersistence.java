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
 * A null persistence
 *
 * @author Artie Copeland
 * @version $Revision: $
 */
public class NullTemplateLanguagePersistence implements ITemplateLanguagePersistence<String> {
    private static final long serialVersionUID = 4456953920956635949L;
    
    @Override
    public void setData(String data) {
    }

    @Override
    public String getData() {
        return null;
    }
}