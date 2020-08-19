import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String duke = "Duke> ";
    public static String cmd = "Input> ";
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String userInput = "";
        ArrayList<String> list = new ArrayList<>();

        startupMsg();

        while (true) {
            System.out.print(cmd);
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            }

            if (!userInput.equals("list")) {
                list.add(userInput);
                System.out.println(duke + "I added '" + userInput + "' to your To-Do List");
            } else {
                int idx = 0;
                System.out.println(duke + "Here's your To-Do List:");
                for (String item : list) {
                    System.out.println(++idx + ". " + item);
                }
            }
        }

        System.out.println(duke + "See you soon!");
    }

    private static void startupMsg() {
        System.out.println(logo);
        System.out.println(duke + "Hi I'm Duke!");
        System.out.println(duke + "What can I do for you?");
    }
}
