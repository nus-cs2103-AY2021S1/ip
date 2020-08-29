package duke;

public class Duke {

    /**
     * Responds to the input command.
     *
     * @param input input command line
     * @return response from duke
     */
    public String getResponse(String input) {
        Command command = new Command(input);
        return command.execute();
    }

}
