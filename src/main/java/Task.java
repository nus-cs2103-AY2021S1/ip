public class Task {

    private String name;
    private boolean done = false;
    private static String doneText = "[\u25A0] ";
    private static String notDoneText = "[\u25A1] ";

    Task(String name) {
        this.name = name;
    }

    public String toString() {
        return done ? doneText + name : notDoneText + name;
    }

    public boolean markDone() {
        if (done) {
            return false;
        } else {
            done = true;
            return true;
        }
    }
}
