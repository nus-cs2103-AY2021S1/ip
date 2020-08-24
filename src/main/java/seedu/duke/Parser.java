package seedu.duke;

/**
 * Parser processes each command from Ui and executes program accordingly.
 */
public class Parser {
    private TaskList taskLists;

    /**
     * Initialize an instance of a Parser.
     *
     * @param taskLists List of Tasks stored in a TaskList class.
     */
    public Parser(TaskList taskLists) {
        this.taskLists = taskLists;
    }

    /**
     * Check for invalid inputs.
     *
     * @param input String that is input by user.
     * @throws DukeException if input does not contain any of the keywords in the method.
     */
    public void checkForInvalidInput(String input) throws DukeException {
        if (!input.contains("todo")) {
            if (!input.contains("deadline")) {
                if (!input.contains("event")) {
                    if (!input.contains("done")) {
                        if (!input.contains("bye")) {
                            if (!input.contains("list")) {
                                if (!input.contains("delete")) {
                                    if (!input.contains("find")) {
                                        Ui.printLines();
                                        throw new DukeException("I don't know what that means! Try again.\n"
                                                + "\n-----------------------------------------------\n");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Process input by user and execute accordingly.
     *
     * @param input String input by user.
     */
    public void read(String input) {
        try {
            this.checkForInvalidInput(input);
            if (input.equals("list")) {
                this.showTaskList();
            } else if (input.contains("done")) {
                taskLists.completeTask(input);
            } else if (input.contains("delete")) {
                taskLists.deleteTask(input);
            } else if (input.contains("todo")) {
                taskLists.addToDo(input);
            } else if (input.contains("deadline")) {
                taskLists.addDeadline(input);
            } else if (input.contains("event")) {
                taskLists.addEvent(input);
            } else if (input.contains("find")){
                taskLists.find(input);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Print out list of Tasks.
     */
    public void showTaskList() {
        Ui.print(taskLists.toString());
    }
}
