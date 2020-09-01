package service;

import domain.DukeDomain;
import dukeoutput.DukeOutput;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;

public class DukeService {

    private final DukeDomain dukeDomain = new DukeDomain();
    private final DukeOutput dukeOutput = new DukeOutput();

    public void addToDoEvent(String input) {
        try {
            Task task = new ToDoTask(input.split("\\s+", 2)[1]);
            this.dukeDomain.addToList(task);
            this.dukeDomain.outputTask(task);
        } catch (IndexOutOfBoundsException exception) {
            this.dukeOutput.printResponse("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public void addDeadlineEvent(String input) {
        String title;
        try {
            title = input.split("\\s+", 2)[1];
        } catch (IndexOutOfBoundsException exception) {
            this.dukeOutput.printResponse("☹ OOPS!!! The description of a deadline cannot be empty.");
            return;
        }
        try {
            String [] titleComponents = title.split("/by", 2);
            Task task = new DeadlineTask(titleComponents[0].trim(), titleComponents[1].trim());
            this.dukeDomain.addToList(task);
            this.dukeDomain.outputTask(task);
        } catch (IndexOutOfBoundsException exception) {
            this.dukeOutput.printResponse("☹ OOPS!!! The date of a deadline cannot be empty.");
        }
    }

    public void addEventTask(String input) {
        String title;
        try {
            title = input.split("\\s+", 2)[1];
        } catch (IndexOutOfBoundsException exception) {
            this.dukeOutput.printResponse("☹ OOPS!!! The description of an event cannot be empty.");
            return;
        }
        try {
            String[] titleComponents = title.split("/at", 2);
            String[] dateAndTime = titleComponents[1].trim().split("\\s+", 2);
            String[] times =  dateAndTime[1].split("-", 2);
            Task task = new EventTask(titleComponents[0].trim(),
                    dateAndTime[0].trim(), times[0].trim(), times[1].trim());
            this.dukeDomain.addToList(task);
            this.dukeDomain.outputTask(task);
        } catch (IndexOutOfBoundsException exception) {
            this.dukeOutput.printResponse("☹ OOPS!!! The start and end time of an event cannot be empty.");
        }
    }

    public void handleDoneCommand(String input) {
        try {
            String[] components = input.split("\\s+", 2);
            int parameter = Integer.parseInt(components[1]);
            String response = this.dukeDomain.markTaskDone(parameter - 1);
            this.dukeOutput.printResponse(response);
        } catch (NumberFormatException exception) {
            this.dukeOutput.printResponse("☹ OOPS!!! the task number has to be a postive integer.");
        } catch (IndexOutOfBoundsException exception) {
            this.dukeOutput.printResponse("☹ OOPS!!! the task number has to be valid");
        }
    }

    public void handleDeleteCommand(String input) {
        try {
            String[] components = input.split("\\s+", 2);
            int parameter = Integer.parseInt(components[1]);
            String response = this.dukeDomain.deleteTask(parameter - 1);
            int taskListSize = this.dukeDomain.getCurrentTaskListSize();
            this.dukeOutput.printResponseWithListSize(response, taskListSize);
        } catch (NumberFormatException exception) {
            this.dukeOutput.printResponse("☹ OOPS!!! the task number has to be a positive integer.");
        } catch (IndexOutOfBoundsException exception) {
            this.dukeOutput.printResponse("☹ OOPS!!! the task number has to be valid");
        }
    }

    public void saveList() {
        this.dukeDomain.saveList();
    }

    public void printList() {
        this.dukeDomain.printList();
    }
}
