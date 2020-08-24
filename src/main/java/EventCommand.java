import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Adds the event entry that the user input to the
 * Arraylist of Duke
 */
public class EventCommand extends Command{
    
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    public EventCommand(String input) {
        super(input);
    }
    /**
     * Add event entry to the Arraylist
     * @param tasks list of tasks given
     * @param ui handles the output to print
     * @param storage writes the save file
     * @throws InvalidDateTimeFormatException if input does not follow format specified
     * @throws InvalidInputException if the input for the delete is incorrect
     * @throws InvalidSaveFileException if there is an issue writing the save file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateTimeFormatException,
            InvalidInputException, InvalidSaveFileException {
        if (super.input.length() <= 6) {
            throw new InvalidInputException("\t☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] splitWord = super.input.split("/");
        String desc = splitWord[0].substring(6,splitWord[0].length()-1);
        String timing = splitWord[1].substring(3);
        Events task;
        try {
            task = new Events(desc,LocalDateTime.parse(timing,dtf) );
        } catch(DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("\tEvent timing input must follow a certain format: yyyy-mm-dd HH:mm " +
                    "e.g. 2020-08-23 16:45");
        }
        tasks.getTasks().add(task);
        System.out.println("\tGot it. I've added this task:\n" + "\t"+task.toString() +
                "\n\tNow you have " + tasks.getTasks().size() + " tasks in the list.");
        storage.saveFile(tasks.getTasks());

    }

    /**
     * Lets the main logic know that it can not exit
     * @return false to prevent loop from exiting
     */
    public boolean isExit() {
        return false;
    }
}
