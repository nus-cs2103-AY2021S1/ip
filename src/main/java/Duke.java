import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<String> entryList = new ArrayList<String>();

    static String line = "    ____________________________________________________________\n";

    public static void printList() {
        int counter = 0;

        System.out.printf(line);

        for (String s: entryList) {
            counter++;
            System.out.printf("     %d. %s%n", counter, s);
        }

        System.out.println(line);
    }

    public static void main(String[] args) {
        /**
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         System.out.println("Hello from\n" + logo);
         **/
        String welcome = line + "     Hello! I'm Cartona (no relation to Cortana).\n" +
                         "     What can I do for you?\n" + line;

        System.out.println(welcome);

        String nextInput = "";
        while (true) {
            nextInput = sc.nextLine();

            if (nextInput.equals("bye")) {
                break;
            } else if (nextInput.equals("list")) {
                printList();
                continue;
            }

            String echo = line + "     added: " + nextInput + "\n" + line;
            System.out.println(echo);
            entryList.add(nextInput);
        }

        String goodbye = line + "     Bye. Hope to see you again soon!\n" + line;
        System.out.println(goodbye);
    }
}