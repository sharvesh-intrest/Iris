package com.volmit.iris.bukkit16;

import com.volmit.iris.core.Iris;
import com.volmit.iris.core.integration.*;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class IrisBukkit16 extends JavaPlugin {
    private IrisIntegration integration;

    public IrisBukkit16()
    {
        integration = new IrisIntegrationBukkit16(this);
    }

    public void onEnable()
    {
        Iris.integrate(integration);
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return null;
    }

    public void onDisable()
    {

    }
}
