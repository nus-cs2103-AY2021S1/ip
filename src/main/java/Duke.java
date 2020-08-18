import java.util.Scanner;

public class Duke {
    private static String horiLine = "____________________________________________________________" + "\n";

    private static void greet() {
        String greeting = "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n";
        System.out.println(horiLine + greeting + horiLine);
    }

    private static void echo() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if(input.equals("bye")) { //exit condition
                String goodbye = "Bye. Hope to see you again soon!";
                System.out.println(horiLine + goodbye + "\n" + horiLine);
                break;
            } else {
                System.out.println(horiLine + input + "\n" + horiLine);
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echo();
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
