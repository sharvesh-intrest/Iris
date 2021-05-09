package com.volmit.iris.core;

import art.arcane.quill.logging.L;
import com.volmit.iris.core.generator.IrisDimensionGenerator;
import com.volmit.iris.core.integration.IrisIntegration;

public class Iris {
    private static IrisIntegration integration;
    private static IrisDimensionGenerator generator;

    public static void integrate(IrisIntegration i)
    {
        integration = i;
    }

    public static IrisIntegration integration(){
        return integration;
    }

    public static void registerDimensionGenerator(IrisDimensionGenerator g)
    {
        generator = g;
    }

    public static IrisDimensionGenerator getDimensionGenerator()
    {
        return generator;
    }
}
