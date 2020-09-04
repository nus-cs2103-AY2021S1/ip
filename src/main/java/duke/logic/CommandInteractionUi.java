package duke.logic;

import duke.task.DukeTask;

/**
 * Encapsulates key functionalities of the UIManager that interacts with a User.
 * Contains functionalities to provide Strings related to Command Responses
 * as well as functionalities to print said Responses.
 */
public interface CommandInteractionUi {
    // HELP
    void printDukeInstructions();
    String getDukeInstructions();
    // ADD COMMAND
    void printAddTask(DukeTask task, int size);
    String getAddTask(DukeTask task, int size);
    // DELETE COMMAND
    void printDeleteTask(DukeTask task, int size);
    String getDeleteTask(DukeTask task, int size);
    // LIST COMMAND -- GETS A NUMBERED TASK
    void printNumberedTask(DukeTask task, int num);
    String getNumberedTask(DukeTask task, int num);
    // DONE COMMAND
    void printMarkAsDone(DukeTask task, int size);
    String getMarkAsDone(DukeTask task, int size);
    // FIND COMMAND
    void printFindCannotBeFound(String keyword);
    String getFindCannotBeFound(String keyword);
    void printFindFilteredList(String keyword, boolean isPlural);
    String getFindFilteredList(String keyword, boolean isPlural);
    // GENERAL
    void printTaskStatus(int size);
    String getTaskStatus(int size);
}
