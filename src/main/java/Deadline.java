public class Deadline extends Task {
    String taskBy;

    Deadline(String taskName, String taskBy) {
        super(taskName);
        this.taskBy = taskBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.taskBy + ")";
    }

    @Override
    public String toFileFormat() {
        return "D|" + (this.isDone ? "1" : "0") + "|" + this.taskName + "|" + this.taskBy;
    }

    static Deadline fromFileFormat(String fileFormatString) {
        String[] tokens = fileFormatString.split("\\|");
        Deadline loaded = new Deadline(tokens[2], tokens[3]);
        if (tokens[1].equals("1")) {
            loaded.setDone();
        }
        return loaded;
    }
}