package duke.command;

import duke.Duke;

public abstract class Command {

    public String[] names;

    public abstract void execute(String str, Duke duke);
}
