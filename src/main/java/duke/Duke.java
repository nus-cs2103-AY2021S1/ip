package duke;

import duke.utils.Constants;

import java.util.Objects;
import java.util.Scanner;


public class Duke {

    private static void respond(Scanner scanner) throws IllegalStateException{
        DukeCommandMatcher dukeCommandMatcher = new DukeCommandMatcher();
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                try {
                    System.out.println(Constants.DIVIDER);
                    String response = dukeCommandMatcher.matchCommand(command);
                    if (Objects.equals(response, "EXIT")) {
                        break;
                    }
                    System.out.println(Constants.DIVIDER);
                }catch(Exception err){
                    System.out.println(err);
                    System.out.println(Constants.DIVIDER);
                }
            }
        scanner.close();
    }

    private static void launchDuke() {
        System.out.println(Constants.DIVIDER);
        System.out.println(Constants.GREETING);
        System.out.println(Constants.DIVIDER);
        Scanner dukeScanner = new Scanner(System.in);
        respond(dukeScanner);
    }


    public static void main(String[] args) {
        launchDuke();
    }


}

