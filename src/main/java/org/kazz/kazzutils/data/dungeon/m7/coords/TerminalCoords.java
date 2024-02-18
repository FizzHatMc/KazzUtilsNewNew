package org.kazz.kazzutils.data.dungeon.m7.coords;

import net.minecraft.util.BlockPos;

public class TerminalCoords {
    protected BlockPos[] list = new BlockPos[5];
    public BlockPos getTankCoords(int area){

        list[0] = new BlockPos(110,114,73);
        list[1] = new BlockPos(68,109,122);
        list[2] = new BlockPos(-1,109,112);
        list[3] = new BlockPos(41,109,30);

        return list[area];
    }

    public BlockPos getMageCoords(int area){

        list[0] = new BlockPos(110,119,79);
        list[1] = new BlockPos(59,120,123);
        list[2] = new BlockPos(-1,119,93);
        list[3] = new BlockPos(44,121,30);

        return list[area];
    }

    public BlockPos getBersCoords(int area){

        list[0] = new BlockPos(90,112,92);
        list[1] = new BlockPos(47,109,122);
        list[2] = new BlockPos(40,124,123);
        list[3] = new BlockPos(18,123,93);
        list[4] = new BlockPos(67,109,30);

        return list[area];
    }
    public BlockPos getArchCoords(int area){

        list[0] = new BlockPos(90,122,101);
        list[1] = new BlockPos(39,108,140);
        list[2] = new BlockPos(-1,109,77);
        list[3] = new BlockPos(72,115,47);

        return list[area];
    }

    public BlockPos getHealerCoords(int area){

        list[0] = new BlockPos(110,120,91);
        list[1] = new BlockPos(63,131,142);
        list[2] = new BlockPos(-1,119,74);
        list[3] = new BlockPos(63,127,34);

        return list[area];
    }
}
