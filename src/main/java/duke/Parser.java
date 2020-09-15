package duke;

/**
 * Handle user input and control the program's logic
 */
class Parser {
    private final TaskList taskList;

    Parser(TaskList taskList) {
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
                taskList.markDone(command.substring(5));
            } else if (command.startsWith("deadline") || command.startsWith("event")
                    || command.startsWith("todo")) {
                taskList.addTask(command);
            } else if (command.startsWith("delete")) {
                taskList.delete(command.substring(7));
            } else if (command.startsWith("find")) {
                taskList.find(command.substring(5));
            } else {
                throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException e) {
            Ui.printException(e);
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printException(new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means"
                    + " :-("));
        }
        return Ui.getResponse();
    }
}
