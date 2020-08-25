import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Duke {

    private final Storage storage;
    private final TaskList ls;
    private final Ui ui;

    enum Command {
        BYE, LIST, DONE, TODO, DEADLINE, EVENT, DELETE, CHECK
    }

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        ls = new TaskList(storage.loadFile());
    }

    public void run() throws IOException {
        ui.showWelcome();
        
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        WhileLoop:
        while (in.hasNextLine()) {
            String str = in.nextLine();
            String[] arr = str.split(" ", 2);

            String upperCaseCmd = Parser.parseCmd(arr[0]);
            Command cmd;
            try {
                cmd = Command.valueOf(upperCaseCmd);
            } catch (IllegalArgumentException ex) {
                ui.invalidInput();
                continue;
            }

            switch (cmd) {
                case BYE:
                    ui.showBye();
                    // rewrite the file to update latest changes
                    Storage.saveFile(this.storage.file, ls);
                    break WhileLoop;

                case LIST:
                    if (ls.isEmpty()) {
                        ui.showListNoTask();
                    } else {
                        ui.showListTask();
                        for (int i = 0; i < ls.size(); i++) {
                            Task task = ls.get(i);
                            int num = i + 1;
                            System.out.println(num + ". " + task.toString());
                        }
                        ui.horizontalDiv();
                    }
                    break;

                case DONE:
                    try {
                        int numToBeMarkedAsDone = Parser.parseInt(str);
                        Task tsk = ls.get(numToBeMarkedAsDone);
                        tsk.markAsDone();
                        ls.set(numToBeMarkedAsDone, tsk);
                        ui.showDoneMsg(tsk.toString());
                    } catch (Exception ex) {
                        ui.showDoneError();
                    }
                    break;

                case TODO:
                    try {
                        Task newTask = new Todo(arr[1]);
                        ls.add(newTask);
                        ui.showTodoMsg(ls, newTask);
                    } catch (Exception ex) {
                        ui.showTodoError();
                    }
                    break;

                case DEADLINE:
                    try {
                        String[] arrOfStr = Parser.parse(arr, 1);
                        try {
                            Task newTask = new Deadline(arrOfStr[0], arrOfStr[1]);
                            ls.add(newTask);
                            ui.showDeadlineEventMsg(ls, newTask);
                        } catch (Exception ex) {
                            ui.showDeadlineFormatError();
                        }
                    } catch (Exception ex) {
                        ui.showDeadlineError();
                    }
                    break;

                case EVENT:
                    try {
                        String[] arrOfStr = Parser.parse(arr, 2);
                        try {
                            Task newTask = new Event(arrOfStr[0], arrOfStr[1]);
                            ls.add(newTask);
                            ui.showDeadlineEventMsg(ls, newTask);
                        } catch (Exception ex) {
                            ui.showEventFormatError();
                        }
                    } catch (Exception ex) {
                        ui.showEventError();
                    }
                    break;
                    
                case DELETE:
                    try {
                        int numToBeDeleted = Parser.parseInt(str);
                        Task tsk = ls.get(numToBeDeleted);
                        ls.remove(numToBeDeleted);
                        ui.showDeleteMsg(ls, tsk);
                    } catch (Exception ex) {
                        ui.showDeleteError();
                    }
                    break;
                    
                case CHECK:
                    try {
                        String checkDate = arr[1];
                        LocalDate date = LocalDate.parse(checkDate);
                        if (ls.isEmpty()) {
                            ui.showCheckNoTask();
                        } else {
                            ui.horizontalDiv();
                            int counter = 0;
                            for (int i = 0; i < ls.size(); i++) {
                                Task task = ls.get(i);
                                if (task instanceof Event) {
                                    if (((Event) task).getAt().equals(date) && !task.isDone) {
                                        counter += 1;
                                        System.out.println(counter + ". " + task.toString());
                                    }
                                } else if (task instanceof Deadline && !task.isDone) {
                                    if (((Deadline) task).getDate().equals(date)) {
                                        counter += 1;
                                        System.out.println(counter + ". " + task.toString());
                                    }
                                }
                            }
                            ui.showCheckTask(counter, date);
                        }
                    } catch (Exception ex) {
                        ui.showCheckError();
                    }
                    break;
                    
                default:
                    ui.invalidInput();
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke(Storage.getFilePath()).run();
    }
}
