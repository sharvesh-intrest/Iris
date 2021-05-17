package com.volmit.iris.core.generator;

import com.volmit.iris.core.Iris;
import com.volmit.iris.core.data.IrisChunk;
import com.volmit.iris.core.object.IrisDimension;
import com.volmit.iris.core.integration.IntegratedDimension;
import com.volmit.iris.core.integration.IrisIntegration;

public class IrisGenerator {
    private IrisDimension dimension;
    private IrisIntegration integration;
    private IntegratedDimension integratedDimension;
    private int seed;

    public IrisGenerator(IrisDimension dimension, int seed)
    {
        this.seed = seed;
        this.dimension = dimension;
        integration = Iris.integration();
        integratedDimension = dimension.getIntegratedEnvironment();
    }

    public IrisChunk generateChunk(int x, int z, Object[] data)
    {
        IrisChunk chunk = integration.createChunk(integratedDimension, x, z, data);

        dimension.generate(chunk, seed);

        return chunk;
    }
}
