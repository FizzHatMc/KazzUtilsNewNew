package org.kazz.kazzutils.features.chatCommands;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.utils.ChatUtils;
import tv.twitch.chat.Chat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartyCommands {
    //  [343] ⚔ [MVP++] RealKazz: test
    //  [343] ⚔ [MVP+] RealKazz: test
    //  [343] ⚔ [VIP+] RealKazz: test
    //  [343] ⚔ [VIP] RealKazz: test
    //  [343] ⚔ RealKazz: test

    //   ⚔ [MVP++] RealKazz: test
    //   ⚔ [MVP+] RealKazz: test
    //   ⚔ [VIP+] RealKazz: test
    //   ⚔ [VIP] RealKazz: test
    //   ⚔ RealKazz: test

    //  [343] [MVP++] RealKazz: test
    //  [343] [MVP+] RealKazz: test
    //  [343] [VIP+] RealKazz: test
    //  [343] [VIP] RealKazz: test
    //  [343] RealKazz: test

    //  [MVP++] RealKazz: test
    //  [MVP+] RealKazz: test
    //  [VIP+] RealKazz: test
    //  [VIP] RealKazz: test
    //  RealKazz: test
/*

    HP DEFENSE SPEED OVERFLOW MANA ????????????????

    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event){
        String UserInput = event.message.getUnformattedText(); //INCLUSIVE USER

        //String user = extractUsername(UserInput);
        ChatUtils.messageToChat(UserInput);


    }
 */


    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (event.type == 2) return;
        if (!Config.partyCommands) return;
        char commandUse = '.';
        switch (Config.prefix){
            case 0: commandUse = '.'; break;
            case 1: commandUse = '?'; break;
            case 2: commandUse = '!'; break;
        }

        String message = event.message.getUnformattedText();


        String regex = "Party > (\\[.+])? ?(.+): \\"+ commandUse +"([^\\s]+)( ?: ([^\\s]+))?$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(noColorCodes(message));


        if (matcher.matches()) {
            String sender = matcher.group(2);
            String command = matcher.group(3);
            //ChatUtils.userError("Sender: " + sender + " Command: " + command);
            command = command.toLowerCase();
            if (command.startsWith("m")) {
                int floor = extractNumberFromCommand(command);
                if (floor != -1) {
                    ChatUtils.userMessage("/joindungeon MASTER_CATACOMBS_FLOOR_" + convertToText(floor));
                    return;
                }
            }

            if (command.startsWith("f")) {
                int floor = extractNumberFromCommand(command);
                if (floor != -1) {
                    ChatUtils.userMessage("/joindungeon CATACOMBS_FLOOR_" + convertToText(floor));
                    return;
                }
            }

            switch (command) {
                case "help":
                    ChatUtils.userMessage("Chat Commands: help, ptme, warp, inv / invite, f{floor}, m{floor} ");
                    break;
                case "ptme":
                    ChatUtils.userMessage("/party transfer " + sender);
                    break;
                case "warp":
                    ChatUtils.userMessage("/party warp ");
                    break;
                case "inv":
                case "invite":
                    ChatUtils.userMessage("/party invite " + matcher.group(4));
                    break;
                case "allinvite":
                case "allinv":
                    ChatUtils.userMessage("/party setting allinvite");
                    break;
                default:
                    ChatUtils.userMessage("Unknown command: " + command);
                    break;
            }


        }
    }


    private String noColorCodes(String message) {


        // Definiere den regulären Ausdruck für Minecraft-Farbcode-Tags
        String regex = "§[0-9a-fklmnor]";

        // Erstelle ein Pattern-Objekt
        Pattern pattern = Pattern.compile(regex);

        // Erstelle einen Matcher für den Eingabetext
        Matcher matcher = pattern.matcher(message);

        // Entferne alle Minecraft-Farbcode-Tags
        String output = matcher.replaceAll("");
        return output;

    }

    public static int extractNumberFromCommand(String command) {
        // Assuming the command format is "?mX" or "?fX" where X is the number
        if (command.startsWith("m") || command.startsWith("f")) {
            try {
                return Integer.parseInt(command.substring(1)); // Extract the number part
            } catch (NumberFormatException e) {
                return -1; // Invalid format (not a valid integer)
            }
        } else {
            return -1; // Command doesn't match expected format
        }
    }

    public static String convertToText(int number) {
        switch (number) {
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            default:
                return "Invalid input"; // Handle out-of-range numbers
        }


    }
}