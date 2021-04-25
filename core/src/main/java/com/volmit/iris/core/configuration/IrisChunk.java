package com.volmit.iris.core.configuration;

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
