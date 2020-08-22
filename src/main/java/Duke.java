package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private enum TaskType {
        TODO, DEADLINE, EVENT;
    }
    private List<Task> tasks;
    private String indentation = "  ";
    private String BYE_COMMAND = "bye";
    private String LIST_COMMAND = "list";
    private String DONE_COMMAND = "done";
    private String DELETE_COMMAND = "delete";
    private String TODO_COMMAND = "todo";
    private String DEADLINE_COMMAND = "deadline";
    private String EVENT_COMMAND = "event";

    Duke() {
        tasks = new ArrayList<>();
    }

    private void addTaskCommand(String task, TaskType taskType) {
        Task newTask;
        String message;

        try {
            switch (taskType) {
            case TODO:
                if (task == null) {
                    throw new ToDoException();
                }
                newTask = new ToDoTask(task);
                break;
            case DEADLINE:
                if (task == null) {
                    throw new DeadlineException();
                }
                String[] arrForDeadline = task.split("/by", 2);

                if (arrForDeadline.length == 1) {
                    throw new DeadlineException();
                }

                String taskForDeadline = arrForDeadline[0].trim();
                String dateForDeadline = arrForDeadline[1].trim();
                newTask = new DeadlineTask(taskForDeadline, dateForDeadline);
                break;
            case EVENT:
                if (task == null) {
                    throw new EventException();
                }
                String[] arrForEvent = task.split("/at", 2);

                if (arrForEvent.length == 1) {
                    throw new EventException();
                }
                String taskForEvent = arrForEvent[0].trim();
                String dateForEvent = arrForEvent[1].trim();
                newTask = new EventTask(taskForEvent, dateForEvent);
                break;
            default:
                newTask = new Task(task);
                break;
            }
            saveTaskToFile(newTask);
            tasks.add(newTask);
            message = "Okay. I will add this task:\n" + indentation + newTask + "\n" +
                    "Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task " : "tasks ") + "in the list.";
        } catch (ToDoException | DeadlineException | EventException | IOException e) {
            message = e.getMessage();
        }

        sendMessage(message);

    }

    private void saveTaskToFile(Task task) throws IOException {
        try {
            Path path = Paths.get("data", "storage.txt");
            FileWriter fw = new FileWriter(path.toString(), true);

            String toBeAppend = Parser.parse(task);
            fw.write(toBeAppend);
            fw.close();
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }

    private void changeTaskInFile(int line) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();
            Path path = Paths.get("data", "storage.txt");
            File file = new File(path.toString());

            Scanner sc = new Scanner(file);

            int count = 1;
            while (sc.hasNext()) {
                String taskLine = sc.nextLine() + "\n";

                if (count == line) {
                    if (taskLine.charAt(4) == '0') {
                        taskLine = taskLine.replaceFirst("0", "1");
                    }
                }

                sb.append(taskLine);
                count++;
            }

            String tobeWritten = sb.toString();
            FileWriter fw = new FileWriter(path.toString());

            fw.write(tobeWritten);
            fw.close();
        } catch (IOException e) {
            throw new FailToReadFileException();
        }
    }

    private void deleteTaskInFile(int line) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();
            Path path = Paths.get("data", "storage.txt");
            File file = new File(path.toString());

            Scanner sc = new Scanner(file);

            int count = 1;
            while (sc.hasNext()) {
                String taskLine = sc.nextLine() + "\n";

                if (count != line) {
                    sb.append(taskLine);
                }
                count++;
            }

            String tobeWritten = sb.toString();
            FileWriter fw = new FileWriter(path.toString());

            fw.write(tobeWritten);
            fw.close();
        } catch (IOException e) {

        }
    }

    private void welcome() {
        String message = "Hi! My name is Duke.\nWhat do you want me to do?";
        sendMessage(message);
    }

    private void exit() {
        String message = "Bye. Thank you for using me!";
        sendMessage(message);
    }

    private void showAllTask() {
        String message;

        if (tasks.isEmpty()) {
            message = "You haven't add any task!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here is the tasks in your list:\n");

            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1) + ". " + tasks.get(i) + "\n");
            }
            message = sb.toString().trim();
        }

        sendMessage(message);
    }

    private void doneTask(String taskNumber) {
        String message;
        try {
            if (taskNumber == null) {
                throw new DoneException();
            }
            int taskNum = Integer.parseInt(taskNumber);
            if (taskNum < 1 || taskNum > tasks.size()) {
                message = "Invalid task number!";
            } else {
                Task task = tasks.get(taskNum - 1);
                task.setStatusToDone();
                changeTaskInFile(taskNum);
                message = "Sucessfully marked this task as done: \n" + indentation + task.toString();
            }
        } catch (NumberFormatException e) {
            message = "Please put a number!";
        } catch (DoneException e) {
            message = e.getMessage();
        } catch (IOException e) {
            message = e.getMessage();
        }
        sendMessage(message);
    }

    private void deleteTask(String taskNumber) {
        String message;
        try {
            if (taskNumber == null) {
                throw new DeleteException();
            }
            int taskNum = Integer.parseInt(taskNumber);
            if (taskNum < 1 || taskNum > tasks.size()) {
                message = "Invalid task number!";
            } else {
                Task task = tasks.remove(taskNum - 1);
                deleteTaskInFile(taskNum);
                message = "Okay. I will delete this task:\n" + indentation + task + "\n" +
                        "Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task " : "tasks ") + "in the list.";
            }
        } catch (NumberFormatException e) {
            message = "Please put a number!";
        } catch (DeleteException e) {
            message = e.getMessage();
        } catch (IOException e) {
            message = e.getMessage();
        }
        sendMessage(message);
    }

    private void takeUserInput() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String userInput = sc.nextLine();
                String[] userInputArr = userInput.split("\\s", 2);
                String command = userInputArr[0];
                String arg = null;


                if (userInputArr.length != 1) {
                    arg = userInputArr[1];
                }

                if (command.equals(BYE_COMMAND)) {
                    break;
                } else if (command.equals(LIST_COMMAND)) {
                    showAllTask();
                } else if (command.equals(DONE_COMMAND)) {
                    doneTask(arg);
                } else if (command.equals(DELETE_COMMAND)) {
                    deleteTask(arg);
                } else if (command.equals(TODO_COMMAND)) {
                    addTaskCommand(arg, TaskType.TODO);
                } else if (command.equals(DEADLINE_COMMAND)) {
                    addTaskCommand(arg, TaskType.DEADLINE);
                } else if (command.equals(EVENT_COMMAND)) {
                    addTaskCommand(arg, TaskType.EVENT);
                } else {
                    throw new NotACommandException();
                }
            } catch (NotACommandException e) {
                sendMessage(e.getMessage());
            }
        }
    }

    private void sendMessage(String sendMessage) {
        System.out.println(createLine(sendMessage));
    }

    private String createLine(String response) {
        Scanner sc = new Scanner(response);
        String equalSign = "=";
        int width = 75;
        StringBuilder sb = new StringBuilder();

        while (sc.hasNext()) {
            String textLine = indentation + sc.nextLine();
            sb.append(textLine + "\n");
            width = Math.max(width, textLine.length() + 2);
        }

        String line = equalSign.repeat(width);

        return line + "\n" + sb.toString() + line;
    }


    private void addTaskReadingList(String task, TaskType taskType, boolean isDone, String desc) {
        Task newTask;
        switch (taskType) {
        case TODO:
            newTask = new ToDoTask(task);
            break;
        case DEADLINE:
            newTask = new DeadlineTask(task, desc);
            break;
        case EVENT:
            newTask = new EventTask(task, desc);
            break;
        default:
            newTask = new Task(task);
            break;
        }

        if (isDone) {
            newTask.setStatusToDone();
        }

        tasks.add(newTask);
    }

    private void createStorageFile() throws IOException {
        try {
            Path dataPath = Paths.get("data");
            File dataFile = new File(dataPath.toString());

            if (!dataFile.exists()) {
                dataFile.mkdir();
            }

            Path storagePath = Paths.get("data", "storage.txt");
            File storageFile = new File(storagePath.toString());


            if (!storageFile.exists()) {
                storageFile.createNewFile();
            }
        } catch (IOException e) {
            throw new FailToReadFileException();
        }

    }

    private void readList() throws IOException {
        createStorageFile();

        Path storagePath = Paths.get("data", "storage.txt");
        File file = new File(storagePath.toString());

        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String taskString = sc.nextLine();
            String[] taskArr = taskString.split("\\|");

            String taskType = taskArr[0].trim();
            boolean isDone = taskArr[1].trim().equals("1");
            String taskName = taskArr[2].trim();

            if (taskType.equals("T")) {
                addTaskReadingList(taskName, TaskType.TODO, isDone, null);
            } else if (taskType.equals("D")) {
                String date = taskArr[3];
                addTaskReadingList(taskName, TaskType.DEADLINE, isDone, date);
            } else {
                String time = taskArr[3];
                addTaskReadingList(taskName, TaskType.EVENT, isDone, time);
            }
        }
    }

    private void run() {
        try {
            readList();
            welcome();
            takeUserInput();
            exit();
        } catch (IOException e) {
            sendMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}