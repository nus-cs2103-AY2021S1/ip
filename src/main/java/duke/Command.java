package main.java.duke;

abstract class Command {
    abstract protected void execute(Storage storage, TaskList tasks, Ui ui);
}
