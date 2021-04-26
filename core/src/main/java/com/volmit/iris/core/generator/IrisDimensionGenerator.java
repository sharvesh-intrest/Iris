package com.volmit.iris.core.generator;

import com.volmit.iris.core.cache.IrisCache;
import com.volmit.iris.core.configuration.IrisDimension;
import com.volmit.iris.core.data.IrisChunk;

public interface IrisDimensionGenerator {
    IrisChunk generateChunk(IrisDimension dimension, IrisCache cache, int x, int z, int maxHeight);
}
