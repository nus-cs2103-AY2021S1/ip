public class Parser {
    public Parser() {
    }

    //takes in a string, returns a string array containing a command type and the details
    //[command type, detail, date]
    public String[] getDetails(String s) {
        String[] details = new String[3];
        String[] rawData = s.split(" ");
        String type = rawData[0];
        String[] extractedDetails = extractDetails(rawData,type);
        details[0] = type;
        details[1] = extractedDetails[0];
        details[2] = extractedDetails[1];
        return details; //finish this code, when given raw data -> extract details. Then implement parser and switch loop
    }


    public String[] extractDetails(String[] details, String type) {
        String[] s = new String[2];
        String s1 = "";
        String s2 = "";
        int counter = 1;

        switch(type) {
            case "todo": //extract details
                for (int i = 1; i < details.length; i++) {
                    s1 = s1 + " " + details[i];
                }
                s[0] = s1;
                break;
            case "deadline": //extract details and date
                for (; counter < details.length; counter++) {
                    if (details[counter].equals("/by")) {
                        s[0] = s1; // get details
                        counter++;
                        break;
                    }
                    s1 = s1 + " " + details[counter];
                }
                for (; counter < details.length; counter++) {
                    s2 = s2 + " " + details[counter];
                }
                s[1] = s2; //date
                break;
            case "event": //extract details and date
                for (; counter < details.length; counter++) {
                    if (details[counter].equals("/at")) {
                        s[0] = s1; // get details
                        counter++;
                        break;
                    }
                    s1 = s1 + " " + details[counter];
                }
                for (; counter < details.length; counter++) {
                    s2 = s2 + " " + details[counter];
                }
                s[1] = s2; //date
                break;
            case ("done"):
                s[0] = details[1];
                break;
            case ("delete"):
                s[0] = details[1];
        }
        return s;
    }
}
