package main.java;

public class Deadline extends Task{
    String date;

    public Deadline(String taskName, String date) throws DukeInvalidDateException, DukeInvalidTaskException {
        super(taskName);
        if(!date.equals(null) && !date.equals(" ")){
            this.date = date;
        } else {
            throw new DukeInvalidDateException();
        }
    }

    @Override
    public String toString() {
        String finished = this.done ? "✓" : "✗";
        String toReturn = "[D]" + "[" + finished + "] " + taskName + " (by: " + date +")";
        return toReturn;
    }
}
