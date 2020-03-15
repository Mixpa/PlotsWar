package com.github.mixpa.region;

import com.github.mixpa.Config;
import com.github.mixpa.Utils;
import org.bukkit.generator.ChunkGenerator;

public class Road {
    public static ChunkGenerator.ChunkData getChunkDate(ChunkGenerator.ChunkData chunkData, ChunkGenerator.BiomeGrid biome) {
        //基岩
        chunkData.setRegion(0, 0, 0, 16, 1, 16, Config.getRoadLowest());
        //石头
        chunkData.setRegion(0, 1, 0, 16, Config.getWorldHeight(), 16, Config.getRoadBody());
        //砖头
        chunkData.setRegion(0, Config.getWorldHeight(), 0, 16, Config.getWorldHeight() + 1, 16, Config.getRoadFloor());
        //设置整个区块为虚空
        Utils.setBiome(biome, Config.getRoadBiome());
        return chunkData;
    }
}
