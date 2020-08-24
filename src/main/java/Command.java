package main.java;

import java.time.DateTimeException;
import java.util.List;

public abstract class Command {
    List<String> input;

    public Command(List<String> input) {
        this.input = input;
    }

    abstract void run();
}

class ByeCommand extends Command {
    public ByeCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run() {
        TaskList.writeFile();
        Ui.bye();
    }
}

class ListCommand extends Command {
    public ListCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run() {
        Ui.list();
        TaskList.printTasks();
    }
}

class DoneCommand extends Command {
    public DoneCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run() {
        int index = Integer.parseInt(input.get(1));
        try {
            TaskList.iDone(index);
            Ui.done(TaskList.getTask(index).toString());
        } catch (IllegalArgumentException e) {
            Ui.fail(e.getMessage());
        }
    }
}

class DeleteCommand extends Command {
    public DeleteCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run() {
        int index = Integer.parseInt(input.get(1));
        try {
            Task task = TaskList.iRemove(index);
            Ui.delete(task.toString(), TaskList.count());
        } catch (IllegalArgumentException e) {
            Ui.fail(e.getMessage());
        }
    }
}

class ClearCommand extends Command {
    public ClearCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run() {
        TaskList.clearAll();
        Ui.clear();
    }
}

class TaskCommand extends Command {
    public TaskCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run() {
        Task task;
        try {
            task = TaskList.addTask(input);
        } catch (IndexOutOfBoundsException e) {
            Ui.fail("Missing argument(s)");
            return;
        } catch (DateTimeException e) {
            Ui.fail(e.getMessage());
            return;
        } catch (Exception e) {
            Ui.fail("An expected error has occurred.");
            return;
        }
        Ui.task(task.toString());
    }
}