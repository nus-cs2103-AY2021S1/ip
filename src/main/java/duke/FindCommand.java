package duke;

public class FindCommand extends PrintTaskCommand {
    public FindCommand(TaskList tasklist, String input) {
        super(Command.FIND, tasklist, input);
    }

    public String execute() throws DukeException{
        if (isValidFormat(userInput)) {
            TaskList newTaskList = createListOfTasksContainingKeyword();
            return outputTasksInTaskList(newTaskList, true);
        } else {
            throw new DukeException("What are you trying to find??");
        }
    }

    private boolean isValidFormat(String input) {
        String[] parsedInput = input.split(Parser.SPACE);
        return parsedInput.length == 2;
    }

    private TaskList createListOfTasksContainingKeyword() {
        String[] parsedInput = userInput.split(Parser.SPACE);
        String keyword = parsedInput[1];
        String keywordsLowerCase = keyword.toLowerCase();
        TaskList listOfTasksContainingKeyword = new TaskList();
        for (int i = 0; i < this.taskList.size(); i++) {
            Task tempTask = taskList.get(i);
            String taskDescription = tempTask.getDescription();
            String taskDescriptionLowerCase = taskDescription.toLowerCase();
            if (taskDescriptionLowerCase.contains(keywordsLowerCase)) {
                listOfTasksContainingKeyword.add(tempTask);
            }
        }
        return listOfTasksContainingKeyword;
    }
}
