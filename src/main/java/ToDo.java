public class ToDo extends Task {

    public ToDo(String description) throws WrongFormatException {
        super(description, "[T]", "todo");
    }
}