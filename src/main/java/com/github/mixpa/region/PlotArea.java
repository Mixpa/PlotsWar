package com.github.mixpa.region;

import com.github.mixpa.Config;
import com.github.mixpa.Utils;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlotArea {
    private int X;
    private int Z;
    private World world;

    public PlotArea(int chunkX, int chunkZ, World world) {
        if (Utils.isRoad(chunkX, chunkZ))
            throw new IllegalArgumentException("can not in Road!");
        this.X = chunkX / Config.getAddLength();
        this.Z = chunkZ / Config.getAddLength();
        this.world = world;
    }

    public PlotArea(Chunk chunk) {
        new PlotArea(chunk.getX(), chunk.getZ(), chunk.getWorld());
    }

    public int getX() {
        return X;
    }

    public int getZ() {
        return Z;
    }

    public World getWorld() {
        return world;
    }

    private List<Chunk> getPlotChunks() {
        ArrayList<Chunk> chunks = new ArrayList<>();
        for (int chunkX = 1; chunkX <= Config.getPlotLength(); chunkX++) {
            for (int chunkZ = 1; chunkZ <= Config.getPlotLength(); chunkZ++) {
                chunks.add(world.getChunkAt(getChunkXorZ(this.X, chunkX), getChunkXorZ(this.Z, chunkZ)));
            }
        }
        return chunks;
    }

    private int getChunkXorZ(int xOrZ, int chunkXorZ) {
        if (xOrZ < 0) return xOrZ * Config.getAddLength() - Config.getRoadLength() + 1 - chunkXorZ;
        else return xOrZ * Config.getAddLength() + Config.getRoadLength() - 1 + chunkXorZ;
    }

    public void resetMineArea(Plot plot) {
        for (Chunk chunk : getPlotChunks()) {
            plot.resetChunk(chunk);
        }
    }

    @Override
    public String toString() {
        return "MineArea{" +
                "chunkX=" + X +
                ", chunkZ=" + Z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlotArea plotArea = (PlotArea) o;
        return X == plotArea.X &&
                Z == plotArea.Z &&
                Objects.equals(world, plotArea.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, Z, world);
    }
}
