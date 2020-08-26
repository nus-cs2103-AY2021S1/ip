import java.io.*;
import java.time.format.DateTimeParseException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            this.ui.sendFailedInitialiseMessage();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.sendInitialiseMessage();
        this.ui.sendBar();
        this.ui.greet();
        this.ui.sendBar();
        String userInput = this.ui.getUserInput();
        while (!userInput.equals("bye")) {
            this.ui.sendBar();
            try {
                if(
                Parser.parseAndExecute(userInput, this.tasks, this.ui)
                ) {
                    this.storage.save(tasks);
                }
            } catch (MissingDoneArgumentException e) {
                this.ui.sendExceptionMessage(e);
            } catch (DoneOutOfRangeException e) {
                this.ui.sendExceptionMessage(e);
            } catch (MissingDeleteArgumentException e) {
                this.ui.sendExceptionMessage(e);
            } catch (DeleteOutOfRangeException e) {
                this.ui.sendExceptionMessage(e);
            } catch (EmptyTodoException e) {
                this.ui.sendExceptionMessage(e);
            } catch (MissingDeadlineDateException e) {
                this.ui.sendExceptionMessage(e);
            } catch (EmptyDeadlineException e) {
                this.ui.sendExceptionMessage(e);
            } catch (MissingEventDateException e) {
                this.ui.sendExceptionMessage(e);
            } catch (EmptyEventException e) {
                this.ui.sendExceptionMessage(e);
            } catch (UnknownCommandException e) {
                this.ui.sendExceptionMessage(e);
            } catch (IOException e) {
                this.ui.sendExceptionMessage(e);
            } catch (DateTimeParseException e) {
                this.ui.sendExceptionMessage("\uD83D\uDE41 OOPS! Date should be in the format: YYYY-MM-DD");
            }
            this.ui.sendBar();
            userInput = this.ui.getUserInput();
        }
        this.ui.sendBar();
        this.ui.bidFarewell();
        this.ui.sendBar();
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/data/Duke.txt").run();
    }
}