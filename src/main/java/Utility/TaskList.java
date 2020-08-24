package Utility;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import Utility.DukeException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public static int getPosition(String s, char target) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == target) {
                return i;
            }
        }
        return -1;
    }

    public static void printAddedTask(ArrayList<Task> ls) {
        System.out.println("Got it. I've added this task:");
        System.out.println(ls.get(ls.size() - 1));
        System.out.println("Now you have " + ls.size() + " tasks in the list.");
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void listAllTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).isDone()) {
                System.out.println((i+1)+  ". " + taskList.get(i));
            } else {
                System.out.println((i+1)+  ". " + taskList.get(i));
            }
        }
    }

    public void completeTask(String input) {
        // mark task as done
        int position = Integer.parseInt(input.substring(5));
        taskList.get(position-1).markDone();

        // print out
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(position-1));
    }

    public void addEvent(String input) throws DukeException {
        if (input.length() <= 6) {
            throw new DukeException("Exception occurred: Name not found for Tasks.Event.");
        }
        int pos = getPosition(input, '/');
        String description = input.substring(6, pos);
        String at = input.substring(pos + 4);
        taskList.add(new Event(description, at));
        printAddedTask(taskList);
    }

    public void addDeadline(String input) throws DukeException {
        if (input.length() <= 9) {
            throw new DukeException("Exception occurred: Name not found for Tasks.Deadline.");
        }
        int pos = getPosition(input, '/');
        String description = input.substring(9, pos);
        String by = input.substring(pos + 4);
        taskList.add(new Deadline(description, by));
        printAddedTask(taskList);
    }

    public void addTodo(String input) {
        taskList.add(new Todo(input.substring(5)));
        printAddedTask(taskList);
    }

    public void deleteTask(String input) throws DukeException {
        if (input.length() <= 7) {
            throw new DukeException("Exception occurred: Kindly enter a number for deletion.");
        }
        int position = Integer.parseInt(input.substring(7));
        System.out.println("Noted. I've removed this task:");
        System.out.println(this.taskList.get(position-1));
        this.taskList.remove(position-1);
    }


}
