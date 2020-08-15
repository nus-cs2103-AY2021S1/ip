import java.util.Scanner;

public class Duke {

    private String name;
    private boolean running;

    public Duke(String name) {
        this.name = name;
        running = true;
        sendMessage("Hello!\n" + this.name + " lives to serve :)");
    }

    public boolean isRunning() {
        return running;
    }

    public void exit() {
        running = false;
        sendMessage("Bye :(");
    }

    public void sendMessage(String message) {
        String pad = "__________________________________________________";
        System.out.println(pad + "\n" + message + "\n" + pad);
    }

    public static void main(String[] args) {
        String logo = "                                                 _     _\n"
                + " _______  _______  _______  _______  __   __    (c).-.(c)\n"
                + "|       ||       ||  _    ||  _    ||  | |  |    / ._. \\ \n"
                + "|_     _||    ___|| |_|   || |_|   ||  |_|  |  __\\( Y )/__\n"
                + "  |   |  |   |___ |       ||       ||       | (_.-/'-'\\-._)\n"
                + "  |   |  |    ___||  _   | |  _   | |_     _|    || T || \n"
                + "  |   |  |   |___ | |_|   || |_|   |  |   |    _.' `-' '._\n"
                + "  |___|  |_______||_______||_______|  |___|   (.-./`-'\\.-.)\n"
                + "                                               `-'     `-'\n"
                + "Level: 1";
        System.out.println(logo);

        Duke duke = new Duke("Tebby");
        Scanner sc = new Scanner(System.in);

        while (duke.isRunning()) {
            String userInput = sc.nextLine();
            if (userInput.toLowerCase().equals("bye")) {
                duke.exit();
            } else {
                duke.sendMessage(userInput);
            }
        }

        sc.close();
    }
}
