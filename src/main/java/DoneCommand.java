package main.java;

public class DoneCommand extends Command {

    protected int taskNumber;

    public DoneCommand(String taskNumber) {
        this.taskNumber = Integer.parseInt(taskNumber);
    }

    @Override
    public void perform(TaskList tasks) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        System.out.println(" Yay! I have marked this task as done: " + "\n"
                + "   " + task.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
