package dukechatbot.executor;

import dukechatbot.command.Command;
import dukechatbot.constant.DukeConstants;
import dukechatbot.dukeoutput.DukeOutput;
import dukechatbot.storage.Storage;
import dukechatbot.tasklist.TaskList;

public class ExitCommandExecutor extends CommandExecutor {
    
    @Override
    public String execute(Command command, TaskList taskList) {
        Storage.save(taskList.getList());
        return DukeOutput.output(DukeConstants.EXIT_RESPONSE);
    }
}
