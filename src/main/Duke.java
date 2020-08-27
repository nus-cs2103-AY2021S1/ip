import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected boolean active = false;
    protected ArrayList<Task> taskList;
    protected File dataDirectory, dataFile;
    protected final String DATA_DIRECTORY_PATH = "data";
    protected final String DATA_FILE_PATH = "./data/dukeData.txt";

    public Duke() {
        taskList = new ArrayList<>();
    }

    public void initialize() {
        active = true;
        Scanner sc = new Scanner(System.in);

        printLogo();

        System.out.println("Initializing...");
        try {
            dataDirectory = new File(DATA_DIRECTORY_PATH);
            dataFile = new File(DATA_FILE_PATH);
            if (!dataDirectory.exists()) {
                if (dataDirectory.mkdir()) {
                    System.out.println("Data Directory is created...");
                } else {
                    System.out.println("Oops...the data directory cannot be created :(");
                }
            } else {
                System.out.println("Data directory located...");
            }
            if (!dataFile.exists()) {

                if (dataFile.createNewFile()) {
                    System.out.println("Data file is created...");
                } else {
                    System.out.println("Oops...the data file cannot be created :(");
                }
            } else {
                System.out.println("Data file located...");
                loadExistingData();
                System.out.println("Initialization complete!");
            }
            welcome();
        } catch (IOException e) {
            active = false;
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (DukeException e) {
            active = false;
            System.out.print("Initialization failed. ");
            System.out.println(e.getMessage());
        }


        while (active) {
            String input = sc.nextLine();
            handleInput(input);
        }
        sc.close();
    }

    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public void welcome() {
        String welcomeMessage = "____________________________________________________\n" +
                "Hello, I'm Duke!\n\n";
        if (taskList.size() < 1) {
            welcomeMessage += "You currently have no tasks. ";
        } else {
            welcomeMessage +=  "Here are your existing tasks:\n";
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String taskString = String.valueOf(i + 1) + "." + task.toString() + "\n";
                welcomeMessage += taskString;
            }
            welcomeMessage += "\n";
        }
        welcomeMessage += "What can I do for you?\n" +
                "____________________________________________________\n";
        System.out.println(welcomeMessage);
    }

    public void loadExistingData() throws IOException, DukeException {
        BufferedReader reader = new BufferedReader(new FileReader(dataFile));

        String line;
        while ((line = reader.readLine()) != null) {
                String[] taskInfo = line.split(" \\| ");
                if (taskInfo.length < 3) {
                    throw new DukeException("Corrupted file: missing field.");
                } else if (!(taskInfo[1].equals("0") || taskInfo[1].equals("1"))) {
                    throw new DukeException("Corrupted file: invalid done field.");
                } else {
                    String type = taskInfo[0];
                    boolean isDone = taskInfo[1].equals("1");
                    String desc = taskInfo[2];

                    Task task;
                    switch (type) {
                        case ("T"):
                            task = new ToDo(desc);
                            break;
                        case ("D"):
                            String by = taskInfo[3];
                            task = new Deadline(desc, by);
                            break;
                        case ("E"):
                            String at = taskInfo[3];
                            task = new Event(desc, at);
                            break;
                        default:
                            throw new DukeException("Corrupted file: invalid task type.");
                    }
                    if (isDone) {
                        task.makeDone();
                    }
                    taskList.add(task);
                }
        }
    }

    public void exit() {
        active = false;
        String goodbyeMessage = "____________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________\n";
        System.out.println(goodbyeMessage);
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
        String addTaskMessage = "____________________________________________________\n" +
                "Got it. I've added this task:\n" +
                newTask.toString() + "\n" +
                "Now you have " + taskList.size() + (taskList.size() > 1 ? " tasks " : " task ") + "in the list.\n" +
                "____________________________________________________\n";
        System.out.println(addTaskMessage);
    }

    public void handleToDo(String info) {
        Task toDo = new ToDo(info);
        addTask(toDo);
    }

    public void handleDeadline(String info) throws DukeException {
        String[] descriptionAndBy = info.split(" /by ", 2);
        if (descriptionAndBy.length < 2) {
            throw new DukeException("Error! To add a deadline, please enter 'deadline [description] /by [date/time]'.");
        } else {
            Task deadline = new Deadline(descriptionAndBy[0], descriptionAndBy[1]);
            addTask(deadline);
        }
    }

    public void handleEvent(String info) throws DukeException{
        String[] descriptionAndAt = info.split(" /at ", 2);
        if (descriptionAndAt.length < 2) {
            throw new DukeException("Error! To add an event, please enter 'event [description] /at [date/time]'.");
        } else {
            Task event = new Event(descriptionAndAt[0], descriptionAndAt[1]);
            addTask(event);
        }
    }

    public void deleteTask(int index) {
        Task task = taskList.remove(index);
        String markDoneMessage = "____________________________________________________\n" +
                "Noted. I've removed this task:\n" +
                task.toString() + "\n" +
                "____________________________________________________\n";
        System.out.println(markDoneMessage);
    }

    public void handleDelete(String info) throws DukeException {
        try {
            int deleteIndex = Integer.valueOf(info) - 1;
            deleteTask(deleteIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error! This task does not exist, please enter a valid task number.");
        }
    }

    public void viewTasks() {
        String list = "____________________________________________________\n";
        if (taskList.size() < 1) {
            list += "You currently have no tasks\n";
        } else {
            list += "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String taskString = String.valueOf(i + 1) + "." + task.toString() + "\n";
                list += taskString;
            }
        }
        list += "____________________________________________________\n";
        System.out.println(list);
    }

    public void markDone(int i) {
        Task task = taskList.get(i);
        task.makeDone();
        String markDoneMessage = "____________________________________________________\n" +
                "Nice! I've marked this task as done:\n" +
                "[\u2713] " + task.getDescription() + "\n" +
                "____________________________________________________\n";
        System.out.println(markDoneMessage);
    }

    public void handleDone(String info) throws DukeException {
        try {
            int doneIndex = Integer.valueOf(info) - 1;
            markDone(doneIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error! This task does not exist, please enter a valid task number.");
        }
    }

    public void handleInput(String input) {
        String[] inputInfo = input.split(" ", 2);
        String command = inputInfo[0];
        try {
            switch (command) {
                case ("list"):
                    viewTasks();
                    break;
                case ("bye"):
                    exit();
                    break;
                case ("done"):
                    if (inputInfo.length < 2) {
                        throw new DukeException("Error! To mark a task as done, please enter 'done [task number]'.");
                    } else {
                        String info = inputInfo[1];
                        handleDone(info);
                    }
                    break;
                case ("delete"):
                    if (inputInfo.length < 2) {
                        throw new DukeException("Error! To delete a task, please enter 'delete [task number]'.");
                    } else {
                        String info = inputInfo[1];
                        handleDelete(info);
                    }
                    break;
                case ("todo"):
                    if (inputInfo.length < 2) {
                        throw new DukeException("Error! To add a todo, please enter 'todo [description]'.");
                    } else {
                        String info = inputInfo[1];
                        handleToDo(info);
                    }
                    break;
                case ("deadline"):
                    if (inputInfo.length < 2) {
                        throw new DukeException("Error! To add a deadline, please enter 'deadline [description] /by [date/time]'.");
                    } else {
                        String info = inputInfo[1];
                        handleDeadline(info);
                    }
                    break;
                case ("event"):
                    if (inputInfo.length < 2) {
                        throw new DukeException("Error! To add an event, please enter 'event [description] /at [date/time]'.");
                    } else {
                        String info = inputInfo[1];
                        handleEvent(info);
                    }
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
