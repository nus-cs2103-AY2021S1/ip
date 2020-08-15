public class Events extends Task {
    String time;

    public Events(String task) {
        // date = 'at Sunday 2-4pm'
        String time = task.substring(task.indexOf("/") + 1, task.length());
        time = Task.reformatDate(time); // (by: Sunday)

        // task = project meeting
        task = task.substring(0, task.indexOf("/") - 1);
        this.task = task;
        this.time = time;
    }

    @Override
    public String toString() {
        String doneIndicator = this.done ? "[✓]" : "[✗]";
        return "[E]" + doneIndicator + " " + this.task + " " + this.time;
    }

}
