package duke.task;

import duke.exceptions.DukeStorageException;
import duke.exceptions.DukeTaskCreationException;

public class ToDo extends Task {

    private ToDo(String description) {
        super(description);
    }

    public static ToDo createTask(String details) throws DukeTaskCreationException {
        if (details == null) {
            throw new DukeTaskCreationException("I need something to work with.");
        }
        return new ToDo(details);
    }

    public String encode() {
        return String.format("T|%s|%s", super.completed ? "Y" : "N", super.description);
    }

    public static ToDo decode(String code) throws DukeStorageException {
        if (code.charAt(0) == 'T') {
            String[] content = code.split("\\|", 3);
            if (content.length != 3) {
                throw new DukeStorageException("There are some holes in my memory...");
            }
            ToDo newToDo = new ToDo(content[2]);
            if (content[1].equals("Y")) {
                newToDo.setCompleted();
            } else if (!content[1].equals("N")) {
                throw new DukeStorageException("There are some holes in my memory...");
            }
            return newToDo;
        } else {
            throw new DukeStorageException("Something doesn't seem right...");
        }
    }

    @Override
    public boolean match(String searchParameter) {
        return description.contains(searchParameter) || searchParameter.contains(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
