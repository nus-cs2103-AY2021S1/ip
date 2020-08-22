import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    LocalDate toDoBy;
    LocalTime time;

    Deadline(String description, LocalDate toDoBy, LocalTime time) {
        super(description);
        this.toDoBy = toDoBy;
        this.time = time;
    }

    Deadline(String description, LocalDate toDoBy) {
        super(description);
        this.toDoBy = toDoBy;
        this.time = null;
    }

    Deadline(String description, String toDoBy, Boolean isDone) {
        super(description, isDone);
        String[] scheduledTime = toDoBy.split(" ");
        this.toDoBy = LocalDate.of(Integer.parseInt(scheduledTime[2]),
                Month.valueOf(scheduledTime[1].trim().toUpperCase()).getValue(),
                Integer.parseInt(scheduledTime[0]));
        if (scheduledTime.length == 4) {
            this.time = LocalTime.parse(scheduledTime[3].trim());
        } else {
            this.time = null;
        }
    }

    public static Deadline createTask(String message) throws DukeException{
        String errMessage1 = " Oops!! You missed out some vital information/keyword... *woof*\n";
        String errMessage2 = " Oops!! You gonna forget what this is about if you\n" +
                " dont give me a description... *woof*\n";
        String errMessage3 = " Oops!! You did not state when you wanna finish this by...\n" +
                " Are you planning to procrastinate? *woof*\n";
        try {
            String messageLowerCase = message.toLowerCase();
            int indOfTime = messageLowerCase.indexOf("/by");
            String description = message.substring(9, indOfTime);
            String deadline = message.substring(indOfTime + 3).trim();
            if (description.isBlank() && deadline.isBlank()) {
                String exMessage = Task.ui.printFormat(errMessage1);
                throw new DukeException(exMessage);
            }else if (deadline.isBlank()) {
                String exMessage = Task.ui.printFormat(errMessage3);
                throw new DukeException(exMessage);
            } else if (description.isBlank()) {
                String exMessage = Task.ui.printFormat(errMessage2);
                throw new DukeException(exMessage);
            } else {
                String[] splitDeadline = deadline.split("\\s+");
                try {
                    String[] inputDate = splitDeadline[0].trim().split("/");
                    String formatDate = inputDate[0] + "-" + inputDate[1] + "-" + inputDate[2];
                    LocalDate date = LocalDate.parse(formatDate);

                    if (splitDeadline.length != 1) {
                        LocalTime time = LocalTime.parse(splitDeadline[1].trim());
                        return new Deadline(description, date, time);
                    } else {
                        return new Deadline(description, date);
                    }
                } catch (Exception e) {
                    String errMessage =
                            Task.ui.printFormat(" Please input deadline in following format:\n"
                                    + "   YYYY/MM/DD HH:MM!\n" + " *Woof woof*\n");
                    throw new DukeException(errMessage);
                }
            }
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            String exMessage = Task.ui.printFormat(errMessage1);
            throw new DukeException(exMessage);
        }
    }

    @Override
    public boolean compareDate(LocalDate date) {
        return toDoBy.compareTo(date) == 0;
    }


    @Override
    public String toString() {
        String s = "[D]" + super.toString() + " (FINISH by: "
                + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(toDoBy);
        if (time == null) {
            return s + ")";
        } else {
            return s + " " + time.toString() + ")";
        }
    }
}
