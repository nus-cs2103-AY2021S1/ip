package main.java;

abstract class Command {
    abstract protected void execute(Storage storage, TaskList tasks, Ui ui);
}
