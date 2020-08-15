import java.util.Scanner;

public class Duke {

    // Method to intiate the bot and allow it to echo the user's command or exit the bot
    public static void startBot() {
        String welcome = "Hello I am Duke!\nHow can I help you?";
        System.out.println(welcome);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.next();
            if (message.equals("bye")) {
                System.out.println("Bye! Have a nice day!");
                break;
            } else {
                System.out.println(message);
            }
        }
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        startBot();
    }
}