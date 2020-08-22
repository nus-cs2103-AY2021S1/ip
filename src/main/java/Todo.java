import java.io.FileWriter;

public class Todo extends Task{
    Todo(String description) {
        super(description);
    }

    Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    public static Todo createTask(String message) throws DukeException{
        String errMessage = " Oops!! You forgot to tell me what this task is about... *woof*\n";
        try {
            String description = message.substring(5);
            if (description.isBlank()) {
                String exMessage = Print.printFormat(errMessage);
                throw new DukeException(exMessage);
            } else {
                return new Todo(description);
            }
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            String exMessage = Print.printFormat(errMessage);
            throw new DukeException(exMessage);
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
