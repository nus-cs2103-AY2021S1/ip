package duke;

import java.util.Scanner;

public class Ui {

    Scanner sc = new Scanner(System.in);

    /**
     * Shows greetings when user starts the bot.
     */
    public void greet() {
        String donLogo = "   ___     ___    _  _     ___     ___ \n"
                + "  |   \\   / _ \\  | \\| |   / __|   / _ \\  \n"
                + "  | |) | | (_) | | .` |  | (_ |  | (_) | \n"
                + "  |___/   \\___/  |_|\\_|   \\___|   \\___/  \n"
                + "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n"
                + "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' \n";
        String msg = "Hola! I'm Dongo :) \n" +
                "How can I help you?";
        System.out.println(donLogo + "\n" + msg);
    }

    /**
     * Shows the loading error if
     * there is a problem in loading the bot.
     */
    public void showLoadingError() {
        System.out.println("Unable to load.... Try again later.");
    }

    /**
     * Reads user's command from Scanner.
     * @return user's command.
     */
    public String readCommand() {
        String command = sc.nextLine();
        return command;
    }

    /**
     * Exits the bot and shows bye message.
     */
    public void bye() {
        sc.close();
        System.out.println("Time to say goodbye :( \n" +
                "Have a great day!");
        System.exit(0);
        return;
    }

    /**
     * Shows pretty line before and after every output.
     */
    public void showLine() {
        //System.out.println("\n__̴ı̴̴̡̡̡ ̡͌l̡̡̡ ̡͌l̡*̡̡ ̴̡ı̴̴̡ ̡̡͡|̲̲̲͡͡͡ ̲▫̲͡ ̲̲̲͡͡π̲̲͡͡ ̲̲͡▫̲̲͡͡ ̲|̡̡̡ ̡ ̴̡ı̴̡̡ ̡͌l̡̡̡̡._____̴ı̴̴̡̡̡ ̡͌l̡̡̡ ̡͌l̡*̡̡ ̴̡ı̴̴̡ ̡̡͡|̲̲̲͡͡͡ ̲▫̲͡ ̲̲̲͡͡π̲̲͡͡ ̲̲͡▫̲̲͡͡ ̲|̡̡̡ ̡ ̴̡ı̴̡̡ ̡͌l̡̡̡̡._____̴ı̴̴̡̡̡ ̡͌l̡̡̡ ̡͌l̡*̡̡ ̴̡ı̴̴̡ ̡̡͡|̲̲̲͡͡͡ ̲▫̲͡ ̲̲̲͡͡π̲̲͡͡ ̲̲͡▫̲̲͡͡ ̲|̡̡̡ ̡ ̴̡ı̴̡̡ ̡͌l̡̡̡̡._____̴ı̴̴̡̡̡ ̡͌l̡̡̡ ̡͌l̡*̡̡ ̴̡ı̴̴̡ ̡̡͡|̲̲̲͡͡͡ ̲▫̲͡ ̲̲̲͡͡π̲̲͡͡ ̲̲͡▫̲̲͡͡ ̲|̡̡̡ ̡ ̴̡ı̴̡̡ ̡͌l̡̡̡̡.___\n");
        System.out.println("\n▬▬ι═══════ﺤ -═══════ι▬▬ ▬▬ι═══════ﺤ -═══════ι▬▬\n");
    }

    /**
     * Shows error message.
     * @param msg Error message.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

}
