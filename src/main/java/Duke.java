package main.java;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // attributes for word storage
        String[] lib = new String[100];
        int curr = 0;

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

            if (echo.equals("list")) {
                System.out.println("---------------\n");
                for (int i = 1; i <= curr; i++) {
                    System.out.println(i + ". " + lib[i - 1] +"\n");
                }
                System.out.println("---------------\n");
                continue;
            }

            System.out.println("---------------\n" + "Storing item " + "(" + curr + "/100)"+
                    " : " + echo + "\n" + "---------------\n");
            lib[curr] = echo;
            curr++;
        }

        sc.close();
    }
}
