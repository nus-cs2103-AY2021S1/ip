package duke;

import java.io.IOException;
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
        greet();
        receiveInput();
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

        } catch (DukeException d) {
            System.out.println(d.getMessage());
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
                        deadline = new Deadline(task, localDate, localTime);
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
                            event = new Event(eventDesc, localDate, localTime);
                        } else {
                            String startTime = startEndTime[0];
                            String endTime = startEndTime[1];
                            LocalTime localStartTime = LocalTime.parse(startTime);
                            LocalTime localEndTime = LocalTime.parse(endTime);
                            event = new Event(eventDesc, localDate, localStartTime, localEndTime);
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
