public class ByeCommand extends Command {
  public ByeCommand(String commandString) {
    super(CommandType.BYE, commandString);
  }

  @Override
  public void execute(TaskList tasks) {
    Ui.showBye();
  }

  @Override
  public boolean isExit() {
    return true;
  }
}
