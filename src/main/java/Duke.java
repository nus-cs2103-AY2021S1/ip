import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private UserInterface ui;
    private List<Task> lstOfTask = new ArrayList<>();

    public Duke() {
        this.ui = new UserInterface();
    }

    private void startup() {
        try {
            populateToLstOfTask();
            ui.greetUser();
        } catch (IOException e) {
            System.out.println(e.toString()+" Error trying to load tasks");
        }
    }

    private String[] parseString(String input) {
        String[] tokens = input.split(" ");
        return tokens;
    }

    private void processCommand(String[] parsedUserInput) {
        try {
            String cmd = parsedUserInput[0];
            Command checkedCommand = Command.valueOfUserCommand(cmd);
            if (checkedCommand == null) {
                ui.showInvalidCommandMessage();
            } else {
                switch (checkedCommand) {
                    case LIST:
                        ui.listTask(lstOfTask);
                        break;
                    case BYE:
                        exit();
                        ui.showExitMessage();
                        break;
                    case DONE:
                        done(parsedUserInput);
                        break;
                    case TODO:
                        addToDo(parsedUserInput);
                        break;
                    case EVENT:
                        addEvent(parsedUserInput);
                        break;
                    case DEADLINE:
                        addDeadline(parsedUserInput);
                        break;
                    case DELETE:
                        delete(parsedUserInput);
                        break;
                }
            }

        } catch (IndexOutOfBoundsException e) {
           ui.showInvalidCommandMessage();
        }
    }



    private void exit() {
        saveTaskContents();
    }

    private void done(String[] parsedUserInput) {

        try {
            String doneTask = parsedUserInput[1];
            int doneTaskNumber = Integer.parseInt(doneTask);
            int identifierNumberInArrayList = doneTaskNumber - 1;
            Task task = this.lstOfTask.get(identifierNumberInArrayList);
            task.markAsDone();

        } catch (IndexOutOfBoundsException e1) {
            ui.showInvalidDoneCommand();
        }
    }

    private void delete(String[] parsedUserInput) {
        try {
            String deleteTask = parsedUserInput[1];
            int doneTaskNumber = Integer.parseInt(deleteTask);
            int identifierNumberInArrayList = doneTaskNumber - 1;
            Task task = this.lstOfTask.get(identifierNumberInArrayList);
            lstOfTask.remove(identifierNumberInArrayList);
            ui.showDeleteTaskMessage(task, getNumOfTask());
        } catch (IndexOutOfBoundsException e1) {
           ui.showInvalidDeleteCommand();
        }
    }

    private void addToDo(String[] parsedUserInput) {
        try {
            String test = parsedUserInput[1];
            String taskDescription = "";
            for (int i = 1; i < parsedUserInput.length; i++) {
                if (i == parsedUserInput.length -1) {
                    taskDescription += parsedUserInput[i];
                } else {
                    taskDescription += parsedUserInput[i] + " ";
                }
            }
            ToDo td = new ToDo(taskDescription);
            lstOfTask.add(td);
            ui.showAddedTaskMessage(td,getNumOfTask());
        } catch (IndexOutOfBoundsException e1) {
            ui.showInvalidTodoCommand();
        }
    }

    private void addEvent(String[] parsedUserInput) {
        String taskDescription = "";
        for(int i = 1; i < parsedUserInput.length; i++) {
            if (i == parsedUserInput.length-1) {
                taskDescription += parsedUserInput[i];
            } else {
                taskDescription += parsedUserInput[i] + " ";
            }
        }

        String[] eventArray = taskDescription.split(" /at ");
        String description = eventArray[0];
        String date = eventArray[1];

        Event event = new Event(description, date.trim());
        lstOfTask.add(event);
        ui.showAddedTaskMessage(event, getNumOfTask());
    }

    private LocalDateTime parseDateAndTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime d1 = LocalDateTime.parse(dateTime,formatter);
        return d1;
    }

    private void addDeadline(String[] parsedUserInput) {
        try {
            String taskDescription = "";
            for (int i = 1; i < parsedUserInput.length; i++) {
                if (i == parsedUserInput.length - 1) {
                    taskDescription += parsedUserInput[i];
                } else {
                    taskDescription += parsedUserInput[i] + " ";
                }
            }


            String[] deadlineArray = taskDescription.split(" /by ");
            String description = deadlineArray[0];
            String date = deadlineArray[1];


            LocalDateTime d1 = parseDateAndTime(date);
            Deadline deadline = new Deadline(description, d1);
            lstOfTask.add(deadline);
            ui.showAddedTaskMessage(deadline, getNumOfTask());
        } catch (DateTimeParseException e) {
            ui.showInvalidDateFormatGiven();
        }
    }

    public int getNumOfTask() {
        return lstOfTask.size();
    }

    private void createNewTextFileCalledTask() {
        File file = new File("Tasks.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveTaskContents() {
        try {
            FileWriter fw = new FileWriter("Tasks.txt");

            createNewTextFileCalledTask();


            for (Task t : lstOfTask) {
                String taskString = t.toString();
                fw.write(taskString + "\n");
            }

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateToLstOfTask() throws IOException {

        File file = new File("Tasks.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st = br.readLine();

        while (st != null) {
            Task t = parser(st);
            lstOfTask.add(t);
            st = br.readLine();
        }

    }

    private Task parser(String str) {
        try {
            String type = str.substring(1, 2);
            String status = str.substring(4, 5);
            String descriptionAndDate = str.substring(6);
            String[] arr = descriptionAndDate.split("\\(");
            String description = arr[0].trim();
            String date = "";
            Task task;


            if (type.equals("E") || type.equals("D")) {
                date = arr[1].substring(4).replace(")", "");
            }

            if (type.equals("T")) {
                task = new ToDo(description);
            } else if (type.equals("E")) {
                task = new Event(description, date);
            } else { // "D"
                task = new Deadline(description, parseDateAndTime(date));
            }

            if (status.equals("\u2713")) {
                task.markAsDone();
            }
            return task;
        }catch (IndexOutOfBoundsException|NullPointerException e) {
            System.out.println(e.getMessage() + "Error in tasks file");
            throw e;
        }
    }

    public static void main(String[] args)  {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        duke.startup();

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] parsedString = duke.parseString(input);
            duke.processCommand(parsedString);
            if (input.equals("bye")) {
                break;
            }
        }


    }


}
