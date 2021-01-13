package duke.dependencies.storage;

import duke.dependencies.dukeexceptions.MissingListException;
import duke.dependencies.task.Schedulable;
import duke.dependencies.task.Task;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that implements the list of Tasks the user has. Implemented with an ArrayList,
 *
 */
public class TaskList {

    /** Loader Object to read and write to save file. */
    private Storage l;

    /** todoList that stores the tasks. */
    private ArrayList<Schedulable> todoList;

    /** Private constructor */
    private TaskList() {
        l = new Storage("data", "taskdata.txt");

        // Checks if there are any save files of todoList.
        // If there is, attempt to read the object as arraylist.
        // If there is none, instantiates the file.
        // And assigns the todolist to a new arraylist.
        if (l.isSavedFilePresent()) {
            try {
                todoList = l.openAndReadObject();
            } catch (MissingListException e) {
//                e.printStackTrace();
                System.out.println("OOPS, there seems to be data corruption in the todolist!");
                System.out.println("Initialising new directory for saving your list...");
                todoList = new ArrayList<>();
            }
        } else {
            l.instantiateFile();
            todoList = new ArrayList<>();
        }
    }

    /**
     * Initialises and returns the Store object.
     *
     * @return the Store object
     */
    public static TaskList initStorage() {
        return new TaskList();
    }

    /**
     * Returns a String in the form of a list with \n appended
     * at the end of each item.
     *
     * @return Returns
     */
    public String getTodosInList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < todoList.size(); i++) {
            sb.append(i+1);
            sb.append(". ");
            sb.append(todoList.get(i).toString());

            // The last item should not have a newline character. List form.
            if (i != todoList.size() - 1) {
                sb.append("\n");
            }
        }
        if (todoList.size() == 0) {
            sb.append("Oops!!! Theres's nothing here!\n")
                    .append("Try adding something to your list?");
        }

        return sb.toString();
    }

    /**
     * Deletes the entire list.
     * @return A string reply indicating list data is wiped.
     */
    public String clearList() {
        todoList = new ArrayList<>();
        l.overwriteAndSave(todoList);
        return "List cleared.";
    }

    /**
     * Adds the specified task to the todoList. Returns a string representation
     * of the task that was added as a reply.
     *
     * @param task Task object to be added.
     * @return String representing the newly added task.
     */
    public String add(Task task) {
        assert !task.isItEmpty() && !task.isMiscTask();  // Add Assertions
        todoList.add(task);
        l.overwriteAndSave(todoList);
        return task.toString();
    }

    /**
     * Finds the given task at index and completes it.
     *
     * @param nums An array of numbers in string form.
     * @return String representing the newly completed task.
     */
    public String done(Integer... nums) { // VARARGS // C-MassOps
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            Schedulable t = todoList.get(nums[i] - 1);
            t.completed();
            sb.append(t.toString());
            if (i != nums.length - 1) {
                sb.append("\n");
            }
        }
        l.overwriteAndSave(todoList);
        return sb.toString();
    }

    /**
     * Finds all given task specified and deletes it from
     * list.
     *
     * @param nums An array of numbers in string form.
     * @return String representing the deleted task.
     */
    public String deleteTask(Integer... nums) {
        StringBuilder sb = new StringBuilder();
        List<Integer> arr = Arrays.asList(nums);
        List<Integer> sortedArr = arr.stream().sorted().collect(Collectors.toList()); // A-Streams
        int offset = 0;
        for (int i = 0; i < sortedArr.size(); i++) {
            int taskIndex = sortedArr.get(i) - offset - 1;
            Schedulable t = todoList.get(taskIndex);
            sb.append(t.toString());
            if (i != sortedArr.size() - 1) {
                sb.append("\n");
            }
            todoList.remove(taskIndex);
            offset++;
        }
        l.overwriteAndSave(todoList);
        return sb.toString();
    }

    /**
     * Returns the number of tasks in the todoList. Includes completed task.
     *
     * @return Size of the list as a String.
     */
    public int getListSize() {
        return todoList.size();
    }

    /**
     * Returns the number of completed tasks in the list.
     *
     * @return Integer number of completed items in the task list.
     */
    public int getNumOfCompleted() {
        int c = 0;
        for (Schedulable schedulable : todoList) {
            if (schedulable.isCompleted()) {
                c++;
            }
        }
        return c;
    }

    /**
     * Finds and returns all matching task that has the keyword in the task.
     * @param keyword Keyword to be matched.
     * @return A list in the form of a string of all task matching the keyword.
     */
    public String findMatching(String keyword) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        List<Schedulable> filteredList = todoList.stream()
                .filter(task -> {
            String taskString = task.toString();
            return taskString.contains(keyword);
        }).collect(Collectors.toList());
        for (Schedulable task : filteredList) {
            String taskString = task.toString();
            sb.append(i++)
                    .append(". ")
                    .append(taskString)
                    .append("\n");

        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Returns the number of incomplete tasks in the list.
     *
     * @return Integer number of incomplete items in the task list.
     */
    public int getNumOfIncomplete() {
        int c = 0;
        for (Schedulable schedulable : todoList) {
            if (!schedulable.isCompleted()) {
                c++;
            }
        }
        return c;
    }


}
