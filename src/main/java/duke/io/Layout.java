package duke.io;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Formats the input into an output to be returned. 
 */
public class Layout {

    public String print(String s) {
        return s;
    }

    public String printTaskList(boolean isFindCommand, ArrayList<Task> arr) {
        StringBuilder sb = new StringBuilder();
        if (arr.size() != 0) {
            if (isFindCommand) {
                sb.append("Here are the matching tasks in your list: ");
            } else {
                sb.append("Here are the tasks in your list: ");   
            }
            for (int i = 0; i < arr.size(); i++) {
                Task task = arr.get(i);
                sb.append("\n" + (i + 1) + "." + task.toString());
            }
        } else {
            sb.append("No tasks");
        }
        return sb.toString();
    }
    
    

    public String printMarkedDone(Task task) {
        return "Nice! I've marked this task as done: " + "\n" + task.toString();
    }
    
    public String printDeleted(Task task, int size) {
        String str = size > 1 ? "tasks" : "duke/task";
        return "Noted. I've removed this task: " + "\n" + task.toString() +
                "\nNow you have " + size + " " + str + " in the list.";
    }

    public String printAddedMessage(String description, int size) {
        String str = size > 1 ? "tasks" : "duke/task";
        return "Got it. I've added this task:" + "\n" + description +
                "\nNow you have " + size + " " + str + " in the list.";
    }
    
    public String printCommands(String [] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(i != arr.length - 1 ? arr[i] + "\n" : arr[i]);
        }
        return sb.toString();
    }

}
