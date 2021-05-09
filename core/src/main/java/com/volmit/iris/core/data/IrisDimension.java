package com.volmit.iris.core.data;

import art.arcane.quill.collections.KList;
import com.volmit.iris.core.Iris;
import com.volmit.iris.core.integration.IntegratedDimension;
import lombok.Data;

@Data
public class IrisDimension
{
    private KList<IrisBiome> biomes;
    private String environment;

    public IntegratedDimension getIntegratedEnvironment()
    {
        return Iris.integration().getDimensionFor(environment);
    }

    public void generate(IrisChunk chunk, int seed) {
        for(int i = 0; i < 16; i++)
        {
            for(int j = 0; j < 16; j++)
            {
                chunk.set(i, 0, j, new IrisBlock("bedrock"));
            }
        }
    }
}
