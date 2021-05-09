package com.volmit.iris.core.util;

import art.arcane.quill.collections.KMap;
import com.volmit.iris.core.Iris;

import java.util.function.Supplier;

public class IrisCache
{
    private final KMap<String, Object> t = new KMap<>();

    public IrisCache()
    {

    }

    public void purge(String key)
    {
        if(key == null)
        {
            Iris.fex("Cannot purge a cache with a null key!");
            return;
        }

        t.remove(key);
    }

    public void purgePrefix(String prefix)
    {
        if(prefix == null)
        {
            Iris.fex("Cannot purge a cache with a null key prefix!");
            return;
        }

        // TODO: There is a faster (less garbage intensive way) to do this
        for(String i : t.k())
        {
            if(i.startsWith(prefix))
            {
                purge(i);
            }
        }
    }

    public int size()
    {
        return t.size();
    }

    public void purge()
    {
        t.clear();
    }

    public <T> T get(String key, Supplier<T> def)
    {
        if(key == null)
        {
            Iris.fex("Cannot query a cache with a null key!");
            return null;
        }

        Object v = t.get(key);

        if(v == null)
        {
            T d = def.get();

            if(d == null)
            {
                Iris.fex("The cache value default is null for key '" + key + "'");
                return null;
            }

            t.put(key, d);
            return d;
        }

        try
        {
            return (T)v;
        }

        catch(Throwable e)
        {
            T d = def.get();

            if(d == null)
            {
                Iris.fex("The cache value default is null for key '" + key + "'");
                return null;
            }

            t.put(key, d);
            Iris.ex(e);
            Iris.f("Failed to convert cache key '" + key + "' from " + v.getClass().getCanonicalName() + " to " + d.getClass().getSimpleName() + "'. Returning the default definition!");
            return d;
        }
    }
}
