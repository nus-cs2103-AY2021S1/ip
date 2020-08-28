package duke.exceptions;

import java.util.Objects;

public class TaskNotSpecifyException extends Exception {

    private String typeOfCommand;

    public TaskNotSpecifyException(String err, String typeOfCommand) {
        super(err);
        this.typeOfCommand = typeOfCommand;
    }

    @Override
    public String getMessage() {
        if (Objects.equals(typeOfCommand, "DELETE")) {
            return "🙃Your majesty! Please specify which task you want me to delete. Thanks";
        } else if (Objects.equals(typeOfCommand, "DONE")) {
            return "🙃Your majesty! Please specify which task you want me to mark as done. Thanks";
        }
        return "TaskNotSpecifyException being thrown";
    }
}
