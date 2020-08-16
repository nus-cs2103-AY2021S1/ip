package duke.Command;

public abstract class Command {

    public abstract String execute(String str);

    public static String parse(String str) throws Exception {
        switch (str) {
            case "bye":
                return new ExitCommand().execute(str);
            case "list":
                return new ListCommand().execute(str);
            case "blah":
                return new RandomCommand().execute(str);
            default:
                throw new Exception();

        }
    }
}
