package duke.ui;

import duke.response.Response;

// Class that handle the printing and formatting of the program's response message.
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String ERROR_HEADER = "___________________________ERROR!___________________________";

    /**
     * Prints a formatted response message to the user.
     *
     * @param resp Response response message to show the user.
     */
    public void print(Response resp) {
        String message = resp.getMessage();
        if (resp.isError()) {
            System.out.print(ERROR_HEADER + "\n" + message + "\n" + DIVIDER + "\n");
        } else {
            System.out.print(DIVIDER + "\n" + message + "\n" + DIVIDER + "\n");
        }
    }
}
