import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    static final String INDENT = "    ";

    private List<Task> list;

    public Duke() {
        this.list = new ArrayList<Task>();
    }

    public String addTask(String t) {
        this.list.add(new Task(t));
        return "added: " + t;
    }

    public String markDone(int index) {
        Task task = list.get(index-1);
        task.markAsDone();
        return "Nice! I've marked this task as done: \n" +
                "      " + task;
    }

    public void printList() {
        String s = "";
        for (int i = 0; i < list.size(); i ++) {
            s += (i + 1) + "." + list.get(i);
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
        Duke duke = new Duke();

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
                duke.printList();
            } else if (nextLine.startsWith("done")) {
                String[] tokens = nextLine.split(" ");
                printWindow(duke.markDone(Integer.parseInt(tokens[1])));
            } else {
                printWindow(duke.addTask(nextLine));
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
