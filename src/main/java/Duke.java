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
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                s.list();
            } else {
                s.add(command);
            }
            command = sc.nextLine();
        }

        System.out.print(s.formatOut(goodbye()));
    }



    private static String welcome() {
        return "Hello, I am Duke !\t\n What can I do for you ?";
    }

    private static String goodbye() { return "Bye ! Hope to see you again soon.";}

}
