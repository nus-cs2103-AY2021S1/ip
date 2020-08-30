package main.java.duke;

public class Parser {

    /**
     * Returns a task object according to the type of Task added by the user
     * @param input the string user input
     * @return a task object according to the type of Task added by the user
     */
    public Task handleInput(String input) {
        String[] commandComponents = input.split(" ", 2);
        String taskType = commandComponents[0];
        Task taskToReturn;
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
