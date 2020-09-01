package dukeoutput;

import constant.DukeConstants;

public class DukeOutput {

    public void outputGreeting() {
        System.out.println("Hello from\n" + DukeConstants.LOGO);
    }

    public void printResponse(String response) {
        System.out.println(DukeConstants.LINE);
        System.out.printf("%s%s\n", DukeConstants.IDENT, response);
        System.out.println(DukeConstants.LINE);
    }

    public void printResponseWithListSize(String response, int listSize) {
        System.out.println(DukeConstants.LINE);
        System.out.printf("%s%s\n", DukeConstants.IDENT, response);
        System.out.printf("%sNow you have %d tasks in the list.%n",
                DukeConstants.IDENT, listSize);
        System.out.println(DukeConstants.LINE);
    }
}
