package duke;

import java.util.ArrayList;
import java.util.List;

public class Ui {
    private static List<String> messages = new ArrayList<>();
    
    public static void addMessage(String message) {
        messages.add(message);
    }

    /**
     * Prints each message in the list of messages on a new line.
     * The entire message is wrapped by two horizontal lines.
     */
    public static void sendMessages() {
        System.out.println("\t____________________________________________________________");
        for (String message : messages) {
            System.out.println("\t " + message);
        }
        System.out.println("\t____________________________________________________________");
        messages = new ArrayList<>(); // clear all messages
    }

    /**
     * Prints the logo.
     */
    public static void printLogo() {
        String logo = "\t    ,---,                                     \n" +
                "\t  .'  .' `\\                     ,---,              \n" +
                "\t,---.'     \\          ,--,    ,---.'|              \n" +
                "\t|   |  .`\\  |       ,'_ /|    |   | :              \n" +
                "\t:   : |  '  |  .--. |  | :    |   | |   ,---.       \n" +
                "\t|   ' '  ;  :,'_ /| :  . |  ,--.__| |  /     \\     \n" +
                "\t'   | ;  .  ||  ' | |  . . /   ,'   | /    /  |     \n" +
                "\t|   | :  |  '|  | ' |  | |.   '  /  |.    ' / |     \n" +
                "\t'   : | /  ; :  | : ;  ; |'   ; |:  |'   ;   /|     \n" +
                "\t|   | '` ,/  '  :  `--'   \\   | '/  ''   |  / |    \n" +
                "\t;   :  .'    :  ,      .-./   :    :||   :    |     \n" +
                "\t|   ,.'       `--`----'    \\   \\  /   \\   \\  /  \n" +
                "\t'---'                       `----'     `----'       \n";
        System.out.println(logo);
    }
}
