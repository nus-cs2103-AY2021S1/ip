package duke.operation;

import duke.task.TaskList;

public class FindOperation extends Operation {
    private final TaskList taskList;
    private final String searchWord;

    public FindOperation(TaskList taskList, String searchWord) {
        this.taskList = taskList;
        this.searchWord = searchWord;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute() {
        TaskList foundTasks = this.taskList.findString(searchWord);
        if (foundTasks.getCurrCapacity() == 0) {
            return String.format("I have found no tasks that match: %s", this.searchWord);
        }
        return "Here are the tasks I have found:\n" + foundTasks.toString();
    }
}
