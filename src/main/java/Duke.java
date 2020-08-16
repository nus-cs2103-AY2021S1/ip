import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static Task[] tasks = new Task[100];
    public static int numberOfTasks = 0;

    public static void printTasks() {
        System.out.println(
                "    ____________________________________________________________");
        for (int i = 0; i < numberOfTasks; i++) {
            Task t = tasks[i];
            System.out.format("     %d.[%s] %s\n", i + 1, t.getStatusIcon(), t.getDescription());
        }
        System.out.println(
                "    ____________________________________________________________\n");
    }

    public static void main(String[] args) {
        boolean running = true;

        String logo =
                "############################################################# \n" +
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
                String endMessage =
                        "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________\n";
                System.out.println(endMessage);
                running = false;

            } else if (input.matches("done \\d+")) {
                int taskNum = Integer.parseInt(input.substring(5));
                if (taskNum <= numberOfTasks) {
                    Task t = tasks[taskNum - 1];
                    t.markAsDone();
                    String doneTaskMessage =
                            "    ____________________________________________________________\n" +
                            "     Nice! I've marked this task as done: \n" +
                            "       [" + t.getStatusIcon() + "] " + t.getDescription() + "\n" +
                            "    ____________________________________________________________";
                    System.out.println(doneTaskMessage);
                } else {
                    String invalidTaskMessage =
                            "    ____________________________________________________________\n" +
                            "     Rrrrwrr:( You you don't even have a task " + taskNum + "\n" +
                            "    ____________________________________________________________";
                    System.out.println(invalidTaskMessage);
                }
            } else if (input.equals("list")) {
                printTasks();
            } else {
                if (numberOfTasks < 100) {
                    tasks[numberOfTasks] = new Task(input);
                    numberOfTasks++;

                    String addTaskMessage =
                            "    ____________________________________________________________\n" +
                            "     added:" + input + "\n" +
                            "    ____________________________________________________________\n";
                    System.out.println(addTaskMessage);
                } else {
                    String tooManyTasksMessage =
                            "    ____________________________________________________________\n" +
                            "    I'm too full to add more tasks :(\n" +
                            "    ____________________________________________________________\n";
                    System.out.println(tooManyTasksMessage);
                }
            }
        }
    }
}
