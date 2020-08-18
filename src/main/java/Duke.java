import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lines = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String introduction = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(introduction);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(lines);
            System.out.println(" " + input);
            System.out.println(lines);
            input = sc.nextLine();
        }
        System.out.println(lines);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(lines);
    }
}
