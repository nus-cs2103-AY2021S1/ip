import java.io.IOException;

public class ExceptionCommand extends Command {
    Exception e;

    public ExceptionCommand(Exception e, TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
        this.e = e;
    }

    @Override
    public String execute() throws IOException {
        return "Jarvis exception: " + this.e.getMessage();
    }
}
