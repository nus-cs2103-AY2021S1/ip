package duke.task;

import duke.util.DukeException;
import java.util.regex.Pattern;

public abstract class Task {
    protected String description;
    protected String tag;
    protected boolean isDone;

    protected Task(String description, String tag, boolean isDone) {
        this.description = description;
        this.tag = tag;
        this.isDone = isDone;
    }

    public void complete() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    public static Task parse(String str) throws DukeException {
        String[] input = str.split(Pattern.quote(" | "));
        try {
            boolean isDone = input[1].equals("1");
            switch (input[0]) {
                case "T":
                    return new ToDo(input[2], isDone);
                case "D":
                    return new Deadline(input[2], input[3], isDone);
                case "E":
                    return new Event(input[2], input[3], isDone);
                default:
                    throw new DukeException("One or more Tasks are wrongly tagged!");
            }
        } catch (ArrayIndexOutOfBoundsException aiooe) {
            throw new DukeException("One or more Tasks have too few arguments!");
        }
    }

    public String toSave() {
        return String.format("%s | %s | %s", this.tag, isDone ? "1" : "0", this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.tag, this.getStatusIcon(), this.description);
    }
}
