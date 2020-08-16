import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        init();
    }

    static void init() {
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
        System.out.println("\tHello boss! This is\n" + logo);
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t___________________________________________________________________________\n");

        Scanner s = new Scanner(System.in);
        Tasks tasks = new Tasks();
        String input = s.nextLine();
        while (!input.equals("bye")) {
            String[] inputArray = input.split(" ");
            if (input.equals("list")) {
                tasks.listTasks();
            } else if (inputArray[0].equals("done")) {
                tasks.markDone(Integer.parseInt(inputArray[1]));
            } else {
                try {
                    tasks.addTask(input);
                } catch (DukeException e) {
                    System.out.println("\t___________________________________________________________________________");
                    System.out.println("\t " + e.getMessage());
                    System.out.println("\t___________________________________________________________________________\n");
                }
            }
            input = s.nextLine();
        }
        bye();
    }

    static void bye() {
        System.out.println("\t___________________________________________________________________________");
        System.out.println("\t Bye. Hope to see you again soon");
        System.out.println("\t___________________________________________________________________________\n");
    }
}
