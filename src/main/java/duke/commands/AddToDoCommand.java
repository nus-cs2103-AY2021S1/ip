package duke.commands;

import duke.*;

import java.time.LocalDate;

public class AddToDoCommand extends Command{
    private String commandContent;

    public AddToDoCommand(String commandContent) {
        this.commandContent = commandContent;
    }


    public String run(TaskList taskList, Storage storage) {
        Task newTask;
        newTask = new ToDo(commandContent);
        taskList.add(newTask);
        return Ui.addTask(newTask, taskList);
    }

}
