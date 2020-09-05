package main.java.duke;

/**
 * The class responsible for identifying the type of task the user is trying to create and add to his/her list.
 */
public class Parser {

    /**
     * Returns a task object according to the type of Task added by the user
     * @param input the string user input
     * @return a task object according to the type of Task added by the user
     * @throws EmptyDescriptionException when the given command has the task type but not the task description.
     */
    public Task handleInput(String input) throws EmptyDescriptionException {
        String[] commandComponents = input.split(" ", 2);
        String taskType = commandComponents[0];
        Task taskToReturn;
        if (commandComponents.length == 1) {
            EmptyDescriptionException emptyDescException = new EmptyDescriptionException();
            throw emptyDescException;
        }
        if (taskType.equals("event")) {
            return new Event(commandComponents[1]);
        } else if (taskType.equals("deadline")) {
            return new Deadline(commandComponents[1]);
        } else if (taskType.equals("todo")) {
            return new Todo(commandComponents[1]);
        } else {
            return null;
        }
    }

}
