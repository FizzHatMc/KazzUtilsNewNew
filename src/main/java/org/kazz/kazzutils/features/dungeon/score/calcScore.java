package org.kazz.kazzutils.features.dungeon.score;

public class calcScore {
    private static int score = 0;
    private static double skill = 100;
    private static double explor = 100;
    private static double speed = 100;
    private static double bonus = 5;
    private static double clearedRooms = 0;
    private static double totalRooms = 0;
    private static double secretsFound = 0;
    private static double secretsTotal = 0;
    private static boolean isPaul = false;


    private void calcSkill(int deaths,int failedPuzzle){
        skill = (100 - deaths * 2 - failedPuzzle * 14);
    }

    private void calcExplor(int floor, boolean master){
        double secretReq = 0;
        if(master || floor == 7){
            secretReq = 100;
        }else if(floor == 6){
            secretReq = 85;
        }else if(floor == 5){
            secretReq = 70;
        }else if(floor == 4){
            secretReq = 60;
        }else if(floor == 3){
            secretReq = 50;
        }else if(floor == 2){
            secretReq = 40;
        }else if(floor == 1) {
            secretReq = 30;
        }

        explor = ((60*clearedRooms)/totalRooms)+((40*secretsFound)/(secretReq*secretsTotal));
    }


    private void calcSpeed(int floor, boolean master, double time){
        double t = 0;
        switch (floor) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 6:
                t = time - 120;
                break;
            case 5:
                t = time - 240;
                break;
            case 7:
                t = time - 360;
                break;
        }
        if(master) t = time - 360;


        if(t<480) {
            speed = 100;
        }else if(t<600 && t >= 480) {
            speed = 140 - (((double) 1 / 12) * t);
        }else if(t<840 && t >= 600) {
            speed = 115 - (((double) 1 / 24) * t);
        }else if(t<1140 && t>=840) {
            speed = 108 - (((double) 1 / 30) * t);
        }else if(t<3940 && t>= 1140) {
            speed = 98.5 - (((double) 1 / 40) * t);
        }else if(t>=3940){
            speed = 0;
        }
    }

    private void calcBonus(int cryptKilled, boolean mimic) {
        if(mimic)bonus+=2;
        bonus+=cryptKilled;
    }

    private void calcScore(){
        score = (int) (skill + explor + speed + bonus);
        if(isPaul) score += 10;
    }
}
