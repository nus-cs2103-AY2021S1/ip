package duke.task;

import duke.exceptions.DukeException;

public class Todo extends Task {

    public Todo(String s) throws DukeException {
        super(s);
    }

    /**
     * Constructor that is overloaded to create a deadline object from the database.
     *
     * @param doneStatus the state of the task from the user's previous session
     * @param s the description of the todo
     */
    public Todo(int doneStatus, String s) {
        super(doneStatus, s);
    }

    @Override
    public String formatTaskForDatabase() {
        int status = super.getDoneStatus() ? 1 : 0;
        return "T|" + status + "|" + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + super.description;
    }
}
