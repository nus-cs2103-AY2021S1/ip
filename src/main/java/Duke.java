import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private String name;
    private boolean running;
    private List<String> list;

    public Duke(String name) {
        this.name = name;
        running = true;
        sendMessage("Hello!\n" + this.name + " lives to serve :)");

        list = new ArrayList<>();
    }

    public boolean isRunning() {
        return running;
    }

    public void genericError() {
        sendMessage("Whoops something went wrong hehe...");
    }

    public void exit() {
        running = false;
        sendMessage("Bye :(");
    }

    public void sendMessage(String message) {
        String pad = "__________________________________________________";
        System.out.println(pad + "\n" + message + "\n" + pad);
    }

    public void addToList(String task) {
        if (list.add(task)) {
            sendMessage("added: " + task);
        } else {
            genericError();
        }
    }

    public void displayList() {
        if (list.isEmpty()) {
            sendMessage("Your list is empty!");
        } else {
            int num = 1;
            String msg = "";
            for (String task: list) {
                msg += String.format("%d. %s", num, task);
                if (num < list.size()) {
                    msg += "\n";
                }
                num++;
            }
            sendMessage(msg);
        }
    }

    public static void main(String[] args) {
        String lvl = "2";
        String logo = "                                                 _     _\n"
                + " _______  _______  _______  _______  __   __    (c).-.(c)\n"
                + "|       ||       ||  _    ||  _    ||  | |  |    / ._. \\ \n"
                + "|_     _||    ___|| |_|   || |_|   ||  |_|  |  __\\( Y )/__\n"
                + "  |   |  |   |___ |       ||       ||       | (_.-/'-'\\-._)\n"
                + "  |   |  |    ___||  _   | |  _   | |_     _|    || " + lvl + " || \n"
                + "  |   |  |   |___ | |_|   || |_|   |  |   |    _.' `-' '._\n"
                + "  |___|  |_______||_______||_______|  |___|   (.-./`-'\\.-.)\n"
                + "                                               `-'     `-'\n"
                + "Level: " + lvl;
        System.out.println(logo);

        Duke duke = new Duke("Tebby");
        Scanner sc = new Scanner(System.in);

        while (duke.isRunning()) {
            String userInput = sc.nextLine();
            String input = userInput.toLowerCase();

            switch(input) {
                case "bye":
                    duke.exit();
                    break;
                case "list":
                    duke.displayList();
                    break;
                default:
                    duke.addToList(input);
                    break;
            }
        }

        sc.close();
    }
}
