package com.volmit.iris.bukkit12;

import art.arcane.quill.collections.KList;
import com.volmit.iris.core.Iris;
import com.volmit.iris.core.data.IrisBlock;
import com.volmit.iris.core.data.IrisChunk;
import com.volmit.iris.core.generator.IrisGenerator;
import com.volmit.iris.core.integration.*;
import com.volmit.iris.core.util.IrisConverter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;

public class IrisBukkit12 extends JavaPlugin {
    public static final IrisConverter<IrisBlock, MaterialData> blockConverter = new IrisConverter<IrisBlock, MaterialData>() {
        @Override
        public MaterialData to(IrisBlock b) {
            return new MaterialData(Material.valueOf(b.getId()));
        }

        @Override
        public IrisBlock from(MaterialData materialData) {
            return new IrisBlock(materialData.getItemType().name());
        }
    };

    public void onEnable()
    {
        Iris.integrate(new IrisIntegration() {
            @Override
            public IrisChunk createChunk(IntegratedDimension dimension, int x, int z, Object[] data) {
                // Use Chunk Data override hack
                return new IrisChunkData12(x, z, dimension.getMaxHeight()>>4, (World) data[0]);
            }

            @Override
            public void registerDimensions(KList<IntegratedDimension> dimensions) {
                dimensions.add(IntegratedDimension.builder()
                       .dimensionId(0)
                       .displayName("Overworld")
                       .identifier(World.Environment.NORMAL.name())
                       .maxHeight(256)
                       .build());
                dimensions.add(IntegratedDimension.builder()
                        .dimensionId(-1)
                        .displayName("Nether")
                        .identifier(World.Environment.NETHER.name())
                        .maxHeight(256)
                        .build());
                dimensions.add(IntegratedDimension.builder()
                        .dimensionId(0)
                        .displayName("The End")
                        .identifier(World.Environment.THE_END.name())
                        .maxHeight(256)
                        .build());
            }

            @Override
            public void registerBlocks(KList<IntegratedBlock> blocks) {
                for(Material i : Material.values())
                {
                    if(i.isBlock())
                    {
                        blocks.add(IntegratedBlock.builder()
                                .mod(minecraft)
                                .displayName(deenum(i))
                                .id(i.name().toLowerCase(Locale.ROOT))
                                .build());
                    }
                }
            }

            @Override
            public void registerBiomes(KList<IntegratedBiome> biomes) {
                for(Biome i : Biome.values())
                {
                    biomes.add(IntegratedBiome.builder()
                            .temperature(0.75f)
                            .biomeId(i.ordinal())
                            .rainfall(0.65f)
                            .identifier(i.name())
                            .displayName(deenum(i))
                            .heightMultiplier(1)
                            .build());
                }
            }

            @Override
            public void registerCapabilities(KList<IntegrationCapability> capabilities) {
                capabilities.add(IntegrationCapability.STUDIO_LINK);
            }

            @Override
            public void i(String msg) {
                getLogger().log(Level.INFO, msg);
            }

            @Override
            public void f(String msg) {
                getLogger().log(Level.SEVERE, msg);
            }

            @Override
            public void w(String msg) {
                getLogger().log(Level.WARNING, msg);
            }

            @Override
            public void v(String msg) {
                getLogger().log(Level.FINE, msg);
            }

            @Override
            public String getVersion() {
                return getDescription().getVersion();
            }

            @Override
            public String getFlavor() {
                return "Bukkit";
            }

            @Override
            public String getPlatform() {
                return Bukkit.getBukkitVersion();
            }
        });
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new ChunkGenerator() {

            @Override
            public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
                return super.generateChunkData(world, random, x, z, biome);
            }

            @Override
            public boolean canSpawn(World world, int x, int z) {
                return super.canSpawn(world, x, z);
            }

            @Override
            public List<BlockPopulator> getDefaultPopulators(World world) {
                return super.getDefaultPopulators(world);
            }

            @Override
            public Location getFixedSpawnLocation(World world, Random random) {
                return super.getFixedSpawnLocation(world, random);
            }
        };
    }

    public void onDisable()
    {

    }
}
