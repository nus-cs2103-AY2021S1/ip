public class Deadlines extends Task {
    protected String date;

    public Deadlines(String task) {
        // date = 'by Sunday'
        String date = task.substring(task.indexOf("/") + 1, task.length());
        date = Task.reformatDate(date); // (by: Sunday)

        // task = return book
        task = task.substring(0, task.indexOf("/") - 1);
        this.task = task;
        this.date = date;
    }

    public Deadlines(String task, String date, boolean done) {
        this.task = task;
        this.date = date;
        this.done = done;
    }

    @Override
    public String toString() {
        String doneIndicator = this.done ? "[✓]" : "[✗]";
        return "[D]" + doneIndicator + " " + this.task + " " + this.date;
    }

}
