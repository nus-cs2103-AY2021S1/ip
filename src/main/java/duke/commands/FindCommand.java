package duke.commands;

import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class FindCommand extends Command{

    private static TaskList temporaryList;

    public FindCommand(String description){
        super(description);
    }

    public String execute(TaskList taskList, Ui ui, Storage storage){
        String[] inputList = description.trim().split(" ", 2);
        temporaryList = new TaskList();

        for (int i = 0; i < taskList.getSize(); i++) {
            Task toAdd = taskList.getTask(i);
            if (toAdd.getTask().contains(inputList[1].trim())) {
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
