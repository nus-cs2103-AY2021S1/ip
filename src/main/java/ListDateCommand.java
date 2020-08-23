import java.time.LocalDate;

public class ListDateCommand extends Command {
  public ListDateCommand(String commandString) {
    super(CommandType.LIST_DATE, commandString);
  }

  @Override
  public void execute(TaskList tasks) throws DukeException {
    String dateString = this.getTaskDescription();
    LocalDate date = Parser.parseDateString(dateString);
    Ui.showListDate(tasks, date);
  }
}
