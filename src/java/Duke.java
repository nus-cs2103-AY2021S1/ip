import java.util.Scanner;

public class Duke {

	private final static String PATH = "data.txt";

	public static void main(String[] args) {
		run();
	}

	private static void run() {
		try {
			Ui.showWelcome();
			Scanner sc = new Scanner(System.in);
			// TODO: clean up storage exceptions
			Storage storage = new Storage(PATH);
			boolean isExit = false;
			while (!isExit) {
				try {
					String input = sc.nextLine().trim();
					Command command = Parser.parseCommand(input);
					command.execute(storage);
					isExit = command.isExit();
				} catch (DukeArgumentException dae) {
					Ui.wrapText(dae.getMessage());
				} catch (DukeExecutionException dee) {
					Ui.wrapText(dee.getMessage());
				}
			}
		} catch (DukeIOException die) {
			Ui.wrapText(die.getMessage());
		}
		Ui.showExit();
	}


}