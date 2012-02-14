/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.endpoint.outbound;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.endpoint.OutboundEndpoint;
import org.mule.processor.AbstractInterceptingMessageProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Propagates properties from request message to response message as defined by
 * {@link OutboundEndpoint#getResponseProperties()}.
 * <p>
 * //TODO This can became a standard MessageProcessor in the response chain if/when event has a (immutable)
 * reference to request message.
 */
public class OutboundResponsePropertiesMessageProcessor extends AbstractInterceptingMessageProcessor
{

    private OutboundEndpoint endpoint;
    private List<String> responsePropertyList;

    public OutboundResponsePropertiesMessageProcessor(OutboundEndpoint endpoint)
    {
        this.endpoint = endpoint;
        // Properties which should be carried over from the request message
        // to the response message
        responsePropertyList = endpoint.getResponseProperties();
    }

    public MuleEvent process(MuleEvent event) throws MuleException
    {
        Map<String, Object> responsePropertyValues = new HashMap<String, Object>();
        for (String propertyName : responsePropertyList)
        {
            Object propertyValue = event.getMessage().getInboundProperty(propertyName);
            if (propertyValue != null)
            {
                responsePropertyValues.put(propertyName, propertyValue);
            }
        }

        MuleEvent responseEvent = processNext(event);

        if (responseEvent != null)
        {
            for (Entry<String, Object> property : responsePropertyValues.entrySet())
            {
                responseEvent.getMessage().setOutboundProperty(property.getKey(), property.getValue());
            }
        }
        return responseEvent;
    }
}
