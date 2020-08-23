public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) {
        StringBuilder sb = new StringBuilder();
        int len = taskList.tasksSize();
        if (len == 0) {
            sb.append("No tasks!");
            ui.format(sb.toString());
            return;
        }
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < len; i++) {
            sb.append(i + 1 + "." + taskList.get(i) + "\n");
        }
        ui.format(sb.toString());
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
