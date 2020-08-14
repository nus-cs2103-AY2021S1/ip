import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Server s = new Server();
        System.out.print(s.formatOut(welcome()));
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            s.perform(input);
            input = sc.nextLine();
        }

        System.out.print(s.formatOut(goodbye()));
    }

    public static String formatOut(String s) {
        return String.format("  %s\n    %s\n  %s\n",hor_line(),s,hor_line());
    }

    private static String hor_line() {
        return "-------------------------------------";
    }

    private static String welcome() {
        return "Hello, I am Duke !\n\t What can I do for you ?";
    }

    private static String goodbye() { return "Bye ! Hope to see you again soon.";}

    public static String donegreet() {
        return "Nice! I've marked this task as done: \n";
    }
}
