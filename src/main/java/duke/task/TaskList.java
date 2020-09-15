package duke.task;

import duke.DukeException;
import duke.utility.Statistic;

import java.util.ArrayList;

public class TaskList {

    /** ArrayList used store tasks **/
    private ArrayList<Task> taskList;
    /** boolean value to check if there is any more updates to the tasks by the user **/
    private boolean isUpdating = true;
    private Statistic statistic;

    /**
     *Class constructor
     */
    public TaskList(Statistic statistic) {
        this.taskList = new ArrayList<>();
        this.statistic = statistic;
    }

    /**
     * Set the indexed duke.task as completed.
     *
     * @param index index The index of the duke.task in the taskList;
     * @throws DukeException  If the index is not within the range of tasks.
     */

    public String doneTask(int index) throws DukeException {
        if (index < 0 || index > taskList.size() - 1) {
            throw new DukeException("please give a correct duke.task index");
        }

        Task doneTask = taskList.get(index);
        if(doneTask.isDone){
            throw new DukeException("This duke.task has already been completed idiot");
        }
        doneTask.complete();
        statistic.addCompletedTask(doneTask);
        String output = "Nice! I've marked this duke.task as done:\n";
        output += String.format("  %s\n", doneTask.toString());
        return output;
    }

    /**
     * List and prints all the duke.task in taskList
     *
     */
    public String listTask() {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            outputString.append(String.format("%d. %s\n", i + 1, taskList.get(i).toString()));
        }
        return outputString.toString();
    }

    /**
     * Add duke.task into the taskList
     *
     * @param newTask The duke.task to be added into taskList
     * @param print If true, prints out the details of the duke.task being added into the list
     */
    public String addTask(Task newTask, Boolean print) {
        taskList.add(newTask);
        String outputString = "";
        if (print) {
            outputString += newTask.printAddTask();
            outputString += printNumberOfTask(taskList.size());
        }

        return outputString;
    }

    /**
     * Find and print out duke.task that matches to the description
     *
     * @param description of the duke.task
     */
    public String findTask(String description) {
        ArrayList<Task> taskMatchingDescription = new ArrayList<>();
        StringBuilder outputString = new StringBuilder();

        for (Task task : taskList) {
            if (task.fitsTask(description)) {
                taskMatchingDescription.add(task);
            }
        }

        for (int i = 0; i < taskMatchingDescription.size(); i++) {
            outputString.append(String.format("%d. %s\n", i + 1, taskMatchingDescription.get(i).toString()));
        }

        return outputString.toString();
    }
    /**
     * return the size of the taskList
     *
     * @return size of the taskList
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * set isUpdating to false
     *
     */
    public void setTaskListNotUpdating() {
        this.isUpdating = false;
    }

    /**
     * get the corresponding duke.task from taskList
     *
     * @param index The index of the duke.task in the taskList
     * @return The duke.task with the corresponding index from the taskList
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * get the boolean value of whether the duke.task is being updated
     *
     * @return the boolean isUpdating
     */
    public boolean isUpdating() {
        return isUpdating;
    }

    /**
     * delete the duke.task with the corresponding index in taskList
     *
     * @param index The index of the duke.task in the taskList
     * @throws DukeException  If the index is not within the range of tasks.
     */

    public String deleteTask(int index) throws DukeException {
        String outputString = "";
        System.out.println(index);
        if (index < -2 || index > taskList.size() - 1) {
            throw new DukeException("please give a correct task index");
        }

        if( index == -2){
            removeAll();
            return "removed all content bitch";
        }else {
            outputString += taskList.get(index).printDeleteTask() + "\n";
            taskList.remove(index);
            outputString += printNumberOfTask(taskList.size());
            return outputString;
        }
    }

    public String getStatistic(){
        return statistic.getStatisticSummary();
    }

    public void removeAll(){
        while(!taskList.isEmpty()){
            taskList.remove(0);
        }
    }

    /**
     * prints the update for the number of tasks in the list
     *
     * @param i The number of tasks in the list
     */
    static String printNumberOfTask(int i) {
        return String.format("Now you have %d tasks in the list.", i);
    }
}
