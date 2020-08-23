import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

	private final static String LINE1 = "_____________________________________________________DUKE___";
	private final static String LINE2 = "------------------------------------------------------------";

	private final static String BYE = "bye";
	private final static String LIST = "list";
	private final static String DONE = "done";
	private final static String DELETE = "delete";
	private final static String TODO = "todo";
	private final static String EVENT = "event";
	private final static String DEADLINE = "deadline";


	public static void main(String[] args) {
		try {
			looper();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DukeException d) {
			d.printStackTrace();
		}
	}

	private static void reply(String text) {
		System.out.printf("%s\n%s\n%s\n", LINE1, text.trim(), LINE2);
	}

	private static void invalid(String message) {
		reply(String.format("Invalid command. %s", message));
	}

	private static void addTaskNotification(Task t) {
		reply(String.format("Added %s", t.toString()));
	}

	private static String[] readCommand(String text) {
		return text.split(" ", 2);
	}


	private static void looper() throws DukeException, IOException {
		reply("Hello");
		Storage storage = new Storage("data.txt");
		ArrayList<Task> list = new ArrayList<>();
		boolean exit = false;
		Scanner sc = new Scanner(System.in);
		while (!exit) {
			try {
				String input = sc.nextLine().trim();
				String[] parsedInput = readCommand(input);
				switch (parsedInput[0]) {
				case BYE:
					exit = true;
					break;
				case LIST:
					reply(storage.toString());
					break;
				case DONE:
					try {
						int index = Integer.parseInt(parsedInput[1]) - 1;
						list.get(index).setCompleted();
						reply(String.format("Task marked as completed: \n%s", list.get(index).toString()));
					} catch (ArrayIndexOutOfBoundsException e) {
						throw new DukeException(e.getMessage());
					} catch (IndexOutOfBoundsException e) {
						throw new DukeException(e.getMessage());
					} catch (NumberFormatException e) {
						throw new DukeException(String.format("%s is not a number that Done can use",
								parsedInput[1]));
					}
					break;
				case DELETE:
					try {
						int index = Integer.parseInt(parsedInput[1]) - 1;
						Task deleted = storage.delete(index);
						reply(String.format("Task deleted: \n%s", deleted.toString()));
					} catch (ArrayIndexOutOfBoundsException e) {
						throw new DukeException(e.getMessage());
					} catch (IndexOutOfBoundsException e) {
						throw new DukeException(e.getMessage());
					} catch (NumberFormatException e) {
						throw new DukeException(String.format("%s is not a number that Delete can use",
								parsedInput[1]));
					}
					break;
				case TODO:
					try {
						Todo todo = new Todo(parsedInput[1]);
						storage.add(todo);
						addTaskNotification(todo);
					} catch (ArrayIndexOutOfBoundsException e) {
						throw new DukeException("Insufficient arguments for Todo");
					}
					break;
				case EVENT:
					try {
						String[] evInput = parsedInput[1].split("/at ", 2);
						Event event = new Event(evInput[0].trim(), LocalDate.parse(evInput[1]));
						storage.add(event);
						addTaskNotification(event);
					} catch (ArrayIndexOutOfBoundsException e) {
						throw new DukeException("Insufficient arguments for Event");
					}
					break;
				case DEADLINE:
					try {
						String[] dlInput = parsedInput[1].split("/by ", 2);
						Deadline deadline = new Deadline(dlInput[0].trim(), LocalDate.parse(dlInput[1].trim()));
						storage.add(deadline);
						addTaskNotification(deadline);
					} catch (ArrayIndexOutOfBoundsException e) {
						throw new DukeException("Insufficient arguments for Deadline");

					}
					break;
				default:
					throw new DukeException("Input did not match any existing command.");
				}
			} catch (DukeException e) {
				System.out.println(String.format("DukeException: %s", e.getMessage()));
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
		reply("Goodbye.");
	}


}