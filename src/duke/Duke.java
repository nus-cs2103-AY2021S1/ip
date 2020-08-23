package duke;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;



public class Duke {

    private List<Task> todos;

    public Duke() {
        todos = new ArrayList<Task>();
        this.todos = todos;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    public void start() {
        try {
            greet();
            this.todos = Duke.readFile();
            receiveInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void greet() {
//        String donLogo = "  ______      _______     ____    __    \n"
//                + " |   _  \\    /   _   \\   |    \\  |  |\n"
//                + " |  | |  |  |   | |   |  |  |\\ \\ |  |\n"
//                + " |  |_|  |  |   |_|   |  |  | \\ \\|  |\n "
//                + "|_____ /    \\______ /   |__|  \\____|\n";
        String donLogo = "   ___     ___    _  _     ___     ___ \n"
                + "  |   \\   / _ \\  | \\| |   / __|   / _ \\  \n"
                + "  | |) | | (_) | | .` |  | (_ |  | (_) | \n"
                + "  |___/   \\___/  |_|\\_|   \\___|   \\___/  \n"
                + "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n"
                + "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' \n";
        String msg = "Hola! I'm Dongo :) \n" +
                "How can I help you?";
        System.out.println(donLogo + "\n" + msg);
    }

    public void receiveInput() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();

            try {
            String[] cmd = command.split(" ", 2);
            String firstWord = cmd[0];
            Commands defCommand = null;

                try {
                    defCommand = Commands.valueOf(firstWord.toUpperCase()); //input to upper case
                } catch (IllegalArgumentException e) {
                    throw new WrongInputException();
                }

                switch (defCommand) {
                    case LIST:
                        listItems();
                        break;
                    case BYE:
                        sc.close();
                        System.out.println("Time to say goodbye :( \n" +
                                "Have a great day!");
                        System.exit(0);
                        return;
                    case DONE:
                        processDone(command);
                        break;
                    case TODO:
                        processTodo(command);
                        break;
                    case DEADLINE:
                        processDeadline(command);
                        break;
                    case EVENT:
                        processEvent(command);
                        break;
                    case DELETE:
                        processDelete(command);
                        break;
                    default:
                        WrongInputException wrong = new WrongInputException();
                        System.out.println(wrong.getMessage());
                        break;
                }
            } catch (DukeException exc) {
                System.out.println(exc.getMessage());
            }
        }

//            if (firstWord.equals("list")) {
//                listItems();
//
//            } else if (firstWord.equals("bye")) {
//                sc.close();
//                System.out.println("Time to say goodbye :( \n" +
//                                    "Have a great day!");
//                System.exit(0);
//                return;
//
//            } else {
//
//                if (firstWord.equals("done")) {
//                    processDone(command);
//                } else if (firstWord.equals("todo")) {
//                    processTodo(command);
//                } else if (firstWord.equals("deadline")) {
//                    processDeadline(command);
//                } else if (firstWord.equals("event")) {
//                    processEvent(command);
//                } else if (firstWord.equals("delete")) {
//                    processDelete(command);
//                } else {
//                    WrongInputException wrong = new WrongInputException();
//                    System.out.println(wrong.getMessage());
//                }
//            }
//        }
    }

    public String removeFirstWord(String command) throws DukeException {
        try {
            String[] cmd = command.split(" ", 2);
            return cmd[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void processDelete(String command) throws DeleteException {
        try {
            String theRest = removeFirstWord(command);
            Integer taskNum = Integer.parseInt(theRest);
            int index = taskNum - 1;
            System.out.println(index);
            deleteTask(index);

        } catch (DukeException d) {
           throw new DeleteException("Please enter a number. I cannot delete nothing :(");
        }
    }
    public void deleteTask(int taskNum) throws DeleteException {
        if(taskNum <= 0 || taskNum >= todos.size()) {
            throw new DeleteException("Please enter a valid task number.");
        } else {
            Task task = this.todos.get(taskNum);
            this.todos.remove(task);
            updateData(this.todos);
            System.out.println("Noted. I've removed this task for you: \n"
                    + task.toString() + "\n"
                    + "Now you have " + this.todos.size() + " task(s) in the list.");
        }

    }

    public void processDone(String command) throws DoneException {
        try {
            String theRest = removeFirstWord(command);
            Integer taskNum = Integer.parseInt(theRest);
            markTaskAsDone(taskNum);
            updateData(this.todos);

        } catch (DukeException d) {
            throw new DoneException("Please specify what you have already done.");
        }
    }

    public void processTodo(String command) throws TodoException {
        try {
            String theRest = removeFirstWord(command);
            Todo todo = new Todo(theRest);
            saveToList(todo);
        } catch (DukeException e) {
            throw new TodoException();
        }
    }

    public void processDeadline(String command) throws DeadlineException {
        try {
            String theRest = removeFirstWord(command);
            String[] taskAndDeadline = theRest.split(" /by", 2);

            try {
                String task = taskAndDeadline[0];
                String date = taskAndDeadline[1];
                Deadline deadline = new Deadline(task, date);
                saveToList(deadline);
            } catch (IndexOutOfBoundsException e) {
                throw new DeadlineException("Please specify the task and deadline.");
            }

        } catch (DukeException d) {
            throw new DeadlineException("Please specify the task and deadline.");
        }
    }

    public void processEvent(String command) throws EventException {
        try {
            String theRest = removeFirstWord(command);
            String[] eventAndDate = theRest.split(" /at", 2);
            try {
                String eventDesc = eventAndDate[0];
                String eventDate = eventAndDate[1];
                Event event = new Event(eventDesc, eventDate);
                saveToList(event);
            } catch (IndexOutOfBoundsException e) {
                throw new EventException("Please specify the event name and date.");
            }

        } catch (DukeException d) {
            throw new EventException("Please specify the event name and date.");
        }
    }

    public void listItems() {
        if(this.todos.size() == 0) {
            System.out.println("You don't have any task in your list.");
        } else {
            StringBuilder todoList = new StringBuilder("Here are the task(s) in your list: \n");
            int num = 1;
            for (Task item : this.todos) {
                todoList.append(num + ". " + item.toString() + "\n");
                num++;
            }
            System.out.println(todoList);
        }
    }

    public void saveToList(Task todo) {
        this.todos.add(todo);
        updateData(this.todos);
        System.out.println("Okay~ I've added this task: \n" + todo.toString());
        System.out.println("Now you have " + this.todos.size() + " task(s) in the list.");
    }

    public void markTaskAsDone(int taskNum) throws DoneException {
        if (taskNum <= 0 || taskNum >= todos.size() ) {
            throw new DoneException("Please enter a valid task number.");
        } else {
            int index = taskNum - 1;
            Task oldTask = this.todos.get(index);
            Task newTask = oldTask.markAsDone();
            this.todos.remove(oldTask);
            this.todos.add(index, newTask);
            System.out.println("YAYY! I've marked this task as done : \n"
                    + newTask.toString());
        }
    }

    private static List<Task> readFile() throws IOException {
        Path folder = Path.of("data");
        Path file = folder.resolve("Duke.txt");

        //Create a new directory by creating all parent directories first
        Files.createDirectories(folder);

        //if user is new and file does not exist, create the file
        if(!Files.exists(file)) {
            Files.createFile(file);
        }

        //read from file
        BufferedReader reader = Files.newBufferedReader(file);
        List<Task> tasks = new ArrayList<>(); //this does not update this.todos
        String currentLine;
        while((currentLine = reader.readLine()) != null) {
            try {
                System.out.println(currentLine);
                Task task = parseData(currentLine);
                tasks.add(task);
            } catch (StorageException e) {
                System.out.println(e.getMessage());
            }
        }
        return tasks;
    }

    private static Task parseData(String line) throws StorageException {
        String[] parsed = line.split("\\s\\|\\s");
        Task task;

        if(parsed.length < 2) {
            throw new StorageException(line + "is in invalid format.");
        } else {
            String identifier = parsed[0]; //get the type of task
            String taskName = parsed[2];
            if(identifier.equals("T")) {
                task = new Todo(taskName);
            } else if (identifier.equals("E")) {
                String date = parsed[3];
                task = new Event(taskName, date);
            } else if (identifier.equals("D")) {
                String date = parsed[3];
                task = new Deadline(taskName, date);
            } else {
                throw new StorageException("Invalid format. Moving on to the next task.");
            }
        }

        String doneIndicator = parsed[1];
        if (doneIndicator.equals("1")) {
            task.markAsDone();
        }

        return task;
    }

    private static void updateData(List<Task> tasks) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Path.of("data/Duke.txt"));
            for(Task task : tasks) {
                String type = task.getType();
                Boolean status = task.getStatus();
                String taskName = task.getDescription();
                String stored = "";
                if(type.equals("T")) {
                    stored = String.format("%s | %d | %s", type, status ? 1 : 0, taskName);
                } else if (type.equals("E")) {
                    String date = ((Event) task).getDate();
                    stored = String.format("%s | %d | %s | %s", type, status ? 1 : 0, taskName, date);
                } else if (type.equals("D")) {
                    String date = ((Deadline) task).getDate();
                    stored = String.format("%s | %d | %s | %s", type, status ? 1 : 0, taskName, date);
                }
                writer.write(stored);
                writer.newLine();
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
