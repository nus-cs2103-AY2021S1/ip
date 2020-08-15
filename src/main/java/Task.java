public abstract class Task {

    boolean done;
    String task;

    Task(String task) {
        this.done = false;
        this.task = task;
    }

    String markDone() {
        this.done = true;
        return "Great job, keep it up!\n" + this.toString();
    };

    @Override
    public String toString() {
        String result = "";
        if (this.done) {
            result += "[\u2713] ";
        } else {
            result += "[\u2718] ";
        }
        result += this.task;
        return result;
    }
}
