package main.java;

abstract class Command {
    protected String commandText;
    protected abstract void execute(String text, TaskList tasklist);
}
