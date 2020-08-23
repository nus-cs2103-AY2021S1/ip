package duke;

import duke.exceptions.EmptyCommandException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.MissingTimeException;
import duke.exceptions.TaskCompletionException;
import duke.exceptions.TaskDeletionException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task t : tasks) {
            System.out.println(i + "." + t.toString());
            i++;
        }
    }

    public void addTask(String s) throws InvalidCommandException, EmptyCommandException, MissingTimeException {
        String str = s.trim();
        if (str.equals("todo") || str.equals("deadline") || str.equals("event")) {
            throw new EmptyCommandException(str);
        }
        if (str.contains(" ")) {
            String[] arr = str.split(" ", 2);
            String str2 = arr[1];
            switch (arr[0]) {
            case "todo":
                ToDo td = new ToDo(str2);
                insert(td);
                break;
            case "deadline":
                if (str2.contains("/by")) {
                    String[] arr2 = str2.split("/by", 2);
                    if (arr2[0].isBlank()) {
                        throw new EmptyCommandException("deadline");
                    }
                    if (arr2[1].isBlank()) {
                        throw new MissingTimeException("deadline");
                    }
                    Deadline dl = new Deadline(arr2[0], arr2[1].trim());
                    insert(dl);
                } else {
                    throw new MissingTimeException("deadline");
                }
                break;
            case "event":
                if (str2.contains("/at")) {
                    String[] arr2 = str2.split("/at", 2);
                    if (arr2[0].isBlank()) {
                        throw new EmptyCommandException("event");
                    }
                    if (arr2[1].isBlank()) {
                        throw new MissingTimeException("event");
                    }
                    Event ev = new Event(arr2[0], arr2[1].trim());
                    insert(ev);
                } else {
                    throw new MissingTimeException("event");
                }
                break;
            default:
                throw new InvalidCommandException();
            }
        } else {
            throw new InvalidCommandException();
        }
    }

    private void insert(Task task) {
        tasks.add(task);
        System.out.println("Task has been added:");
        System.out.println(task.toString());
        System.out.println("You now have " + tasks.size() + " tasks in the list");
    }

    private void delete(int i) {
        Task t = tasks.get(i - 1);
        tasks.remove(i - 1);
        System.out.println("Task has been removed.");
        System.out.println(t.toString());
        System.out.println("You now have " + tasks.size() + " tasks in the list");
    }

    public void completeTask(String str) throws TaskCompletionException {
        if (!str.startsWith("done ")) {
            throw new TaskCompletionException(tasks.size());
        }
        String val = str.substring(5);
        if (isInteger(val)) {
            int i = Integer.parseInt(val);
            if (i > 0 && i <= tasks.size()) {
                tasks.get(i - 1).complete();
            } else {
                throw new TaskCompletionException(tasks.size());
            }
        } else {
            throw new TaskCompletionException(tasks.size());
        }
    }

    public void deleteTask(String str) throws TaskDeletionException {
        if (!str.startsWith("delete ")) {
            throw new TaskDeletionException(tasks.size());
        }
        String val = str.substring(7);
        if (isInteger(val)) {
            int i = Integer.parseInt(val);
            if (i > 0 && i <= tasks.size()) {
                delete(i);
            } else {
                throw new TaskDeletionException(tasks.size());
            }
        } else {
            throw new TaskDeletionException(tasks.size());
        }
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
