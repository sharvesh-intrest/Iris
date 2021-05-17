package com.volmit.iris.bukkit16;

import art.arcane.quill.collections.KList;
import com.volmit.iris.core.data.IrisChunk;
import com.volmit.iris.core.integration.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.Locale;
import java.util.logging.Level;

public class IrisIntegrationBukkit16 extends IrisIntegration {
    private final Plugin plugin;

    public IrisIntegrationBukkit16(Plugin plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean isValid() {
        return plugin.isEnabled() && Bukkit.getPluginManager().getPlugin(plugin.getName()) != null;
    }

    @Override
    public File getIrisFolder() {
        return plugin.getDataFolder();
    }

    @Override
    public IrisChunk createChunk(IntegratedDimension dimension, int x, int z, Object[] data) {
        // Use Chunk Data override hack
        return new IrisChunk(x, z, dimension.getMaxHeight() >> 4);
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
                ItemMeta im = new ItemStack(i).getItemMeta();
                String displayName = im != null && im.hasLocalizedName() ? im.getLocalizedName() : deenum(i);
                blocks.add(IntegratedBlock.builder()
                        .mod(minecraft)
                        .displayName(displayName)
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
        capabilities.add(IntegrationCapability.BIOMES_3D);
        capabilities.add(IntegrationCapability.STUDIO_LINK);
    }

    @Override
    public void i(String msg) {
        plugin.getLogger().log(Level.INFO, msg);
    }

    @Override
    public void f(String msg) {
        plugin.getLogger().log(Level.SEVERE, msg);
    }

    @Override
    public void w(String msg) {
        plugin.getLogger().log(Level.WARNING, msg);
    }

    @Override
    public void v(String msg) {
        plugin.getLogger().log(Level.FINE, msg);
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String getFlavor() {
        return "Bukkit";
    }

    @Override
    public String getPlatform() {
        return Bukkit.getBukkitVersion();
    }
}
