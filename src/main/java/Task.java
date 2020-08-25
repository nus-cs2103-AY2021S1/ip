public class Task {

    String name;
    boolean done = false;

    Task(String name) {
        this.name = name;
    }

    public String getDescription() {
        return name;
    }

    public String toString() {
        return done ? Text.doneText + name : Text.notDoneText + name;
    }

    public String saveString() {
        return "T/break/" + this.done + "/break/" + name;
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
