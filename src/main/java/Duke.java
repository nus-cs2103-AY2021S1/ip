import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static Task[] tasks = new Task[100];
    public static int numberOfTasks = 0;

    public enum TaskType { EXIT, TODO, DEADLINE, EVENT, DONE, LIST, INVALID };

    public static void printTasks() {
        System.out.println(
                "    ____________________________________________________________");
        for (int i = 0; i < numberOfTasks; i++) {
            Task t = tasks[i];
            System.out.format("     %d.%s\n", i + 1, t.toString());
        }
        System.out.println(
                "    ____________________________________________________________\n");
    }

    public static TaskType findTaskType(String input, String[] arr) {
        if (input.equals("bye")) {
            return TaskType.EXIT;
        } else if (input.equals("list")) {
            return TaskType.LIST;
        } else {
            String firstWord = arr[0];
            switch (firstWord) {
                case "done":
                    if (arr.length != 2 || !arr[1].matches("\\d+")) {
                        String errorMsg = "    ____________________________________________________________\n" +
                                "     ☹ OOPS!!! The description of a done needs can only be a single integer.\n" +
                                "    ____________________________________________________________";
                        System.out.println(errorMsg);
                    } else {
                        return TaskType.DONE;
                    }
                case "todo":
                    if (arr.length < 2) {
                        String errorMsg = "    ____________________________________________________________\n" +
                                "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                "    ____________________________________________________________";
                        System.out.println(errorMsg);
                        return TaskType.INVALID;
                    } else {
                        return TaskType.TODO;
                    }
                case "deadline": {
                    boolean hasSuffix = false;
                    int indexOfSuffix = -1;
                    for (int i = 0; i < arr.length; i++) {
                        System.out.println(arr[i]);
                        if (arr[i].equals("/by")) {
                            indexOfSuffix = i;
                            hasSuffix = true;
                            break;
                        }
                    }
                    System.out.println(indexOfSuffix);
                    if (hasSuffix && indexOfSuffix > 1 && arr.length - 1 > indexOfSuffix) {
                        return TaskType.DEADLINE;
                    } else {
                        String errorMsg = "    ____________________________________________________________\n" +
                                "     ☹ OOPS!!! Ensure that deadlines have a description\n" +
                                "     followed by suffix '/by' and a date after it\n" +
                                "    ____________________________________________________________";
                        System.out.println(errorMsg);
                        return TaskType.INVALID;
                    }
                }
                case "event": {
                    boolean hasSuffix = false;
                    int indexOfSuffix = -1;
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i].equals("/at")) {
                            indexOfSuffix = i;
                            hasSuffix = true;
                            break;
                        }
                    }
                    if (hasSuffix && indexOfSuffix > 1 && arr.length - 1 > indexOfSuffix) {
                        return TaskType.EVENT;
                    } else {
                        String errorMsg = "    ____________________________________________________________\n" +
                                "     ☹ OOPS!!! Ensure that events have a description\n" +
                                "     followed by suffix '/at' and a time after it\n" +
                                "    ____________________________________________________________";
                        System.out.println(errorMsg);
                        return TaskType.INVALID;
                    }
                }
                default:
                    String errorMsg = "    ____________________________________________________________\n" +
                            "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                            "    ____________________________________________________________";
                    System.out.println(errorMsg);
                    return TaskType.INVALID;
            }
        }
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
            String[] arrayForm = input.split(" ", 0);
            TaskType type = findTaskType(input, arrayForm);

            switch (type) {
                case INVALID: {
                    break;
                }
                case EXIT: {
                    String endMessage =
                            "    ____________________________________________________________\n" +
                                    "     Bye. Hope to see you again soon!\n" +
                                    "    ____________________________________________________________\n";
                    System.out.println(endMessage);
                    running = false;
                    break;
                }
                case LIST: { printTasks(); break; }
                case TODO: {
                    String[] arr = input.split(" ", 2);
                    String description = arr[1];
                    Task t = new Todo(description);
                    tasks[numberOfTasks] = t;
                    numberOfTasks++;

                    String addTaskMessage =
                            "    ____________________________________________________________\n" +
                                    "     Got it. I've added this task:\n" +
                                    "       " + t.toString() + "\n" +
                                    "     Now you have " + numberOfTasks + " tasks in the list.\n" +
                                    "    ____________________________________________________________\n";
                    System.out.println(addTaskMessage);
                    break;
                }
                case DEADLINE: {
                    int index = -1;
                    for (int i = 0; i < arrayForm.length; i++) {
                        if (arrayForm[i].equals("/by")) {
                            index = i;
                            break;
                        }
                    }
                    StringBuilder description = new StringBuilder();
                    for (int i = 1; i < index; i++) {
                        if (i == 1) {
                            description.append(arrayForm[i]);
                        } else {
                            description.append(" ").append(arrayForm[i]);
                        }
                    }
                    StringBuilder doBy = new StringBuilder();
                    for (int j = index + 1; j < arrayForm.length; j++) {
                        if (j == index + 1) {
                            doBy.append(arrayForm[j]);
                        } else {
                            doBy.append(" ").append(arrayForm[j]);
                        }
                    }
                    Task t = new Deadline(description.toString(), doBy.toString());
                    tasks[numberOfTasks] = t;
                    numberOfTasks++;
                    String msg = "    ____________________________________________________________\n" +
                            "     Got it. I've added this task: \n" +
                            "       " + t.toString() + "\n" +
                            "     Now you have " + numberOfTasks + " tasks in the list.\n" +
                            "    ____________________________________________________________";
                    System.out.println(msg);
                    break;
                }
                case EVENT: {
                    int index = -1;
                    for (int i = 0; i < arrayForm.length; i++) {
                        if (arrayForm[i].equals("/at")) {
                            index = i;
                            break;
                        }
                    }
                    StringBuilder description = new StringBuilder();
                    for (int i = 1; i < index; i++) {
                        if (i == 1) {
                            description.append(arrayForm[i]);
                        } else {
                            description.append(" ").append(arrayForm[i]);
                        }
                    }
                    StringBuilder date = new StringBuilder();
                    for (int j = index + 1; j < arrayForm.length; j++) {
                        if (j == index + 1) {
                            date.append(arrayForm[j]);
                        } else {
                            date.append(" ").append(arrayForm[j]);
                        }
                    }
                    Task t = new Event(description.toString(), date.toString());
                    tasks[numberOfTasks] = t;
                    numberOfTasks++;
                    String msg = "    ____________________________________________________________\n" +
                            "     Got it. I've added this task: \n" +
                            "       " + t.toString() + "\n" +
                            "     Now you have " + numberOfTasks + " tasks in the list.\n" +
                            "    ____________________________________________________________";
                    System.out.println(msg);
                    break;
                }
                case DONE: {
                    int taskIndex = Integer.parseInt(arrayForm[1]) - 1;
                    Task t = tasks[taskIndex];
                    t.markAsDone();
                    String doneTaskMessage =
                            "    ____________________________________________________________\n" +
                                    "     Nice! I've marked this task as done: \n" +
                                    "       " + t.toString() + "\n" +
                                    "    ____________________________________________________________";
                    System.out.println(doneTaskMessage);
                    break;
                }
                default:
                    System.out.println("Something must have gone wrong.");
            }
        }
    }
}
