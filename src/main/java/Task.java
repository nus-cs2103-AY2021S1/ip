import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static void getListOfTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            String nothing = "   ____________________________________________________________"
                    + "\n    Can't seem to find any task hmm..."
                    + "\n   ____________________________________________________________\n";
            System.out.println(nothing);
        } else {
            String lst = "   ____________________________________________________________"
                    + "\n    Here are the task(s) in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                int index = i + 1;
                lst += "\n    " + index + ". " + tasks.get(i);
            }
            lst += "\n   ____________________________________________________________\n";
            System.out.println(lst);
        }
    }

    public static void createTask(ArrayList<Task> tasks, String task) {
        Task newTask = new Task(task);
        tasks.add(newTask);
        String str = "   ____________________________________________________________"
                + "\n    Got it. I've added this task:"
                + "\n      " + tasks.get(tasks.size() - 1)
                + "\n    Now you have " + tasks.size() + " task(s) in the list."
                + "\n   ____________________________________________________________\n";
        System.out.println(str);
    }

    public static void done(ArrayList<Task> tasks, Integer index) {
        tasks.get(index - 1).markAsDone();
        String str = "   ____________________________________________________________"
                    + "\n    Nice! I've marked this task as done:\n      "
                    + tasks.get(index - 1)
                    + "\n   ____________________________________________________________\n";
        System.out.println(str);
    }

    public static void delete(ArrayList<Task> tasks, Integer index) {
        String str = "   ____________________________________________________________"
                   + "\n    Noted. I've removed this task:\n      "
                   + tasks.remove(index - 1)
                   + "\n    Now you have " + tasks.size() +" task(s) in the list."
                   + "\n   ____________________________________________________________\n";
        System.out.println(str);
    }

    public static void invalid_input(String invalid) {
        String str = "   ____________________________________________________________"
                + "\n    Invalid input: " + invalid
                + "\n   ____________________________________________________________\n";
        System.out.println(str);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
