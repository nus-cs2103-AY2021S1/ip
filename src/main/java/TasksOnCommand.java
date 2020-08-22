package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TasksOnCommand extends Command{
    private LocalDate date;

    public TasksOnCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);

        StringBuilder output = new StringBuilder("\t Here are the tasks on ")
                .append(formattedDate).append(":\n");

        int numbering = 1;
        boolean isFree = true;
        Task task;
        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            if (task.hasSameDate(this.date)) {
                if (isFree) {
                    isFree = false;
                }
                output.append("\t ").append(numbering).append(".").append(task).append("\n");
                numbering++;
            }
        }

        if (isFree) {
            ui.showMessage("\tYay! You have nothing to do on " + formattedDate + "! :-)\n");
        } else {
            ui.showMessage(output.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
