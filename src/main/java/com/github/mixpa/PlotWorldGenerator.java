package com.github.mixpa;

import com.github.mixpa.region.Plot;
import com.github.mixpa.region.PlotArea;
import com.github.mixpa.region.Road;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.*;

public class PlotWorldGenerator extends ChunkGenerator {
    private static Map<Plot, Integer> plotComponent;
    private static List<PlotWorldGenerator> list = new ArrayList<>();
    private volatile Map<PlotArea, Plot> plotMap;

    public PlotWorldGenerator(String worldName) {
        plotMap = new HashMap<>();
        Map<Plot, Integer> configMap = Config.getPlotComponent();
        plotComponent = new LinkedHashMap<>();
        int chance = 0;
        for (Map.Entry<Plot, Integer> entry : configMap.entrySet()) {
            chance += entry.getValue();
            plotComponent.put(entry.getKey(), chance);
        }
        list.add(this);
    }

    public static List<PlotWorldGenerator> getList() {
        return list;
    }

    public Map<PlotArea, Plot> getPlotMap() {
        return plotMap;
    }

    public void setPlotMap(Map<PlotArea, Plot> plotMap) {
        this.plotMap = plotMap;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        List<BlockPopulator> list = new ArrayList<>();
        list.add(new RoadPopulator());
        return list;
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);
        //如果这个区块属于道路
        if (Utils.isRoad(x, z)) {
            if (!Utils.isRoad(x + 1, z + 1)) {
                chunkData.setBlock(15, Config.getWorldHeight() + 1, 15, Config.getRoadFrame());
            }
            if (!Utils.isRoad(x + 1, z - 1)) {
                chunkData.setBlock(15, Config.getWorldHeight() + 1, 0, Config.getRoadFrame());
            }
            if (!Utils.isRoad(x - 1, z - 1)) {
                chunkData.setBlock(0, Config.getWorldHeight() + 1, 0, Config.getRoadFrame());
            }
            if (!Utils.isRoad(x - 1, z + 1)) {
                chunkData.setBlock(0, Config.getWorldHeight() + 1, 15, Config.getRoadFrame());
            }
            return Road.getChunkDate(chunkData, biome);
        } else {//此区块属于玩家可以建筑占领的区块
            int randomInt = random.nextInt(99);
            //PlotArea代表了一整块玩家可以建筑占领的范围 就是被道路包围的一块地区
            PlotArea plotArea = new PlotArea(x, z, world);
            //如果map中没有输入过PlotArea
            if (!plotMap.containsKey(plotArea)) {
                for (Map.Entry<Plot, Integer> entry : plotComponent.entrySet()) {
                    if (entry.getValue() > randomInt) {
                        plotMap.put(plotArea, entry.getKey());
                        entry.getKey().resetChunk(chunkData);
                        break;
                    }
                }
                //如果map中已经有了PlotArea
            } else plotMap.get(plotArea).resetChunk(chunkData);
            return Plot.resetChunkDate(chunkData, biome);
        }
    }

    /**
     *
     * @param world 世界
     * @param random 随机生成器
     * @return 玩家诞生在世界的位置
     */
    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 8, Config.getWorldHeight() + 1, 8);
    }
}
