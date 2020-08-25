package duke.resource;

import duke.Command;

public class Parser {

    public static Command parse(String command) {
        return new Command(command.split(" "));
    }

}
