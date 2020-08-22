public class ToDo extends Task {

    public ToDo(String description) throws WrongFormatException {
        super(description, "[T]", "todo", false);
    }

    public ToDo(String description, boolean isDone) throws WrongFormatException {
        super(description, "[T]", "todo", isDone);
    }
}