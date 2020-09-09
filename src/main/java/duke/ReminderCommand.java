package duke;

import java.time.LocalDate;

public class ReminderCommand extends Command {
    
    public boolean checkDeadline(Deadline d, LocalDate today) {
        return (d.by.isAfter(today) || d.by.isEqual(today));
    }
    
    public boolean checkEvent(Event e, LocalDate today) {
        return (e.at.isAfter(today) || e.at.isEqual(today));
    }
    
    @Override
    public String execute(TaskList taskList, Ui ui, Storage store) throws DukeException {
        LocalDate today = LocalDate.now();
        String overdue = "";
        String notOverdue = "";
        for (Task task : taskList.getList()) {
            if (task instanceof Deadline) {
                if (checkDeadline((Deadline) task, today)) {
                    notOverdue += task.toString() + "\n";
                } else {
                    overdue += task.toString() + "\n";
                }
            }
            if (task instanceof Event) {
                if (checkEvent((Event) task, today)) {
                    notOverdue += task.toString() + "\n";
                } else {
                    overdue += task.toString() + "\n";
                }
            }
        }
        return ui.showOverdue() + overdue + ui.showNotOverdue() + notOverdue;
    }
}
