public class Todo extends Task {

    Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T|" + (this.isDone ? "1" : "0") + "|" + this.taskName;
    }

    static Todo fromFileFormat(String fileFormatString) {
        String[] tokens = fileFormatString.split("\\|");
        Todo loaded = new Todo(tokens[2]);
        if (tokens[1].equals("1")) {
            loaded.setDone();
        }
        return loaded;
    }
}