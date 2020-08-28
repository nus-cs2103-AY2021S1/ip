package duke.Commands;

import duke.TaskList.TaskList;
import duke.Tasks.Task;
import duke.Ui.Ui;

/**
 * A method that finds and prints a list of Tasks with the
 * given keyword
 */
public class Find {
    private static TaskList temporaryList;

    /**
     * Prints the list of Task that contains the given keyword
     * @param toFind
     * @param taskList
     */
    public static void find(String toFind, TaskList taskList) {
        temporaryList = new TaskList();
        for (int i = 0; i < taskList.getSize(); i++) {
            Task toAdd = taskList.getTask(i);
            if (toAdd.getTask().contains(toFind)) {
                temporaryList.addTask(toAdd);
            }
        }

        if (temporaryList.getSize() == 0) {
            Ui.lineFormatter("There are no tasks found in this find!");
        } else {
            showResults();
        }
    }

    /**
     * Prints the Tasks that are stored in the temporaryList
     */
    public static void showResults(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("There were " + temporaryList.getSize() + " tasks found\n");
        for (int j = 0; j < temporaryList.getSize(); j++) {
            Task currentTask = temporaryList.getTask(j);
            stringBuffer.append((j + 1) + "." + currentTask.toString() + "\n");
        }
        Ui.lineFormatter(stringBuffer.toString());
    }
}

