public class Task {
    boolean done;
    String task;

    Task(String task) {
        this.done = false;
        this.task = task;
    }

    public String markDone() {
        this.done = true;
        return "Great job, keep it up!\n" + this.toString();
    }

    @Override
    public String toString() {
        String result = "";
        if (this.done) {
            result += "[✓] ";
        } else {
            result += "[x] ";
        }
        result += this.task;
        return result;
    }
}
