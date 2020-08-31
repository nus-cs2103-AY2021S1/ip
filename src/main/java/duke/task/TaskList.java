package duke.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;
    private static final String starline = "**************************************************************************";
    
    public TaskList() {
        this.list = new ArrayList<>();
    }
    
    public TaskList(BufferedReader br) {
        try {
            this.list = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                Task task = this.add(line, false);
                boolean done = Boolean.parseBoolean(br.readLine());
                if (done) task.markDone();
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error populating task list with saved tasks: " + e);
        }
        
    }
    
    public List<Task> getTaskList() {
        return this.list;
    }

    public void list() {
        System.out.println(starline + "\nHere are the tasks in your list:");
        for (int i=0; i < this.list.size(); i++) {
            printTask(i);
        }
        System.out.println(starline);
    }

    public void printTask(int listIndex) {
        String task = String.format("%d.%s", listIndex+1, this.list.get(listIndex));
        System.out.println(task);
    }

    public void echo(String input) {
        System.out.println("added: " + input);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }
    
    private void throwEmptyFieldException(String taskType, String ... fields) {
        StringBuilder emptyFields = new StringBuilder();
        boolean isFirstField = true;
        for (String field: fields) {
            if (isFirstField) {
                emptyFields.append(field);
                isFirstField = false;
            } else {
                emptyFields.append("/").append(field);
            }
        }
        
        throw new IllegalArgumentException("OOPS! The " + emptyFields + " of " + taskType + " cannot be empty.");
    }
    
    private void throwInvalidTaskSyntaxException(String taskType) {
        throw new IllegalArgumentException(String.format("OOPS! Invalid syntax. To add a %s, use:\n%s", taskType,
                Task.getFormat(taskType.toUpperCase())));
    }

    public Task add(String input, boolean echo) {
        String[] splitInput = input.split(" ", 2);
        String taskType = splitInput[0];
        Task newTask;
        
        switch(taskType) {
            case "todo":
                if (input.matches("todo (\\S+\\s?)+")) {
                    newTask = new Todo(splitInput[1]);
                    break;
                } else if (input.matches("todo\\s?")) {
                    throwEmptyFieldException("todo", "description");
                } else {
                    throwInvalidTaskSyntaxException("todo");
                }
            case "deadline":
                if (input.matches("deadline (\\S+\\s?)+ /by (\\S+\\s?)+")) {
                    String[] splitDeadline = splitInput[1].split(" /by ");
                    String deadlineDesc = splitDeadline[0];
                    String by = splitDeadline[1];
                    newTask = new Deadline(deadlineDesc, by);
                    break;
                } else if (input.matches("deadline\\s?") || !input.contains(" by ")){
                    throwEmptyFieldException("deadline", "description", "date");
                } else {
                    throwInvalidTaskSyntaxException("deadline");
                }
            case "event":
                if (input.matches("event (\\S+\\s?)+ /at (\\S+\\s?)+")) {
                    String[] splitEvent = splitInput[1].split(" /at ");
                    String eventDesc = splitEvent[0];
                    String at = splitEvent[1];
                    newTask = new Event(eventDesc, at);
                    break;
                } else if (input.matches("event\\s?") || !input.contains(" at ")) {
                    throwEmptyFieldException("event", "description", "location");
                } else {
                    throwInvalidTaskSyntaxException("event");
                }
            default:
                throw new IllegalArgumentException("OOPS! There is no task of type " + taskType + "!");
        }
        this.list.add(newTask);
        if (echo) echo(newTask.toString());
        return newTask;
    }

    public void markTaskAsDone(String listIndexString) {
        try {
            int listIndexNumber = Integer.parseInt(listIndexString);
            Task task = this.list.get(listIndexNumber - 1);
            task.markDone();
            System.out.println("Nice! I've marked this task as done:\n" + task);
        } catch (IndexOutOfBoundsException ex) { // if list index is not in the list
            System.out.println("OOPS! This task index does not exist! Type 'list' to check out your tasks.");
        } catch (NumberFormatException ex) { // if list index string is not an integer
            System.out.println("OOPS! The keyword 'done' is used to check off tasks as follows:" +
                    "   done <task index>");
        }
    }
    
    public void deleteTask(String listIndexString) {
        try {
            int listIndexNumber = Integer.parseInt(listIndexString);
            Task task = this.list.get(listIndexNumber - 1);
            this.list.remove(listIndexNumber - 1);
            System.out.println("The following task has been deleted:\n" + task);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("OOPS! This task index does not exist! Type 'list' to check out your tasks.");
        } catch (NumberFormatException ex) { // if list index string is not an integer
            System.out.println("OOPS! The keyword 'delete' is used to delete tasks as follows:" +
                    "   delete <task index>");
        }
    }
}
