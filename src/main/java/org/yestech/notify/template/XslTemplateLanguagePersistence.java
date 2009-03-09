/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.template;

import org.springframework.beans.factory.annotation.Required;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Used to persist the internal of XSL based {@link ITemplateLanguage}
 *
 */
final public class XslTemplateLanguagePersistence implements ITemplateLanguagePersistence<Serializable> {

    private Serializable data;
    private String filePath;

    public XslTemplateLanguagePersistence() {
        super();
    }

    public Serializable getData() {
        return data;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setData(Serializable data) {
        this.data = data;
    }

    @Required
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
