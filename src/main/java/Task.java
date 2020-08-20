public class Task {

    String type;
    String name;
    boolean done = false;
    String date;
    private static String doneText = "[\u25A0] ";
    private static String notDoneText = "[\u25A1] ";

    Task(String type, String name, String date) {
        this.type = type;
        this.name = name;
        this.date = date;
    }

    public String toString() {
        return name;
//        return done ? doneText + name : notDoneText + name;
    }

    public void markDone() {
        done = true;
    }
}
