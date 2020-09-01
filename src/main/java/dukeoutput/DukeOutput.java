package dukeoutput;

import constant.DukeConstants;

import java.util.Collections;
import java.util.List;

public class DukeOutput {

    public static void outputGreeting() {
        System.out.println("Hello from\n" + DukeConstants.LOGO);
    }

    public static void output(String response) {
        String formattedResponse = DukeOutputFormatter.format(Collections.singletonList(response));
        System.out.println(formattedResponse);
    }

    public static void output(List<String> response) {
        String formattedResponse = DukeOutputFormatter.format(response);
        System.out.println(formattedResponse);
    }

    public static void output(List<String> responses, List<Integer> indentIndexes) {
        String formattedResponse = DukeOutputFormatter.format(responses, indentIndexes);
        System.out.println(formattedResponse);
    }
}