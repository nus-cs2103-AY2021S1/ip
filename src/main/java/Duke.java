import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.print(formatOut(welcome()));
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("exit")) {
            System.out.print(formatOut(command));
            command = sc.nextLine();
        }

        System.out.print(formatOut(goodbye()));
    }

    private static String formatOut(String s) {
        return String.format("  %s\n    %s\n    %s\n",hor_line(),s,hor_line());
    }

    private static String hor_line() {
        return "-------------------------------------";
    }

    private static String welcome() {
        return "Hello, I am Duke !\n What can I do for you ?";
    }

    private static String goodbye() {return "Bye ! Hope to see you again soon.";}

}
