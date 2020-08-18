import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String lineDivider = "------------------------------------------";

    public static void echo(String message) {
        System.out.println(lineDivider);
        System.out.println(message);
        System.out.println(lineDivider + "\n");
    }

    public static String showList(ArrayList<String> list) {
        StringBuffer lst = new StringBuffer();
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            if (i + 1 != listSize) {
                lst.append((i + 1) + ". " + list.get(i) + "\n");
            } else {
                lst.append((i + 1) + ". " + list.get(i));
            }
        }
        return lst.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>(2);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        echo("Hello! I'm Duke\nWhat can I do for you?");
        while (sc.hasNext()) {
            String msg = sc.nextLine();
            if (msg.equals("bye")) {
                echo("Bye. Hope to see you again soon!");
                break;
            } else if (msg.equals("list")) {
                echo(showList(list));
            } else {
                list.add(msg);
                echo("added: " + msg);
            }
        }
    }
}
