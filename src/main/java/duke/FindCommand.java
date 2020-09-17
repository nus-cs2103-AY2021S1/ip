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
        TaskList listOfTasksContainingKeyword = new TaskList();
        for (int i = 0; i < this.taskList.size(); i++) {
            Task tempTask = taskList.get(i);
            String taskDescription = tempTask.getDescription();
            // contains will return true for "bookstore" when searching for "book"
            // contains is case - sensitive "Book" and "book" is different
            if (taskDescription.contains(keyword)) {
                listOfTasksContainingKeyword.add(tempTask);
            }
        }
        return listOfTasksContainingKeyword;
    }
}
