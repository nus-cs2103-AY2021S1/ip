public class FindCommand extends Command {
    protected boolean isExit;
    private String[] searchTerms;

    /**
     * Instantiates FindCommand object.
     * @param searchTerms Keyword to find.
     */
    public FindCommand(String[] searchTerms) {
        this.searchTerms = searchTerms;
    }

    /**
     * Runs command to handle find command.
     *
     * @param arrayOfTasks Array of Tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     * @return Response object
     */
    public Response runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        assert arrayOfTasks != null || ui != null || storage != null
                : "arrayOfTasks, Ui and Storage objects cannot be null";

        TaskList matchedTasksList = new TaskList();
        int arraySize = arrayOfTasks.taskArraySize();
        int index = 0;
        while (index < arraySize) {
            Task matchedTask = arrayOfTasks.get(index);
            String[] splitElements = matchedTask.description.split(" ");
            boolean isMatching = false;

            String delimiter = "";
            String keyword = String.join(delimiter, searchTerms);
            String taskInList = String.join(delimiter, splitElements);
            int keywordLength = keyword.length();
            int taskLength = taskInList.length();

            if (isSubSequence(keyword, taskInList, keywordLength, taskLength)) {
                isMatching = true;
            }

            if (!isMatching) {
                // Do nothing.
            } else {
                matchedTasksList.addTask(matchedTask);
            }
            index++;
        }

        if (matchedTasksList.taskArraySize() == 0) {
            return ui.noMatchMessage();
        } else {
            return ui.matchingMessage(matchedTasksList);
        }
    }

    /**
     * Since this is not a exit command, it does not signal the program to exit.
     *
     * @return exitCheck as False
     */
    public boolean exitChecker() {
        isExit = false;
        return isExit;
    }

    static boolean isSubSequence(String keyword, String task, int keywordLength, int taskLength) {
        if (taskLength == 0) {
            return false;
        }

        if (keywordLength == 0) {
            return true;
        }

        char keywordCharacter = keyword.charAt(keywordLength - 1);
        char taskCharacter = task.charAt(taskLength - 1);

        // Checks if last characters of two strings matches
        if (keywordCharacter == taskCharacter) {
            return isSubSequence(keyword, task, keywordLength - 1, taskLength - 1);
        }

        // Recurse if last characters do not match
        return isSubSequence(keyword, task, keywordLength, taskLength - 1);
    }
}
