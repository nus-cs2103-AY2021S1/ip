package main.java.Command;

import main.java.AnonymousException;
import main.java.DescriptionException;
import main.java.DukeDateTimeParserException;
import main.java.NoIndexException;
import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

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
