package com.github.mixpa;

import com.github.mixpa.region.Plot;
import com.github.mixpa.region.Road;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PlotWorldGenerator extends ChunkGenerator {
    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        List<BlockPopulator> list = new ArrayList<>();
        list.add(new RoadPopulator());
        return list;
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);
        //如果这个区块属于玩家设置的道路
        if (Utils.isRoad(x, z))
            return Road.getChunkDate(chunkData, biome);
        int randomInt = random.nextInt(99);
        for (Map.Entry<Plot, Integer> entry : Config.getPlotComponent().entrySet()) {
            if (entry.getValue() > randomInt) {
                return entry.getKey().resetChunkDate(chunkData, biome);
            }
        }
        return super.generateChunkData(world, random, x, z, biome);
    }
}
