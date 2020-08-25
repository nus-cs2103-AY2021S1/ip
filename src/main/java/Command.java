package main.java;

public class Command {
    private String task;
    private String action;
    private boolean end;

    public Command(String task, String action) {
        this.end = false;
        this.task = task;
        this.action = action;
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        if (action == null) {
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

    public boolean isEnd(){
        return this.end;
    }
}
