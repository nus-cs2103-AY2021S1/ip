import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {

    protected AddEventCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
        try {
            String[] eventInfo = retrieveEventInfo(this.parsedCommand);
            String[] timeStamp = eventInfo[1].split(" ");

            LocalDate eventDate = LocalDate.parse(timeStamp[0], dateFormatter);
            LocalTime eventTime = LocalTime.parse(timeStamp[1], timeFormatter);

            Task toAdd = new Event(eventInfo[0], eventDate, eventTime);

            tasks.addTask(toAdd);

            String successReply = "  Success! This event task has been added: \n\t" +
                    toAdd.toString() + "\n   You have " + tasks.getListSize() + " tasks in your list now.";
            ui.printReply(successReply);

            storage.saveFile(tasks);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException ex) {
            String err = "The task date format is incorrect. \n" +
                    "Please input a valid date using the format: 'dd/mm/yyyy hh:mm'. For eg, 10/8/2020 18:00";
            throw new InvalidFunctionException(err);
        }
    }

    public String[] retrieveEventInfo(String[] parsedCommand) throws InvalidTaskException {
        String[] eventInfo = new String[2];
        String description = "";
        String time = "";
        if (parsedCommand.length == 0) {
            String err = "Your event task has missing arguments and has an incorrect format. " +
                    "The task cannot be created.\n" +
                    "Type '/commands' to view the correct command for task creation!";
            throw new InvalidTaskException(err);
        } else {
            String[] taskInputArray = parsedCommand[1].split(" /at ");
            if (!parsedCommand[1].contains(" /at ") && !parsedCommand[1].endsWith("/at")) {
                String err = "Your event task has an incorrect format. The task cannot be created. \n" +
                        "Type '/commands' to view the correct command for task creation!";
                throw new InvalidTaskException(err);
            } else if (parsedCommand[1].trim().equals("/at")) {
                String err = "Your event task is missing a description and time stamp. " +
                        "The task cannot be created. \n" +
                        "Type '/commands' to view the correct command for task creation!";
                throw new InvalidTaskException(err);
            } else if (parsedCommand[1].trim().endsWith("/at")) {
                String err = "Your event task is missing a time stamp. The task cannot be created. \n" +
                        "Type '/commands' to view the correct command for task creation!";
                throw new InvalidTaskException(err);
            } else if (taskInputArray.length == 1 || taskInputArray[0].isBlank()) {
                String err = "Your event task is missing a description. The task cannot be created. \n" +
                        "Type '/commands' to view the correct command for task creation!";
                throw new InvalidTaskException(err);
            } else {
                description = taskInputArray[0];
                time = taskInputArray[1].trim();
            }
        }
        eventInfo[0] = description;
        eventInfo[1] = time;
        return eventInfo;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
