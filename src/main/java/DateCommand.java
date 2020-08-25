import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class DateCommand extends Command{
    public DateCommand(String inputCommand) {
        super(inputCommand);
    }

    @Override
    public void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException {
        Pattern pattern = Pattern.compile("date [0-9]{1,2}/[0-9]{1,2}/[0-9]{4,4}");
        if(!pattern.matcher(inputCommand).matches()) {
            throw new DukeCommandException("\u2639 OOPS!!! Wrong 'date' command format!");
        } else {
            String date = inputCommand.split(" ")[1];
            LocalDate time = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            ui.printMessage("Here are the tasks of date " + date + ":");

            int ctr = 1;
            for(Task task: list.getList()) {
                if(task instanceof Deadline) {
                    Deadline deadline = (Deadline)task;
                    LocalDateTime temp = deadline.by;
                    if(time.getYear() == temp.getYear() && time.getDayOfYear() == temp.getDayOfYear()) {
                        ui.printMessage(""+ctr + "."+ task);
                        ctr++;
                    }
                }
                if(task instanceof Event) {
                    Event event = (Event)task;
                    LocalDateTime temp = event.at;
                    if(time.getYear() == temp.getYear() && time.getDayOfYear() == temp.getDayOfYear()) {
                        ui.printMessage(""+ctr + "."+ task);
                        ctr++;
                    }
                }
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
