package com.github.mixpa;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

class RoadPopulator extends BlockPopulator {
    private static int addLength = Config.getPlotLength() + Config.getRoadLength();

    private static void setZBrickX15(Chunk chunk) {
        for (int z = 0; z < 16; z++) {
            chunk.getBlock(15, Config.getWorldHeight() + 1, z).setType(Config.getRoadFrame());
        }
    }

    private static void setZBrickX0(Chunk chunk) {
        for (int z = 0; z < 16; z++) {
            chunk.getBlock(0, Config.getWorldHeight() + 1, z).setType(Config.getRoadFrame());
        }
    }

    private static void setXBrickZ15(Chunk chunk) {
        for (int x = 0; x < 16; x++) {
            chunk.getBlock(x, Config.getWorldHeight() + 1, 15).setType(Config.getRoadFrame());
        }
    }

    private static void setXBrickZ0(Chunk chunk) {
        for (int x = 0; x < 16; x++) {
            chunk.getBlock(x, Config.getWorldHeight() + 1, 0).setType(Config.getRoadFrame());
        }
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        int x = chunk.getX();
        int z = chunk.getZ();
        if (Utils.isRoad(chunk)) {
            if (Math.abs(x) % addLength > Config.getRoadLength() - 1) {
                if (!Utils.isRoad(x, z + 1)){
                    setXBrickZ15(chunk);
                }
                if (!Utils.isRoad(x, z - 1)) {
                    setXBrickZ0(chunk);
                }
            }
            if (Math.abs(z) % addLength > Config.getRoadLength() - 1) {
                if (!Utils.isRoad(x + 1, z)){
                    setZBrickX15(chunk);
                }
                if (!Utils.isRoad(x - 1, z)){
                    setZBrickX0(chunk);
                }
            }
        }
    }
}