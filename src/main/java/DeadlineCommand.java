package main.java;

public class DeadlineCommand extends Command {

    Deadline deadline;

    public DeadlineCommand(String description, String date) {
        this.deadline = new Deadline(description, date);
    }

    @Override
    public void perform(TaskList tasks) {
        tasks.add(deadline);
        System.out.println(" Okay! I have added this task:" + "\n" + "   "
            + deadline.toString() + "\n" + " Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks."
            : " task."));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
