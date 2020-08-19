package main.java;

public class Task {
    String taskName;
    Boolean done;

    public Task(String taskName) throws DukeInvalidTaskException {
        if (!taskName.equals(null) && !taskName.equals(" ")) {
            this.taskName = taskName;
            this.done = false;
        } else {
            throw new DukeInvalidTaskException();
        }
    }

    public void checkOff() {
        this.done = true;
    }

    @Override
    public String toString(){
        String finished = this.done ? "✓" : "✗";
        String toReturn = "[" + finished + "]" + taskName;
        return toReturn;
    }
}
