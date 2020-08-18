import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalDiv = "____________________________________________________________";

        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        System.out.println(horizontalDiv);
        System.out.println("Hello! I'm Dude\n" + "What can I do for you today?");
        System.out.println(horizontalDiv);
        while(in.hasNext()) {
            String str = in.next();

            if(str.equals("bye")) {
                System.out.println(horizontalDiv);
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println(horizontalDiv);
                break;
            } else {
                System.out.println(horizontalDiv);
                System.out.println(str);
                System.out.println(horizontalDiv);
            }
        }
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
