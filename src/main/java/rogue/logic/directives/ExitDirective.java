package rogue.logic.directives;

import rogue.model.task.TaskList;

import rogue.logic.Report;

import rogue.storage.Storage;

import rogue.ui.Ui;

public class ExitDirective implements Executable {
    private final String MESSAGE_EXIT_SUCCESS = "hOpE To sEe yOu aGaIn. NoT.";

    @Override
    public Report execute(Storage storage, TaskList tasks, Ui ui) {
        return new Report(MESSAGE_EXIT_SUCCESS, true);
    }
}
