package com.volmit.iris.core;

import com.volmit.iris.core.generator.IrisDimensionGenerator;

public class Iris {
    private static IrisDimensionGenerator generator;

    public static void registerDimensionGenerator(IrisDimensionGenerator g)
    {
        generator = g;
    }

    public static IrisDimensionGenerator getDimensionGenerator()
    {
        return generator;
    }
}
