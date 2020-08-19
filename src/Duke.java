import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String horizontal = "________________________" + "\n";
    public static ArrayList<String> list = new ArrayList<>();

    public static void action() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if  (input.equals("bye")) {
                String bye = "Bye. Hope to see you again soon!" + "\n";
                System.out.println(horizontal + bye + horizontal);
                break;
            } else if (input.equals("list")) {
                System.out.println(horizontal);
                int counter = 1;
                for (String str: list) {
                    System.out.println(counter + ". " + str);
                    counter++;
                }
                System.out.println(horizontal);
            } else {
                list.add(input);
                System.out.println(horizontal + "added: " + input + "\n" + horizontal);
            }
        }
    }

    public static void greet() {
        String hello = "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n";
        System.out.println(horizontal + hello + horizontal);
    }

    public static void main(String[] args) {
        greet();
        action();

        //String logo = " ____        _        \n"
        //      + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
    }
}