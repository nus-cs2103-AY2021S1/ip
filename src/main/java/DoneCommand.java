import java.io.IOException;

public class DoneCommand extends Command {
    public String input;

    public DoneCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean isExited() {
        return false;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) throws InvalidDoneInputException, IOException {
        try {
            int num = Integer.parseInt(input.replaceAll("\\s+", ""));
            if (num > tasklist.getNumOfTask() || num <= 0) {
                throw new InvalidDoneInputException();
            }
            Task task = tasklist.markDone(num);
            tasklist.updateData(storage);
            ui.showDone(task);
        } catch (NumberFormatException e) {
            throw new InvalidDoneInputException();
        }
    }
}
