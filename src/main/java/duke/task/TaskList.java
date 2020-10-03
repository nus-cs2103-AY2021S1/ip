package duke.task;

import duke.component.Storage;
import duke.component.Ui;
import duke.exception.DukeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
    private ArrayList<Task> taskArrayList;

    /**
     * Constructor for TaskList class with no parameters
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    /**
     * Getter to retrieve ArrayList taskArrayList from instance of TaskList
     *
     * @return taskArrayList
     */
    public ArrayList<Task> getTaskList() {
        return taskArrayList;
    }

    /**
     * Method to add task to taskArrayList, writes to duke.txt file
     *
     * @param storage to write to duke.txt file
     * @param task to be printed and written
     * @throws IOException for errors relating to parsing of the file in the storage.writeToFile function
     */
    public void addTask(Task task, Storage storage) throws IOException {
        taskArrayList.add(task);
        storage.writeToFile(task);
    }

    /**
     * Method that checks if the specified task has a date
     * @param task to check
     * @return boolean
     */
    public boolean hasDate(Task task) {
        return (task instanceof Deadline || task instanceof Event);
    }

    /**
     * Method to sort tasks by date
     *
     */
    // @@author nicholas-gcc
    // CS2103T tp teammate test
    public void sortDates() {
        Collections.sort(this.taskArrayList, (t1, t2) -> {
            if (t1 instanceof Todo && hasDate(t2)) {
                return -1;
                // sorts Todos with no date at the top of list
            } else if (hasDate(t1) && t2 instanceof Todo) {
                return 1;
            } else if (t1 instanceof Todo && t2 instanceof Todo) {
                return 0;
            } else {
                return t1.getLocalDate().compareTo(t2.getLocalDate());
            }
        });
    }


    /**
     * Method to prints list of tasks in taskArrayList
     *
     * @return output String of tasks
     */
    public String printTasks() {
        sortDates();
        if (taskArrayList.size() == 0){
            return "There are no tasks!\n";
        } else {
            return Ui.printList(this.taskArrayList);
        }
    }

    /**
     * Method to remove task of ith position (in a 1 to n index)
     * from taskArrayList and prints the resulting list
     *
     * @param index
     * @throws DukeException
     */
    public void delete(int index) throws DukeException {
        if (index < 0 || index > taskArrayList.size()) {
            throw new DukeException("invalid task number");
        }
        taskArrayList.remove(index - 1);
    }

    /**
     * Marks list in taskArrayList as done, updates entries in duke.txt file
     *
     * @param index index of element in the taskArrayList
     * @param storage
     */
    public void setAllDone(int index, Storage storage) {
        Task doneTask = taskArrayList.get(index - 1);
        doneTask.markAsDone();
        storage.replaceDone(doneTask.getDescription());
    }

    public String find(String substring) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        sb.append("Here are the matching tasks in your list:\n");

        for (Task t : taskArrayList) {
            if (t.getDescription().contains(substring)) {
                counter++;
                sb.append(counter + "." + t.toString());
            }
        }
        if (counter > 0) {
            return sb.toString();
        } else {
            return "No match found!\n";
        }
    }

}
