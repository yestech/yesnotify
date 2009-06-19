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

import org.springframework.beans.factory.annotation.Required;
import org.terracotta.message.pipe.BlockingQueueBasedPipe;
import org.terracotta.message.topology.DefaultTopology;
import org.terracotta.workmanager.DistributedWorker;
import org.yestech.notify.objectmodel.INotificationJob;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * @author Artie Copeland
 * @version $Revision: $
 */
public class TerracottaNotificationWorker {

    private String queueName;
    private int queueSize = 1000;
    private DistributedWorker worker;

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public String getQueueName() {
        return queueName;
    }

    @Required
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    @PostConstruct
    public void init() throws Exception {
//        final DefaultTopology.Factory topologyFactory = new DefaultTopology.Factory(new BlockingQueueBasedPipe.Factory(queueSize));
//        final ExecutorService executorService = Executors.newSingleThreadExecutor();
//        DistributedWorker workerFactory = new DistributedWorker(queueName,topologyFactory, executorService);
//        worker = workerFactory.create();
        worker = new DistributedWorker(queueName);
        worker.start();
    }

    @PreDestroy
    public void destroy() {
        worker.stop();
    }
}