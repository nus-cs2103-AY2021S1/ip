package duke.task;

import duke.storage.CsvToTask;

/**
 * A Task with no defined datetime
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean isCompleted, String description) {
        super(isCompleted, description);
    }

    @Override
    public String toCsv() {
        return CsvToTask.TODO + ","
                + this.isCompleted() + ","
                + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]"
                + "[" + this.isCompletedSymbol() + "]"
                + " " + this.getDescription();
    }

}
