package duke;

import java.util.ArrayList;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> relevantTasks = new ArrayList<>();
        for (Task t : tasks.getTasklist()) {
            System.out.println("TASK: " + t);
            if (t.getDescription().contains(keyword)) {
                relevantTasks.add(t);
            }
        }

        ui.printDivider();
        ui.printMsg("Here are the matching tasks Mr Camel found:");
        int counter = 1;
        for (Task t : relevantTasks) {
            ui.printMsg(counter + ". " + t.toString());
            counter++;
        }

        ui.printDivider();
    }
}
