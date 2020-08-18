import java.util.Scanner;
public class Duke {
    public static String horizontal = "________________________" + "\n";

    public static void action() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                System.out.println(horizontal + input + "\n" + horizontal);
            } else if (input.equals("blah")) {
                System.out.println(horizontal + input + "\n" + horizontal);
            } else if (input.equals("bye")) {
                String bye = "Bye. Hope to see you again soon!" + "\n";
                System.out.println(horizontal + bye + horizontal);
                break;
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