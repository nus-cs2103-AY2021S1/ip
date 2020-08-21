public class StringUtils {
    public static String[] parseInputSections(String input, String breakPt1, String breakPt2) throws DukeException {
        try {
            checkIllegalChar(input);
        } catch (DukeException e) {
            throw e;
        }

        int size = 2;
        if (breakPt2.equals("")) {
            size = 1;
        }
        String[] res = new String[size];

        String[] splitByBP1 = input.split(breakPt1);
        if (splitByBP1.length <= 1) {
            throw new DukeException(breakPt1 + " description cannot be empty!");
        }

        String description = splitByBP1[1].trim();
        if (description.equals("")) {
            throw new DukeException(breakPt1 + " description cannot be empty!");
        }

        if (size == 1) {
            res[0] = description;
            return res;
        }

        String[] splitByBP2 = description.split(breakPt2);
        if (!description.contains(breakPt2)) {
            throw new DukeException(breakPt2 + " keyword must be in input for all " + breakPt1 + " commands!");
        }

        if (splitByBP2.length <= 1 || splitByBP2[1].trim().equals("")) {
            throw new DukeException("Content after " + breakPt2 + " should not be empty!");
        }

        res[0] = splitByBP2[0].trim();
        res[1] = splitByBP2[1].trim();

        return res;
    }

    private static void checkIllegalChar(String input) throws DukeException {
        char[] illegalChar = {'|'};
        for (char c : illegalChar) {
            if (input.indexOf(c) != -1) {
                throw new DukeException("Illegal Character: " + c);
            }
        }
    }

    public static <T> void printWithWrapper(T[] arr, boolean withNumbering) {
        String BORDER = "=======================================";
        String INDENT = "    ";
        System.out.println(BORDER);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                break;
            }

            String prepend = INDENT;
            if (withNumbering) {
                prepend += (i + 1) + ". ";
            }
            System.out.println(prepend + arr[i].toString());
        }
        System.out.println(BORDER);
    }
}
