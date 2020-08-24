import java.util.Scanner;

public abstract class Command {
    TaskManager tm;
    Ui ui;
    Scanner sc;

    public void setUtility(TaskManager tm, Ui ui, Scanner sc) {
        this.tm = tm;
        this.ui = ui;
        this.sc = sc;
    }

    public abstract boolean execute() throws DukeException;
}