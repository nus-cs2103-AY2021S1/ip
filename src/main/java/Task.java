import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    abstract String getTaskDetailsForSave();

    public static PrintWriter initialiseWriter() throws DukeException {
        try {
            PrintWriter writer = new PrintWriter("data/TaskList.txt");
            return writer;
        } catch (IOException e) {
            throw new DukeException("OOPS! I'm sorry, there is an error during initialisation :-(");
        }
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }
}
