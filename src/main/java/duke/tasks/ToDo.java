package duke.tasks;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;

/** This class represents a todo. */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * @return A String representing the ToDo object, to be used when saving todos to the storage file.
     */
    @Override
    public String txtFileFormat() {
        return "T" + DELIMITER + super.txtFileFormat();
    }

    /**
     * @return A String representing the ToDo object, to be used when printing the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public void updateDate(LocalDate date) throws DukeException {
        throw new DukeException("Todos don't have dates!");
    }

    @Override
    public void updateTime(LocalTime time) throws DukeException {
        throw new DukeException("Todos don't have times!");
    }

    @Override
    public void updateTime(LocalTime startTime, LocalTime endTime) throws DukeException {
        throw new DukeException("Todos don't have times!");
    }

    @Override
    public LocalDate getDate() {
        return null;
    }
}
