package duke.task;

import duke.component.Storage;
import duke.component.Ui;
import duke.exception.DukeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
    private ArrayList<Task> taskArrayList;

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskArrayList;
    }

    /**
     * Adds task to taskArrayList, writes to duke.txt file
     *
     * @param storage to write to duke.txt file
     * @param task to be printed and written
     * @throws IOException for errors relating to parsing of the file in the storage.writeToFile function
     */
    public void addTask(Storage storage, Task task) throws IOException {
        taskArrayList.add(task);
        storage.writeToFile(task);
    }

    public boolean hasDate(Task task) {
        return (task instanceof Deadline || task instanceof Event);
    }

    public void sortDates() {
        Collections.sort(this.taskArrayList, (t1, t2) -> {
            if (t1 instanceof Todo && hasDate(t2)) {
                return -1;
                //sorts Todos with no date at the top of list
            }
            else if (hasDate(t1) && t2 instanceof Todo) {
                return 1;
            }
            else if (t1 instanceof Todo && t2 instanceof Todo) {
                return 0;
            }
            else return t1.getLocalDate().compareTo(t2.getLocalDate());

        });
    }


    /**
     * prints list of tasks when user inputs "list"
     *
     */
    public String printTasks() {
        sortDates();

        if (taskArrayList.size() == 0){
            return "There are no tasks!\n";
        }

        String s = Ui.printList(this.taskArrayList);
        return s;
    }

    /**
     * removes task of ith position (in a 1 to n index) from arrayList and prints the resulting list
     *
     * @param i
     * @throws DukeException
     */
    public void delete(int i) throws DukeException {
        if (i < 0 || i > taskArrayList.size()) {
            throw new DukeException("invalid task number");
        }
        taskArrayList.remove(i - 1);
    }

    /**
     * Marks list in taskArrayList as done, updates entries in duke.txt file
     *
     * @param i
     * @param storage
     */
    public void setAllDone(int i, Storage storage) {
        Task doneTask = taskArrayList.get(i - 1);
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
