import java.util.Scanner;

public class Duke {
    private TaskList taskList;
    private Parser parser;
    public Duke() {
        this.taskList = new TaskList();
        this.parser = new Parser(this.taskList);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Util.systemMessage(Util.logo);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            try {
                parser.parseAndRun(input);
            } catch (DukeException e) {
                Util.systemMessage(e.getMessage());
            }
            input = scanner.nextLine();
        }
        Util.systemMessage("bye sir thanks for using me sir hope to see you again sir");
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
