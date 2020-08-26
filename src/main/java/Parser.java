import java.time.LocalDate;

public class Parser {
    public static Command manage(String input) throws DukeException {
        if (input.equals("list")) {
            return new PrintlistCommand();
        } else if (input.contains("check")) {
            int checkInt;
            try {
                String intAtBack = input.substring(6, input.length());
                checkInt = Integer.parseInt(intAtBack);
                return new CheckCommand(checkInt);
            } catch (Exception e) {
                return new ErrorCommand(e.getMessage());
            }
        } else if (input.contains("find")) {
            String keyword = input.substring(5, input.length());
            return new PrintsearchCommand(keyword);

        } else if (input.contains("remove")) {
            int removeInt;
            try {
                String intAtBack = input.substring(7, input.length());
                removeInt = Integer.parseInt(intAtBack);
                return new RemoveCommand(removeInt);
            } catch (Exception e) {
                return new ErrorCommand(e.getMessage());
            }
        } else if (input.contains("todo")) {
            if (checkEmpty(input, "todo")) {
                throw new DukeException("Much error! You have to describe your mission!");
            }
            ToDo todoTask = new ToDo(input.substring(5, input.length()));
            return new AddCommand(todoTask);
        } else if (input.contains("deadline")) {
            if (checkEmpty(input, "deadline")) {
                throw new DukeException("Much error! You have to describe your mission!");
            }
            if (checkPlan(input)) {
                throw new DukeException("Oh no, you do not have a planned timing for your mission!");
            }
            if (input.contains("/")) {
                int notePos = input.indexOf("/") + 1;
                String note = input.substring(notePos, input.length());
                String echo = input.substring(9, notePos - 1) + " ------> " + note;
                if (input.contains("by")) {
                    int byPos = input.indexOf("by") + 3;
                    String time = input.substring(byPos, input.length());
                    try {
                        String parsedTime = formatDate(time);
                        String listForm = input.substring(9, notePos - 1) + "by " + parsedTime;
                        return new AddCommand(new Deadline(listForm));
                    } catch (Exception e) {
                        return new AddCommand(new Deadline(echo));
                    }
                } else {
                    return new AddCommand(new Deadline(echo));
                }
            } else {
                //this.addToList(new Deadline(input.substring(9, input.length())));
                return new AddCommand(new Deadline(input.substring(9, input.length())));
            }
        }
        else if (input.contains("event")) {
            if(checkEmpty(input, "event")) {
                throw new DukeException("Much error! You have to describe your mission!");
            }
            if(checkPlan(input)) {
                throw new DukeException("Oh no, you do not have a planned timing for your mission!");
            }
            if(input.contains("/")) {
                int notePos = input.indexOf("/") + 1;
                String note = input.substring(notePos, input.length());
                String echo = input.substring(6, notePos - 1) + " ------> " + note;
                if(input.contains("on")) {
                    int byPos = input.indexOf("on") + 3;
                    String time = input.substring(byPos, input.length());
                    try {
                        String parsedTime = formatDate(time);
                        String listForm = input.substring(6, notePos - 1) + "on " + parsedTime;
                        return new AddCommand(new Event(listForm));
                    }
                    catch(Exception e) {
                        return new AddCommand(new Event(echo));
                    }
                }
                else {
                    return new AddCommand(new Event(echo));
                }
            }
            else {
                return new AddCommand(new Event(input.substring(6)));
            }
        }
        else {
            throw new DukeException("Oops! There is no such keyword!");
        }
    }

    public static Boolean checkEmpty(String input, String keyWord) {
        int keywordLength = keyWord.length();
        String remainingDescription = input.substring(keywordLength, input.length());
        if (remainingDescription.length() == 0 ) {
            return true;
        }
        else if (remainingDescription.length() > 1 && remainingDescription.charAt(1) == 32) {
            return true;
        }
        else if (remainingDescription.length() <= 1) {
            return true;
        }
        else {
            return false;
        }
    }

    //returns true if there is a description
    public static Boolean checkPlan(String input) {
        if(input.contains("/")) {
            String remainingDescription = input.substring(input.indexOf("/") + 1, input.length());
            if(remainingDescription.length() != 0) {
                return false;
            }
        }
        return true;
    }

    public static String formatDate(String date) throws Exception {
        LocalDate parseDate = LocalDate.parse(date);
        return parseDate.getDayOfWeek() + " " + parseDate.getDayOfMonth() + " "
                + parseDate.getMonth() + " " + parseDate.getYear();
    }

}
