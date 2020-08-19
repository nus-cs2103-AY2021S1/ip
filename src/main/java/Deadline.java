public class Deadline extends Task{
    String time;
    protected Deadline(String line) {
        super();
        String[] command = line.split(" \\/by ");
        this.item = command[0];
        this.time = command[1];
        taskType = "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + time + ")";
    }
}
