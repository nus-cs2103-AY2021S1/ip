import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String lineDivider = "------------------------------------------";

    public static void echo(String message) {
        System.out.println(lineDivider);
        System.out.println(message);
        System.out.println(lineDivider + "\n");
    }

    public static String showList(ArrayList<Task> list) {
        StringBuffer lst = new StringBuffer();
        lst.append("Here are the tasks in your list:\n");
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            if (i + 1 != listSize) {
                lst.append((i + 1) + ". " + list.get(i).toString() + "\n");
            } else {
                lst.append((i + 1) + ". " + list.get(i).toString());
            }
        }
        return lst.toString();
    }

    public static boolean isInt(String s) {
        try{
            int num = Integer.parseInt(s);
            // is an integer!
            return true;
        } catch (NumberFormatException e) {
            // not an integer!
            return false;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

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
            }

            if (msg.equals("list")) {
                echo(showList(list));
                continue;
            }

            String[] msgArray = msg.split(" ");
            if (msgArray.length == 2 && msgArray[0].equals("done") && isInt(msgArray[1])) {
                 int index = Integer.parseInt(msgArray[1]) - 1;

                 if (index < 0 || index >= list.size()) {
                     echo("Index out of range!");
                 } else {
                     list.get(index).markAsDone();
                     echo("Nice! I've marked this task as done:\n" + list.get(index).toString());
                 }
                 continue;
            }

            list.add(new Task(msg));
            echo("added: " + msg);
        }
    }
}
