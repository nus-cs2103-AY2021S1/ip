public class Todo extends Task {

    Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        String repr = "[T]" + super.toString();
        for (String tag : tags) {
            repr += " #" + tag;
        }
        return repr;
    }

    public String toFileFormat() {
        return "T|" + (this.isDone ? "1" : "0") + "|" + this.taskName  + "|" + tagsFileFormat();
    }

    /**
     * Load a todo task from the storage file format.
     *
     * @param fileFormatString storage file format string representation of the todo task
     */
    static Todo fromFileFormat(String fileFormatString) {
        String[] tokens = fileFormatString.split("\\|");
        Todo loaded = new Todo(tokens[2]);

        if (tokens.length >= 4) {
            String[] tags = tokens[3].split("#");
            for (String tag : tags) {
                loaded.addTag(tag);
            }
        }

        if (tokens[1].equals("1")) {
            loaded.setDone();
        }
        return loaded;
    }
}
