package duke;

import java.util.Scanner;

public class Ui {

    Scanner sc = new Scanner(System.in);

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

    public void showLoadingError() {
        System.out.println("Unable to load.... Try again later.");
    }

    public String readCommand() {
        String command = sc.nextLine();
        return command;
//            if (firstWord.equals("list")) {
//                listItems();
//
//            } else if (firstWord.equals("bye")) {
//                sc.close();
//                System.out.println("Time to say goodbye :( \n" +
//                                    "Have a great day!");
//                System.exit(0);
//                return;
//
//            } else {
//
//                if (firstWord.equals("done")) {
//                    processDone(duke.command);
//                } else if (firstWord.equals("todo")) {
//                    processTodo(duke.command);
//                } else if (firstWord.equals("deadline")) {
//                    processDeadline(duke.command);
//                } else if (firstWord.equals("event")) {
//                    processEvent(duke.command);
//                } else if (firstWord.equals("delete")) {
//                    processDelete(duke.command);
//                } else {
//                    WrongInputException wrong = new WrongInputException();
//                    System.out.println(wrong.getMessage());
//                }
//            }
//        }
    }

    public void bye() {
        sc.close();
        System.out.println("Time to say goodbye :( \n" +
                "Have a great day!");
        System.exit(0);
        return;
    }

    public void showLine() {
        //System.out.println("\n__̴ı̴̴̡̡̡ ̡͌l̡̡̡ ̡͌l̡*̡̡ ̴̡ı̴̴̡ ̡̡͡|̲̲̲͡͡͡ ̲▫̲͡ ̲̲̲͡͡π̲̲͡͡ ̲̲͡▫̲̲͡͡ ̲|̡̡̡ ̡ ̴̡ı̴̡̡ ̡͌l̡̡̡̡._____̴ı̴̴̡̡̡ ̡͌l̡̡̡ ̡͌l̡*̡̡ ̴̡ı̴̴̡ ̡̡͡|̲̲̲͡͡͡ ̲▫̲͡ ̲̲̲͡͡π̲̲͡͡ ̲̲͡▫̲̲͡͡ ̲|̡̡̡ ̡ ̴̡ı̴̡̡ ̡͌l̡̡̡̡._____̴ı̴̴̡̡̡ ̡͌l̡̡̡ ̡͌l̡*̡̡ ̴̡ı̴̴̡ ̡̡͡|̲̲̲͡͡͡ ̲▫̲͡ ̲̲̲͡͡π̲̲͡͡ ̲̲͡▫̲̲͡͡ ̲|̡̡̡ ̡ ̴̡ı̴̡̡ ̡͌l̡̡̡̡._____̴ı̴̴̡̡̡ ̡͌l̡̡̡ ̡͌l̡*̡̡ ̴̡ı̴̴̡ ̡̡͡|̲̲̲͡͡͡ ̲▫̲͡ ̲̲̲͡͡π̲̲͡͡ ̲̲͡▫̲̲͡͡ ̲|̡̡̡ ̡ ̴̡ı̴̡̡ ̡͌l̡̡̡̡.___\n");
        System.out.println("\n▬▬ι═══════ﺤ -═══════ι▬▬ ▬▬ι═══════ﺤ -═══════ι▬▬\n");
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

}
