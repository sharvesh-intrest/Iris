package com.volmit.iris.core.util;

import art.arcane.quill.collections.KMap;
import art.arcane.quill.collections.KSet;
import art.arcane.quill.execution.ChronoLatch;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadBiased {
    private static final KMap<Long, IrisCache> caches = new KMap<>();
    private static final KMap<Long, Thread> taggedThreads = new KMap<>();
    private static final ReentrantLock lock = new ReentrantLock(false);
    private static final ChronoLatch cl = new ChronoLatch(60000);

    public static void tick()
    {
        if(!cl.flip())
        {
            return;
        }

        lock.lock();
        for(long i : taggedThreads.k())
        {
            if(!taggedThreads.get(i).isAlive())
            {
                taggedThreads.remove(i);
                caches.remove(i);
            }
        }
        lock.unlock();
    }

    private static long tag()
    {
        Thread t = Thread.currentThread();
        long id = t.getId();
        taggedThreads.put(id, t);
        return id;
    }

    public static IrisCache cache()
    {
        lock.lock();
        IrisCache c = caches.compute(tag(), (k,v) -> v == null ? new IrisCache() : v);
        lock.unlock();
        return c;
    }
}
