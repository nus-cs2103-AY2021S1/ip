package duke;

import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of Task object and methods that deal with modification to the list of tasks.
 */

public class TaskList {

    private static ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * updating the taskList from the database if file is found
     * @param listOfTask to be created.
     */
    TaskList(List<String> listOfTask) {
        this.tasks = new ArrayList<>();
        for (int i = 0; i < listOfTask.size(); i++) {
            addTaskFromFile(listOfTask.get(i), tasks);
        }
    }

    /**
     * method to mark a task in the list as done
     * @param remain index of the task in list
     */

    public static void doneTask(String remain) {
        int index = Integer.parseInt(remain);
        tasks.get(index-1).markAsDone();
        System.out.println("Nice! This task is marked as done!");
        System.out.println(tasks.get(index-1));
    }

    /**
     * method to add a todo item into the list
     * @param command description of a todo
     */

    public static void createTodo(String command) {
        if (command.isEmpty()) {
            System.out.println(new DukeException("todo"));
        } else {
            tasks.add(new Todo(command));
            Ui.addedTask(new Todo(command), tasks.size());
        }
    }

    /**
     * method to add an Event into the list
     * @param command description of an event
     */

    public static void createEvent(String command) {
        try {
            String description = command.split(" /at ", 2)[0];
            String at = command.split(" /at ", 2)[1].replace(" ", "");
            tasks.add(new Event(description, at));
            Ui.addedTask(new Event(description, at), tasks.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(new DukeException("event"));
        } catch (DateTimeParseException e2) {
            System.out.println(new DukeException("time"));
        }
    }

    /**
     * method to add a deadline into the list
     * @param command description of an event
     */
    public static void createDeadline(String command) {
        try {
            String description = command.split("/by ", 2)[0];
            String by = command.split("/by ", 2)[1].replace(" ", "");
            tasks.add(new Deadline(description, by));
            Ui.addedTask(new Deadline(description, by), tasks.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(new DukeException("deadline"));
        }  catch (DateTimeParseException e2) {
            System.out.println(new DukeException("time"));
        }
    }

    /**
     * method to recall the list of tasks
     * @return
     */
    public List<Task> getTaskList() {
        return tasks;
    }

    public static void addTaskFromFile(String task, ArrayList<Task> store) {
        String type = task.split(" ", 2)[0];
        String remain = task.split(" ", 2)[1];
        String done = remain.split(" ", 2)[0];
        String title = remain.split(" ", 2)[1];
        //System.out.println(title);
        if (type.isEmpty()) {
            return;
        }
        if (type.equals("todo")) {
            store.add(new Todo(title, Boolean.valueOf(done)));
        } else {
            String description = title.split("/", 2)[0];
            Task newTask;

            if (type.equals("deadline")) {
                String by = title.split("/by ", 2)[1];
                newTask = new Deadline(description, by, Boolean.valueOf(done));
                store.add(newTask);
            }
            if (type.equals("event")) {
                String at = title.split("/at ", 2)[1];
                newTask = new Event(description, at, Boolean.valueOf(done));
                store.add(newTask);
            }
        }
    }

    /**
     * A method to find any task that contains the keyword specified and asks the Ui to print.
     * @param name of the keyword.
     */

    public static void findTask(String name) {
        List<Task> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).findTask(name)) {
                result.add(tasks.get(i));
            }
        }
        if (result.isEmpty()) {
            Ui.printFoundTask(false, result);
        } else {
            Ui.printFoundTask(true, result);
        }
    }

    public static void printTaskList() {
        Ui.printTaskList(tasks);
    }

    /**
     * method to delete a specific task at the index of the list
     * @param command the specified index
     */

    public static void deleteTask(String command) {
        int index = Integer.parseInt(command);
        Task k = tasks.get(index-1);
        tasks.remove(k);
        Ui.deletedTask(k);
    }
}
