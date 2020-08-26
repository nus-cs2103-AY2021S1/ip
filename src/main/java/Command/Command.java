package main.java.Command;

import main.java.Exception.AnonymousException;
import main.java.Exception.DescriptionException;
import main.java.Exception.DukeDateTimeParserException;
import main.java.Exception.DukeKeywordException;
import main.java.Exception.NoIndexException;
import main.java.Storage.Storage;
import main.java.Task.TaskList;
import main.java.Ui.Ui;

import java.io.IOException;

public abstract class Command {
    public static final String DONE_COMMAND = "done";
    public static final String DELETE_COMMAND = "delete";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String EXIT_COMMAND = "bye";
    public static final String FIND_COMMAND = "find";
    public static final String HELP_COMMAND = "--help";
    public static final String LIST_COMMAND = "list";
    public static final String TODO_COMMAND ="todo";
    public static final String SHOW_AFTER_COMMAND = "show after";
    public static final String SHOW_BEFORE_COMMAND = "show before";
    public static final String DELETE_ALL_COMMAND = "delete all";
    public static final String DONE_ALL_COMMAND = "done all";

    public boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws IOException, AnonymousException, DescriptionException, DukeDateTimeParserException, NoIndexException, DukeKeywordException;

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }
}
