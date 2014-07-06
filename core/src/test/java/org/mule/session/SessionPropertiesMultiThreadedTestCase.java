/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.session;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.mule.api.MuleSession;
import org.mule.tck.junit4.AbstractMuleContextTestCase;

import java.io.IOException;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class SessionPropertiesMultiThreadedTestCase extends AbstractMuleContextTestCase
{

    /**
     * When invoking a Flow directly session properties are preserved
     */
    @Test
    public void copySessionWhileConcurrentModification() throws Exception
    {
        final MuleSession session = new DefaultMuleSession();

        session.setProperty("any", "thing");

        ExecutorService pool = concurrentSessionModifier(session, 3);
        new DefaultMuleSession(session);

        pool.shutdownNow();
    }

    private ExecutorService concurrentSessionModifier(final MuleSession session, int numberThreads) throws IOException, ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(numberThreads);

        Callable<Void> modifierCallable = new Callable<Void>()
        {
            @Override
            public Void call() throws Exception
            {
                while(true)
                {
                    session.setProperty(Thread.currentThread().getName(), StringUtils.EMPTY);
                    session.removeProperty(Thread.currentThread().getName());
                }
            }
        };

        for(int i=0; i<numberThreads; i++)
        {
            pool.submit(modifierCallable);
        }

        return pool;
    }

}
