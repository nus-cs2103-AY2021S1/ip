package rogue.logic.directives;

import rogue.storage.Storage;
import rogue.model.task.TaskList;
import rogue.ui.Ui;

import rogue.logic.Report;

import rogue.commons.exceptions.IncorrectArgumentException;
import rogue.storage.exceptions.StorageException;

public interface Executable {
    Report execute(Storage storage, TaskList tasks, Ui ui) throws IncorrectArgumentException, StorageException;
}
