package task;

import exceptions.DukeException;
import exceptions.InvalidDescriptionException;

public class Todo extends Task {

    public Todo(String s) throws DukeException {
        super(s);
    }

    public Todo(int doneStatus, String s)  {
        super(doneStatus ,s);
    }



    @Override
    public String formatTaskForDatabase() {
        int status = super.isDone ? 1 : 0;
        return "T|" + status + "|" + super.description;
    }


    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + super.description;
    }
}
