/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.util;

import java.io.Serializable;

class ConcurrentCaseInsensitiveHashMapKey implements Serializable
{
    private static final long serialVersionUID = 1142069456763182432L;

    protected final String stringValue;

    public ConcurrentCaseInsensitiveHashMapKey(String stringValue)
    {
        this.stringValue = stringValue;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass())
        {
            return false;
        }

        return stringValue.equalsIgnoreCase(((ConcurrentCaseInsensitiveHashMapKey) obj).getValue());
    }

    @Override
    public int hashCode()
    {
        return stringValue.toLowerCase().hashCode();
    }

    @Override
    public String toString()
    {
        return stringValue;
    }

    public String getValue()
    {
        return stringValue;
    }
}
