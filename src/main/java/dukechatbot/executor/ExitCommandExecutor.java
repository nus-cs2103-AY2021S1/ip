package dukechatbot.executor;

import dukechatbot.command.Command;
import dukechatbot.constant.DukeConstants;
import dukechatbot.dukeoutput.DukeOutput;
import dukechatbot.storage.Storage;
import dukechatbot.tasklist.TaskList;


/**
 * Represents executor of exit command.
 * Saves the task list to disk.
 * Returns exit response message.
 */
public class ExitCommandExecutor implements CommandExecutor {

    /**
     * Saves the task list to disk.
     * 
     * @param command
     * @param taskList
     * @return Response from Duke.
     */
    @Override
    public String execute(Command command, TaskList taskList) {
        Storage.save(taskList.getList());
        return DukeOutput.getOutput(DukeConstants.EXIT_RESPONSE);
    }
}
