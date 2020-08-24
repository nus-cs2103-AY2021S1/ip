
public abstract class Command {
    protected String name;
    protected String usage;
    protected String description;

    Command(){}
    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }
}
