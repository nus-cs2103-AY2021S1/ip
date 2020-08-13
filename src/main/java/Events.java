import java.util.ArrayList;

public class Events extends Task {

    String calender;

    public Events(String description, String calender) {
        super(description);
        this.calender = calender;
    }

    public static void createEvent(ArrayList<Task> tasks, String event, String scheduled) {
        Events events = new Events(event, scheduled);
        tasks.add(events);
        String str = "   ____________________________________________________________"
                + "\n    Got it. I've added this task:"
                + "\n      " + tasks.get(tasks.size() - 1)
                + "\n    Now you have " + tasks.size() + " tasks in the list."
                + "\n   ____________________________________________________________\n";
        System.out.println(str);
    }

    public static void invalid_input() {
        invalid_input("Missing description and/or scheduled time!\n" +
                "    Did you follow the below format???\n" +
                "    event <event description> /at <deadline>");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + calender + ")";
    }
}
