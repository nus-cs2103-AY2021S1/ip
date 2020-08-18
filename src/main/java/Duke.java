import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String str = "";
        System.out.println("    _________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    _________________________________");

        ArrayList<String> lst = new ArrayList<>();

        while (!str.equals("bye")) {
            str = sc.nextLine();
            if (str.equals("list")) {
                System.out.println("    _________________________________");
                for (int i = 0; i < lst.size(); i++) {
                    String item = (i + 1) + ". " + lst.get(i);
                    System.out.println("    " + item);
                }
                System.out.println("    _________________________________");
            } else if (!str.equals("bye")) {
                System.out.println("    _________________________________");
                System.out.println("    added: " + str);
                System.out.println("    _________________________________");
                lst.add(str);
            }
        }

        System.out.println("    _________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _________________________________");

    }
}
