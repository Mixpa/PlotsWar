package com.github.mixpa;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;

public class Utils {
    public static boolean isRoad(int chunkX, int chunkZ) {
        int addLength = Config.getRoadLength() + Config.getPlotLength();
        if (chunkX == 0 || chunkZ == 0)
            return true;
        if (Math.abs(chunkX) % addLength <= Config.getRoadLength() - 1)
            return true;
        else return Math.abs(chunkZ) % addLength <= Config.getRoadLength() - 1;
    }

    public static boolean isRoad(Chunk chunk) {
        return isRoad(chunk.getX(), chunk.getZ());
    }

    public static boolean isRoad(Block block) {
        return isRoad(block.getChunk());
    }

    public static boolean isWorld(World world) {
        return world.getGenerator() instanceof PlotWorldGenerator;
    }

    public static boolean isWorld(Player player) {
        return isWorld(player.getWorld());
    }

    public static void setBiome(ChunkGenerator.BiomeGrid biome, Biome type) {
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 256; y++) {
                for (int z = 0; z < 16; z++) {
                    biome.setBiome(x, y, z, type);
                }
            }
        }
    }
}
