
import main.java.TaskList;
import main.java.Parser;
import main.java.Ui;

import java.util.Scanner;

public class Duke {
    private TaskList taskList;

    Duke() {
        this.taskList = new TaskList();
    }

    public static void main(String[] args) {
        Duke mrduke = new Duke();
        mrduke.run();
    }

    public void run() {
        boolean toContinue;
        Ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        do {
            String answer = sc.nextLine();
            toContinue = Parser.understandText(answer, this.taskList);
        } while (toContinue);
    }
}
