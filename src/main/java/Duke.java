import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String introduction = "    ____________________________________________________________\n" +
                "      ____        _        \n" +
                "     |  _ \\ _   _| | _____ \n" +
                "     | | | | | | | |/ / _ \\\n" +
                "     | |_| | |_| |   <  __/\n" +
                "     |____/ \\__,_|_|\\_\\___|\n" +
                "     Hello! I'm Thuya\n" +
                "     What may I do for you, sir/madam?\n" +
                "    ____________________________________________________________\n";
        System.out.println(introduction);
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();
        while (!command.equals("bye")) {
            System.out.println(
                    "    ____________________________________________________________\n" +
                    "     " + command +"\n" +
                    "    ____________________________________________________________\n" );
            command = scanner.next();
        }
        System.out.println(
                "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n" +
                "\n"
        );
    }
}
