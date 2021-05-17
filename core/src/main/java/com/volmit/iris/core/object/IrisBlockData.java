package com.volmit.iris.core.object;

import art.arcane.quill.collections.KMap;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@EqualsAndHashCode(callSuper = true)
@Data
public class IrisBlockData extends IrisBlock {
    private KMap<String, Object> data;

    public IrisBlockData()
    {
        this("minecraft", "air");
    }

    public IrisBlockData(String mod, String id)
    {
        super(mod, id);
        data = new KMap<>();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        super.write(dos);
        dos.writeShort(data.size());

        //TODO Durrrrrr....
    }

    @Override
    public void read(DataInputStream din) throws IOException {
        super.read(din);
    }
}
