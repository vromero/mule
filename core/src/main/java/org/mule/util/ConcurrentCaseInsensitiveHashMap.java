/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.util;

import org.apache.commons.collections.Transformer;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCaseInsensitiveHashMap<K extends String, V> implements Map<K, V>, Serializable
{
    private static final long serialVersionUID = 4242069146763182391L;

    protected final ConcurrentHashMap<ConcurrentCaseInsensitiveHashMapKey, V> backingMap;

    transient protected KeyToStringTransformer keyToStringTransformer = new KeyToStringTransformer();

    /**
     * @see ConcurrentHashMap#ConcurrentHashMap()
     */
    public ConcurrentCaseInsensitiveHashMap(int initialCapacity, float loadFactor, int concurrencyLevel)
    {
        backingMap = new ConcurrentHashMap<>(initialCapacity, loadFactor, concurrencyLevel);
    }

    /**
     * @see ConcurrentCaseInsensitiveHashMap#ConcurrentCaseInsensitiveHashMap(int, float)
     */
    public ConcurrentCaseInsensitiveHashMap(int initialCapacity, float loadFactor)
    {
        backingMap = new ConcurrentHashMap<>(initialCapacity, loadFactor);
    }


    /**
     * @see ConcurrentCaseInsensitiveHashMap#ConcurrentCaseInsensitiveHashMap(int)
     */
    public ConcurrentCaseInsensitiveHashMap(int initialCapacity)
    {
        backingMap = new ConcurrentHashMap<>(initialCapacity);
    }

    /**
     * @see ConcurrentCaseInsensitiveHashMap#ConcurrentCaseInsensitiveHashMap()
     */
    public ConcurrentCaseInsensitiveHashMap()
    {
        backingMap = new ConcurrentHashMap<>();
    }

    /**
     * @see ConcurrentCaseInsensitiveHashMap#size()
     */
    @Override
    public int size()
    {
        return backingMap.size();
    }

    /**
     * @see ConcurrentCaseInsensitiveHashMap#isEmpty()
     */
    @Override
    public boolean isEmpty()
    {
        return backingMap.isEmpty();
    }

    /**
     * @see ConcurrentCaseInsensitiveHashMap#containsKey(Object)
     */
    @Override
    public boolean containsKey(Object key)
    {
        return backingMap.containsKey(((K) key));
    }

    /**
     * @see ConcurrentCaseInsensitiveHashMap#containsValue(Object)
     */
    @Override
    public boolean containsValue(Object value)
    {
        return backingMap.containsKey(value);
    }

    /**
     * @see ConcurrentCaseInsensitiveHashMap#get(Object)
     */
    @Override
    public V get(Object key)
    {
        return backingMap.get(new ConcurrentCaseInsensitiveHashMapKey((String) key));
    }

    /**
     * @see ConcurrentCaseInsensitiveHashMap#put(K, V)
     */
    @Override
    public V put(K key, V value)
    {
        return backingMap.put(new ConcurrentCaseInsensitiveHashMapKey((String) key), value);
    }

    /**
     * @see ConcurrentCaseInsensitiveHashMap#remove(Object)
     */
    @Override
    public V remove(Object key) {
        return backingMap.remove(new ConcurrentCaseInsensitiveHashMapKey((String)key));
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if (m instanceof ConcurrentCaseInsensitiveHashMap)
        {
            backingMap.putAll(((ConcurrentCaseInsensitiveHashMap) m).backingMap);
        }
        else
        {
            Iterator<? extends Entry<? extends K, ? extends V>> iterator = m.entrySet().iterator();

            while (iterator.hasNext())
            {
                Entry<? extends K, ? extends V> element = iterator.next();
                backingMap.put(new ConcurrentCaseInsensitiveHashMapKey(element.getKey()), element.getValue());
            }
        }
    }

    /**
     * @see ConcurrentCaseInsensitiveHashMap#clear()
     */
    @Override
    public void clear()
    {
        backingMap.clear();
    }

    /**
     * @see ConcurrentCaseInsensitiveHashMap#keySet()
     */
    @Override
    public Set<K> keySet()
    {
        // TODO ASDFASDFASDF
        return (Set) new KeySet(backingMap.keySet());
    }

    /**
     * @see ConcurrentCaseInsensitiveHashMap#values()
     */
    @Override
    public Collection<V> values()
    {
        // TODO ASDFASDFASDF
        return backingMap.values();
    }

    /**
     * @see ConcurrentCaseInsensitiveHashMap#entrySet()
     */
    @Override
    public Set<Entry<K, V>> entrySet()
    {
        return (Set) new EntrySet(backingMap.entrySet());
    }

    protected class KeyToStringTransformer implements Transformer
    {
        @Override
        public Object transform(Object input)
        {
            return ((ConcurrentCaseInsensitiveHashMapKey)input).getValue();
        }
    }



    protected class KeyIterator extends AbstractBackedIterator
    {
        public KeyIterator(Set keys)
        {
            super(keys);
        }

        @Override
        public final String next()
        {
            return (String) keyToStringTransformer.transform(backingIterator.next());
        }
    }


    abstract protected class AbstractBackedIterator implements Iterator
    {
        Iterator backingIterator;

        public AbstractBackedIterator(Set keys)
        {
            backingIterator = keys.iterator();
        }

        @Override
        public boolean hasNext()
        {
            return backingIterator.hasNext();
        }

        @Override
        public final void remove()
        {
            backingIterator.remove();
        }
    }

    abstract protected class AbstractBackedSet<T> extends AbstractSet<T>
    {
        Set backingSet;

        public AbstractBackedSet(Set keys)
        {
            this.backingSet = keys;
        }

        @Override
        public int size()
        {
            return backingSet.size();
        }

        @Override
        public boolean isEmpty()
        {
            return backingSet.isEmpty();
        }

        @Override
        public boolean contains(Object o)
        {
            return backingSet.contains(o);
        }

        @Override
        public boolean remove(Object o)
        {
            return backingSet.remove(o);
        }

        @Override
        public void clear()
        {
            backingSet.clear();
        }
    }

    protected class KeySet extends AbstractBackedSet<String>
    {
        public KeySet(Set keys)
        {
            super(keys);
        }

        @Override
        public Iterator<String> iterator()
        {
            return new KeyIterator(backingSet);
        }
    }

    protected class EntrySetIterator<String, V> extends AbstractBackedIterator
    {
        public EntrySetIterator(Set keys)
        {
            super(keys);
        }

        @Override
        public final Entry<String, V> next()
        {
            return (Entry<String, V>) insensitiveToSensitiveEntry((Entry) backingIterator.next());
        }

        private Object insensitiveToSensitiveEntry(Entry input)
        {
            ConcurrentCaseInsensitiveHashMapKey key = (ConcurrentCaseInsensitiveHashMapKey) input.getKey();
            return new AbstractMap.SimpleEntry<>(key.getValue(), input.getValue());
        }
    }

    protected class EntrySet extends AbstractBackedSet<Entry<String, V>>
    {
        public EntrySet(Set keys)
        {
            super(keys);
        }

        @Override
        public Iterator<Entry<String, V>> iterator()
        {
            return new EntrySetIterator(backingSet);
        }
    }

}
