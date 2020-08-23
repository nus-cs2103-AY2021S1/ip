package main.java;

import java.util.ArrayList;

public class DeadlineCommand extends Command {

    String[] commandDetails;

    public DeadlineCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        String day = commandDetails[1].split(" ", 2)[1];
        System.out.println("~ \n Got it. I've added this task: ");
        Deadline deadline = new Deadline(commandDetails[0], day.trim());
        tasks.add(deadline);
        System.out.println(String.format("   %s \n Now you have %d tasks in the list. \n~",
                deadline, tasks.size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
