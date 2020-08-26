package main.java;

public class ListCommand extends Command {

    @Override
    public void perform(TaskList tasks) {
        System.out.println(" These are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println(" " + (i + 1) + "." + t.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
