package seedu.duke;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Runs the Duke program, where user types in commands to add, delete, and mark tasks as done.
 */

public class Duke {

    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy kkmm", Locale.ENGLISH);
    protected Ui ui;
    protected Storage storage;
    protected TaskList tasklist;

    public Duke() {
        this.ui = new Ui();
        this.storage =  new Storage();
        try {
            this.tasklist = new TaskList(storage.load());
        } catch (IOException e) {
            this.ui.showLoadingError();
            this.tasklist = new TaskList();
        }
    }

    /**
     * Runs the actual program.
     */
    public void run() {
        this.ui.start();

        boolean isExit = false;

        while(!isExit) {
            try {
                String line = this.ui.getUserCommand();
                this.ui.linePrinter();
                Command c = new Parser().parse(line);
                c.execute(this.tasklist, this.ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.showError(e.getMessage());
            } catch (DateTimeException e) {
                this.ui.showError("Can't read your date man. Put it like this ok? --> 25 Mar 2020 1930");
            } finally {
                if (! isExit) {
                    this.ui.linePrinter();
                }
            }
        }

        try {

            String tasks = "";

            for (Task t : this.tasklist.getList()) {
                String check = "";
                if (t.isComplete()) {
                    check = "\u2713";
                } else {
                    check = "\u2718";
                }
                String toAdd = t.getType() + "*" + check + "*" + t.toString();
                String addition = "";
                if (t.getTime() == null) {
                    addition = "\n";
                } else {
                    addition = "*" + t.getTime().format(FORMATTER) + "\n";
                }
                tasks = tasks + toAdd + addition;
            }

            this.storage.save(tasks);
        } catch (IOException e) {
            this.ui.showError("Whoops! Some kind of error :/ see here: " + e.getMessage());
        }
        this.ui.bye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
