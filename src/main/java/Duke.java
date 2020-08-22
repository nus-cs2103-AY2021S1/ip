import java.time.LocalDateTime;
import java.util.*;

public class Duke {
    private static ArrayList<Task> userList = new ArrayList<>();

    private static void processEventOrDeadline(String input) throws EventException, DeadlineException {
        String[] temp = input.split("/");
        String[] commandTemp = temp[0].split(" ");
        String command = commandTemp[0];
        if (temp.length != 2) {
            if (command.equals("deadline")) {
                if (temp.length > 2) {
                    // if user ended up using a dd/mm/yyyy HHHH format
                    throw new DeadlineException("Please input date time format in DD-MM-YYYY HHHH format!\n" +
                            "Eg: 2-12-2019 1800 OR 02-12-2019 1800");
                } else {
                    throw new DeadlineException("Both time description and description of a deadline must be filled!");
                }
            } else if (command.equals("event")) {
                if (temp.length > 2) {
                    // if user ended up using a dd/mm/yyyy HHHH format
                    throw new EventException("Please input date time format in DD-MM-YYYY HHHH format!\n" +
                            "Eg: 2-12-2019 1800 OR 02-12-2019 1800");
                } else {
                    throw new EventException("Both time description and description of a event must be filled!");
                }
            }
        }

        String description = "";
        for (int i = 1; i < commandTemp.length; i++) {
            description += commandTemp[i] + " ";
        }


        String[] timeTemp = temp[1].split(" ");

        if (timeTemp.length != 3) {
            // timeTemp[0] = timeCommand which is a by or at
            // timeTemp[1] = DDMMYYYY
            // timeTemp[2] = HHHH
            if (command.equals("deadline")) {
                throw new DeadlineException("Please input date time format in DD-MM-YYYY HHHH format!\n" +
                        "Eg: 2-12-2019 1800 OR 02-12-2019 1800");
            } else if (command.equals("event")) {
                throw new EventException("Please input date time format in DD-MM-YYYY HHHH format!\n" +
                        "Eg: 2-12-2019 1800 OR 02-12-2019 1800");
            }
        }

        String timeCommand = timeTemp[0];
        String DDMMYYYY = timeTemp[1];
        String[] DDMMYYYtemp = DDMMYYYY.split("-");
        String HHHH = timeTemp[2];

        if (DDMMYYYtemp.length != 3 || HHHH.length() != 4) {
            if (command.equals("deadline")) {
                throw new DeadlineException("Please input date time format in DD-MM-YYYY HHHH format!\n" +
                            "Eg: 2-12-2019 1800 OR 02-12-2019 1800");
            } else if (command.equals("event")) {
                throw new EventException("Please input date time format in DD-MM-YYYY HHHH format!\n" +
                        "Eg: 2-12-2019 1800 OR 02-12-2019 1800");
            }
        }

        int day = Integer.parseInt(DDMMYYYtemp[0]);
        int month = Integer.parseInt(DDMMYYYtemp[1]);
        int year = Integer.parseInt(DDMMYYYtemp[2]);
        int hour = Integer.parseInt(HHHH.substring(0, 2));
        int min = Integer.parseInt(HHHH.substring(2, 4));

        System.out.println("Got it. I've added this task:");

        if (command.equals("deadline")) {
            Task task = new Deadline(description, LocalDateTime.of(year, month, day, hour, min));
            userList.add(task);
            System.out.println(task);
            System.out.println("Now you have " + userList.size() + " tasks in the list.");
        } else if (command.equals("event")) {
            Task task = new Event(description, LocalDateTime.of(year, month, day, hour, min));
            userList.add(task);
            System.out.println(task);
            System.out.println("Now you have " + userList.size() + " tasks in the list.");
        }
    }

    private static void processToDo(String input) throws ToDoException {
        String[] temp = input.split(" ");

        String command = temp[0];
        String description = "";

        for(int i = 1; i < temp.length; i++) {
            description += temp[i] + " ";
        }

        if (description.equals("")) {
            throw new ToDoException("The description of a todo cannot be empty!");
        }

        Task task = new ToDo(description);
        userList.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + userList.size() + " tasks in the list.");
    }

    private static void processDelete(String input) throws DeletionException {
        String[] temp = input.split(" ");
        if (temp.length == 1) {
            throw new DeletionException("Please input index after delete! Example of input would be 'delete 1' which deletes 1st item from list");
        }
        if (temp.length > 2) {
            throw new DeletionException("Too many arguments! Example of input would be 'delete 1' which deletes 1st item from list");
        }

        String command = temp[0];
        int index = Integer.parseInt(temp[1]) - 1;

        if (index >= userList.size() || index < 0) {
            throw new DeletionException("Item does not exist in list!");
        }

        Task task = userList.get(index);
        userList.remove(index);

        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + userList.size() + " tasks in the list.");
    }

    private static void processDone(String input) throws DoneException {
        String[] temp = input.split(" ");
        if (temp.length == 1) {
            throw new DoneException("Please input number after done! Example of input would be 'done 2' checks item number 2 from list");
        }
        if (temp.length > 2) {
            throw new DoneException("Too many arguements! Example of input would be 'done 2' which checks item number 2 from list");
        }
        int listNumber = Integer.parseInt(temp[1]);
        if (listNumber > userList.size()) {
            throw new DoneException("Item number " + listNumber + " does not exist in list!");
        }
        userList.get(listNumber - 1).completedTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(userList.get(listNumber - 1).toString());
    }

    private static void processList() {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : userList) {
            System.out.println(index + ". " + task.toString());
            index++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                // when user inputs bye

                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else if (input.equals("list")) {
                // when user wants to print list of task

                processList();

            } else if (input.startsWith("done")) {
                // when user completes task

                try {
                    processDone(input);
                } catch (DoneException e) {
                    System.out.println(e.getMessage());
                }

            } else if ((input.startsWith("deadline") || input.startsWith("event")) || input.startsWith("todo")) {
                // user trying to input to List

                if (input.startsWith("deadline") || input.startsWith("event")) {
                    // input starts with deadline or event

                    try {
                        processEventOrDeadline(input);
                    } catch (EventException | DeadlineException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (input.startsWith("todo")){
                    // input starts with todo

                    try {
                        processToDo(input);
                    } catch (ToDoException e) {
                        System.out.println(e.getMessage());
                    }
                }

            } else if (input.startsWith("delete")) {
                // if input starts with delete

                try {
                    processDelete(input);
                } catch (DeletionException e) {
                    System.out.println(e.getMessage());
                }

            } else {
                // invalid input

                try {
                    throw new InvalidInputException("I'm sorry, but I don't know what that means! :-(");
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }

            }
        }
    }
}