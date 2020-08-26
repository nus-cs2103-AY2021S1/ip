package main.java;

class DoneCommand extends Command {

    DoneCommand() {
        this.commandText = "done";
    }

    @Override
    public void execute(String text, TaskList taskList) {
        int index = Integer.parseInt(text);
        taskList.completeTask(index);
    }
}
