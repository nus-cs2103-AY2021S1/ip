package services;

import domains.DukeDomain;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.ToDoTask;

public class DukeService {

    private DukeDomain dukeDomain = new DukeDomain();

    public void addToDoEvent(String response) {
        try {
            Task task = new ToDoTask(response.split("\\s+", 2)[1]);
            dukeDomain.addToList(task);
            dukeDomain.outputTask(task);
        } catch (IndexOutOfBoundsException exception) {
            printResponse("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public void addDeadlineEvent(String response) {
        String title;
        try {
            title = response.split("\\s+", 2)[1];
        } catch (IndexOutOfBoundsException exception) {
            printResponse("☹ OOPS!!! The description of a deadline cannot be empty.");
            return;
        }
        try {
            String [] titleComponents = title.split("/by", 2);
            Task task = new DeadlineTask(titleComponents[0].trim(), titleComponents[1].trim());
            dukeDomain.addToList(task);
            dukeDomain.outputTask(task);
        } catch (IndexOutOfBoundsException exception) {
            printResponse("☹ OOPS!!! The date of a deadline cannot be empty.");
        }
    }

    public void addEventTask(String response) {
        String title;
        try {
            title = response.split("\\s+", 2)[1];
        } catch (IndexOutOfBoundsException exception) {
            printResponse("☹ OOPS!!! The description of an event cannot be empty.");
            return;
        }
        try {
            String[] titleComponents = title.split("/at", 2);
            Task task = new EventTask(titleComponents[0].trim(), titleComponents[1].trim());
            dukeDomain.addToList(task);
            dukeDomain.outputTask(task);
        } catch (IndexOutOfBoundsException exception) {
            printResponse("☹ OOPS!!! The start and end time of an event cannot be empty.");
        }
    }

    public void handleDoneCommand(String response) {
        try {
            String[] components = response.split("\\s+", 2);
            int parameter = Integer.parseInt(components[1]);
            dukeDomain.markTaskDone(parameter - 1);
        } catch (NumberFormatException exception) {
            printResponse("☹ OOPS!!! the task number has to be a postive integer.");
        } catch (IndexOutOfBoundsException exception) {
            printResponse("☹ OOPS!!! the task number has to be valid");
        }
    }

    public void handleDeleteCommand(String response) {
        try {
            String[] components = response.split("\\s+", 2);
            int parameter = Integer.parseInt(components[1]);
            dukeDomain.deleteTask(parameter - 1);
        } catch (NumberFormatException exception) {
            printResponse("☹ OOPS!!! the task number has to be a postive integer.");
        } catch (IndexOutOfBoundsException exception) {
            printResponse("☹ OOPS!!! the task number has to be valid");
        }
    }

    public void printResponse(String response) {
        dukeDomain.printResponse(response);
    }

    public void saveList() {
        dukeDomain.saveList();
    }

    public void printList() {
        dukeDomain.printList();
    }
}
