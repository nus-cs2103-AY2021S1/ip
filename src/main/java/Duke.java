package main.java;

import java.time.format.DateTimeParseException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {
    private static final String PATH = "data";
    private static final String FILENAME = "tasks.txt";

    public static void main(String[] args) {
        boolean userInput = true;
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> storedItems = getTaskList();
        String border = "____________________________________________________________";
        String logo = " ____        ____\n"
                + "|  _ \\  ___ |  _ \\\n"
                + "| | | |/ _ \\| | | |\n"
                + "| |_| || __/| |_| |\n"
                + "|____/ \\___||____/\n";

        //greets
        System.out.println("Hello I am\n" + logo + "\n" + "Feed me some stuff! :3\n");

        while (userInput) {
            String input = sc.nextLine();
            String arr[] = input.split(" ", 2);
            String command = arr[0];
            System.out.println(command);
            try {
                if (command.equals("list")) {

                    System.out.println(border);
                    for (int i = 0; i < storedItems.size(); i++) {
                        System.out.println(String.format("%d. %s", i + 1, storedItems.get(i)));
                    }
                    System.out.println(border);

                } else if (command.equals("done")) {
                    if (arr.length < 2) {
                        throw new DukeException("The description of a done cannot be empty!");
                    }
                    int taskNum = Integer.parseInt(arr[1]);
                    if (taskNum > 0 && taskNum <= storedItems.size()) {
                        Task task = storedItems.get(taskNum - 1);
                        task.doTask();
                        System.out.println(border + "\n" + "Nice I've digested the following:\n"
                                + task + "\n" + "Now I'm hungry again! FEED ME MORE :3\n" + border);

                    } else {
                        throw new DukeException("What are you done with? Gotta specify a valid task number!");
                    }
                } else if (command.equals("delete")) {

                    if (arr.length < 2) {
                        throw new DukeException("The description of a delete cannot be empty!");
                    }
                    int deleteNum = Integer.parseInt(arr[1]);
                    if (deleteNum > 0 && deleteNum <= storedItems.size()) {
                        Task task = storedItems.get(deleteNum - 1);
                        storedItems.remove((deleteNum - 1));
                        System.out.println(border + "\n" + "Nooo you can't take away what you've already given me...\n"
                                + "Okay fine. It's in my stomach tho... ASDFGUUVHHH!!\n"
                                + "The following has been removed:\n"
                                + task + "\n" + "Now I'm feeling sick :( there's " + storedItems.size()
                                + " thing(s) in my belly now...HUNGRY!\n"
                                + border);
                    } else {
                        throw new DukeException("What are you deleting? Gotta specify a valid task number!");
                    }
                } else if (command.equals("uwu")) {
                    System.out.println(border + "\n" + "owo\n" + border);
                } else if (command.equals("owo")) {
                    System.out.println(border + "\n" + "uwu\n" + border);
                } else if (command.equals("exit")) {
                    System.out.println(border + "\n" + "bb cya again!\n" + border);
                    check = false;
                } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    if (arr.length < 2) {
                        throw new DukeException("The description of a task cannot be empty!");
                    }
                    Task newTask;
                    if (!command.equals("todo")) {
                        if (!arr[1].contains(" /by ") && !arr[1].contains(" /at ")) {
                            throw new DukeException("The date has to be specified!");
                        }
                        if (command.equals("deadline")) {
                            String temp[] = arr[1].split(" /by ", 2);
                            newTask = new Deadline(temp[0], temp[1].replace('/', '-'));
                        } else {
                            String temp[] = arr[1].split(" /at ", 2);
                            newTask = new Event(temp[0],  temp[1].replace('/', '-'));
                        }
                    } else {
                        newTask = new Todo(arr[1]);
                    }
                    storedItems.add(newTask);
                    System.out.println(border + "\n"
                            + "*Gobble gobble* the following has been eated OwO:\n"
                            + newTask.toString() + "\n"
                            + "I now have " + storedItems.size() + " thing(s) in my belly\n"
                            + border);
                } else {
                        throw new DukeException("I don't understand what you're saying HMM...");
                }
            } catch (DukeException e) {
                System.out.println(border + "\n" + e + "\n" + border);
            } catch (DateTimeParseException e) {
                System.out.println("☹ OOPSIE!! " + "Time format should be yyyy/mm/dd or yyyy-mm-dd.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveTaskList(String taskToAdd) throws IOException{
        File directory = new File(PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(PATH + "/" + FILENAME);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(PATH + "/" + FILENAME, true);
        fw.write(taskToAdd + "\n");
        fw.close();
    }

    private static void editTaskList(String newTask, int taskNum, boolean delete) throws IOException {
        ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(PATH + "/" + FILENAME)));
        if (delete) {
            fileContent.remove(taskNum - 1);
        } else {
            fileContent.set(taskNum - 1, newTask);
        }

        Files.write(Paths.get(PATH + "/" + FILENAME), fileContent);
    }

    private static ArrayList<Task> getTaskList() {
        File file = new File(PATH + "/" + FILENAME);
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] currLine = scanner.nextLine().split(" \\| ");
                String typeOfTask = currLine[0];
                switch (typeOfTask) {
                case "T":
                    Task todo = new Todo(currLine[2]);
                    if (Integer.parseInt(currLine[1]) == 1) {
                        todo.doTask();
                    }
                    list.add(todo);
                    break;
                case "D":
                    Task deadline = new Deadline(currLine[2], currLine[3]);
                    if (Integer.parseInt(currLine[1]) == 1) {
                        deadline.doTask();
                    }
                    list.add(deadline);
                    break;
                case "E":
                    Task event = new Event(currLine[2], currLine[3]);
                    if (Integer.parseInt(currLine[1]) == 1) {
                        event.doTask();
                    }
                    list.add(event);
                    break;
                default:
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            return list;
        }
    }
}
