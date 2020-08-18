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

        } else {
            Task newTask = new Task(str);
            store(newTask);
        }

        System.out.println("    ____________________________________________________________\n");
    }

    public void store(Task task) {
        this.lst.add(task);
        System.out.println("     added: " + task.getDescription());
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
