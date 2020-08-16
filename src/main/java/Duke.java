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
        Tasks tasks = new Tasks();
        String input = s.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                tasks.printTasks();
            } else {
                tasks.addTask(input);
            }
            input = s.nextLine();
        }
        bye();
    }

    static void bye() {
        System.out.println("\t___________________________________________________________________________");
        System.out.println("\t" + "\tBye. Hope to see you again soon");
        System.out.println("\t___________________________________________________________________________\n");
    }
}
