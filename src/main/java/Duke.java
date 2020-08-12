import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from the other side of\n" + logo);
        String greet = "\t____________________________________________________________\n"
                + "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n";
        System.out.println(greet);
        Scanner sc = new Scanner(System.in);
        String input = "";
        String echoizer = "\t____________________________________________________________\n"
                + "\t%s\n"
                + "\t____________________________________________________________\n";
        String exit = "Bye. Hope to see you again soon!";
        while (sc.hasNext()) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(String.format(echoizer, exit));
                break;
            } else {
                System.out.println(String.format(echoizer, input));
            }
        }
    }
}
