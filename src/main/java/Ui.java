import java.util.ArrayList;
import java.util.List;

public class Ui {
    private static List<String> messages = new ArrayList<>();
    
    public static void addMessage(String message) {
        messages.add(message);
    }

    public static void sendMessages() {
        System.out.println("\t____________________________________________________________");
        for (String message : messages) {
            System.out.println("\t " + message);
        }
        System.out.println("\t____________________________________________________________");
        messages = new ArrayList<>(); // clear all messages
    }
    
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
