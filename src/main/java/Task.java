public class Task {
    String desc;
    boolean isDone;

    public Task(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        String check = isDone ? "\u2713" : "\u2718";
        return check + " " + desc;
    }

    public void done() {
        isDone = true;
    }



}
