package org.kazz.kazzutils.features.events.mytho;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.kazz.kazzutils.utils.ChatUtils;
import scala.Int;

public class mobTracker {
    public static double inq = 0;
    public static double champ = 0;
    public static double mino = 0;
    public static double gaia = 0;
    public static double lynx = 0;
    public static double hunter = 0;
    public static int totalMobs = 0;
    public static double coins = 0;
    public static double burrows = 0;

    public static double inqChance = 0;
    public static double champChance = 0;
    public static double minoChance = 0;
    public static double gaiaChance = 0;
    public static double lynxChance = 0;
    public static double hunterChance = 0;
    public static int feathers = 0;
    public static int hunter2 = 0;
    public static int lynx2 = 0;
    public static int mino2 = 0;
    public static int gaia2 = 0;
    public static int champ2 = 0;
    public static int inq2 = 0;



    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (event.type == 2) return;

        String message = event.message.getUnformattedText();
        //ChatUtils.userError(message);
        if(message.contains("You dug out a Minos Inquisitor!")){
            //ChatUtils.userError("inq:"+inq);
            inq++;
        } else if (message.contains("You dug out a Minos Champion!")) {
            //ChatUtils.userError("champ:"+champ);
            champ++;
        }else if (message.contains("You dug out a Minotaur!")){
            //ChatUtils.userError("mino:"+mino);
            mino++;
        }else if (message.contains("You dug out a Gaia Construct!")){
            //ChatUtils.userError("gaia:"+gaia);
            gaia++;
        }else if (message.contains("You dug out Siamese Lynxes!")){
            //ChatUtils.userError("lynx:"+lynx);
            lynx++;
        }else if (message.contains("You dug out a Minos Hunter!")){
            //ChatUtils.userError("hunter:"+hunter);
            hunter++;
        }else if (message.contains("You dug out") && message.contains("coins!")){
            String coin = message.substring(message.indexOf("out")+4,message.indexOf("coins!")-1).replace(",","");
            int coins2 = Integer.parseInt(coin);
            double coins3 = (double) coins2 / 1000000;
            coins3 = coins3 * 100;
            int komma = (int) coins3;
            coins3 = (double) komma / 100;


            coins +=  coins3;

        }else if(message.contains("You dug out a Griffin Feather! ")){
            feathers++;
        }else if (message.contains("You dug out a Griffin Burrow!")) burrows++;

        hunter2 = (int) mobTracker.hunter;
        lynx2 = (int) mobTracker.lynx;
        mino2 = (int) mobTracker.mino;
        gaia2 = (int) mobTracker.gaia;
        champ2 = (int) mobTracker.champ;
        inq2 = (int) mobTracker.inq;
        totalMobs = inq2+champ2+mino2+gaia2+lynx2+hunter2;

        if(totalMobs>0) {
            inqChance =  (inq / totalMobs) * 100;
            inqChance = (double) Math.round(inqChance * 100) / 100;
            champChance =  (champ / totalMobs) * 100;
            champChance = (double) Math.round(champChance * 100) / 100;
            minoChance =  (mino / totalMobs) * 100;
            minoChance = (double) Math.round(minoChance * 100) / 100;
            gaiaChance =  (gaia / totalMobs) * 100;
            gaiaChance = (double) Math.round(gaiaChance * 100) / 100;
            lynxChance =  (lynx / totalMobs) * 100;
            lynxChance = (double) Math.round(lynxChance * 100) / 100;
            hunterChance =  (hunter / totalMobs) * 100;
            hunterChance = (double) Math.round(hunterChance * 100) / 100;
        }



    }
}
