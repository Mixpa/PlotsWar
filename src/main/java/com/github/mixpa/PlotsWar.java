package com.github.mixpa;

import com.github.mixpa.region.Plot;
import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedHashMap;


public class PlotsWar extends JavaPlugin {
    @Override
    public void onEnable() {
        LinkedHashMap<Plot, Integer> plotComponent = new LinkedHashMap<>();
        LinkedHashMap<Material, Integer> blockComponent0 = new LinkedHashMap<>();
        blockComponent0.put(Material.STONE, 100);
        LinkedHashMap<Material, Integer> blockComponent1 = new LinkedHashMap<>();
        blockComponent1.put(Material.DIAMOND_BLOCK, 100);
        plotComponent.put(new Plot("default", blockComponent0), 50);
        plotComponent.put(new Plot("dim", blockComponent1), 50);
        Config.setPlotComponent(plotComponent);
        getLogger().info("插件已经启动");
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new PlotWorldGenerator(worldName);
    }

}
