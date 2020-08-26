package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * A class that stores a list of tasks.
 */
public class TaskList {

    public ArrayList<Task> list;
    public int count;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.count = 0;
    }

    /**
     * Constructs a TaskList with pre-existing Tasks from given Optional of a Stream of Strings.
     * @param content  Optional of a Stream of Strings with supposed input of the save file.
     */
    public TaskList(Optional<Stream<String>> content) {
        this.list = new ArrayList<>();
        this.count = 0;
        if (content.isEmpty()) {
            //do nothing
        } else {
            content.get().forEach((line) -> {
                System.out.println("the thingy" + line);
                char startChar = line.charAt(0);
                boolean isDone = line.charAt(4) == '1';
                switch (startChar) {
                    case 'T': {
                        String description = line.substring(8);
                        ToDo taskToAdd = new ToDo(description, isDone);
                        this.list.add(taskToAdd);
                        this.count++;
                        break;
                    }
                    case 'D': {
                        String descriptionAndDeadline = line.substring(8);
                        int stringBreak = descriptionAndDeadline.indexOf('|');
                        String deadline = descriptionAndDeadline.substring(stringBreak + 2);
                        String description = descriptionAndDeadline.substring(0, stringBreak - 1);
                        Deadline taskToAdd = new Deadline(description, deadline, isDone);
                        this.list.add(taskToAdd);
                        this.count++;
                        break;
                    }
                    case 'E': {
                        String descriptionAndDate = line.substring(8);
                        int stringBreak = descriptionAndDate.indexOf('|');
                        String date = descriptionAndDate.substring(stringBreak + 2);
                        String description = descriptionAndDate.substring(0, stringBreak - 1);
                        Event taskToAdd = new Event(description, date, isDone);
                        this.list.add(taskToAdd);
                        this.count++;
                        break;
                    }
                }
            });
        }

    }

    /**
     * Adds a task into the TaskList.
     * @param task  The task to be added.
     * @return  String representation of the task added.
     */
    public String addTask(Task task) {
        this.list.add(task);
        this.count++;
        return task.toString();
    }

    /**
     * Marks a task in the TaskList as done.
     * @param index  The index of the task to be marked as done.
     * @return  String representation of the task marked as done.
     */
    public String markTaskAsDone(int index) {
        this.list.get(index).markAsDone();
        return this.list.get(index).toString();
    }

    /**
     * Deletes a task from the TaskList.
     * @param index  The index of the task to be deleted.
     * @return  String representation of the task deleted.
     */
    public String deleteTask(int index) {
        String representation = this.list.get(index).toString();
        this.list.remove(index);
        this.count--;
        return representation;
    }

    /**
     * Gets the number of tasks in the TaskList.
     * @return  int of the number of tasks.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * String representation of the TaskList.
     * @return  String representation of the TaskList.
     */
    @Override
    public String toString() {
        String representation = "";
        if (this.count > 0) {
            for (int i = 0; i < this.count - 1; i++) {
                representation += (i + 1 + ". " + list.get(i) + "\n");
            }
            representation += this.count + ". " + list.get(this.count - 1);
        }
        return representation;
    }

    /**
     * String representation of the TaskList that is to be saved on a file.
     * @return  String representation of the TaskList that is to be saved on a file.
     */
    public String fileText() {
        String representation = "";
        if (this.count > 0) {
            for (int i = 0; i < this.count - 1; i++) {
                representation += (list.get(i).fileText() + "\n");
            }
            representation += list.get(this.count - 1).fileText();
        }
        return representation;
    }
}
