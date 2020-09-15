package duke.commands;

import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Finds a task on the taskList, based on the keyword
 */
public class FindCommand extends Command{

    private static TaskList temporaryList;

    public FindCommand(String description){
        super(description);
    }

    /**
     * Finds a task on the taskList, based on the keyword
     *
     * @param taskList
     * @param ui
     * @param storage
     * @return String based on the Task found
     */
    public String execute(TaskList taskList, Ui ui, Storage storage){
        String[] inputList = description.trim().split(" ", 2);
        String toFind = inputList[1].trim().toLowerCase();
        temporaryList = new TaskList();

        //asserts the command to have the proper format
        assert(inputList.length == 2);
        for (int i = 0; i < taskList.getSize(); i++) {
            Task toAdd = taskList.getTask(i);
            if (toAdd.getTask().contains(toFind)) {
                temporaryList.addTask(toAdd);
            }
        }

        if (temporaryList.getSize() == 0) {
            ui.lineFormatter("There are no tasks found in this find!");
            return "There are no tasks found in this find!";

        } else {

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("There were " + temporaryList.getSize() + " tasks found\n");
            for (int j = 0; j < temporaryList.getSize(); j++) {
                Task currentTask = temporaryList.getTask(j);
                stringBuffer.append((j + 1) + "." + currentTask.toString() + "\n");
            }
            ui.lineFormatter(stringBuffer.toString());
            return stringBuffer.toString();
        }
    }

    @Override
    public boolean isComplete(){
        return false;
    }


}
