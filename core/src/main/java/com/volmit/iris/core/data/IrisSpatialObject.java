package com.volmit.iris.core.data;

import art.arcane.quill.collections.DataPalette;
import art.arcane.quill.collections.hunk.storage.AtomicIntegerHunk;
import com.volmit.iris.core.object.IrisBlock;
import com.volmit.iris.core.util.IrisMaps;

import java.util.Map;

public class IrisSpatialObject
{
    private int width;
    private int height;
    private int depth;
    private Map<IrisBlockVector, IrisBlock> blocks;

    public IrisSpatialObject(int width, int height, int depth)
    {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.blocks = IrisMaps.newFootprintMap();
    }
}
