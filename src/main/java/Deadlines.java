import java.util.ArrayList;

public class Deadlines extends Task {
    private String deadlineTime;

    public Deadlines(String taskTitle, String deadlineTime) {
        super(taskTitle);
        this.deadlineTime = deadlineTime;
    }

    public static void addDeadlineTask(String taskTitle, String deadlineTime, ArrayList<Task> tasks) {
        Deadlines newDeadline = new Deadlines(taskTitle, deadlineTime);
        tasks.add(newDeadline);

        String outputMsg = "\n___________________________________________________________"
                + "\n (^.^)"
                + "\n Got it. I've added this task:"
                + "\n     " + tasks.size() + "." + tasks.get(tasks.size() - 1)
                + "\n Now you have " + tasks.size() + " tasks in the list."
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + deadlineTime + ")";
    }
}
