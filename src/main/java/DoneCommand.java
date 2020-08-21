package main.java;

import java.io.IOException;

public class DoneCommand extends Command {
    private int count;

    public DoneCommand(int count) {
        this.count = count;
    }

    @Override
    void excecute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, IOException {

        if(!taskList.has(count - 1)) {
            //System.out.println(Duke.makeBlock("There is no such task"));
            throw new TaskNotFoundException("There is no such task");
        } else {
            taskList.markAsCompleted(count - 1);
            ui.showDone(taskList.getTask(count - 1), count);
            storage.writeRecord(taskList);
        }
    }
}
