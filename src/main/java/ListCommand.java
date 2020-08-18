package main.java;

public class ListCommand extends Command {

    public ListCommand() {
        names = new String[] { "list" };
    }

    @Override
    public void execute() {
        System.out.println(Duke.inputThings);
    }
}
