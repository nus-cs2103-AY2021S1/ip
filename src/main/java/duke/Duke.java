package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    protected TaskList taskList = new TaskList();
    protected Ui ui = new Ui(taskList);

    public void activate() {
        Scanner sc = new Scanner(System.in);
        ui.greeting();

        String input = "";
        while (!input.equals("bye") && sc.hasNextLine()) {
            try {
                input = sc.nextLine();
                Command c = Parser.parse(input);
                c.execute(taskList, ui, input);
            } catch (DukeException e) {
                System.out.println(e + "\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        duke.activate();
    }
}
