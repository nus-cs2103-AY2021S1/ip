package task;

import java.util.List;

import exception.DukeException;

import task.tasks.Task;
import task.tasks.Todo;
import task.tasks.Deadline;
import task.tasks.Event;

import storage.Storage;

public class TaskList {

    protected List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(String userCommand) {
        if (userCommand.contains("todo")) { // To Do
            // E.g todowork
            if (userCommand.split(" ").length == 1) {
                DukeException.invalidTodo();
            } else {
                // Add and report that the todo is added
                String[] userCommandSplit = userCommand.split(" ", 2);
                String description = userCommandSplit[1];
                Task newTask = new Todo(description);
                this.tasks.add(newTask);
                TaskDescription.addedTaskDescription(this.tasks, newTask);
                Storage.appendToFile(newTask.toString());
            }
        } else if (userCommand.contains("deadline")) { // Deadline
            try {
                String[] userCommandSplit = userCommand.split(" /by ");
                String description = userCommandSplit[0].split(" ", 2)[1];
                String by = userCommandSplit[1];

                // Add and report that the deadline is added
                Task newTask = new Deadline(description, by);
                this.tasks.add(newTask);
                TaskDescription.addedTaskDescription(this.tasks, newTask);
                Storage.appendToFile(newTask.toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                // E.g deadline return book /bylmklmlmlkmlkmlmlmlmlmkl Sunday
                DukeException.invalidDeadline();
            }
        } else { // Event
            try {
                String[] userCommandSplit = userCommand.split(" /at ");
                String description = userCommandSplit[0].split(" ", 2)[1];
                String at = userCommandSplit[1];

                // Add and report that the event is added
                Task newTask = new Event(description, at);
                this.tasks.add(newTask);
                TaskDescription.addedTaskDescription(this.tasks, newTask);
                Storage.appendToFile(newTask.toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                // E.g event project meeting /atlmklmlmlkmlkmlmlmlmlmkl Mon 2-4pm
                DukeException.invalidEvent();
            }
        }
    }

    public void markTaskDone(String userCommand) {
        try {
            // E.g given "done 1", we split to ["done", "1"]
            String[] userCommandSplit = userCommand.split(" ");

            // To prevent cases such as "done 1 7", "done", "done123123123"
            if (userCommandSplit.length != 2) {
                DukeException.invalidCommand();
            } else {
                // Take serial number e.g 1 "done 1"
                int serialNumber = Integer.parseInt(userCommandSplit[1]);
                int index = serialNumber - 1;

                // Mark as done and report that the task is done
                Task doneTask = this.tasks.get(index);
                String currentText = doneTask.toString();
                doneTask.markAsDone();
                String amendedText = doneTask.toString();
                TaskDescription.doneTaskDescription(doneTask);
                Storage.amendFile(currentText, amendedText);
            }
        } catch (IndexOutOfBoundsException e) {
            // E.g "done 719329813298712398123" is not valid as number of tasks is cap to 100 by requirements
            DukeException.noSuchTask();
        } catch (NumberFormatException e) {
            // E.g "done work"
            DukeException.invalidCommand();
        }
    }

    public void deleteTask(String userCommand) {
        try {
            // E.g given "delete 1", we split to ["delete", "1"]
            String[] userCommandSplit = userCommand.split(" ");

            // To prevent cases such as "delete 1 7", "delete", "delete123123123"
            if (userCommandSplit.length != 2) {
                DukeException.invalidCommand();
            } else {
                // Take serial number e.g 1 "delete 1" and delete
                int serialNumber = Integer.parseInt(userCommandSplit[1]);
                int index = serialNumber - 1;

                // Mark as deleted and report that the task is deleted
                Task deletedTask = this.tasks.get(index);
                this.tasks.remove(index);
                TaskDescription.deletedTaskDescription(this.tasks, deletedTask);
                Storage.deleteFromFile(deletedTask.toString());
            }
        } catch (IndexOutOfBoundsException e) {
            // E.g "delete 719329813298712398123" is not valid as number of tasks is cap to 100 by requirements
            DukeException.noSuchTask();
        }
    }
}
