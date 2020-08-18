import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // this method prints a horizontal line of fixed length
    public static void horiLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
            if (i == length - 1) System.out.println("");
        }
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        // welcome message
        horiLine(60);
        System.out.println("  Hello! I'm IntelliGent!\n  What can I do for you?");
        horiLine(60);
        while (sc.hasNext()) {
            String nextInput = sc.next();
            if (nextInput.equals("bye")) {
                horiLine(60);
                System.out.println("  Bye. Hope to see you again soon!");
                horiLine(60);
                sc.close();
                break;
            } else {
                horiLine(60);
                System.out.println("  " + nextInput);
                horiLine(60);
            }
        }
    }
}
