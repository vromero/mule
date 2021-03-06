/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.jersey;

import static org.junit.Assert.assertEquals;

import org.mule.api.MuleMessage;
import org.mule.api.client.LocalMuleClient;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.transport.http.HttpConnector;
import org.mule.transport.http.HttpConstants;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;

public class MultipleExceptionMapperTestCase extends org.mule.tck.junit4.FunctionalTestCase
{
    @Rule
    public DynamicPort port = new DynamicPort("port");

    @Override
    protected String getConfigFile()
    {
        return "multiple-exception-mapper-config.xml";
    }

    @Test
    public void mapsToBeanBadRequestException() throws Exception
    {
        LocalMuleClient client = muleContext.getClient();

        Map<String, Object> props = new HashMap<String, Object>();
        props.put(HttpConnector.HTTP_METHOD_PROPERTY, HttpConstants.METHOD_GET);

        MuleMessage result = client.send("http://localhost:" + port.getNumber() + "/helloworld/throwBadRequestException", TEST_MESSAGE, props);

        assertEquals((Integer) HttpConstants.SC_BAD_REQUEST, result.getInboundProperty(HttpConnector.HTTP_STATUS_PROPERTY, 0));
    }


    @Test
    public void mapsToHelloWorldException() throws Exception
    {
        LocalMuleClient client = muleContext.getClient();

        Map<String, Object> props = new HashMap<String, Object>();
        props.put(HttpConnector.HTTP_METHOD_PROPERTY, HttpConstants.METHOD_GET);

        MuleMessage result = client.send("http://localhost:" + port.getNumber() + "/helloworld/throwException", TEST_MESSAGE, props);

        assertEquals((Integer) HttpConstants.SC_SERVICE_UNAVAILABLE, result.getInboundProperty(HttpConnector.HTTP_STATUS_PROPERTY, 0));
    }
}
