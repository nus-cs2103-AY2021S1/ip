public class Convert {
    static String at(String s) {
        String first = s.split("/at")[0];
        String second = s.split("/at")[1];
        return first + "(at:" + second + ")";
    }

    static String by(String s) {
        String first = s.split("/by")[0];
        String second = s.split("/by")[1];
        return first + "(by:" + second + ")";
    }
}