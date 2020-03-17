package com.github.mixpa;

import com.github.mixpa.region.Plot;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import java.util.Map;

public class Config {
    private static int plotLength = 2;
    private static int roadLength = 1;
    private static int addLength = plotLength + roadLength;
    private static int worldHeight = 70;
    private static Biome roadBiome = Biome.THE_VOID;
    private static Biome plotBiome = Biome.PLAINS;
    private static Material roadBody = Material.STONE;
    //道路最底层的方块
    private static Material roadLowest = Material.BEDROCK;
    //道路最上层的地板
    private static Material roadFloor = Material.SMOOTH_STONE;
    private static Material roadFrame = Material.SMOOTH_STONE_SLAB;
    //生成世界的时候所有的非道路区块的成分比例
    private static Map<Plot, Integer> plotComponent;

    public static int getAddLength() {
        return addLength;
    }

    public static Material getRoadFrame() {
        return roadFrame;
    }

    public static void setRoadFrame(Material roadFrame) {
        Config.roadFrame = roadFrame;
    }

    public static Biome getPlotBiome() {
        return plotBiome;
    }

    public static void setPlotBiome(Biome plotBiome) {
        Config.plotBiome = plotBiome;
    }

    public static Map<Plot, Integer> getPlotComponent() {
        return plotComponent;
    }

    public static void setPlotComponent(Map<Plot, Integer> plotComponent) {
        Config.plotComponent = plotComponent;
    }

    public static Material getRoadLowest() {
        return roadLowest;
    }

    public static void setRoadLowest(Material roadLowest) {
        Config.roadLowest = roadLowest;
    }

    public static Material getRoadFloor() {
        return roadFloor;
    }

    public static void setRoadFloor(Material roadFloor) {
        Config.roadFloor = roadFloor;
    }

    public static Material getRoadBody() {
        return roadBody;
    }

    public static void setRoadBody(Material roadBody) {
        Config.roadBody = roadBody;
    }

    public static Biome getRoadBiome() {
        return roadBiome;
    }

    public static void setRoadBiome(Biome roadBiome) {
        Config.roadBiome = roadBiome;
    }

    public static int getWorldHeight() {
        return worldHeight;
    }

    public static void setWorldHeight(int worldHeight) {
        Config.worldHeight = worldHeight;
    }

    public static int getPlotLength() {
        return plotLength;
    }

    public static void setPlotLength(int plotLength) {
        Config.plotLength = plotLength;
    }

    public static int getRoadLength() {
        return roadLength;
    }

    public static void setRoadLength(int roadLength) {
        Config.roadLength = roadLength;
    }

}
