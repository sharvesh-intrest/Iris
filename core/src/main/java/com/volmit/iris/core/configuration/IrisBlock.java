package com.volmit.iris.core.configuration;

import lombok.Data;

import java.util.Map;

@Data
public class IrisBlock {
    private final String mod;
    private final String id;

    public IrisBlock()
    {
        this("minecraft", "air");
    }

    public IrisBlock(String mod, String id)
    {
        this.mod = mod;
        this.id = id;
    }

    public String toString()
    {
        return mod + ":" + id;
    }
}
