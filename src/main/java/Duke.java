import java.util.Scanner;
import java.util.concurrent.LinkedTransferQueue;

public class Duke {
    static String divider = "    ____________________________________________________________\n";
    static void start() {
        String logo =
                                " .----------------.  .----------------.  .----------------.  .----------------. \n" +
                                        "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                                        "| |  ________    | || |     ____     | || |     ____     | || |  ___  ____   | |\n" +
                                        "| | |_   ___ `.  | || |   .'    `.   | || |   .'    `.   | || | |_  ||_  _|  | |\n" +
                                        "| |   | |   `. \\ | || |  /  .--.  \\  | || |  /  .--.  \\  | || |   | |_/ /    | |\n" +
                                        "| |   | |    | | | || |  | |    | |  | || |  | |    | |  | || |   |  __'.    | |\n" +
                                        "| |  _| |___.' / | || |  \\  `--'  /  | || |  \\  `--'  /  | || |  _| |  \\ \\_  | |\n" +
                                        "| | |________.'  | || |   `.____.'   | || |   `.____.'   | || | |____||____| | |\n" +
                                        "| |              | || |              | || |              | || |              | |\n" +
                                        "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                                        " '----------------'  '----------------'  '----------------'  '----------------' \n";
        String intro =
                "\n" +
                "\n" +
                logo +
                "\n" +
                divider +
                "     Hola! I am dook\n" +
                "     how i can help u?\n" + divider;
        System.out.println(intro);
    }
    static void print(String str){
        String intro = divider + "     " + str + "\n" + divider;
        System.out.println(intro);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        start();
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            print(input);
            input = sc.nextLine();
        }
        print("see u later alligator");
    }
}
