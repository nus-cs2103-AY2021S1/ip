package main.java.Command;

import main.java.Exception.AnonymousException;
import main.java.Exception.DescriptionException;
import main.java.Exception.DukeDateTimeParserException;
import main.java.Exception.NoIndexException;
import main.java.Storage.Storage;
import main.java.Task.TaskList;
import main.java.Ui.Ui;

import java.io.IOException;

public abstract class Command {
    public static String DONE_COMMAND = "done";
    public static String DELETE_COMMAND = "delete";
    public static String DEADLINE_COMMAND = "deadline";
    public static String EVENT_COMMAND = "event";
    public static String EXIT_COMMAND = "bye";
    public static String HELP_COMMAND = "--help";
    public static String LIST_COMMAND = "list";
    public static String TODO_COMMAND ="todo";
    public static String SHOW_AFTER_COMMAND = "show after";
    public static String SHOW_BEFORE_COMMAND = "show before";
    public static String DELETE_ALL_COMMAND = "delete all";
    public static String DONE_ALL_COMMAND = "done all";

    public boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws IOException, AnonymousException, DescriptionException, DukeDateTimeParserException, NoIndexException;

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }
}
