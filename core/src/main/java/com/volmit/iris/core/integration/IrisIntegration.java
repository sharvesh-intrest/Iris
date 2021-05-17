package com.volmit.iris.core.integration;

import art.arcane.quill.collections.KList;
import art.arcane.quill.format.Form;
import art.arcane.quill.tools.ExceptionTools;
import com.volmit.iris.core.data.IrisChunk;
import com.volmit.iris.core.ifs.IrisFileSystem;

import java.io.File;
import java.util.Iterator;
import java.util.Locale;

public abstract class IrisIntegration {
    private IntegratedDimension[] dimensions;
    private IntegratedBlock[] blocks;
    private IntegratedBiome[] biomes;
    private IntegrationCapability[] capabilities;
    private IrisFileSystem ifs;
    public static final String minecraft = "minecraft";

    public IrisIntegration()
    {
        i("Starting Iris " + getVersion() + " for " + getFlavor() + " (" + getPlatform() + ")");
        KList<IntegratedDimension> a = new KList<>();
        registerDimensions(a);
        this.dimensions = a.array();
        v("Integrated " + Form.plural(dimensions.length, "Dimension"));

        KList<IntegratedBlock> b = new KList<>();
        registerBlocks(b);
        this.blocks = b.array();
        v("Integrated " + Form.plural(blocks.length, "Block"));

        KList<IntegratedBiome> c = new KList<>();
        registerBiomes(c);
        this.biomes = c.array();
        v("Integrated " + Form.plural(biomes.length, "Biome"));

        KList<IntegrationCapability> d = new KList<>();
        registerCapabilities(d);
        this.capabilities = d.array();
        v("Integrated " + Form.plural(capabilities.length, "Capability", "Capabilities"));
        ifs = new IrisFileSystem(getIrisFolder());
    }

    public Iterator<IntegratedBlock> getBlocks(String search)
    {
        return new Iterator<IntegratedBlock>() {
            int c = 0;

            @Override
            public boolean hasNext() {
                return c < blocks.length-1;
            }

            private IntegratedBlock getNext()
            {
                return blocks[c++];
            }

            @Override
            public IntegratedBlock next() {
                IntegratedBlock b;

                //noinspection StatementWithEmptyBody
                while(!(b = getNext()).match(search))
                {
                    // Nope
                }

                return b;
            }
        };
    }

    public Iterator<IntegratedBlock> getBlocksForMod(String mod)
    {
        return new Iterator<IntegratedBlock>() {
            int c = 0;

            @Override
            public boolean hasNext() {
                return c < blocks.length-1;
            }

            private IntegratedBlock getNext()
            {
                return blocks[c++];
            }

            @Override
            public IntegratedBlock next() {
                IntegratedBlock b;

                //noinspection StatementWithEmptyBody
                while(!(b = getNext()).getMod().equalsIgnoreCase(mod))
                {
                    // Nope
                }

                return b;
            }
        };
    }

    public final String deenum(String e)
    {
        return Form.capitalizeWords(e.toLowerCase(Locale.ROOT).replaceAll("\\Q_\\E", " "));
    }

    public final String deenum(Enum<?> e)
    {
        return deenum(e.name());
    }

    public abstract boolean isValid();
    public abstract File getIrisFolder();
    public abstract IrisChunk createChunk(IntegratedDimension dimension, int x, int z, Object[] data);
    public abstract void registerDimensions(KList<IntegratedDimension> dimensions);
    public abstract void registerBlocks(KList<IntegratedBlock> blocks);
    public abstract void registerBiomes(KList<IntegratedBiome> biomes);
    public abstract void registerCapabilities(KList<IntegrationCapability> capabilities);
    public abstract void i(String msg);
    public abstract void f(String msg);
    public abstract void w(String msg);
    public abstract void v(String msg);
    public abstract String getVersion();
    public abstract String getFlavor();
    public abstract String getPlatform();
    public void ex(Throwable e)
    {
        for (String i : ExceptionTools.toStrings(e)) {
            f(i);
        }
    }

    public IntegratedDimension getDimensionFor(String environment) {
        for(IntegratedDimension d : dimensions)
        {
            if(d.getIdentifier().equalsIgnoreCase(environment))
            {
                return d;
            }
        }

        return dimensions[0];
    }

    public void tick()
    {

    }
}
