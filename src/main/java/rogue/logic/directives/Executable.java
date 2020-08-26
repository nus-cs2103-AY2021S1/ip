package rogue.logic.directives;

import rogue.model.task.TaskList;

import rogue.logic.Report;

import rogue.storage.Storage;
import rogue.storage.exceptions.StorageException;

import rogue.ui.Ui;

import rogue.commons.exceptions.IncorrectArgumentException;

public interface Executable {
    Report execute(Storage storage, TaskList tasks, Ui ui) throws IncorrectArgumentException, StorageException;
}
