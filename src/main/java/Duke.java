import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("\\save.txt");
        ui.printWelcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String next = sc.nextLine();
            if (next.equals("bye") ) {
                System.out.println("Bye! Woof!");
                break;
            }
            try {
                Command c = Parser.manage(next);
                c.execute(ui, storage);
            } catch (DukeException e) {
                System.out.println(e);
            }

        }

    }
}
