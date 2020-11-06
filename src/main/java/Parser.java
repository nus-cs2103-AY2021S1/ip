import java.util.Arrays;
/**
 * The Parser object is in charge of separating the details of the user input into a String array.
 * Typically, the command is split into [command, detail, deadline].
 */

public class Parser {

    public Parser() {
    }

    /***
     * Returns a string array containing the command type, command details and date details. If there
     * are no details then the array slot is left as null. Reads the raw input from the user as
     * a string and split the based on whitespace.
     *
     * @param String input from the user
     * @return a size 3 string array containing command type, command detail and date detail
     */

    public String[] getDetails(String s) {
        String[] rawData = s.split(" ");
        String[] extractedDetails = extractDetails(rawData);
        return extractedDetails;
    }

    /**
     * Returns a size 3 array containing command type, command detail and command date. Any array
     * slots that have no inputs are left as null. The original raw input is split and processed
     * depending on the command type. The command types available are todo, deadline, event, done and
     * delete.
     *
     * @param string array of the raw user input
     * @return a size 3 array containing command type, command detail and date detail.
     **/
    public String[] extractDetails(String[] details) {
        String type = details[0];
        String[] s = new String[3];
        String s1 = "";
        String s2 = "";
        int counter = 1;

        switch (type) {
            case "todo":
                if (details.length == 1) { //empty todo
                    s[1] = null;
                    break;
                } else { // form a single string of detail
                    for (int i = 1; i < details.length; i++) {
                        s1 = s1 + " " + details[i];
                    }
                    s[1] = s1;
                    break;
                }

            case "deadline":
                for (; counter < details.length; counter++) {
                    if (details[counter].equals("/by")) { //base case for task details
                        s[1] = s1;  //enter detail into extracted details array
                        break;
                    }
                    s1 = s1 + " " + details[counter]; //build a string of task details
                }
                for (counter++; counter < details.length; counter++) {//build a string of time detail
                    if (counter == details.length - 1) {
                        s2 = s2 + details[counter];
                    } else {
                        s2 = s2 + details[counter] + " ";
                    }
                }
                s[2] = s2; //enter time detail into extracted detail array
                break;
            case "event":
                for (; counter < details.length; counter++) {
                    if (details[counter].equals("/at")) { //base case for task details
                        s[1] = s1; // enter detail into final array
                        break;
                    }
                    s1 = s1 + " " + details[counter]; //build string of detail
                }
                for (counter++; counter < details.length; counter++) {
                    if (counter == details.length - 1) {
                        s2 = s2 + details[counter];
                    } else {
                        s2 = s2 + details[counter] + " ";
                    }
                }
                s[2] = s2; //enter time detail into final array
                break;
            case ("done"):
                s[1] = details[1]; //expected to be an int
                break;
            case ("delete"):
                s[1] = details[1]; //expected to be an int
                break;
            case ("bye"):
                break;
            case ("find"):
                s[1] = details[1];
                break;
            case ("tag"):
                if (details.length < 3) {
                    s[1] = null;
                    s[2] = null;
                    break;
                } else {
                    s[1] = details[1];
                    s[2] = details[2];
                    break;
                }
            default:
                break;
        }
        s[0] = type;
        return s;
    }
}
