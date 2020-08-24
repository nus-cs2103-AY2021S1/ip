import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Duke {

    public static void greeting() {

        String border = "    ============================================================";

        System.out.println(border);
        System.out.println("    Hello! I'm Duke" + "\n" + "    What can I do for you?");
        System.out.println(border);
    }

    public static void commandTask(String input, TaskList newList) throws DukeException {

        String[] splitString = input.split(" ",2);

        if (splitString[0].equals("todo")) {
            if(splitString.length == 1) {
                throw new DukeException("    ☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                String info = splitString[1];
                Todos newTodo = new Todos(info);
                newList.addTask(newTodo);
            }

        } else if (splitString[0].equals("deadline")) {
            if(splitString.length == 1) {
                throw new DukeException("    ☹ OOPS!!! The description of a deadline cannot be empty.");
            } else {
                String info = splitString[1];
                String[] information = info.split(" /by ");
                if (information.length == 1) {
                    throw new DukeException("    ☹ OOPS!!! Please specify the deadline time");
                } else {
                    String description = information[0];
                    String by = information[1];

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        LocalDateTime dateTime = LocalDateTime.parse(by, formatter);

                        Deadlines newDeadline = new Deadlines(description, dateTime);

                        newList.addTask(newDeadline);
                    } catch (DateTimeException ex) {
                        System.out.println("    Please specify the date and time in this format dd/MM/yyy HH:mm");
                    }
                }
            }

        } else if (splitString[0].equals("event")) {
            if(splitString.length == 1) {
                throw new DukeException("    ☹ OOPS!!! The description of an event cannot be empty.");
            } else {
                String info = splitString[1];
                String[] information = info.split(" /at ");

                if (information.length == 1) {
                    throw new DukeException("    ☹ OOPS!!! Please state the event time");
                } else {
                    String description = information[0];
                    String at = information[1];
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        LocalDateTime dateTime = LocalDateTime.parse(at, formatter);

                        Events newEvent = new Events(description, dateTime);

                        newList.addTask(newEvent);
                    } catch (DateTimeException ex) {
                        System.out.println("    Please specify the date and time in this format dd/MM/yyy HH:mm");
                    }
                }
            }

        } else {
            throw new DukeException("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void commandHandler(String input, TaskList newList) throws DukeException {
        String[] splitInput = input.split(" ");

        if (splitInput[0].equals("list")) {
            newList.listTasks();

        } else if (splitInput[0].equals("done")) {
            if (splitInput.length == 1) {
                throw new DukeException("    ☹ OOPS!!! Please specify which task is done");
            } else {
                String taskNumberString = splitInput[1];
                try {
                    int taskNumberInt = Integer.parseInt(taskNumberString) - 1;

                    if (taskNumberInt + 1 > newList.getLength()) {
                        throw new DukeException("    ☹ OOPS!!! Your task number is out of bounds");
                    } else {
                        Task t = newList.get(taskNumberInt);
                        t.markDone();
                        newList.updateDone();
                    }
                } catch (NumberFormatException e) {
                    throw new DukeException("    ☹ OOPS!!! Invalid task number.");

                }
            }
        } else if (splitInput[0].equals("delete")) {
            if (splitInput.length == 1) {
                throw new DukeException("    ☹ OOPS!!! Please specify which task you want to delete");
            } else {
                String taskNumberString = splitInput[1];
                try {
                    int taskNumberInt = Integer.parseInt(taskNumberString) - 1;

                    if (taskNumberInt + 1 > newList.getLength()) {
                        throw new DukeException("    ☹ OOPS!!! Your task number is out of bounds");
                    } else {
                        newList.removeTask(taskNumberInt);
                    }
                } catch (NumberFormatException e) {
                    throw new DukeException("    ☹ OOPS!!! Invalid task number.");
                }
            }
        } else {
            try {
                commandTask(input,newList);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }



    public static void main(String[] args) {
        String underscore = "    ____________________________________________________________";
        greeting();

        Scanner sc = new Scanner(System.in);
        String home = System.getProperty("user.home");

        try {
            File f = new File(home + "/Desktop/ip/data/duke.txt");

            TaskList newList = new TaskList(f);

            while(sc.hasNext()) {
                String input = sc.nextLine();
                System.out.println(underscore);
                try {
                    String[] splitInput = input.split(" ");
                    if (splitInput[0].equals("bye")) {
                        System.out.println("     Bye. Hope to see you again soon!");
                        return;
                    } else {
                        commandHandler(input, newList);
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(underscore);
            }
        } catch (FileNotFoundException e) {
            File newFile = new File(home + "/Desktop/ip/data/duke.txt");
            try {
                newFile.createNewFile();

                TaskList newList = new TaskList(newFile);

                while(sc.hasNext()) {
                    String input = sc.nextLine();
                    System.out.println(underscore);
                    try {
                        String[] splitInput = input.split(" ");
                        if (splitInput[0].equals("bye")) {
                            System.out.println("     Bye. Hope to see you again soon!");
                            return;
                        } else {
                            commandHandler(input, newList);
                        }
                    } catch (DukeException error) {
                        System.out.println(error.getMessage());
                    }
                    System.out.println(underscore);
                }
            } catch (IOException ex) {
                System.out.println("An error occurred, file could not be created.");
            }
        }
        sc.close();
    }
}

