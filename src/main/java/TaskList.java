import java.util.ArrayList;

public class TaskList {
    static ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public static void addTask(Task task) {
        tasks.add(task);
        String str = "   ____________________________________________________________"
                + "\n    Got it. I've added this task:"
                + "\n      " + task
                + "\n    Now you have " + tasks.size() + " task(s) in the list."
                + "\n   ____________________________________________________________\n";
        System.out.println(str);
    }

    public static void getListOfTasks() throws DukeException {
        if (tasks.isEmpty()) {
            try {
                throw new DukeException("", DukeExceptionType.EMPTY_LIST);
            } catch (DukeException e) {
                System.err.println(e);
            }
        } else {
            String lst = "   ____________________________________________________________"
                    + "\n    Here are the tasks in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                int index = i + 1;
                lst += "\n    " + index + ". " + tasks.get(i);
            }
            lst += "\n   ____________________________________________________________\n";
            System.out.println(lst);
        }
    }

    public static void done(Integer index) {
        tasks.get(index - 1).markAsDone();
        String str = "   ____________________________________________________________"
                + "\n    Nice! I've marked this task as done:\n      "
                + tasks.get(index - 1)
                + "\n   ____________________________________________________________\n";
        System.out.println(str);
    }

    public static void delete(Integer index) {
        String str = "   ____________________________________________________________"
                + "\n    Noted. I've removed this task:\n      "
                + tasks.remove(index - 1)
                + "\n    Now you have " + tasks.size() + " task(s) in the list."
                + "\n   ____________________________________________________________\n";
        System.out.println(str);
    }


}
