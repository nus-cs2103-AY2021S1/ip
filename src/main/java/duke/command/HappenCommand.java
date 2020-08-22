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
        DateTimeFormatter parse = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM d yyyy");
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
                    LocalDate date = LocalDate.parse(detail, parse);
                    ui.printList(list, t -> t.happenOnDate(date), "happening on " + date.format(output) + " ");
                }
            } else if (type.equals("before") && !sc.hasNext()) {
                if (detail.equals("today")) {
                    ui.printList(list, Task::happenBeforeToday, "happening before today ");
                } else {
                    LocalDate date = LocalDate.parse(detail, parse);
                    ui.printList(list, t -> t.happenBeforeDate(date),
                            "happening before " + date.format(output) + " ");
                }
            } else if (type.equals("after") && !sc.hasNext()) {
                if (detail.equals("today")) {
                    ui.printList(list, Task::happenAfterToday, "happening after today ");
                } else {
                    LocalDate date = LocalDate.parse(detail, parse);
                    ui.printList(list, t -> t.happenAfterDate(date),
                            "happening after " + date.format(output) + " ");
                }
            } else if (type.equals("in") && sc.next().equals("days") && !sc.hasNext()) {
                int n = Integer.parseInt(detail);
                if (n <= 0) {
                    throw new InvalidCommandException("Please input a positive integer for happen in command");
                }
                ui.printList(list, t -> t.happenIn(n), "happening in " + n + " days");
            } else if (type.equals("between") && sc.hasNext()) {
                String detail2 = sc.next();
                if (sc.hasNext()) {
                    throw new InvalidCommandException("Invalid happen command input");
                }
                LocalDate date1 = LocalDate.parse(detail, parse);
                LocalDate date2 = LocalDate.parse(detail2, parse);
                if (!date1.isBefore(date2)) {
                    throw new InvalidCommandException("Latter date is before former date for happen between");
                }
                ui.printList(list, t -> t.happenBetween(date1, date2),
                        "happening bewteen " + date1.format(output) + " and " + date2.format(output) + " ");
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
