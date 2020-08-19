import Util.Constants;
import Util.UtilFunction;

import java.util.Scanner;


public class Duke {

    public static void Echo(Scanner scanner) throws IllegalStateException{
        while(scanner.hasNextLine()){
            String lineToPrint = scanner.nextLine();
            System.out.println(Constants.DIVIDER);

            if(UtilFunction.matchPattern(Constants.EXITPATTERN, lineToPrint)){
                closeDuke();
                break;
            }

            System.out.println(lineToPrint);
            System.out.println(Constants.DIVIDER);
        }
    }

    public static void launchDuke() {
        System.out.println(Constants.DIVIDER);
        System.out.println(Constants.GREETING);
        System.out.println(Constants.DIVIDER);
        Scanner dukeScanner = new Scanner(System.in);
        Echo(dukeScanner);
    }

    public static void closeDuke() {
        System.out.println("Farewell/再見/さようなら～～");
    }

    public static void main(String[] args) {
        launchDuke();
    }


}

