public class FindCommand extends Command {
    private String keyword;
    boolean isExit;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

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

    public boolean exitCheck() {
        isExit = false;
        return isExit;
    }
}