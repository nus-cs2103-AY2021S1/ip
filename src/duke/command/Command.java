package duke.command;

public abstract class Command {

    public String[] names;

    public abstract void execute(String str);
}
