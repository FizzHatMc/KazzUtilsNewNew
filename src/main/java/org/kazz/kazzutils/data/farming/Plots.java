package org.kazz.kazzutils.data.farming;

import net.minecraft.util.BlockPos;

public class Plots {
    public int infectedAmount = 0;
    public int plotNumber = 0;
    public BlockPos plotPos = null;
    public String plotName = "";

    public Plots(){}

    public Plots(int infected, int plotNumber, String plotName) {
        this.infectedAmount = infected;
        this.plotNumber = plotNumber;
        this.plotPos = getBlockPosFromPlotNumber(plotNumber);
        this.plotName = plotName;
    }

    public Plots(int infected, String plotName) {
        this.infectedAmount = infected;
        this.plotName = plotName;
    }

    /*

-192,-192   -96,-192    0,-192   96,-192    192,-192
-192,-96    -96,-96     0,-96    96,-96     192,-96
-192,0      -96,0       0,0      96,0       192,0
-192,96     -96,96      0,96     96,96      192,96

-192,192    -96,192     0,192    96,192     192,192

     21 13 9 14  22
     15 5  1  6  16
     10 2  x  3  11
     17 7  4  8  18
     23 19 12 20 24

     2  3   4  5  6
     11 12 13 14 15
     20 21 22 24 24
     29 30 31 32 33
     38 39 40 41 42

     */


    public static int slotNumberToPlotNumber(int slotNumber) {
        switch(slotNumber) {
            case 2: return 21; 
            case 3: return 13; 
            case 4: return 9; 
            case 5: return 14; 
            case 6: return 22; 
            case 11: return 15; 
            case 12: return 5; 
            case 13: return 1; 
            case 14: return 6; 
            case 15: return 16; 
            case 20: return 10; 
            case 21: return 2; 
            case 23: return 4; 
            case 24: return 3; 
            case 29: return 11; 
            case 30: return 12; 
            case 31: return 4; 
            case 32: return 8; 
            case 33: return 18; 
            case 38: return 20; 
            case 39: return 19; 
            case 40: return 12; 
            case 41: return 24; 
            case 42: return 24; 

            default: return 0; 
        }
    }


    public static BlockPos getBlockPosFromPlotNumber(int PlotNumber) {
        BlockPos pos = null;

        switch(PlotNumber){
            case 1: return pos = new BlockPos(0,75,-96);
            case 2: return pos = new BlockPos(-96,75,0);
            case 3: return pos = new BlockPos(96,75,0);
            case 4: return pos = new BlockPos(0,75,96);
            case 5: return pos = new BlockPos(-96,75,-96);
            case 6: return pos = new BlockPos(96,75,-96);
            case 7: return pos = new BlockPos(-96,75,96);
            case 8: return pos = new BlockPos(96,75,96);

            case 9: return pos = new BlockPos(0,75,-192);
            case 10: return pos = new BlockPos(-192,75,0);
            case 11: return pos = new BlockPos(192,75,0);
            case 12: return pos = new BlockPos(0,75,192);

            case 13: return pos = new BlockPos(-96,75,-192);
            case 14: return pos = new BlockPos(96,75,-192);
            case 15: return pos = new BlockPos(-192,75,-96);
            case 16: return pos = new BlockPos(192,75,-96);
            case 17: return pos = new BlockPos(-192,75,96);
            case 18: return pos = new BlockPos(192,75,96);
            case 19: return pos = new BlockPos(-96,75,192);
            case 20: return pos = new BlockPos(96,75,192);

            case 21: return pos = new BlockPos(-192,75,-192);
            case 22: return pos = new BlockPos(192,75,-192);
            case 23: return pos = new BlockPos(-192,75,192);
            case 24: return pos = new BlockPos(192,75,192);

            default: return pos = new BlockPos(0,0,0);
        }

    }

}
