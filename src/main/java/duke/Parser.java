package duke;

public class Parser {
    /**
     * Parses user input.
     * @param input User input.
     * @return The user input parsed into a String array.
     * @throws MissingDescriptionException If the user did not specify the description.
     * @throws MissingDurationException If the user did not specify the time.
     * @throws InvalidInputException If the user input is invalid.
     */
    public static String[] parseInput(String input)
            throws MissingDescriptionException, MissingDurationException, InvalidInputException {
        String[] arr = input.split("\\s", 2);
        String pref = arr[0];
        String rest;
        if (arr[0].equalsIgnoreCase("bye")) {

        } else if (arr[0].equalsIgnoreCase("list")) {

        } else if (arr[0].equalsIgnoreCase("find")) {

        } else if (arr[0].equalsIgnoreCase("done")) {
            if (arr.length == 1) {
                throw new MissingDescriptionException("done");
            }
        } else if (arr[0].equalsIgnoreCase("delete")) {
            if (arr.length == 1) {
                throw new MissingDescriptionException("delete");
            }
        } else if (arr[0].equalsIgnoreCase("todo")) {
            if (arr.length == 1) {
                throw new MissingDescriptionException("todo");
            }
        } else if (arr[0].equalsIgnoreCase("deadline")) {
            if (arr.length == 1) {
                throw new MissingDescriptionException("deadline");
            }
            rest = arr[1];
            arr = new String[3];
            String[] arr1 = rest.split("\\s/by\\s", 2);
            arr[0] = pref;

            if (arr1.length == 1) {
                throw new MissingDurationException("deadline");
            }
            arr[1] = arr1[0];
            arr[2] = arr1[1];
        } else if (arr[0].equalsIgnoreCase("event")) {
            if (arr.length == 1) {
                throw new MissingDescriptionException("event");
            }
            rest = arr[1];
            arr = new String[3];
            String[] arr1 = rest.split("\\s/at\\s", 2);
            arr[0] = pref;

            if (arr1.length == 1) {
                throw new MissingDurationException("event");
            }
            assert arr1.length > 2;

            arr[1] = arr1[0];
            arr[2] = arr1[1];
        } else {
            throw new InvalidInputException();
        }

        return arr;
    }
}
