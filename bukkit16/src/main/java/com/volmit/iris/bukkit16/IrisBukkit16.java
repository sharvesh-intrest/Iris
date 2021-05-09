package com.volmit.iris.bukkit16;

import art.arcane.quill.collections.KList;
import art.arcane.quill.format.Form;
import com.volmit.iris.core.Iris;
import com.volmit.iris.core.integration.IntegratedBiome;
import com.volmit.iris.core.integration.IntegratedBlock;
import com.volmit.iris.core.integration.IntegratedDimension;
import com.volmit.iris.core.integration.IrisIntegration;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public class IrisBukkit16 extends JavaPlugin {
    public void onEnable()
    {
        Iris.integrate(new IrisIntegration() {
            @Override
            public KList<IntegratedDimension> getDimensions() {
                return new KList<IntegratedDimension>()
                        .qadd(IntegratedDimension.builder()
                                .dimensionId(0)
                                .displayName("Overworld")
                                .identifier(World.Environment.NORMAL.name())
                                .maxHeight(256)
                                .build())
                        .qadd(IntegratedDimension.builder()
                                .dimensionId(-1)
                                .displayName("Nether")
                                .identifier(World.Environment.NETHER.name())
                                .maxHeight(256)
                                .build())
                        .qadd(IntegratedDimension.builder()
                                .dimensionId(0)
                                .displayName("The End")
                                .identifier(World.Environment.THE_END.name())
                                .maxHeight(256)
                                .build());
            }

            @Override
            public KList<IntegratedBiome> getBiomes() {
                return KList.from(Biome.values()).convert((b) -> IntegratedBiome.builder()
                        .biomeId(b.ordinal())
                        .heightMultiplier(1)
                        .rainfall(0.5f)
                        .temperature(0.75f)
                        .displayName(Form.capitalizeWords(b.name().toLowerCase(Locale.ROOT).replaceAll("\\Q_\\E", " ")))
                        .identifier(b.name())
                        .build());
            }

            @Override
            public KList<IntegratedBlock> getBlocks() {
                return KList.from(Material.values()).convert((i) -> i.isBlock() ? IntegratedBlock.builder()
                        .id(i.getKey().getKey())
                        .mod("minecraft")
                        .build() : null);
            }
        });
    }

    public void onDisable()
    {

    }
}
