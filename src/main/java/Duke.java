import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static String greeting = "Hello, I'm Duke, your personal assistant. \n What can I do for you?";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);
        while(sc.hasNextLine()) {
            String order = sc.nextLine();
            String reply = process(order, list);
            System.out.println(reply);
        }
    }

    public static String process(String order, List<String> list) {
        if (order.equals("bye")) {
            return "    Bye-bye, see you next time!";
        } else if (order.equals("list")) {
            if (list.size() == 0) {
                return "    Well done! You've completed all your tasks.";
            } else {
                return printList(list);
            }
        } else {
            list.add(order);
            return "    added:"+order;
        }
    }

    private static String printList(List<String> list) {
        int size = list.size();
        String str = "";
        for (int i = 1; i <= size; i++) {
            str += "\n"+i+". "+list.get(i-1);
        }
        return str;
    }
}
