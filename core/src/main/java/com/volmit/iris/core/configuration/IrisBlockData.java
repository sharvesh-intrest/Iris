package com.volmit.iris.core;

import art.arcane.quill.collections.KMap;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class IrisBlockData extends IrisBlock {
    private final KMap<String, Object> data;

    public IrisBlockData()
    {
        this("minecraft", "air");
    }

    public IrisBlockData(String mod, String id)
    {
        super(mod, id);
        data = new KMap<>();
    }
}
