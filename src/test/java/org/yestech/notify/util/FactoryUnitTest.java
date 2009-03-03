/*
 *
 * Original Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.util;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import org.yestech.notify.objectmodel.ISender;
import org.yestech.notify.objectmodel.IRecipient;
import static org.yestech.notify.util.Factory.createSender;
import static org.yestech.notify.util.Factory.createRecipient;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public class FactoryUnitTest {
    @Test
    public void testCreateSender() {
        ISender sender = createSender();
        assertNotNull(sender);
    }

    @Test
    public void testCreateRecipient() {
        IRecipient recipient = createRecipient();
        assertNotNull(recipient);
    }
}
