/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.module.launcher.log4j2;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mule.tck.junit4.AbstractMuleTestCase;
import org.mule.tck.size.SmallTest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.selector.ContextSelector;
import org.apache.logging.log4j.message.MessageFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@SmallTest
@RunWith(MockitoJUnitRunner.class)
public class DispatchingLoggerTestCase extends AbstractMuleTestCase
{

    private static final String LOGGER_NAME = DispatchingLoggerTestCase.class.getName();
    private static final String MESSAGE = "Hello Log!";

    private ClassLoader currentClassLoader;

    @Mock
    private ClassLoader additionalClassLoader;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Logger originalLogger;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private LoggerContext loggerContext;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private ContextSelector contextSelector;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private MessageFactory messageFactory;

    private Logger logger;

    @Before
    public void before()
    {
        currentClassLoader = Thread.currentThread().getContextClassLoader();
        when(loggerContext.getConfiguration().getLoggerConfig(anyString()).getLevel()).thenReturn(Level.INFO);

        logger = new DispatchingLogger(originalLogger, currentClassLoader, loggerContext, contextSelector, messageFactory)
        {
            @Override
            public String getName()
            {
                return LOGGER_NAME;
            }
        };
    }

    @Test
    public void currentClassLoader()
    {
        logger.info(MESSAGE);
        verify(originalLogger).info(MESSAGE);
    }

    @Test
    public void otherClassLoader()
    {
        Thread.currentThread().setContextClassLoader(additionalClassLoader);
        try
        {
            logger.info(MESSAGE);
            verify(contextSelector).getContext(LOGGER_NAME, additionalClassLoader, true);
        }
        finally
        {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }
    }

}
