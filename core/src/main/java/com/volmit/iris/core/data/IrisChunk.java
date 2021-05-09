package com.volmit.iris.core.data;

import lombok.Data;

@Data
public class IrisChunk
{
    private final int x;
    private final int z;
    private final IrisChunkSection[] sections;

    public IrisChunk(int x, int z, int maxSections)
    {
        this.x = x;
        this.z = z;
        sections = new IrisChunkSection[maxSections];
    }

    public int maxY()
    {
        return sections.length * 16;
    }

    public void set(int x, int y, int z, IrisBlock block)
    {
        getOrCreateSection(y>>4).set(x&15, y&15, z&15, block);
    }

    public IrisBlock get(int x, int y, int z)
    {
        return getOrCreateSection(y>>4).get(x&15, y&15, z&15);
    }

    public IrisChunkSection getSection(int sectionY)
    {
       return sections[sectionY];
    }

    public IrisChunkSection getOrCreateSection(int sectionY) {
        IrisChunkSection section = getSection(sectionY);
        if (section == null)
        {
            int s = sectionY >> 4;
            section = new IrisChunkSection(x, s, z);
            sections[s] = section;
        }

        return section;
    }
}
