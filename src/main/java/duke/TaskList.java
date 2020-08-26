package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> storedTasks;

    public TaskList(ArrayList<Task> storedTasks) {
        this.storedTasks = storedTasks;
    }
    
    // Handles cases where user enters empty input
    public void validateScannerInput(String input) throws DukeException {
        if (input.trim().length() == 0) {
            throw new DukeException("Come again, Your Majesty?");
        }
    }

    // Handles cases where user does not enter a valid description for tasks
    public void validateAdd(String[] task) throws DukeException {
        if ((task.length == 1 || task[1].trim().length() == 0) &&
                (task[0].equalsIgnoreCase("todo") ||
                        task[0].equalsIgnoreCase("event") ||
                        task[0].equalsIgnoreCase("deadline"))
        ) {
            throw new DukeException("Do give me more details about this " + task[0] + ", Your Majesty.");
        }
    }

    // Handles cases where user does not enter the correct / commands (i.e. /by for deadlines, /on for events)
    public void validateSlashCommands(String[] task) throws DukeException {
        if(task[0].equalsIgnoreCase("deadline") && task[1].split("/by ", 2).length == 1){
            throw new DukeException("Use /by for deadlines, Your Majesty.");
        } else if (task[0].equalsIgnoreCase("event") &&  task[1].split("/on ", 2).length == 1){
            throw new DukeException("Use /on for events, Your Majesty.");
        }
    }

    // Handles cases where user enters a number that does not correspond to an existing task
    public void validateIndex(int taskNumber) throws DukeException{
        if(taskNumber > storedTasks.size() || taskNumber <= 0) {
            throw new DukeException("Your Majesty, there's no such agenda in my detailed records.");
        }
    }

    // Handles cases where user does not input a valid number after conquer/delete commands
    public void validateConquerDelete(String[] command) throws DukeException {
        try {
            Integer.parseInt(sanitiseInput(command[1]));
        } catch (Exception err) {
            throw new DukeException("Please enter valid numbers after your command, Your Majesty.");
        }
    }

    // Removes leading whitespaces in a String
    public String sanitiseInput(String input) {
        return input.stripLeading();
    }
    
    public void printAllTasks() {
        Ui.printAllTasksUi(storedTasks);
    }
    
    public Task addTask(String task) {
        Task toBeReturned = null;
        try {
            validateScannerInput(task);
            String splitTask[] = task.split(" ", 2);
            Task newTask = null;
            validateAdd(splitTask);
            validateSlashCommands(splitTask);

            switch(splitTask[0].toLowerCase()) {
            case "todo":
                newTask = new ToDo(splitTask[1]);
                break;
            case "deadline":
                newTask = new Deadline(splitTask[1]);
                break;
            case "event":
                newTask = new Event(splitTask[1]);
                break;
            default:
                throw new DukeException("I'm afraid I do not understand that command, Your Majesty.");
            }
            if (newTask != null) {
                storedTasks.add(newTask);
                Ui.addedMessage(newTask, storedTasks.size());
            }
            toBeReturned = newTask;
        } catch (DukeException err) {
            Ui.dukeErrorMessage(err);
        }
        return toBeReturned;
    }
    
    public int conquerTask(String[] command) {
        int indexToBeReturned = -1;
        try {
            validateConquerDelete(command);
            int taskNumber = Integer.parseInt(sanitiseInput(command[1]));
            validateIndex(taskNumber);

            storedTasks.get(taskNumber - 1).markAsDone();
            indexToBeReturned = taskNumber;
            Ui.conqueredMessage(storedTasks.get(taskNumber - 1));
        } catch (DukeException err) {
            Ui.dukeErrorMessage(err);
        }
        return indexToBeReturned;
    }

    public int deleteTask(String[] command) {
        int indexToBeReturned = -1;
        try {
            validateConquerDelete(command);
            int taskNumber = Integer.parseInt(sanitiseInput(command[1]));
            validateIndex(taskNumber);

            Task deletedTask = storedTasks.get(taskNumber - 1);
            storedTasks.remove(deletedTask);
            indexToBeReturned = taskNumber;
            Ui.deletedMessage(deletedTask, storedTasks.size());
        } catch (DukeException err) {
            Ui.dukeErrorMessage(err);
        }
        return indexToBeReturned;
    }
    
    public ArrayList<Task> getStoredTasks() {
        return this.storedTasks;
    }
}
