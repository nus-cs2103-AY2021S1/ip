package duke;

import duke.textstoreandprint.TextPrinter;

import java.util.Scanner;

public class Ui {

    private CommandParserAndLogic commandParserAndLogic;

    private static boolean hasEnded = false;


    public Ui(TaskList taskList) {
        commandParserAndLogic = new CommandParserAndLogic(taskList);
    }

    public void run() {

        String current;
        Scanner sc = new Scanner(System.in);
        TextPrinter.printPromptMsg();

        while (!hasEnded) {

            current = sc.nextLine();
            commandParserAndLogic.handleInput(current);

        }
        sc.close();
    }

    public static void endUi() {
        hasEnded = true;
    }
}
