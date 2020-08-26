public class Event extends Task {
    protected String at;

    public Event(String task, String at) {
        super(task);
        this.at = at;
    }

    @Override
    public String writeMessage() {
        String done = "";
        if (this.done) {
            done = "✓";
        } else {
            done = "✗";
        }
        return String.format("E | %s | %s", done, this.task, this.at);
    }

    @Override
    public String toString() {
        String[] ats = at.split(" ");
        String str = " (";
        for (int i = 0; i < ats.length; i++) {
            if (i == 0) {
                str += ats[i] + ": ";
            } else if (i == ats.length - 1) {
                str += ats[i];
            } else {
                str += ats[i] + " ";
            }
        }
        return "[E][" + (this.done ? "✓" : "✗") + "] " + this.task + str + ")";
    }
}
