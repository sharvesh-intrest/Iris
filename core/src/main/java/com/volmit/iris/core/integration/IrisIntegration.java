package com.volmit.iris.core.integration;

import art.arcane.quill.collections.KList;
import com.volmit.iris.core.configuration.IrisBlock;
import com.volmit.iris.core.configuration.IrisDimension;

import java.util.Iterator;

public interface IrisIntegration {
    KList<IntegratedDimension> getDimensions();

    KList<IntegratedBiome> getBiomes();

    KList<IntegratedBlock> getBlocks();
}
