import java.util.ArrayList;

public class Deadlines extends Task {
    private String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public static void createDeadline(ArrayList<Task> tasks, String task, String due) {
        Deadlines deadlines = new Deadlines(task, due);
        tasks.add(deadlines);
        String str = "   ____________________________________________________________"
                + "\n    Got it. I've added this task:"
                + "\n      " + tasks.get(tasks.size() - 1)
                + "\n    Now you have " + tasks.size() + " tasks in the list."
                + "\n   ____________________________________________________________\n";
        System.out.println(str);
    }

    public static void invalid_input() {
        invalid_input("Missing description and/or deadline!\n" +
                "    Did you follow the below format???\n" +
                "    deadline <task description> /by <deadline>");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + deadline + ")";
    }
}
