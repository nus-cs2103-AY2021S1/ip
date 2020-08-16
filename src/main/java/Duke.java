import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private List<String> todos;

    public Duke() {
        todos = new ArrayList<String>();
        this.todos = todos;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println(duke.toString());
        echo();
        //duke.receiveInput(); //lvl 2
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String command;
        while (sc.hasNextLine()) {
            command = sc.nextLine();
            if (!command.equals("bye")) {
                System.out.println(command);
            } else {
                sc.close();
                System.out.println("bai~ see you!");
                System.exit(0);
                return;
            }
        }
    }

    @Override
    public String toString() {
        String dongLogo = "  ______      _______     ____    __    \n"
                + " |   _  \\    /   _   \\   |    \\  |  |\n"
                + " |  | |  |  |   | |   |  |  |\\ \\ |  |\n"
                + " |  |_|  |  |   |_|   |  |  | \\ \\|  |\n "
                + "|_____ /    \\______ /   |__|  \\____|\n";
        String msg = "Hola! I'm Don \n" +
                "How can I help you?";
        return dongLogo + "\n" + msg;
    }

}
