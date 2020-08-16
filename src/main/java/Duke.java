import main.java.Logic;
import java.util.Scanner;

public class Duke {
    private Logic logic;

    Duke() {
        this.logic = new Logic();
    }
    public static void main(String[] args) {
        Duke mrduke = new Duke();
        mrduke.initialize();
    }
    public void initialize() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String INITIAL_PRINTING = logo + "Hello! I'm Duke\n" + "What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(INITIAL_PRINTING);
        this.run();
    }
    public void run() {
        boolean toContinue;
        Scanner sc = new Scanner(System.in);
        do {
            String answer = sc.nextLine();
            toContinue = this.logic.digestString(answer);
        } while (toContinue);
    }
}
