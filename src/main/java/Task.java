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
                    + "\n    Here are the tasks in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                int index = i + 1;
                lst += "\n    " + index + ". " + tasks.get(i);
            }
            lst += "\n   ____________________________________________________________\n";
            System.out.println(lst);
        }
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
