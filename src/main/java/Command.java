package main.java;

abstract class Command {

    abstract public void perform(TaskList tasks);
    abstract boolean isExit();
}
