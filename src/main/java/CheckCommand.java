import java.time.LocalDate;

import java.util.ArrayList;

public class CheckCommand extends Command {

    private LocalDate date;

    public CheckCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> lib = tasks.getTaskList();

        ui.printCheckStatement(date);
        for (int i = 0; i < lib.size(); i++) {
            if (lib.get(i).date == null) {
                continue;
            }

            if (lib.get(i).date.equals(date)) {
                ui.showTask(lib.get(i).toString());
            }
        }
        ui.printEndLine();
    }

    @Override
    public boolean isDone() {
        return false;
    }

}
