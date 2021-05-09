package com.volmit.iris.core.data;

import lombok.Data;

import java.util.Map;

@Data
public class IrisBlock {
    private static final String minecraft = "minecraft";
    private static final String air = "air";
    private final String mod;
    private final String id;

    public IrisBlock()
    {
        this(minecraft, air);
    }

    public IrisBlock(String mod, String id)
    {
        this.mod = mod;
        this.id = id;
    }

    public IrisBlock(String id)
    {
        this(minecraft, id);
    }

    public String toString()
    {
        return mod + ":" + id;
    }
}
