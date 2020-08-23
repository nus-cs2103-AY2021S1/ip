package main.java;

public class Task {
    protected String taskName;
    protected Boolean done;

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

    public Boolean isDone(){
        return this.done;
    }

    public String getTaskName(){
        return this.taskName;
    }

    @Override
    public String toString(){
        String finished = this.done ? "✓" : "✗";
        String toReturn = "[" + finished + "]" + taskName;
        return toReturn;
    }
}
