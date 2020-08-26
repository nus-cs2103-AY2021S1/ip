import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {

    public static void action(TaskList taskList, Ui ui, Storage storage) {
        ui.welcome();
        storage.load(taskList, ui);
        ui.printSaved(taskList);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    ui.printBye();
                    break;
                } else if (input.equals("list")) {
                    ui.printList(taskList);
                } else if (input.startsWith("done")) {
                    String[] number = input.split("done ");
                    int num = Integer.parseInt(number[1]);
                    Task task = taskList.getTask(num);
                    task.markAsDone();
                    storage.saveTasks(taskList, ui);
                    ui.printDone(taskList.getTask(num));
                } else if (input.startsWith("delete")) {
                    String[] number = input.split("delete ");
                    int num = Integer.parseInt(number[1]);
                    Task task = taskList.getTask(num);
                    taskList.deleteTask(num);
                    ui.printDelete(taskList, task);
                    storage.saveTasks(taskList, ui);
                } else if (input.startsWith("todo")) {
                    String[] array = input.split("todo ");
                    if (array.length < 2) {
                        throw new DukeException("The description of a todo cannot be empty!");
                    } else {
                        String des = array[1];
                        Task todo = new ToDos(des);
                        taskList.addTask(todo);
                        ui.printAdd(taskList, todo);
                        storage.saveTasks(taskList, ui);
                    }
                } else if (input.startsWith("deadline")) {
                    String[] array = input.split("deadline ");
                    if (array.length < 2) {
                        throw new DukeException("The description of a deadline cannot be empty!");
                    } else {
                        String[] arr = array[1].split(" /by ");
                        String des = arr[0];
                        if (arr.length == 1) {
                            throw new DukeException("The deadline of the task cannot be empty!");
                        } else {
                            try {
                                String due = arr[1];
                                Task dl = new Deadline(des, due);
                                taskList.addTask(dl);
                                ui.printAdd(taskList, dl);
                                storage.saveTasks(taskList, ui);
                            } catch (DateTimeParseException e) {
                                System.out.println("Please use this format: \n" +
                                        "dd-MM-yyyy HHmm");
                            }
                        }
                    }
                } else if (input.startsWith("event")) {
                    String[] array = input.split("event ");
                    if (array.length < 2) {
                        throw new DukeException("The description of a event cannot be empty!");
                    } else {
                        String[] arr = array[1].split(" /at ");
                        String des = arr[0];
                        if (arr.length == 1) {
                            throw new DukeException("The deadline of the event cannot be empty!");
                        } else {
                            try {
                                String due = arr[1];
                                Task event = new Events(des, due);
                                taskList.addTask(event);
                                ui.printAdd(taskList, event);
                                storage.saveTasks(taskList, ui);
                            } catch (DateTimeParseException e) {
                                System.out.println("Please use this format: \n" +
                                        "dd-MM-yyyy HHmm");
                            }
                        }
                    }
                } else if (input.startsWith("find")) {
                    String word = input.split("find ")[1];
                    ui.find(word, taskList);
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means.");
                }
            } catch (DukeException e) {
                ui.printDukeError(e);
            }
        }
        sc.close();
    }




}
