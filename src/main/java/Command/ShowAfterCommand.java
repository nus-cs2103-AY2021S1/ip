package main.java.Command;

import main.java.DeadlineTask;
import main.java.DukeDateTimeParserException;
import main.java.EventTask;
import main.java.Parser;
import main.java.Storage;
import main.java.Task;
import main.java.TaskList;
import main.java.Ui;

import java.time.LocalDate;

public class ShowAfterCommand extends Command {

    private String command;

    public ShowAfterCommand(String command) {
        super();
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeDateTimeParserException {
        LocalDate localDate = Parser.findDateParser(this.command);
        StringBuilder sb = new StringBuilder();
        int i = 1;

        for(Task task : tasks.getTasks()) {
            if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                if (deadlineTask.getDateTime().toLocalDate().isAfter(localDate)) {
                    sb.append(ui.formatMessage(i + ". " + deadlineTask + "\n"));
                    i++;
                }

            } else if(task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                if (eventTask.getDateTime().toLocalDate().isAfter(localDate)) {
                    sb.append(ui.formatMessage(i + ". " + eventTask + "\n"));
                    i++;
                }
            }

        }

        ui.messageTemplate(sb.toString());
    }
}
