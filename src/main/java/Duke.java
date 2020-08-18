import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String breakline = "    ______________________________________________________";
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Welcome to \n" + logo + "\n    Your personal assistant :)");
        System.out.println(breakline);
        System.out.println(breakline);

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String userCommand = sc.nextLine();
            if (userCommand.equals("bye")) {
                System.out.println(breakline);
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(breakline);
                break;
            }
            switch (userCommand) {
                default:
                    System.out.println(breakline);
                    System.out.println("    " + userCommand);
                    System.out.println(breakline);
                    break;
            }
        }
        sc.close();
    }
}
