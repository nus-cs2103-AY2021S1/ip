public abstract class Task {
    String desc;
    boolean isDone;
    String symbol;
    String time = "";

    public Task(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        String check = isDone ? "\u2713" : "\u2718";
        return check + " " + symbol + " " + desc + " " + time;
    }

    public void done() {
        isDone = true;
    }

}
