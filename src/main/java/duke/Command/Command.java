package duke.Command;

public abstract class Command {

    public abstract String execute();

    public static String parse(String str) throws Exception {
        switch (str) {
            case "bye":
                return new ExitCommand().execute();
            case "list":
                return new ListCommand().execute();
            case "blah":
                return new RandomCommand().execute();
            default:
                throw new Exception();
        }
    }
}
