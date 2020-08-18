package main.java;

public class DoneCommand extends Command {

    public DoneCommand() {
        names = new String[] { "done" };
    }

    @Override
    public void execute(String str) {
        UIPrint.drawLine(UIPrint.star, 50);

        int taskIndex = Integer.parseInt(str) - 1;

        if (Duke.tasks.size() <= taskIndex || taskIndex < 0) {
            System.out.println("Sorry " + str + " is not a valid index");
        } else {
            Task task = Duke.tasks.get(taskIndex);
            task.markAsDone();
            System.out.println("Nice, I've marked this task as done:");
            System.out.println(task);
        }

        UIPrint.drawLine(UIPrint.star, 50);
    }
}
