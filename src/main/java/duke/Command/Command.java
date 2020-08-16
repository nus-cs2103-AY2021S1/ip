package duke.Command;

import java.util.ArrayList;

public abstract class Command {

    public abstract String execute(String str);

    public static ArrayList<String> listArray = new ArrayList<>();

    public static String parse(String str) throws Exception {
        switch (str) {
            case "bye":
                return new ExitCommand().execute(str);
            case "list":
                return new ListCommand().execute(str);
            case "blah":
                return new RandomCommand().execute(str);
            default:
                try {
                    return new AddCommand().execute(str);
                } catch (Exception e) {
                    throw new Exception();
                }
        }
    }
}
