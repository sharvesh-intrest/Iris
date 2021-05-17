package com.volmit.iris.core.object;

import com.volmit.iris.core.ifs.Binary;
import com.volmit.iris.core.ifs.IFSNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@EqualsAndHashCode(callSuper = true)
@Data
public class IrisBlock extends IFSNode implements Binary {
    private static final String minecraft = "minecraft";
    private static final String air = "air";
    private String mod;
    private String id;

    public IrisBlock()
    {
        this(minecraft, air);
    }

    @Override
    public Iterable<? extends IFSNode> getDependencies() {
        return IFSNode.NONE;
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

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeUTF(mod);
        dos.writeUTF(id);
    }

    @Override
    public void read(DataInputStream din) throws IOException {
        mod = din.readUTF();
        id = din.readUTF();
    }
}
