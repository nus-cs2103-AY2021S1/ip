import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {

    protected AddDeadlineCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
        try {
            String[] deadlineInfo = retrieveDeadlineInfo(this.parsedCommand);
            String[] timeStamp = deadlineInfo[1].split(" ");

            LocalDate deadlineDate = LocalDate.parse(timeStamp[0], dateFormatter);
            LocalTime deadlineTime = LocalTime.parse(timeStamp[1], timeFormatter);

            Task toAdd = new Deadline(deadlineInfo[0], deadlineDate, deadlineTime);

            tasks.addTask(toAdd);

            String successReply = "  Success! This deadline task has been added: \n\t" +
                    toAdd.toString() + "\n   You have " + tasks.getListSize() + " tasks in your list now.";
            ui.printReply(successReply);

            storage.saveFile(tasks);
        } catch (DateTimeParseException ex) {
            String err = "The task date format is incorrect. \n" +
                    "Please input a valid date using the format: 'dd/mm/yyyy hh:mm'. For eg, 10/8/2020 18:00";
            throw new InvalidFunctionException(err);
        } catch (DukeException ex) {
            throw ex;
        }
    }

    public String[] retrieveDeadlineInfo(String[] parsedCommand) throws InvalidTaskException {
        String[] deadlineInfo = new String[2];
        String description = "";
        String time = "";
        if (parsedCommand.length == 0) {
            String err = "Your deadline task has missing arguments and has an incorrect format. " +
                    "The task cannot be created.\n" +
                    "Type '/commands' to view the correct command for task creation!";
            throw new InvalidTaskException(err);
        } else {
            String[] taskInputArray = parsedCommand[1].split(" /by ");
            if (!parsedCommand[1].contains(" /by ") && !parsedCommand[1].endsWith("/by")) {
                String err = "Your deadline task has an incorrect format. The task cannot be created. \n" +
                        "Type '/commands' to view the correct command for task creation!";
                throw new InvalidTaskException(err);
            } else if (parsedCommand[1].trim().equals("/by")) {
                String err = "Your deadline task is missing a description and time stamp. " +
                        "The task cannot be created. \n" +
                        "Type '/commands' to view the correct command for task creation!";
                throw new InvalidTaskException(err);
            } else if (parsedCommand[1].trim().endsWith("/by")) {
                String err = "Your deadline task is missing a time stamp. The task cannot be created. \n" +
                        "Type '/commands' to view the correct command for task creation!";
                throw new InvalidTaskException(err);
            } else if (taskInputArray.length == 1 || taskInputArray[0].isBlank()) {
                String err = "Your deadline task is missing a description. The task cannot be created. \n" +
                        "Type '/commands' to view the correct command for task creation!";
                throw new InvalidTaskException(err);
            } else {
                description = taskInputArray[0];
                time = taskInputArray[1].trim();
            }
        }
        deadlineInfo[0] = description;
        deadlineInfo[1] = time;
        return deadlineInfo;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
