public class ToDo extends Task{
    public ToDo(String todo) throws EmptyDescriptionException {
        super(todo.substring(5));
        if (description.length() <= 5) {
            throw new EmptyDescriptionException("oops! the description of a todo cannot be empty");
        }
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
