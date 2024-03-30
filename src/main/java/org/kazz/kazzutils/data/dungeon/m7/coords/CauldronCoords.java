package org.kazz.kazzutils.data.dungeon.m7.coords;

import net.minecraft.util.BlockPos;

public class CauldronCoords {

        public static BlockPos getRelic(int clas){
            BlockPos re = null;

            switch(clas){
                case 0: re = new BlockPos(49.5,6.5,44.5); break; //green
                case 1: re = new BlockPos(59.5,6.5,44.5); break; //blue
                case 2: re = new BlockPos(54.5,6.5,41.5); break; //purp
                case 3: re = new BlockPos(57.5,6.5,42.5); break; //orange
                case 4: re = new BlockPos(51.5,6.5,42.5); break; //red
            }

            return re;
        }


}
