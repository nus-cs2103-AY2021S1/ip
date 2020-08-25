package duke.commands;

import duke.exceptions.StorageOperationException;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Colour;
import duke.utils.Ui;

public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    public void executeCommand(TaskManager taskManager, Ui formatter, Storage storage){
        taskManager.addTask(task);
        int totalNumberOfTasks = taskManager.getTotalNumberOfTasks();
        String output = "Got it. I've added this task:\n " + Colour.Green(task.toString()) + "\n";
        String numberOfTasks = totalNumberOfTasks < 2 ? " task in the list." : " tasks in the list.";
        formatter.print(output + "Now you have a total of " + String.valueOf(totalNumberOfTasks) + numberOfTasks);
        try {
            storage.save(taskManager);
        } catch (StorageOperationException e){
            formatter.print(Colour.Red(e.getMessage()));
        }
    }
}
