package com.volmit.iris.core.util;

import io.timeandspace.smoothie.OptimizationObjective;
import io.timeandspace.smoothie.SmoothieMap;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IrisMaps {
    /**
     * This map is thread safe
     * This map reduces memory usage per key by 4x
     * This map is slower
     * @param <A> the key
     * @param <B> the value
     * @return the map
     */
    public static <A, B> Map<A, B> newFootprintMap()
    {
        return Collections.synchronizedMap(SmoothieMap.<A, B>newBuilder()
                .optimizeFor(OptimizationObjective.FOOTPRINT)
                .build());
    }

    /**
     * This map is thread safe
     * This map reduces memory usage per key by almost 4x
     * This map reduces garbage production (less heavy gc)
     * This map is slower
     * @param <A> the key
     * @param <B> the value
     * @return the map
     */
    public static <A, B> Map<A, B> newGarbageMap()
    {
        return Collections.synchronizedMap(SmoothieMap.<A, B>newBuilder()
                .optimizeFor(OptimizationObjective.LOW_GARBAGE)
                .build());
    }

    /**
     * This map is thread safe but tons of writes can cause incorrect data (bad for intense caching)
     * This map is memory intensive
     * This map is incredibly fast. Up to 16 threads can write to different bins simultaneously.
     * @param <A> the key
     * @param <B> the value
     * @return the map
     */
    public static <A, B> Map<A, B> newFastMap()
    {
        return new ConcurrentHashMap<>();
    }
}
