package duke;

public class FindCommand extends PrintTaskCommand {
    public FindCommand(TaskList tasklist, String input) {
        super(Command.FIND, tasklist, input);
    }

    /**
     * Returns all the matching tasks
     *
     * @return String containing the description of all matching tasks.
     * @throws DukeException If no keyword was provided by user.
     */
    public String execute() throws DukeException {
        if (isValidFormat(userInput)) {
            TaskList newTaskList = createListOfTasksContainingKeyword();
            if (newTaskList.size() == 0) {
                return Ui.informNoTaskFound();
            }
            return outputTasksInTaskList(newTaskList, true);
        } else {
            throw new DukeException("What are you trying to find??");
        }
    }

    /**
     * Checks if the user input is in a valid format supported by Duke.
     *
     * @param input Input of the User.
     * @return True if user input is in valid format.
     */
    private boolean isValidFormat(String input) {
        String[] parsedInput = input.split(Parser.SPACE);
        return parsedInput.length == 2;
    }

    /**
     * Creates a new TaskList with only tasks that contains the keyword.
     *
     * @return New TaskList.
     */
    private TaskList createListOfTasksContainingKeyword() {
        String[] parsedInput = userInput.split(Parser.SPACE);
        String keyword = parsedInput[1].toLowerCase();
        TaskList listOfTasksContainingKeyword = new TaskList();
        for (int i = 0; i < this.taskList.size(); i++) {
            Task tempTask = taskList.get(i);
            String taskDescription = tempTask.getDescription().toLowerCase();
            if (taskDescription.contains(keyword)) {
                listOfTasksContainingKeyword.add(tempTask);
            }
        }
        return listOfTasksContainingKeyword;
    }
}
