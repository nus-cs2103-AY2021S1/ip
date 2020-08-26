package main.java;

class DeleteCommand extends Command {

    DeleteCommand() {
        this.commandText = "delete";
    }

    @Override
    public void execute(String text, TaskList taskList) {
        int index = Integer.parseInt(text);
        taskList.deleteTask(index);
    }
}
