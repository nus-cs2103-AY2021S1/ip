package main.java;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.listFoundTasks(tasks.filterByKeyword(keyword));
    }
}
