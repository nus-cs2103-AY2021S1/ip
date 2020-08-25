package main.java;

import java.util.List;

public abstract class Command {
    List<String> input;

    public Command(List<String> input) {
        this.input = input;
    }

    abstract void run(TaskList taskList) throws UserException;
}

class ByeCommand extends Command {
    public ByeCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run(TaskList taskList) {
        taskList.save();
        Ui.bye();
    }
}

class ListCommand extends Command {
    public ListCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run(TaskList taskList) {
        Ui.list(taskList.printTasks());
        taskList.printTasks();
    }
}

class DoneCommand extends Command {
    public DoneCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run(TaskList taskList) throws InvalidArgumentException {
        int index = Integer.parseInt(input.get(1));
        taskList.finishTask(index);
        Ui.done(taskList.getTask(index).toString());
    }
}

class DeleteCommand extends Command {
    public DeleteCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run(TaskList taskList) throws InvalidArgumentException {
        int index = Integer.parseInt(input.get(1));
        Task task = taskList.removeTask(index);
        Ui.delete(task.toString(), taskList.count());
    }
}

class ClearCommand extends Command {
    public ClearCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run(TaskList taskList) {
        taskList.clearAll();
        Ui.clear();
    }
}

class TaskCommand extends Command {
    public TaskCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run(TaskList taskList) throws UserException {
        Task task;
        if (input.size() < 4) throw new InvalidArgumentException("Missing argument(s)");
        task = taskList.addTask(input);
        Ui.task(task.toString(), taskList.count());
    }
}