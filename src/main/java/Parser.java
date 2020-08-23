public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }
    public void checkForInvalidInput(String input) throws DukeException {
        if (!input.contains("todo")) {
            if (!input.contains("deadline")) {
                if (!input.contains("event")) {
                    if (!input.contains("done")) {
                        if (!input.contains("bye")) {
                            if (!input.contains("list")) {
                                if (!input.contains("delete")) {
                                    Ui.printLines();
                                    throw new DukeException("I don't know what that means! Try again.\n"
                                            + "\n-----------------------------------------------\n");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void read(String input) {
        try {
            this.checkForInvalidInput(input);
            if (input.equals("list")) {
                this.showTaskList();
            } else if (input.contains("done")) {
                taskList.completeTask(input);
            } else if (input.contains("delete")) {
                taskList.deleteTask(input);
            } else if (input.contains("todo")) {
                taskList.addToDo(input);
            } else if (input.contains("deadline")) {
                taskList.addDeadline(input);
            } else if (input.contains("event")) {
                taskList.addEvent(input);
            } else {

            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showTaskList() {
        Ui.print(taskList.toString());
    }
}
