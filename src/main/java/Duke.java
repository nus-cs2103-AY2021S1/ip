import java.util.Calendar;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        init();
    }

    static void init() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);

        String logo = "         ,---._                                                            \n" +
                        "       .-- -.' \\    ,---,       ,-.----.                 ,---,  .--.--.    \n" +
                        "       |    |   :  '  .' \\      \\    /  \\        ,---.,`--.' | /  /    '.  \n" +
                        "       :    ;   | /  ;    '.    ;   :    \\      /__./||   :  :|  :  /`. /  \n" +
                        "       :        |:  :       \\   |   | .\\ : ,---.;  ; |:   |  ';  |  |--`   \n" +
                        "       |    :   ::  |   /\\   \\  .   : |: |/___/ \\  | ||   :  ||  :  ;_     \n" +
                        "       :         |  :  ' ;.   : |   |  \\ :\\   ;  \\ ' |'   '  ; \\  \\    `.  \n" +
                        "       |    ;   ||  |  ;/  \\   \\|   : .  / \\   \\  \\: ||   |  |  `----.   \\ \n" +
                        "   ___ l         '  :  | \\  \\ ,';   | |  \\  ;   \\  ' .'   :  ;  __ \\  \\  | \n" +
                        " /    /\\    J   :|  |  '  '--'  |   | ;\\  \\  \\   \\   '|   |  ' /  /`--'  / \n" +
                        "/  ../  `..-    ,|  :  :        :   ' | \\.'   \\   `  ;'   :  |'--'.     /  \n" +
                        "\\    \\         ; |  | ,'        :   : :-'      :   \\ |;   |.'   `--'---'   \n" +
                        " \\    \\      ,'  `--''          |   |.'         '---\" '---'                \n" +
                        "  \"---....--'                   `---'                                      \n";
        if (hour < 12) {
            System.out.println("\tGood morning boss! This is\n" + logo);
        } else if (hour < 20) {
            System.out.println("\tGood afternoon boss! This is\n" + logo);
        } else {
            System.out.println("\tGood evening boss! This is\n" + logo);
        }
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t___________________________________________________________________________\n");

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while (!input.equals("bye")) {
            echo(input);
            input = s.nextLine();
        }
        echo("\tBye. Hope to see you again soon");
    }

    static void echo(String s) {
        System.out.println("\t___________________________________________________________________________");
        System.out.println("\t" + s);
        System.out.println("\t___________________________________________________________________________\n");
    }
}
