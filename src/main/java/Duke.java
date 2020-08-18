import java.util.Scanner;

public class Duke {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        /**
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         System.out.println("Hello from\n" + logo);
         **/
        String line = "    ____________________________________________________________\n";
        String welcome = line + "     Hello! I'm Cartona\n" +
                         "     What can I do for you?\n" + line;

        System.out.println(welcome);

        String nextInput = "";
        while (true) {
            nextInput = sc.nextLine();
            if (nextInput.equals("bye")) {
                break;
            }
            String echo = line + "     " + nextInput + "\n" + line;
            System.out.println(echo);
        }

        String goodbye = line + "     Bye. Hope to see you again soon!\n" + line;
        System.out.println(goodbye);
    }
}
