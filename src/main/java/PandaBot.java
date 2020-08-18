import java.util.Scanner;

public class PandaBot {

    // main runs greetings

    public static void main(String[] args) {
        String logo =
                 " ____                    _   \n"
               + "|  _ \\                  | |  \n"
               + "| |_| |___  _ _  __  ___| | ___  _\n"
               + "| ___/  _ \\| | |/  |/ _   |/ _ \\| | \n"
               + "| |  | |_|   |  _  | |_|  | |_|   | \n"
               + "|_|  \\____,__|_| |_|\\___,_|\\___,__|" + " bot \n\n";

        System.out.println(logo + "Hello! I'm PandaBot.\n" + "What can I do for you? \n");

        // echoes command by user
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String command = sc.next();
            if (command.equals("bye")) {
                // exits when user types bye
                System.out.println("Bye! Remember to do your work! See you soon~");
                break;
            } else {
                System.out.println(command + "\n");
            }
        }

        sc.close();
    }
}
