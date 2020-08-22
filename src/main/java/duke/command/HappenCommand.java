package duke.command;

import duke.component.*;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HappenCommand extends Command {
    public HappenCommand(String input) {
        super(input);
    }

    @Override
    public void execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        String description = input.substring(7);
        Scanner sc = new Scanner(description);
        try {
            String type, detail;
            try {
                type = sc.next();
                detail = sc.next();
            } catch (Exception e) {
                throw new InvalidCommandException("Invalid happen command input");
            }
            if (type.equals("on") && !sc.hasNext()) {
                if (detail.equals("today")) {
                    ui.printList(list, Task::happenToday, "happening today ");
                } else {
                    LocalDate date = LocalDate.parse(detail, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    ui.printList(list, t -> t.happenOnDate(date), "happening on " +
                            date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ");
                }
            } else if (type.equals("before") && !sc.hasNext()) {
                if (detail.equals("today")) {
                    ui.printList(list, Task::happenBeforeToday, "happening before today ");
                } else {
                    LocalDate date = LocalDate.parse(detail, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    ui.printList(list, t -> t.happenBeforeDate(date), "happening before " +
                            date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ");
                }
            } else if (type.equals("after") && !sc.hasNext()) {
                if (detail.equals("today")) {
                    ui.printList(list, Task::happenAfterToday, "happening after today ");
                } else {
                    LocalDate date = LocalDate.parse(detail, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    ui.printList(list, t -> t.happenAfterDate(date), "happening after " +
                            date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ");
                }
            } else {
                throw new InvalidCommandException("Invalid happen command input");
            }
        } catch (InvalidCommandException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid date format. Please use yyyy-MM-dd");
        }
    }
}
