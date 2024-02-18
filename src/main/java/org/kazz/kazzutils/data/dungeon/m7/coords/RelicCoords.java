package org.kazz.kazzutils.data.dungeon.m7.coords;

import net.minecraft.util.BlockPos;

public class RelicCoords {
    public static BlockPos getRelic(int clas){
        BlockPos re = null;

        switch(clas){
            case 0: re = new BlockPos(20,6,94); break;
            case 1: re = new BlockPos(91,6,94); break;
            case 2: re = new BlockPos(56,8,132); break;
            case 3: re = new BlockPos(92,6,56); break;
            case 4: re = new BlockPos(20,6,59); break;
        }

        return re;
    }
}
