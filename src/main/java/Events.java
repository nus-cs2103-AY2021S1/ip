import java.util.ArrayList;

public class Events extends Task {
    private String eventTime;

    public Events(String taskTitle, String eventTime) {
        super(taskTitle);
        this.eventTime = eventTime;
    }

    public static void addEventTask(String taskTitle, String eventTime, ArrayList<Task> tasks) {
        Events newEvent = new Events(taskTitle, eventTime);
        tasks.add(newEvent);

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
        return "[E]" + super.toString() + " (at:" + eventTime + ")";
    }
}
