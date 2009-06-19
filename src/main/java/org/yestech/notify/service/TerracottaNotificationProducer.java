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
package org.yestech.notify.service;

import static com.google.common.collect.Lists.newArrayList;
import commonj.work.WorkItem;
import org.springframework.beans.factory.annotation.Required;
import org.terracotta.message.routing.LoadBalancingRouter;
import org.terracotta.message.routing.Router;
import org.terracotta.workmanager.DistributedWorkManager;
import org.yestech.notify.objectmodel.INotificationJob;
import org.yestech.lib.xml.XmlUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Artie Copeland
 * @version $Revision: $
 */
public class TerracottaNotificationProducer implements INotificationProducer {

    private String queueName;
    private DistributedWorkManager workManager;
    private Router router = new LoadBalancingRouter();
    private int queueSize = 1000;
    private MemoryNotificationConsumer consumer;

//    private List<WorkItem> scheduledWorkItems;

    public MemoryNotificationConsumer getConsumer() {
        return consumer;
    }

    @Required
    public void setConsumer(MemoryNotificationConsumer consumer) {
        this.consumer = consumer;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    public String getQueueName() {
        return queueName;
    }

    @Required
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    @PostConstruct
    public void init() {
//        final DefaultTopology.Factory topologyFactory = new DefaultTopology.Factory(new BlockingQueueBasedPipe.Factory(queueSize));
//        workManager = new DistributedWorkManager(queueName, topologyFactory, router);
        workManager = new DistributedWorkManager(queueName, router);
//        scheduledWorkItems = newArrayList();
    }

    @PreDestroy
    public void destroy() {
//        workManager.removeCompletedWorks();
        workManager.clearCompletedWorks();
        workManager.shutdown();
//        Finally, for both the static and dynamic implementations, it is strongly suggested to:
//
//        Call removeCompletedWorks and clearCompletedWorks methods when you don't need to wait for work completion (or you already waited for and don't care anymore).
//        Call shutdown in your work manager instances after finishing to use them, as well as stop in the worker instances.

    }

    @Override
    public void send(INotificationJob notificationJob) {
        DefaultNotificationWork work = new DefaultNotificationWork();
        final String notificationXml = XmlUtils.toXml(notificationJob);

        work.setNotificationXml(notificationXml);
        work.setConsumer(consumer);
        WorkItem item = workManager.schedule(work);
    }

}
