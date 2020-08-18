import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String line = "____________________________________________________________";
    public static ArrayList<String> list = new ArrayList<>();

    public static void start() {
         String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         String start = "Hello! I'm Duke, your personal assistant. \nWhat can I do for you?";
         System.out.println(line);
         System.out.println(logo);
         System.out.println(start);
         System.out.println(line);
    }

    public static void end() {
        String end = "Goodbye! Hope to see you again soon. :)";
        System.out.println(line);
        System.out.println(end);
        System.out.println(line);
    }

    public static void main(String[] args) {
        //start
        start();

        // take in inputs
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            } else {
                list.add(input);
                System.out.println(line);
                System.out.println("added: " + input);
            }
            System.out.println(line);
            input = sc.nextLine();
        }

        // end
        end();
    }
}
