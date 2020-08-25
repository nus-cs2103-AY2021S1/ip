package main.java;

import java.io.IOException;

public class AddCommand extends Command {
    Task task;
    AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws BobIOException {
        tasks.add(task);
        storage.appendToStorage(task.saveFormat() + System.lineSeparator());
        storage.flushWriter();
        ui.addTask(task);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        } else if (o instanceof AddCommand) {
            AddCommand c = (AddCommand) o;
            return c.task.equals(this.task);
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return "Adds " + task.toString() + " to list";
    }
}
