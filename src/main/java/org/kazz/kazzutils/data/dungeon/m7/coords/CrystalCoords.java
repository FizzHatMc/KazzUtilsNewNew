package org.kazz.kazzutils.data.dungeon.m7.coords;

import net.minecraft.util.BlockPos;

public class CrystalCoords {
    public static BlockPos getCrystal(int clas){
        BlockPos re = null;
        if(clas == 1){ re = new BlockPos(64,238,50);}else if(clas == 3){re = new BlockPos(82,238,50);}
        return re;
    }
}
