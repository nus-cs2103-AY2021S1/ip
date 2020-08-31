package duke;

import java.util.Scanner;

class Ui{

	public void showWelcome() {
		String logo = "____________________________________________________________\n"
                /*+ " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"*/
				+ " Hello I'm Duke\n"
				+ " What can I do for you?\n"
				+ "____________________________________________________________\n";
		System.out.println(logo);
	}

	public String readCommand() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

/*	public  void showLoadingError() {

	}
*/
	public void showLine() {
		System.out.println("____________________________________________________________\n");
	}

	public void showError() {
		System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
	}

	public void showOutput(String string){
		System.out.println(string);
	}
}