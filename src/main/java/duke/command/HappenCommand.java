package duke.command;

import duke.component.*;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HappenCommand extends Command {
    public HappenCommand(String input) {
        super(input);
    }

    @Override
    public String execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        String description = input.substring(7);
        String[] detail = description.split(" ");
        DateTimeFormatter parse = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM d yyyy");
        try {
            if (detail[0].equals("on") && detail.length == 2) {
                if (detail[1].equals("today")) {
                    return ui.printList(list, Task::happenToday, "happening today ");
                } else {
                    LocalDate date = LocalDate.parse(detail[1], parse);
                    return ui.printList(list, t -> t.happenOnDate(date), "happening on " + date.format(output) + " ");
                }
            } else if (detail[0].equals("before") && detail.length == 2) {
                if (detail[1].equals("today")) {
                    return ui.printList(list, Task::happenBeforeToday, "happening before today ");
                } else {
                    LocalDate date = LocalDate.parse(detail[1], parse);
                    return ui.printList(list, t -> t.happenBeforeDate(date),
                            "happening before " + date.format(output) + " ");
                }
            } else if (detail[0].equals("after") && detail.length == 2) {
                if (detail[1].equals("today")) {
                    return ui.printList(list, Task::happenAfterToday, "happening after today ");
                } else {
                    LocalDate date = LocalDate.parse(detail[1], parse);
                    return ui.printList(list, t -> t.happenAfterDate(date),
                            "happening after " + date.format(output) + " ");
                }
            } else if (detail.length == 3 && detail[0].equals("in") && detail[2].equals("days")) {
                int n = Integer.parseInt(detail[1]);
                if (n <= 0) {
                    throw new InvalidCommandException("Please input a positive integer for happen in command.");
                }
                return ui.printList(list, t -> t.happenIn(n), "happening in " + n + " days ");
            } else if (detail[0].equals("between") && detail.length == 3) {
                LocalDate date1 = LocalDate.parse(detail[1], parse);
                LocalDate date2 = LocalDate.parse(detail[2], parse);
                if (!date1.isBefore(date2)) {
                    throw new InvalidCommandException("Latter date is before former date for happen between.");
                }
                return ui.printList(list, t -> t.happenBetween(date1, date2),
                        "happening between " + date1.format(output) + " and " + date2.format(output) + " ");
            } else {
                throw new InvalidCommandException("Invalid happen command input.");
            }
        } catch (InvalidCommandException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof HappenCommand) {
            return input.equals(((HappenCommand) obj).input);
        } else {
            return false;
        }
    }
}
