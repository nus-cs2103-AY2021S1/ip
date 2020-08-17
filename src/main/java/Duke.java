import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    static final String INDENT = "    ";

    public static void printList(List<String> list) {
        String s = "";
        for (int i = 0; i < list.size(); i ++) {
            s += String.valueOf(i+1) + ". " + list.get(i);
            if (i != list.size() - 1) {
                s += '\n' + INDENT;
            }
        }
        if (s.equals("")) {
            printWindow("There is nothing in the list!");
        } else {
            printWindow(s);
        }
    }

    public static void printWindow(String s) {
        System.out.println(INDENT + HORIZONTAL_LINE);
        System.out.println(INDENT + s);
        System.out.println(INDENT + HORIZONTAL_LINE);
    }

    public static void printBye() {
        System.out.println(INDENT + HORIZONTAL_LINE);
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.print(INDENT + HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        System.out.println(INDENT + HORIZONTAL_LINE);
        System.out.println("    Hello! I'm Duke\n" +
                "    What can I do for you?");
        System.out.println(INDENT + HORIZONTAL_LINE);

        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            if (nextLine.equals("bye")) {
                printBye();
                break;
            } else if (nextLine.equals("list")) {
                printList(list);
            } else {
                list.add(nextLine);
                printWindow("added: " + nextLine);
            }
        }
    }
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
}
