import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String line = "____________________________________________________________";

    private String name = "Bolot";
    private ArrayList<String> list = new ArrayList<>();

    public void greet() {
        System.out.println(line);
        System.out.println(String.format("Hello! I'm %s, your personal chat-bot companion.", name));
        System.out.println("How may I help you?");
        System.out.println(line);
    }

    public void echo() {
        Scanner sc = new Scanner(System.in);
        String end = "bye";

        String type = sc.nextLine();

        while (!type.equalsIgnoreCase(end)) {
            System.out.println(line);
            System.out.println(type);
            System.out.println(line);

            type = sc.nextLine();
        }

        System.out.println(line);
        System.out.println("Bye! Thank you for chatting with me!");
        System.out.println("Hope to see you again soon!");
        System.out.println(line);
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//
//        System.out.println("Hello from\n" + logo);

        Duke bot = new Duke();
        bot.greet();
        bot.echo();
    }


}
