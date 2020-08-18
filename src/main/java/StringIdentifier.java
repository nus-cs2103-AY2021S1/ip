import java.util.List;
import java.util.ArrayList;

public class StringIdentifier {
    private static boolean isProgramRunning = true;
    private static List<Task> lst = new ArrayList<>();

    public boolean isRunning() {
        return this.isProgramRunning;
    }

    public void checker(String str) {
        System.out.println("    ____________________________________________________________");
        if (str.equals("bye")) {
            close();

        } else if (str.equals("list")) {
            displayList();

        } else if (str.substring(0, 5).equals("done ")) {
            int length = str.length();
            String index = str.substring(5, length);
            markDone(Integer.parseInt(index));

        } else if (str.substring(0, 5).equals("todo ")) {
            int length = str.length();
            Todo newTodo = new Todo(str.substring(5, length));
            store(newTodo);

        } else if (str.substring(0, 9).equals("deadline ")) {
            int length = str.length();
            int end = str.indexOf("/by");
            Deadline newDeadline = new Deadline(str.substring(9, end),
                                                str.substring(end + 3, length));
            store(newDeadline);

        } else if (str.substring(0, 6).equals("event ")) {
            int length = str.length();
            int end = str.indexOf("/at");
            Event newEvent = new Event(str.substring(6, end),
                                       str.substring(end + 3, length));
            store(newEvent);

        } else {
            System.out.println("Incorrect input");
        }

        System.out.println("    ____________________________________________________________\n");
    }

    public void store(Task task) {
        this.lst.add(task);
        System.out.println("     Got it. I've added this task:\n"
                         + "       " + task);
        System.out.println("     Now you have " + this.lst.size() + " task(s) in the list.");
    }

    public void displayList() {
        int size = this.lst.size();
        System.out.println("     Here are the tasks in your list:");

        int index = 1;
        for (int i = 0; i < size; i++) {
            System.out.println("     " + index + "." + this.lst.get(i));
            index ++;
        }
        index ++;
    }

    public void markDone(int index) {
        int realIndex = index - 1;
        Task taskSubject = this.lst.get(realIndex);
        taskSubject.markAsDone();
        System.out.println("     Nice! I've marked this task as done:\n"
                         + "       " + taskSubject);
    }

    public void close() {
        this.isProgramRunning = false;
        System.out.println("     Bye. Hope to see you again soon!");
    }
}
