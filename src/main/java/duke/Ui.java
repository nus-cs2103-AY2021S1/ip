package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a messenger that displays messages on the user interface.
 */
public class Ui {
    private static List<String> messages = new ArrayList<>();

    public static void addMessage(String message) {
        messages.add(message);
    }

    public static String getMessages() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String message : messages) {
            stringBuilder.append(message).append("\n");
        }
        messages = new ArrayList<>(); // clear all messages
        String message = stringBuilder.toString();
        System.out.println(message);
        return message;
    }

    /**
     * Prints the logo.
     */
    public static void printLogo() {
        String logo = "\t    ,---,                                       \n"
                + "\t  .'  .' `\\                     ,---,              \n"
                + "\t,---.'     \\          ,--,    ,---.'|              \n"
                + "\t|   |  .`\\  |       ,'_ /|    |   | :              \n"
                + "\t:   : |  '  |  .--. |  | :    |   | |   ,---.       \n"
                + "\t|   ' '  ;  :,'_ /| :  . |  ,--.__| |  /     \\     \n"
                + "\t'   | ;  .  ||  ' | |  . . /   ,'   | /    /  |     \n"
                + "\t|   | :  |  '|  | ' |  | |.   '  /  |.    ' / |     \n"
                + "\t'   : | /  ; :  | : ;  ; |'   ; |:  |'   ;   /|     \n"
                + "\t|   | '` ,/  '  :  `--'   \\   | '/  ''   |  / |    \n"
                + "\t;   :  .'    :  ,      .-./   :    :||   :    |     \n"
                + "\t|   ,.'       `--`----'    \\   \\  /   \\   \\  /  \n"
                + "\t'---'                       `----'     `----'       \n";
        System.out.println(logo);
    }
}
