package duke;

import java.util.Scanner;

import duke.commands.Command;

public class Duke {
    /**
     * Runs the duke.Duke programme
     * @param args main args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage("\\save.txt");
        Ui ui = new Ui(sc);
        ui.printWelcome();
        while (sc.hasNextLine()) {
            String next = sc.nextLine();
            try {
                Command c = Parser.manage(next);
                c.execute(ui, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Gets a string output from Duke based on input given in order to output message for client.
     * @param input Input that is parsed to generate a output.
     */
    public String getResponse(String input) throws DukeException {
        Ui ui = new Ui();
        Storage storage = new Storage("\\save.txt");
        Command c = Parser.manage(input);
        return (c.execute(ui, storage));
    }
}
