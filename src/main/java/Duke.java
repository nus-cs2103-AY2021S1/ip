import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);

        String indent = "    ";
        String textIndent = "     ";
        String divide = indent + "____________________________________________________________\n";

        String exitMessage = textIndent + "Bye. Hope to see you again soon!\n";
        System.out.println(divide + textIndent + "Hello! I'm Duke\n" + textIndent + "What can I do for you?\n" + divide);

        while(sc.hasNext()) {
            String text = sc.nextLine();
            if (text.equals("bye")) {
                System.out.println(divide + exitMessage + divide);
                break;
            }
            System.out.println(divide + textIndent + text + "\n" + divide);

        }
    }
}
