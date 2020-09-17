package duke;

/**
 * Handle user input and control the program's logic
 */
class LogicController {
    private final TaskList taskList;

    LogicController(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * start reading in users' input and parse them, then return the response
     *
     * @return program response to users' input
     */
    String run(String command) {
        try {
            if (command.equals("bye")) {
                Ui.exit();
                System.exit(0);
            } else if (command.equals("list")) {
                taskList.list();
            } else if (command.startsWith("done")) {
                taskList.markDone(command.substring(4));
            } else if (command.startsWith("deadline") || command.startsWith("event")
                    || command.startsWith("todo")) {
                taskList.addTask(command);
            } else if (command.startsWith("delete")) {
                taskList.delete(command.substring(6));
            } else if (command.startsWith("find")) {
                taskList.find(command.substring(4));
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            Ui.printException(e);
        }
        return Ui.getResponse();
    }
}
