import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;


public class Duke {
    //private static final String FILEPATH = System.getProperty("user.dir");
    static String bot = "Dave says:\n";
    static String line = "_______________________________________________________________";

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Save save = null;

        //loading
        try {
            save = new Save("data/duke.txt");
            tasks = save.read();
        } catch (IOException ex){
            System.err.println(ex.getMessage());
        }


        //Initial greetings
        System.out.println(line);
        System.out.println(bot + "Greetings from me, Dave!\n" + "How can I help you? ^_^");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            String[] input = userInput.split(" ");
            int len = userInput.length();
            Command command;
            try {
                //parse input into string -> enum
                try {
                    String com = input[0].toString();
                    command = Command.valueOf(com.toUpperCase());
                } catch (NullPointerException | IllegalArgumentException ex) {
                    throw new DukeException("You have keyed in an invalid command!\n(Valid commands: todo, deadline, event, list, delete, bye, done)");
                }
                switch (command) {
                    case BYE:
                        System.out.println(line);
                        System.out.println(bot + "Goodbye! Hope to see you again soon! ^_^");
                        System.out.println(line);
                        System.exit(0);
                        break;
                    case LIST:
                        if (userInput.equals("list")) {
                            showTaskList(tasks);
                        } else {
                            throw new DukeException("You have keyed in an invalid format for command 'list'!");
                        }
                        break;
                    case DONE:
                        int pos = Integer.parseInt(userInput.substring(5, len));
                        taskIsDone(tasks, pos);
                        break;
                    case TODO:
                        if (!userInput.substring(4).isBlank()) { //if got space behind, it will add also
                            ToDo todo = new ToDo(userInput.substring(5));
                            tasks.add(todo); //adds into tasks list
                            System.out.println(line);
                            System.out.print(bot);
                            System.out.println("Got it! I've added this task:");
                            //System.out.println(addedText + userInput);
                            System.out.println(todo);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            System.out.println(line);
                        } else {
                            throw new DukeException("The description of a todo cannot be empty!");
                        }
                        break;
                    case DEADLINE:
                        if (!userInput.substring(8).isBlank()) {
                            try {
//                                int indexOfSlash = userInput.indexOf('/');
//                                String description = userInput.substring(9, indexOfSlash - 1);
//                                String date = userInput.substring(indexOfSlash + 4);
                                String de = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                                String description = de.split(" /by ")[0];
                                String date = de.split(" /by ")[1];
                                Deadline deadline = new Deadline(description, date);
                                tasks.add(deadline);
                                System.out.println(line);
                                System.out.print(bot);
                                System.out.println("Got it! I've added this task:");
                                System.out.println(deadline);
                                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                                System.out.println(line);
                            } catch (PatternSyntaxException | ArrayIndexOutOfBoundsException ex) {
                                throw new DukeException("You have keyed in an invalid command for 'deadline'!");
                            }
                        } else {
                            throw new DukeException("The description of a deadline cannot be empty!");
                        }
                        break;
                    case EVENT:
                        if (!userInput.substring(5).isBlank()) {
                            try {
//                                int indexOfSlash = userInput.indexOf('/');
//                                String description = userInput.substring(6, indexOfSlash - 1);
//                                String dateAndTime = userInput.substring(indexOfSlash + 4);
                                String ev = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                                String description = ev.split(" /at ")[0];
                                String dateAndTime = ev.split(" /at ")[1];
                                Event event = new Event(description, dateAndTime);
                                tasks.add(event);
                                System.out.println(line);
                                System.out.print(bot);
                                System.out.println("Got it! I've added this task:");
                                System.out.println(event);
                                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                                System.out.println(line);
                            } catch (PatternSyntaxException | ArrayIndexOutOfBoundsException ex ) {
                                throw new DukeException("You have keyed in an invalid command for 'event'!");
                            }
                        } else {
                            throw new DukeException("The description of an event cannot be empty!");
                        }
                        break;
                    case DELETE:
                        if (!userInput.substring(6).isBlank()) {
                            try {
                                String toDelete = userInput.substring(7);
                                int index = Integer.parseInt(toDelete);
                                if (index <= tasks.size() && index > 0) {
                                    System.out.println(line);
                                    System.out.print(bot);
                                    System.out.println("Noted! I've deleted this task:");
                                    System.out.println(tasks.get(index - 1));
                                    tasks.remove(index - 1);
                                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                                    System.out.println(line);
                                } else {
                                    throw new IndexOutOfBoundsException();
                                }
                            } catch (NumberFormatException | IndexOutOfBoundsException ex){
                                throw new DukeException("The number keyed in is invalid!");
                            }
                        } else {
                            throw new DukeException("The description of a delete cannot be empty!");
                        }
                        break;
                    default:
                        throw new DukeException("You have keyed in an invalid command!\n(Valid commands: todo, deadline, event, list, delete, bye, done)");
                }

                save.writeToFile(tasks);

            } catch (DukeException | IOException ex) {
                System.out.println(line);
                System.out.print(bot);
                System.out.println(ex.getMessage());
                System.out.println(line);
            }
        }

        scanner.close();
    }

    static void showTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(line);
            System.out.print(bot);
            System.out.println("There are no tasks in your list yet! >_<");
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.print(bot);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                //System.out.println(i + 1 + "." + " " + "[" + tasks.get(i).getStatusIcon() + "]" + " " + tasks.get(i).getTask());
                System.out.println(i + 1 + "." + " " + tasks.get(i));
            }
            System.out.println(line);
        }
    }

    static void taskIsDone(ArrayList<Task> tasks, int pos) throws DukeException {
        if (pos <= tasks.size() && pos > 0) {
            tasks.get(pos - 1).markAsDone(); //marking task as done
            System.out.println(line);
            System.out.print(bot);
            System.out.println("Great work! I've marked this task as done:");
            //System.out.println("[" + tasks.get(pos - 1).getStatusIcon() + "]" + " " + tasks.get(pos - 1).getTask());
            System.out.println(tasks.get(pos - 1));
            System.out.println("Keep the ticks going! ^_^");
            System.out.println(line);
        } else {
            throw new DukeException("You have keyed in an invalid number!");
        }
    }
}




