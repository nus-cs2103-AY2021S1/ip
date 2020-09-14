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
    void printTaskMarkAsDone(DukeTask task, int size);

    String getTaskMarkAsDone(DukeTask task, int size);

    // FIND COMMAND
    void printKeywordCannotBeFound(String keyword);

    String getKeywordCannotBeFound(String keyword);

    void printKeywordFoundResult(String keyword, boolean isPlural);

    String getKeywordFoundResult(String keyword, boolean isPlural);

    // SORT COMMAND
    void printSortListResult(String tag, boolean isPlural);

    String getSortListResult(String tag, boolean isPlural);

    // GENERAL
    void printTaskStatus(int size);

    String getTaskStatus(int size);
}
