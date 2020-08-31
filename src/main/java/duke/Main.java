package duke;

import duke.cmd.Duke;

/**
 * Main entry point of application
 * Deals with args and decides whether to run the cmd or gui application
 */
public class Main {

    /**
     * The main entry point of application
     * @param args Program arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
