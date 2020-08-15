import ip.src.main.java.Layout;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Layout layout = new Layout();

        layout.printLine();
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm" + "\n" + logo + "\n\t" + "What can I do for you?";
        layout.print(greeting);
        layout.printLine();

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String next = sc.nextLine();
            if (next.equals("")) {
                System.exit(0);
                sc.close();
            }
            layout.printLine();
            if (next.equals("bye")) {
                layout.print("Bye. Hope to see you again soon!");
            } else {
                layout.print(next);
            }
            layout.printLine();
        }


    }
}
