package main.java.duke.command;

import main.java.duke.*;
import main.java.duke.task.ToDo;

public class ToDoCommand extends Command {

    String[] commandDetails;

    public ToDoCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println(" Got it. I've added this task: ");
        ToDo toDo = new ToDo(commandDetails[1]);
        tasks.getTasks().add(toDo);
        System.out.println(String.format("   %s \n Now you have %d tasks in the list. ",
                toDo, tasks.getTasks().size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
