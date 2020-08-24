package duke;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {

    private List<Task> todos;

    private Duke() {
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

    private void greet() {
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

    private void receiveInput() {
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
                        processBye(sc);
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
                    case SHOW:
                        processShow(command);
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

    private void processBye(Scanner sc) {
        sc.close();
        System.out.println("Time to say goodbye :( \n" +
                "Have a great day!");
        System.exit(0);
        return;
    }

    private String removeFirstWord(String command) throws DukeException {
        try {
            String[] cmd = command.split(" ", 2);
            return cmd[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private void processDelete(String command) throws DeleteException {
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

    private void deleteTask(int index) throws DeleteException {
        if(index < 0 || index > todos.size()) {
            throw new DeleteException("Please enter a valid task number.");
        } else {
            Task task = this.todos.get(index);
            this.todos.remove(task);
            updateData(this.todos);
            System.out.println("Noted. I've removed this task for you: \n"
                    + task.toString() + "\n"
                    + "Now you have " + this.todos.size() + " task(s) in the list.");
        }

    }

    private void processDone(String command) throws DoneException {
        try {
            String theRest = removeFirstWord(command);
            Integer taskNum = Integer.parseInt(theRest);
            markTaskAsDone(taskNum);
            updateData(this.todos);

        } catch (DukeException d) {
            throw new DoneException("Please enter a valid task number");
        }
    }

    private void processTodo(String command) throws TodoException {
        try {
            String theRest = removeFirstWord(command);
            Todo todo = new Todo(theRest);
            saveToList(todo);
        } catch (DukeException e) {
            throw new TodoException();
        }
    }

    private void processDeadline(String command) throws DeadlineException {
        try {
            String theRest = removeFirstWord(command);
            String[] taskAndDeadlineAndTime = theRest.split(" /by ", 2);
            Deadline deadline;

            try {
                String task = taskAndDeadlineAndTime[0];
                String dateAndTime = taskAndDeadlineAndTime[1];
                String[] dateTime = dateAndTime.split(" ", 2);

                String date = dateTime[0];

                try {
                    LocalDate localDate = LocalDate.parse(date);

                    if (dateTime.length < 2) {
                        deadline = new Deadline(task, localDate);
                    } else {
                        String time = dateTime[1];
                        LocalTime localTime = LocalTime.parse(time);
                        deadline = new Deadline(task, false, localDate, localTime);
                    }
                    saveToList(deadline);
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter the date in YYYY/MM/DD format and time in HH:MM format.");
                }

            } catch (IndexOutOfBoundsException e) {
                throw new DeadlineException("Please specify the task and deadline.");
            }

        } catch (DukeException d) {
            throw new DeadlineException("Please specify the task and deadline.");
        }
    }

    private void processEvent(String command) throws EventException {
        try {
            String theRest = removeFirstWord(command);
            String[] eventAndDateAndTime = theRest.split(" /at ", 2);
            Event event;

            try {
                String eventDesc = eventAndDateAndTime[0];
                String eventDateAndTime = eventAndDateAndTime[1];

                String[] dateTime = eventDateAndTime.split(" ", 2);

                String date = dateTime[0];

                try {
                    LocalDate localDate = LocalDate.parse(date);

                    if(dateTime.length < 2) {
                        event = new Event(eventDesc, localDate);
                    } else {

                        String time = dateTime[1];
                        String[] startEndTime = time.split("-");
                        if (startEndTime.length < 2) {
                            String startTime = startEndTime[0];
                            LocalTime localTime = LocalTime.parse(startTime);
                            event = new Event(eventDesc, false, localDate, localTime);
                        } else {
                            String startTime = startEndTime[0];
                            String endTime = startEndTime[1];
                            LocalTime localStartTime = LocalTime.parse(startTime);
                            LocalTime localEndTime = LocalTime.parse(endTime);
                            event = new Event(eventDesc, false, localDate, localStartTime, localEndTime);
                        }
                    }

                    saveToList(event);

                } catch (DateTimeParseException e) {
                    System.out.println("Please enter the date in YYYY/MM/DD format and time in HH:MM format..");
                }

            } catch (IndexOutOfBoundsException e) {
                throw new EventException("Please specify the event name and date.");
            }

        } catch (DukeException d) {
            throw new EventException("Please specify the event name and date.");
        }
    }

    private void processShow(String command) throws CalendarException {
        try {
            String date = removeFirstWord(command);
            showCalendar(date);
        } catch (DukeException e) {
            throw new CalendarException("Invalid input date format. Please try again.");
        }
    }

    private void listItems() {
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

    private void saveToList(Task todo) {
        this.todos.add(todo);
        updateData(this.todos);
        System.out.println("Okay~ I've added this task: \n" + todo.toString());
        System.out.println("Now you have " + this.todos.size() + " task(s) in the list.");
    }

    private void markTaskAsDone(int taskNum) throws DoneException {
        if (taskNum <= 0 || taskNum > todos.size() ) {
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
            } catch (DukeException d) {
                System.out.println(d.getMessage());
            }
        }
        return tasks;
    }

    private static Task parseData(String line) throws DukeException {

        try {
            String[] parsed = line.split("\\s\\|\\s");
            Task task;
            if(parsed.length < 2) {
                throw new StorageException(line + "is in invalid format.");
            } else {
                    String identifier = parsed[0]; //get the type of task
                    String doneIndicator = parsed[1];
                    String taskName = parsed[2];
                    if (identifier.equals("T")) {
                        if(doneIndicator.equals("1")) {
                            task = new Todo(taskName, true);
                        } else {
                            task = new Todo(taskName);
                        }

                    } else if (identifier.equals("E")) {
                        String dateAndTime = parsed[3];
                        String[] dateTime = dateAndTime.split("\\s", 2);
                        String date = dateTime[0];
                        LocalDate localDate = LocalDate.parse(date);

                        if(dateTime.length < 2) {
                            if (doneIndicator.equals("1")) {
                                task = new Event(taskName, true, localDate);
                            } else {
                                task = new Event(taskName, localDate);
                            }

                        } else {
                            String time = dateTime[1];
                            String[] startEndTime = time.split("-");
                            String startTime = startEndTime[0];
                            LocalTime localStartTime = LocalTime.parse(startTime);

                            if(startEndTime.length < 2) {
                                if (doneIndicator.equals("1")) {
                                    task = new Event(taskName, true, localDate, localStartTime);
                                } else {
                                    task = new Event(taskName, false, localDate, localStartTime);
                                }
                            } else {
                                String endTime = startEndTime[1];
                                LocalTime localEndTime = LocalTime.parse(endTime);
                                if (doneIndicator.equals("1")) {
                                    task = new Event(taskName, true, localDate, localStartTime, localEndTime);
                                } else {
                                    task = new Event(taskName, false, localDate, localStartTime, localEndTime);
                                }
                            }
                        }

                    } else if (identifier.equals("D")) {
                        String dateAndTime = parsed[3];
                        String[] dateTime = dateAndTime.split("\\s", 2);
                        String date = dateTime[0];
                        LocalDate localDate = LocalDate.parse(date);

                        if(dateTime.length < 2) {
                            if (doneIndicator.equals("1")) {
                                task = new Deadline(taskName, true, localDate);
                            } else {
                                task = new Deadline(taskName, localDate);
                            }
                        } else {
                            String time = dateTime[1];
                            LocalTime localTime = LocalTime.parse(time);
                            if (doneIndicator.equals("1")) {
                                task = new Deadline(taskName, true, localDate, localTime);
                            } else {
                                task = new Deadline(taskName, false, localDate, localTime);
                            }
                        }

                    } else {
                        throw new StorageException("Invalid format. Moving on to the next task.");
                    }

            }

//            String doneIndicator = parsed[1];
//            if (doneIndicator.equals("1")) {
//                task.markAsDone();
//            }
            return task;

        } catch(DateTimeParseException e) {
                throw new CalendarException("Please input the correct date and time format. YYYY-MM-DD for date and HH:MM for time.");
        }

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
                    Event event = (Event) task;
                    LocalDate date = event.getDate();
                    LocalTime startTime = event.getStartTime();
                    LocalTime endTime = event.getEndTime();

                    if(startTime == null) {
                        stored = String.format("%s | %d | %s | %s", type, status ? 1 : 0, taskName, date);
                    } else if(endTime == null) {
                        stored = String.format("%s | %d | %s | %s | %s", type, status ? 1 : 0, taskName, date, startTime);
                    } else {
                        stored = String.format("%s | %d | %s | %s | %s-%s", type, status ? 1 : 0, taskName, date, startTime, endTime);
                    }

                } else if (type.equals("D")) {
                    Deadline deadline = (Deadline) task;
                    LocalDate date = deadline.getDeadline();
                    LocalTime time = deadline.getTime();

                    if(time == null) {
                        stored = String.format("%s | %d | %s | %s", type, status ? 1 : 0, taskName, date);
                    } else {
                        stored = String.format("%s | %d | %s | %s | %s", type, status ? 1 : 0, taskName, date, time);

                    }

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

    private void showCalendar(String date) {
        LocalDate localDate = LocalDate.parse(date);
        Boolean hasSomething = false;
        System.out.println("Tasks on : " + localDate.getDayOfWeek() + ", "
                + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));

        for(Task task : this.todos) {
            String type = task.getType();
            if(type.equals("E")) {
                Event event = (Event) task;
                LocalDate eventDate = event.getDate();
                if(eventDate.equals(localDate)) {
                    hasSomething = true;
                    System.out.println(task);
                }

            } else if(type.equals("D")) {
                Deadline deadline = (Deadline) task;
                LocalDate deadlineDate = deadline.getDeadline();
                if(deadlineDate.equals(localDate)) {
                    hasSomething = true;
                    System.out.println(task);
                }

            } else {
                continue;
            }
        }

        if(!hasSomething) {
            System.out.println("You have nothing to do on this day!");
        }
    }
}
