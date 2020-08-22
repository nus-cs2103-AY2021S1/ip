public class Events extends Task {
    protected String time;

    public Events(String task) {
        // date = 'at Sunday 2-4pm'
        String time = task.substring(task.indexOf("/") + 1, task.length());
        time = Task.reformatDate(time); // (by: Sunday)

        // task = project meeting
        task = task.substring(0, task.indexOf("/") - 1);
        this.task = task;
        this.time = time;
    }

    public Events(String task, String time, boolean done) {
        this.task = task;
        this.time = time;
        this.done = done;
    }

    @Override
    public String toString() {
        String doneIndicator = this.done ? "[✓]" : "[✗]";
        return "[E]" + doneIndicator + " " + this.task + " " + this.time;
    }

    @Override
    public String parseToSaveFormat() {
        String res = "";
        String isDoneStr = this.done ? "1" : "0";
        res = "E - " + isDoneStr + " - " + this.task + " - " + this.time;
        return res;
    }

}
