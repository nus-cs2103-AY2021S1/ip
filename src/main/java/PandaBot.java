import java.util.Scanner;

public class PandaBot {
    private static String[] tasks = new String[100];
    private static int counter = 0;


    public static void main(String[] args) {
        String logo =
                 " ____                    _   \n"
               + "|  _ \\                  | |  \n"
               + "| |_| |___  _ _  __  ___| | ___  _\n"
               + "| ___/  _ \\| | |/  |/ _   |/ _ \\| | \n"
               + "| |  | |_|   |  _  | |_|  | |_|   | \n"
               + "|_|  \\____,__|_| |_|\\___,_|\\___,__|" + " bot\n\n";

        System.out.println(logo + "Hello! I'm PandaBot.\n" + "What can I do for you?\n");

        // echoes command by user
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye! Remember to do your work! See you soon~");
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] != null) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    } else {
                        break;
                    }
                }
            } else {
                System.out.println("Added: " + command);
                tasks[counter] = command;
                counter++;
            }
        }
        sc.close();
    }
}
