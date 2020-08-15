import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        Boolean botRunning = true;
        String logo =
                "___.           __                         \n"
                        + "\\_ |__ _____ _/  |_  _____ _____    ____  \n"
                        + " | __ \\__  \\     __\\/     \\__  \\  /     \\\n"
                        + " | \\_\\ \\/ __ \\|  | |  Y Y  \\/ __ \\|   |  \\ \n"
                        + " |___  (____  /__| |__|_|  (____  /___|  /\n"
                        + "     \\/     \\/           \\/     \\/     \\/\n";
        System.out.println("Hello from\n" + logo);

        while (botRunning) {
            String input = myObj.nextLine();
            if (input.equals("bye")) {
                botRunning = false;
            } else {
                System.out.println(input);
            }
        }
        System.out.println("SAYONARA HUMAN ಥ﹏ಥ");
    }
}
