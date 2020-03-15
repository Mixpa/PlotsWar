package com.github.mixpa;

import com.github.mixpa.region.Plot;
import org.bukkit.*;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedHashMap;

public class PlotsWar extends JavaPlugin {
    @Override
    public void onEnable() {
        LinkedHashMap<Plot, Integer> plotComponent = new LinkedHashMap<>();
        LinkedHashMap<Material, Integer> blockComponent = new LinkedHashMap<>();
        blockComponent.put(Material.STONE, 100);
        plotComponent.put(new Plot("default", blockComponent), 100);
        Config.setPlotComponent(plotComponent);
        getLogger().info("插件启动了");
        WorldCreator wc = new WorldCreator("test");
        wc.generator(new PlotWorldGenerator());
        wc.environment(World.Environment.NORMAL);
        wc.type(WorldType.FLAT);
        wc.seed(2333);
        Bukkit.createWorld(wc);

    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new PlotWorldGenerator();
    }

}
