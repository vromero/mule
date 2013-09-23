/*
 * (c) 2003-2014 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */
package org.mule.module.logging;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ContainerLogHandlerThreadTestCase extends AbstractLogHandlerThreadTestCase
{

    public ContainerLogHandlerThreadTestCase(LoggerFactoryFactory loggerFactory, String logHandlerThreadName)
    {
        super(loggerFactory, logHandlerThreadName);
    }

    @Test
    public void doesNotStarsLogHandlerThreadOnContainerMode() throws Exception
    {
        loggerFactory.create();

        assertFalse("Created an unexpected LoggerReferenceHandler instance", createdLoggerReferenceHandler);
    }
}