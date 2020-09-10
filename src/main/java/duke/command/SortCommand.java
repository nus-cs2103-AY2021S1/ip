package duke.command;
import java.util.ArrayList;
import java.util.stream.IntStream;

import duke.exception.InvalidInstructionFormatException;
import duke.logic.CommandInteractionUi;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.task.DeadlineTask;
import duke.task.DukeTask;
import duke.task.EventTask;


/**
 * Represents an Sort Command by the user.
 * Sorts the user's tasks and displays them like LIST
 * Currently it sorts by chronological order
 * Since TodoTasks do not have a date, they are appended at the end in any order
 */
public class SortCommand extends Command {
    private final String tag; // denotes what to sort

    public SortCommand(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean getExitStatus() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, CommandInteractionUi uiManager,
                        StorageManager storageManager, boolean isGuiTask) throws InvalidInstructionFormatException {
        assert uiManager != null : "ListCommand must have a uiManager";
        assert taskList != null : "ListCommand must have a taskList";
        verifyTag(tag);
        taskList.sortTaskList();

        ArrayList<DukeTask> filteredList = new ArrayList<>();
        taskList.getTaskList().forEach(task -> {
            if (tag.equals("all")) {
                filteredList.add(task);
            } else if (tag.equals("deadline") && task instanceof DeadlineTask) {
                filteredList.add(task);
            } else if (tag.equals("event") && task instanceof EventTask) {
                filteredList.add(task);
            }
        });

        if (isGuiTask) {
            StringBuilder output = new StringBuilder(
                    uiManager.getSortList(tag, filteredList.size() > 1) + "\n");
            IntStream.range(0, filteredList.size())
                    .forEach(i -> output.append(uiManager.getNumberedTask(filteredList.get(i), i)).append("\n"));
            response = output.toString();
        } else {
            uiManager.printSortList(tag, filteredList.size() > 1);
            IntStream.range(0, filteredList.size())
                    .forEach(i -> uiManager.printNumberedTask(filteredList.get(i), i));
        }
    }

    /**
     * Verifies that the tag matches the tag required by the Instruction.
     * Tags are:
     * all - for all Tasks.
     * deadline - for deadline Tasks.
     * event - for event Tasks.
     * @param tag String denoting tag.
     * @throws InvalidInstructionFormatException when tag is invalid.
     */
    private void verifyTag(String tag) throws InvalidInstructionFormatException {
        switch (tag) {
        case "all":
        case "deadline":
        case "event":
            return;
        default:
            throw new InvalidInstructionFormatException();
        }
    }
}
