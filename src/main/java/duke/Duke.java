package duke;
import duke.command.Command;
import duke.command.Commands;
import duke.exception.InvalidArgumentException;
import duke.exception.UserException;
import duke.gui.Launcher;
import duke.misc.Parser;
import duke.misc.Ui;
import duke.task.TaskList;

import java.util.List;
import java.util.Scanner;

public class Duke {
    private TaskList taskList;

    public String getResponse(String input) {
        try {
            List<String> inputTokens = Parser.parseCommand(input);
            Command command = Commands.create(inputTokens);
            return command.run(taskList);
        } catch (UserException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Unhandled exception: \n"
                    + e.getMessage() + "\n";
        }
    }

    public Duke() {
        try {
            taskList = new TaskList();
        } catch (InvalidArgumentException e) {
            System.out.println("Error parsing file");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);

        System.out.println(Ui.wrap(Ui.greet()));
        while (true) {
            String input = sc.nextLine();
            String response = duke.getResponse(input);
            System.out.println(Ui.wrap(response));
            if (input.equals("bye")) break;
        }
        sc.close();
    }
}











