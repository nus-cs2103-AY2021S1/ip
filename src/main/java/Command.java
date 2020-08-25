package main.java;

import java.io.IOException;

/**
 * The command class is used to handle and execute commands
 * after input by user is processed by the parser.
 */

public class Command {
    private String task;
    private String action;
    private boolean end;

    /**
     * Initializes a Command object
     *
     * @param task The input task given by the user
     * @param action The type of action take as given by the task.
     */
    public Command(String task, String action) {
        this.end = false;
        this.task = task;
        this.action = action;
    }
    /**
     * Executes the action on task given.
     *
     * @param taskList the current task list in use.
     * @param ui the user interface object.
     */

    public void execute(TaskList taskList, Ui ui) throws IOException, DukeException {
        if(action == null){
            ui.showInvalidCommand();
        } else if (action.equals("bye")) {
            this.end = true;
            ui.showEnd();
            ui.close();
        } else if (action.equals("list")) {
            ui.showList(taskList.getList());
        } else if (action.indexOf("done") == 0) {
            String[] split = action.split("done ");
            try {
                Integer taskNumber = Integer.parseInt(split[1]);
                taskList.checkOff(taskNumber);
            } catch (NumberFormatException err) {
                System.out.println("Please input a valid number");
            }
        } else if (action.equals("delete")) {
            taskList.delete(task);
        } else if (action.equals("todo")) {
            taskList.addToDo(task);
            ui.showAdded();
        } else if (action.equals("deadline")) {
            taskList.addDeadline(task);
            ui.showAdded();
        } else if (action.equals("event")) {
            taskList.addEvent(task);
            ui.showAdded();
        } else {
            ui.showInvalidCommand();
        }
    }

    /**
     * Returns a Boolean, with true indicating that the command given ends the interaction
     * with the user, false indicating otherwise.
     */

    public boolean isEnd(){
        return this.end;
    }
}
