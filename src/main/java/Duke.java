import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";

    private static final Scanner s = new Scanner(System.in);
    private static final String line = "     _____________________________________";
    private static ArrayList<Task> listOfTasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        while (s.hasNextLine()) {
            try {
                String userCommand = s.nextLine();
                if (userCommand.equals("bye")) {
                    exit();
                    s.close();
                    break;
                } else {
                    if (userCommand.equals("list")) {
                        getList();
                    } else {
                        String[] wordArray = userCommand.split(" ");
                        int noOfWords = wordArray.length;
                        if (noOfWords == 0) {
                            throw new InvalidInputException("I don't know what you need " +
                                    "me to do since the command is empty!");
                        }
                        if (wordArray[0].equals("done")) {
                            if (noOfWords == 1) {
                                throw new InvalidRequestException("Please tell me which task you want " +
                                        "to be marked as DONE.");
                            }
                            if (noOfWords > 2) {
                                throw new InvalidRequestException("I can only handle one request to " +
                                        "mark a task as DONE once! Please check your command.");
                            }
                            Integer index = Integer.parseInt(wordArray[1]);
                            if (listOfTasks.size() < index || index < 0) {
                                throw new InvalidRequestException("You have entered an invalid task " +
                                        "number! Please try again.");
                            }
                            Task theTask = listOfTasks.get(index - 1);
                            theTask.setTaskToBeDone();
                            System.out.println(line);
                            System.out.println("     Great! The task: [" + theTask.getName()
                                    + "] is marked as Done.\n" + "     " + theTask.toString());
                            System.out.println(line);
                        } else {
                            Task newTask;
                            if (wordArray[0].equals("todo")) {
                                if (noOfWords == 1) {
                                    throw new InvalidTodoException("Please tell me the name " +
                                            "of the todo task! Or else I cannot add it into list.");
                                }
                                String taskName = userCommand.split(" ", 2)[1];
                                newTask = new Todo(taskName);
                            } else if (wordArray[0].equals("deadline")) {
                                if (noOfWords == 1) {
                                    throw new InvalidDeadlineException("Please tell me the name " +
                                            "and the time due of the deadline task! Or else I " +
                                            "cannot add it into list.");
                                }
                                String body = userCommand.split(" ", 2)[1];
                                if (body.split(" /by").length < 2) {
                                        throw new InvalidDeadlineException("Please tell me both the name and" +
                                                " the time due of the deadline task in the correct form! " +
                                                "Don't forget to include the time by using /by.");
                                }
                                String taskName = body.split(" /by ")[0];
                                String time = body.split(" /by ")[1];
                                newTask = new Deadline(taskName, time);
                            } else if (wordArray[0].equals("event")) {
                                if (noOfWords == 1) {
                                    throw new InvalidEventException("Please tell me the name and time period" +
                                            " of the event task! Or else I cannot add it into the list.");
                                }
                                String body = userCommand.split(" ", 2)[1];
                                if (body.split(" /at").length < 2) {
                                        throw new InvalidEventException("Please tell me both the name and " +
                                                "the time period of the event task in the correct form! " +
                                                "Don't forget to include the time by using /at.");
                                }
                                String taskName = body.split(" /at ")[0];
                                String time = body.split(" /at ")[1];
                                newTask = new Event(taskName, time);
                            } else {
                                throw new InvalidInputException("I cannot understand your command! " +
                                        "Please ensure your command follows the rules.");
                            }
                            listOfTasks.add(newTask);
                            System.out.println(line);
                            System.out.println("     The task: [" + newTask.getName() + "] is added into the list!\n"
                                    + "        " + newTask);
                            int noOfTasks = listOfTasks.size();
                            if (noOfTasks == 1) {
                                System.out.println("     There is 1 task in total in your list.");
                            } else {
                                System.out.println("     There are " + listOfTasks.size() + " tasks in total in your list.");
                            }
                            System.out.println(line);
                            ;
                        }
                    }
                }
            } catch (InvalidInputException e) {
                System.out.println(line);
                System.out.println("     " +e.getMessage());
                System.out.println(line);

            }
        }
    }
        private static void greet() {
            System.out.println(line);
            System.out.println("     Hi, I am\n" + logo);
            System.out.println("     Is there anything I could help with?");
            System.out.println(line);
        }

        private static void exit() {
            System.out.println(line);
            System.out.println("     Bye! I look forward to meeting you next time!");
            System.out.println(line);
        }

        private static void getList() {
            int noOfTasks = listOfTasks.size();
            if (noOfTasks == 0) {
                System.out.println(line);
                System.out.println("     There is no task in the list yet!");
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("     Here are the tasks in the list:");
                for (int i = 0; i < noOfTasks; i++) {
                    Task task = listOfTasks.get(i);
                    System.out.println("     " + (i + 1) + "." + task);
                }
                System.out.println(line);
            }
        }

}
