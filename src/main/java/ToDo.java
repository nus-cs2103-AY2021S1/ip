package main.java;

public class ToDo extends Task {

    ToDo(String description) throws DukeException{
        super(description);
        if(description == null || description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty!");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
