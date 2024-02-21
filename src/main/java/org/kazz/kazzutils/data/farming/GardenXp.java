package org.kazz.kazzutils.data.farming;

public class GardenXp {
    public static int getGardenXp(int currentlvl, double percent){
        double xp = 0;
        switch (currentlvl){
            case 1:
                xp = 70 * (percent/100);
                break;

            case 2:
                xp = 100 * (percent/100);
                break;

            case 3:
                xp = 140 * (percent/100);
                break;

            case 4:
                xp = 240 * (percent/100);
                break;

            case 5:
                xp = 600 * (percent/100);
                break;

            case 6:
                xp = 1500 * (percent/100);
                break;

            case 7:
                xp = 2000 * (percent/100);
                break;

            case 8:
                xp = 2500 * (percent/100);
                break;

            case 9:
                xp = 3000 * (percent/100);
                break;

            case 10:
            case 12:
            case 11:
            case 13:
            case 14:
                xp = 10000 * (percent/100);
                break;
        }

        return (int) xp;
    }

    public static int getMaxXp(int currentlvl){
        double xp = 0;

        switch (currentlvl){
            case 1:
                xp = 70;
                break;

            case 2:
                xp = 100 ;
                break;

            case 3:
                xp = 140;
                break;

            case 4:
                xp = 240;
                break;

            case 5:
                xp = 600;
                break;

            case 6:
                xp = 1500;
                break;

            case 7:
                xp = 2000;
                break;

            case 8:
                xp = 2500;
                break;

            case 9:
                xp = 3000;
                break;

            case 10:
            case 12:
            case 11:
            case 13:
            case 14:
                xp = 10000;
                break;
        }
        return (int) xp;
    }

}
