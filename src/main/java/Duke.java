import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Duke {
    public static void main(String[] args) {
        try {
            init();
        } catch (IOException e) {
            System.out.println("Oops! An error has occurred when reading data from local disk.");
        }
    }

    static void init() throws IOException {
        final String FILE_PATH = "data/data.txt";
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
        Tasks tasks = new Tasks();
        try {
            File f = new File(FILE_PATH);
            if (!f.exists()) {
                if (f.getParentFile().mkdirs() && f.createNewFile()) {
                    System.out.println("\tStorage space for you tasks has been initialized successfully.");
                }
            } else {
                tasks.processStorage(f, new Scanner(f));
            }
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong when loading your tasks from storage.");
            e.printStackTrace();
            return;
        }
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t___________________________________________________________________________\n");

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while (!input.equals("bye")) {
            try {
                tasks.processInput(input);
            } catch (DukeException e) {
                System.out.println("\t___________________________________________________________________________");
                System.out.println("\t " + e.getMessage());
                System.out.println("\t___________________________________________________________________________\n");
            }
            input = s.nextLine();
        }
        tasks.writeData();
        bye();
    }

    static void bye() {
        System.out.println("\t___________________________________________________________________________");
        System.out.println("\t Bye. Hope to see you again soon");
        System.out.println("\t___________________________________________________________________________\n");
    }
}
