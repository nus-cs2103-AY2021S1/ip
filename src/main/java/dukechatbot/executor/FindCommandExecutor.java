package dukechatbot.executor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dukechatbot.command.Command;
import dukechatbot.command.FindCommand;
import dukechatbot.constant.DukeConstants;
import dukechatbot.dukeoutput.DukeOutput;
import dukechatbot.task.Task;
import dukechatbot.tasklist.TaskList;

/**
 * Represents executor of Find command.
 * Executes the action of finding tasks that contain the search keyword.
 *
 */
public class FindCommandExecutor extends CommandExecutor {

    /**
     * Gets the list of task in task list that contains the search keyword
     * and converts them to their toString information for the list of response.
     *
     * @param command
     * @param taskList
     */
    @Override
    public void execute(Command command, TaskList taskList) {
        String searchKeyword = ((FindCommand) command).getArgument();
        List<String> responses = new ArrayList<>(Collections.singletonList(DukeConstants.FIND_OUTPUT));
        List<Task> matchedTasks = taskList.findMatches(searchKeyword);
        matchedTasks.stream().map(Task::toString).forEach(responses::add);
        DukeOutput.output(responses);
    }
}
