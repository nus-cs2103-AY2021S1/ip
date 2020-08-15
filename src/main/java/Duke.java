import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static String[] tasks = new String[100];
    public static int numberOfTasks = 0;

    public static void printTasks() {
        System.out.println(
                "    ____________________________________________________________");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.format("     %d. %s\n", i + 1, tasks[i]);
        }
        System.out.println(
                "    ____________________________________________________________\n");
    }

    public static void main(String[] args) {
        boolean running = true;

        String logo = "############################################################# \n" +
                "###################################################   ####### \n" +
                "###############################################   /~\\   #####\n" +
                "############################################   _- `~~~', ####\n" +
                "##########################################  _-~       )  ####\n" +
                "#######################################  _-~          |  ####\n" +
                "####################################  _-~            ;  #####\n" +
                "##########################  __---___-~              |   #####\n" +
                "#######################   _~   ,,                  ;  `,,  ##\n" +
                "#####################  _-~    ;'                  |  ,'  ; ##\n" +
                "###################  _~      '                    `~'   ; ###\n" +
                "############   __---;                                 ,' ####\n" +
                "########   __~~  ___                                ,' ######\n" +
                "#####  _-~~   -~~ _                               ,' ########\n" +
                "##### `-_         _                              ; ##########\n" +
                "#######  ~~----~~~   ;                          ; ###########\n" +
                "#########  /          ;                        ; ############\n" +
                "#######  /             ;                      ; #############\n" +
                "#####  /                `                    ; ##############\n" +
                "###  /                                      ; ###############\n" +
                "#                                            ################";
        System.out.println("Hello from\n" + logo);

        System.out.println(
                "    ____________________________________________________________\n" +
                "     Henlo! I am Woolf,\n" +
                "     here to help you track those delicious tasks!\n" +
                "     Am I a good boy yet?\n" +
                "    ____________________________________________________________\n");

        while(running) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(
                        "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________\n");

                running = false;
            } else if (input.equals("list")) {
                printTasks();
            } else {
                tasks[numberOfTasks] = input;
                numberOfTasks++;

                System.out.println(
                        "    ____________________________________________________________\n" +
                        String.format("     added:" + input + "\n") +
                        "    ____________________________________________________________\n");
            }
        }
    }
}
