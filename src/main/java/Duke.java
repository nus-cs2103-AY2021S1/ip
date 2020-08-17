import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final List<Task> storage = new ArrayList<>();
    private static final String border = "____________________________________________________________\n";

    public static boolean checkBye(String s) {
        return s.equals("bye");
    }

    public static void exitLine() {
        System.out.println(border + "Bye. Hope to see you again soon!\n" + border);
    }

    public static boolean checkList(String s) {
        return s.equals("list");
    }

    public static void displayList() {
        int listLen = storage.size();
        System.out.println(border.replace("\n", ""));
        for (int i = 1; i <= listLen; i++) {
            Task curr = storage.get(i - 1);
            System.out.println(i + "." + curr);
        }
        System.out.println(border);
    }

    public static void addTask(String s, String next) throws DukeException {
        Task toAdd = new Task("");
        if (s.matches("todo|deadline|event|done") && next.equals("")) {
            throw new DukeException("OOPS!!! The description of " + s + " cannot be empty");
        }

        switch (s) {
            case "todo":
                ToDo todo = new ToDo(next);
                storage.add(todo);
                toAdd = todo;
                break;
            case "deadline": {
                String[] ls = next.split("/by ");
                Deadline deadline = new Deadline(ls[0], ls[1]);
                storage.add(deadline);
                toAdd = deadline;
                break;
            }
            case "event": {
                String[] ls = next.split("/at ");
                Event event = new Event(ls[0], ls[1]);
                storage.add(event);
                toAdd = event;
                break;
            }
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");
        }
        
        System.out.println(
                border + "Got it. I've added this task:\n"
                        + "  " + toAdd + "\n"
                        + "Now you have " + storage.size() + " tasks in the list.\n"
                        + border
        );
    }

    public static boolean checkDone(String s) {
        return s.equals("done");
    }

    public static void doneTask(String s) {
        if (s.equals("")) {
            try {
                addTask("done", "");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return;
        }

        int i = Integer.parseInt(s);
        if (i < 1 || i > storage.size()) {
            System.out.println(
                    border + "You have entered an invalid number: " + i
                        + ". Please try again.\n" + border
            );
        } else {
            Task t = storage.get(i - 1);
            Task completed = t.setDone(true);
            storage.set(i - 1, completed);
            System.out.println(
                    border + "Nice! I've marked this task as done:\n" + "  "
                            + completed + "\n" + border
            );
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(border + "Hello! I'm Duke\n" + "What can I do for you?\n" + border);

        while (true) {
            String test = scan.next();
            if (checkBye(test)) {
                exitLine();
                break;
            } else {
                String next = scan.nextLine().replaceFirst(" ", "");
                if (checkList(test)) {
                    displayList();
                } else if (checkDone(test)) {
                    doneTask(next);
                } else {
                    try {
                        addTask(test, next);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}
