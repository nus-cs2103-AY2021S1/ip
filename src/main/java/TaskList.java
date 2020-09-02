import java.io.IOException;
import java.util.ArrayList;

/**
 * TaskList class handles the operations to modify list of tasks given by the user
 *
 */
public class TaskList {

    private ArrayList<Task> taskArrayList;

    /**
     * Constructor for TaskList with no argument, creates empty ArrayList
     *
     */
    public TaskList() {
        taskArrayList = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
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


    /**
     * prints list of tasks when user inputs "list"
     *
     */
    public String printTasks() {
        if (taskArrayList.size() == 0){
            return "There are no tasks!\n";
        }
        else return Ui.printList(this.taskArrayList);
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
    public void setDone(int i, Storage storage) {
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
