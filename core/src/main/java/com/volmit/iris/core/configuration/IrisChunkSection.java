package com.volmit.iris.core.configuration;

import art.arcane.quill.collections.hunk.storage.ArrayHunk;
import com.volmit.iris.core.configuration.IrisBlock;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class IrisChunkSection extends ArrayHunk<IrisBlock> {
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
}
