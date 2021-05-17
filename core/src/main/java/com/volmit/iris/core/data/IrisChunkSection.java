package com.volmit.iris.core.data;

import art.arcane.quill.collections.hunk.storage.ArrayHunk;
import com.volmit.iris.core.ifs.Binary;
import com.volmit.iris.core.object.IrisBlock;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@EqualsAndHashCode(callSuper = true)
@Data
public class IrisChunkSection extends ArrayHunk<IrisBlock> implements Binary {
    private final int x;
    private final int y;
    private final int z;

    public IrisChunkSection(int x, int y, int z)
    {
        super(16, 16, 16);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {

    }

    @Override
    public void read(DataInputStream din) throws IOException {

    }
}
