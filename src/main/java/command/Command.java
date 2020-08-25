package main.java.command;

import main.java.exception.UserException;
import main.java.task.TaskList;

import java.util.List;

public abstract class Command {
    List<String> input;

    public Command(List<String> input) {
        this.input = input;
    }

    abstract public void run(TaskList taskList) throws UserException;
}

