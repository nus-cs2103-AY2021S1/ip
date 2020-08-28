/**
 * Implements methods for FindCommand.
 */
public class FindCommand extends Command {
    protected boolean isExit;
    private String keyword;

    /**
     * Instantiates FindCommand object.
     * @param keyword Keyword used to find matching tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Runs command to handle find command.
     *
     * @param arrayOfTasks Array of tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     */
    public void runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        TaskList matchedTasksList = new TaskList();
        int arraySize = arrayOfTasks.taskArraySize();
        int index = 0;
        while (index < arraySize) {
            Task matchedTask = arrayOfTasks.get(index);
            String[] splitElements = matchedTask.description.split(" ");
            int numOfElements = splitElements.length;
            boolean isMatching = false;
            int secondIndex = 0;
            while (secondIndex < numOfElements) {
                if (splitElements[secondIndex].strip().equalsIgnoreCase(keyword)) {
                    isMatching = true;
                    break;
                }
                secondIndex++;
            }
            if (!isMatching) {
                // Do nothing.
            } else {
                matchedTasksList.addTask(matchedTask);
            }
            index++;
        }

        if (matchedTasksList.taskArraySize() == 0) {
            ui.noMatchMessage();
        } else {
            ui.matchingMessage(matchedTasksList);
        }
    }

    /**
     * Checks if the program has to exit Duke.
     *
     * @return exitCheck as False
     */
    public boolean exitCheck() {
        isExit = false;
        return isExit;
    }
}
