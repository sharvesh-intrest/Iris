package com.volmit.iris.bukkit12;

import com.volmit.iris.core.data.IrisBlock;
import com.volmit.iris.core.data.IrisChunk;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.material.MaterialData;

public class IrisChunkData12 extends IrisChunk {
    private ChunkGenerator.ChunkData chunkData;
    public IrisChunkData12(int x, int z, int maxSections, World world) {
        super(x, z, maxSections);
        chunkData = Bukkit.getServer().createChunkData(world);
    }

    @Override
    public void set(int x, int y, int z, IrisBlock block) {
        chunkData.setBlock(x&15, y, z&15, IrisBukkit12.blockConverter.to(block));
    }

    @Override
    public IrisBlock get(int x, int y, int z) {
        return IrisBukkit12.blockConverter.from(chunkData.getTypeAndData(x&15,y,z&15));
    }
}
