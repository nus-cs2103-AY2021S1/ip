package junimo.interaction;

import junimo.task.TaskList;

/**
 * Deals with parsing and making sense of user commands.
 */
public class Parser {
    private final TaskList taskList;

    /**
     * Constructs an instance of Parser that adds, retrieves and delete tasks from taskList.
     * @param taskList taskList that contains and handles the user's list of Tasks.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses user input commands and handles them appropriately.
     * Feedbacks to user if commands are not understandable/ in the wrong format.
     *
     * Handles the following command types: bye, done, delete, todo, deadline and event.
     */
    public String parseInputCommand(String inputCommand) {
        // "bye" breaks the while loop and causes the program to exit()
        if (!inputCommand.equals("bye")) {
            String[] splitNext = inputCommand.split(" ", 2);

            // "list" prints the task list
            if (inputCommand.equals("list")) {
                return taskList.list();

                // "done" checks off boxes, need to check for input errors
            } else if (splitNext[0].equals("done")) {
                try {
                    return taskList.markTaskAsDone(splitNext[1]);
                } catch (IndexOutOfBoundsException ex) {
                    String errMessage = "Please indicate which task you'd like to check off!";
                    System.out.println(errMessage);
                    return errMessage;
                }

                // to "delete" tasks from the taskList
            } else if (splitNext[0].equals("delete")) {
                try {
                    return taskList.deleteTask(splitNext[1]);
                } catch (IndexOutOfBoundsException ex) {
                    String errMessage = "Please indicate which task you'd like to delete!";
                    System.out.println(errMessage);
                    return errMessage;
                }

            } else if (splitNext[0].equals("find")) {
                if (splitNext.length == 1) {
                    return taskList.find("");
                } else {
                    return taskList.find(splitNext[1]);
                }

                // for ToDos, Deadlines, Events
            } else if (splitNext[0].equals("todo") || splitNext[0].equals("deadline") || splitNext[0].equals("event")) {
                try {
                    return taskList.add(inputCommand, false, true);
                } catch (IllegalArgumentException ex) {
                    String errMessage = ex.getMessage() + "\n";
                    System.out.println(errMessage);
                    return errMessage;
                }
            } else {
                String errMessage = "Sorry I don't know what that means!\n";
                System.out.println(errMessage);
                return errMessage;
            }
        }
        return Greeting.exit();
    }
}
