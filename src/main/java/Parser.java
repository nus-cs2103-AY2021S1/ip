public class Parser {
    public static boolean execute(TaskList taskList, Ui ui, Storage storage, String input) throws DukeException {
        if (input.equalsIgnoreCase("bye")) { //if user types "bye"
            ui.printGoodbye();
            return true;
        } else if (input.equalsIgnoreCase("list")) { //if user types "list"
            ui.printTasks(taskList);
            return false;
        } else if (input.toLowerCase().startsWith("done")) { //if user input starts with "done"
            taskList.handleDoneInput(input);
            storage.write(taskList);
            return false;
        } else if (input.toLowerCase().startsWith("delete")) {
            taskList.handleDeleteInput(input);
            storage.write(taskList);
            return false;
        } else if (input.toLowerCase().startsWith("todo")) {
            taskList.handleTodoInput(input);
            storage.write(taskList);
            return false;
        } else if (input.toLowerCase().startsWith("deadline")) {
            taskList.handleDeadlineInput(input);
            storage.write(taskList);
            return false;
        } else if (input.toLowerCase().startsWith("event")) {
            taskList.handleEventInput(input);
            storage.write(taskList);
            return false;
        } else {
            throw new DukeException("Please enter a valid deadline");
        }
    }
}
