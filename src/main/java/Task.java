public class Task {

    String name;
    boolean done = false;
    private static String doneText = "[\u25A0] ";
    private static String notDoneText = "[\u25A1] ";

    Task(String name) {
        this.name = name;
    }

    public String toString() {
        return done ? doneText + name : notDoneText + name;
    }

    public void markDone() {
        done = true;
    }
}
