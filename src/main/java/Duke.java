package main.java;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = "   ____        _        \n"
                + "  |  _ \\ _   _| | _____ \n"
                + "  | | | | | | | |/ / _ \\\n"
                + "  | |_| | |_| |   <  __/\n"
                + "  |____/ \\__,_|_|\\_\\___|\n";
        System.out.println( "---------------\n" + "  Hello from\n" + logo + "\n" +
                "---------------\n" + "  What can I do for you today?\n" + "---------------\n");

        while (sc.hasNext()) {
            String echo = sc.next();
            if (echo.equals("bye")) {
                System.out.println("---------------\n" + "Hope to see you again!\n"
                        + "---------------\n");
                break;
            }
            System.out.println("---------------\n" + echo + "\n" + "---------------\n");
        }

        sc.close();
    }
}
