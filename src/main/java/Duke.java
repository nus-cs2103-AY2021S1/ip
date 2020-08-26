import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String DATEREGEX = "^(19|20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
    
    //Load or create file
    private static ArrayList<Task> loadFile() {
        ArrayList<Task> lst = new ArrayList<>();

        try {
            File file = new File("./TodoList.txt");
            if (!file.createNewFile()) {
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {
                    String line = fileReader.nextLine();
                    String[] command = line.split(" \\| ");
                    switch (command[0]) {
                        case "T":
                            Todo todo = new Todo(command[2]);
                            if (command[1].equals("1")) {
                                todo.setDone();
                            }
                            lst.add(todo);
                            break;
                        case "D":
                            Deadline deadline = new Deadline(command[2], command[3]);
                            if (command[1].equals("1")) {
                                deadline.setDone();
                            }
                            lst.add(deadline);
                            break;
                        case "E":
                            Event event = new Event(command[2], command[3]);
                            if (command[1].equals("1")) {
                                event.setDone();
                            }
                            lst.add(event);
                            break;
                    }
                }
                fileReader.close();
                System.out.println("\tHistory found and loaded!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Other error occurred. " + e.getMessage());
        }

        return lst;
    }

    //Write to file
    private static boolean writeChangedList(ArrayList<Task> lst) {
        try {
            FileWriter fileWriter = new FileWriter("./TodoList.txt");
            String listToString = "";
            for (Task t : lst) {
                if (t instanceof Todo) {
                    listToString += "T | " + (t.getStatus() ? 1 : 0) + " | " + t.getDescription() + "\n";
                } else if (t instanceof Deadline) {
                    listToString += "D | " + (t.getStatus() ? 1 : 0) + " | " + t.getDescription() + " | " +
                            ((Deadline) t).getBy() + "\n";
                } else {
                    listToString += "E | " + (t.getStatus() ? 1 : 0) + " | " + t.getDescription() + " | " +
                            ((Event) t).getAt() + "\n";
                }
            }
            fileWriter.write(listToString);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("\tError occurred. " + e.getMessage());
            return false;
        }

    }

    //Selective print list
    private static void printList(ArrayList<Task> lst, String option, LocalDate... date) {
        int i = 1;
        for (Task t : lst) {
            if (option.equals("Undone") && t.getStatus()) {
                continue;
            } else if (option.equals("Date") && !t.getDate().equals(date[0])){
                continue;
            }

            System.out.println("\t" + i + ". " + t);
            i++;
        }
    }

    public static String[] translate(String text) throws DukeException {
        String commandList = "bye|list|(done|delete)[\\s]+[\\d]+|todo.*|deadline.*|event.*|on.*";
        if (text.matches(commandList)) {
            String[] commandParaPair = text.split(" ", 2);
            return commandParaPair;
        } else {
            throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static int[] execute(String[] commandParaPair, ArrayList<Task> list, boolean running, int activeTasks)
            throws DukeException {
        switch (commandParaPair[0]) {
            case "bye":
                System.out.println("\tList saved!\n\tIt was my pleasure assisting you.\n\tSee you next Time!");
                running = false;
                break;
            case "list":
                System.out.println("\tHere are the tasks in your list:");
                printList(list, "All");
                break;
            case "on":
                System.out.println("\tYou have these tasks on this date:");
                printList(list, "Date", LocalDate.parse(commandParaPair[1]));
                break;
            case "done":
            case "delete":
                int taskNo = Integer.parseInt(commandParaPair[1].replaceAll("[\\s]+", ""));
                if (taskNo > list.size()) { //If requested index is out of bound.
                    throw new DukeException("\tYou don't have that many tasks!!!");
                } else { //If index can be found, set the task to be done and move it to the last.
                    Task task = list.get(taskNo - 1);
                    list.remove(task);
                    if (commandParaPair[0].equals("done")) {
                        task.setDone();
                        list.add(task);
                        activeTasks--;
                        System.out.println("\tYou have finished this task!\n\t" + task +
                                "\n\tLet's move on to the next one!");
                        printList(list, "Undone");
                    } else {
                        System.out.println("\tYou have deleted this task!\n\t" + task +
                                "\n\tHere are other tasks on your list:");
                        printList(list, "All");
                    }
                    writeChangedList(list);
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                if (commandParaPair.length < 2) {
                    throw new DukeException("\t☹ OOPS!!! The description of a task cannot be empty.");
                } else {
                    Task newTask;
                    if (commandParaPair[0].equals("todo")) {
                        newTask = new Todo(commandParaPair[1]);
                    } else {
                        String para = commandParaPair[1];
                        String[] contentTimePair = para.split(" /by | /at ", 2);
                        if (contentTimePair.length < 2) {
                            throw new DukeException("\t☹ OOPS!!! The time of a deadline / event cannot be empty.");
                        } else {
                            if (contentTimePair[1].matches(DATEREGEX)) {
                                LocalDate time = LocalDate.parse(contentTimePair[1]);
                                if (commandParaPair[0].equals("deadline")) {
                                    newTask = new Deadline(contentTimePair[0], time);
                                } else {
                                    newTask = new Event(contentTimePair[0], time);
                                }
                            } else {
                                throw new DukeException("\t☹ OOPS!!! The time format cannot be identified!");
                            }
                        }
                    }

                    list.add(activeTasks, newTask);
                    writeChangedList(list);
                    activeTasks++;
                    System.out.println("\tadded: " + newTask);
                    System.out.println("\tYou have " + list.size() + " tasks, " + activeTasks + " undone!");
                    printList(list, "All");
                }
                break;
        }
        int[] pair = new int[]{running ? 1 : 0, activeTasks};
        return pair;
    }

    public static void main(String[] args) {
        //Load or create file
        ArrayList<Task> todoList = loadFile();

        //Duke start and greetings.
        System.out.println("\tHello!\n\tI am Baymax, your personal idle time companion." +
                "\n\tHow may I help you?");

        //Add List
        Scanner scanner = new Scanner(System.in);

        //Keep track of number of tasks undone
        int noOfTasks = todoList.size();

        //Check if conversation has ended
        boolean running = true;

        //Continuously read input from user
        while (running) {
            String text = scanner.nextLine();
            try {
                String[] CommandParaPair = translate(text);
                int[] pair = execute(CommandParaPair, todoList, running, noOfTasks);
                running = pair[0] == 1;
                noOfTasks = pair[1];
            } catch (DukeException error) {
                System.out.println(error.getMessage());
            }
        }
    }
}
