package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String content;

    public FindCommand(String content) {
        super(false);
        this.content = content;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuffer sb = new StringBuffer();
        int index = 1;
        sb.append("Here are the matching tasks in your list:\n\t ");
        boolean hasTasks = false;
        for (Task t : tasks.getList()) {
            CharSequence contentToFind = content.subSequence(0, content.length());
            String taskDescription = t.getDescription();
            if (taskDescription.contains(contentToFind)) {
                hasTasks = true;
                sb.append(index).append(".").append(t.toString()).append("\n\t ");
                index++;
            }
        }
        if (hasTasks) {
            ui.printMessage(sb.delete(sb.length() - 3, sb.length() - 1).toString());
        } else {
            ui.printMessage("You do not have any tasks containing " + "\"" + content + "\"!");
        }
    }
}
