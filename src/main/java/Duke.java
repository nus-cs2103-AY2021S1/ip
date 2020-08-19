import Util.Constants;
import Util.DukeCommandMatcher;
import Util.UtilFunction;

import java.util.Scanner;


public class Duke {

    private static void Respond(Scanner scanner) throws IllegalStateException{
        DukeCommandMatcher dukeCommandMatcher = new DukeCommandMatcher();
        while(scanner.hasNextLine()){
            String command = scanner.nextLine();
            System.out.println(Constants.DIVIDER);
            dukeCommandMatcher.matchCommand(command);
            System.out.println(Constants.DIVIDER);
        }
    }

    private static void launchDuke() {
        System.out.println(Constants.DIVIDER);
        System.out.println(Constants.GREETING);
        System.out.println(Constants.DIVIDER);
        Scanner dukeScanner = new Scanner(System.in);
        Respond(dukeScanner);
    }


    public static void main(String[] args) {
        launchDuke();
    }


}

