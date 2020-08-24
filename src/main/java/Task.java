public abstract class Task {

    boolean done;
    String task;

    Task(String task) {
        this.done = false;
        this.task = task;
    }

    Task(String task, boolean done) {
        this.done = done;
        this.task = task;
    }

    String markDone() {
        this.done = true;
        return "Great job, keep it up!\n" + this.toString();
    }

    String revertDone() {
        this.done = false;
        return "Guess you made a mistake huh?\n" + this.toString();
    }

    String getSaveString() {
        String result = "";
        if (this.done) {
            result += "[1] ";
        } else {
            result += "[0] ";
        }
        result += this.task;
        return result;
    }

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
