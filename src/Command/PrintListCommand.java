package Command;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

import java.util.Arrays;


public class PrintListCommand extends Command {

    public PrintListCommand(String[] command) {
        super(command);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof PrintListCommand) {
            PrintListCommand cur = (PrintListCommand) o;
            if (Arrays.equals(this.command, cur.command)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
