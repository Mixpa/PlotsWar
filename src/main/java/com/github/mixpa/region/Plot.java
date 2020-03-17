package com.github.mixpa.region;

import com.github.mixpa.Config;
import com.github.mixpa.Utils;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Plot {
    private String name;
    private Map<Material, Integer> componentMap;
    private LinkedHashMap<Material, Integer> randomMap;

    public Plot(String name, Map<Material, Integer> componentMap) {
        this.name = name;
        this.componentMap = componentMap;
        randomMap = new LinkedHashMap<>();
        int chance = 0;
        for (Map.Entry<Material, Integer> entry : componentMap.entrySet()) {
            chance += entry.getValue();
            randomMap.put(entry.getKey(), chance);
        }
        if (chance != 100) {
            randomMap.put(Material.AIR, 100 - chance);
        }
    }

    public String getName() {
        return name;
    }

    public Map<Material, Integer> getComponentMap() {
        return componentMap;
    }

    public void resetChunk(Chunk chunk) {
        resetPlot(chunk);
    }
    public void resetChunk(ChunkGenerator.ChunkData chunk) {
        resetPlot(chunk);
    }

    public static ChunkGenerator.ChunkData resetChunkDate(ChunkGenerator.ChunkData chunkData, ChunkGenerator.BiomeGrid biome) {
        //设置整个区块为平原
        Utils.setBiome(biome, Config.getPlotBiome());
        //基岩
        chunkData.setRegion(0, 0, 0, 16, 1, 16, Material.BEDROCK);
        return chunkData;
    }

    private <A> void resetPlot(A chunkDef) {
        if (!(chunkDef instanceof Chunk || chunkDef instanceof ChunkGenerator.ChunkData))
            throw new IllegalArgumentException("转换类型必须是Chunk或者ChunkDate!");
        Random random = new Random();
        int randomInt;
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 1; y <= Config.getWorldHeight(); y++) {
                    randomInt = random.nextInt(99);
                    for (Map.Entry<Material, Integer> entry : randomMap.entrySet()) {
                        if (entry.getValue() > randomInt) {
                            if (chunkDef instanceof Chunk)
                                ((Chunk) chunkDef).getBlock(x, y, z).setType(entry.getKey());
                            if (chunkDef instanceof ChunkGenerator.ChunkData)
                                ((ChunkGenerator.ChunkData) chunkDef).setBlock(x, y, z, entry.getKey());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plot plot = (Plot) o;
        return Objects.equals(name, plot.name) &&
                Objects.equals(componentMap, plot.componentMap) &&
                Objects.equals(randomMap, plot.randomMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, componentMap, randomMap);
    }
}
