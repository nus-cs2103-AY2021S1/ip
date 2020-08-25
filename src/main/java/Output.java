
//
//    public void eventResponse(TaskList taskList, String command) {
//        try {
//            String detail = formatTimingInput("/at", command.substring(5))[0];
//            String timing = formatTimingInput("/at", command.substring(5))[1].trim();
//
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
//            LocalDateTime dateTime = LocalDateTime.parse(timing, formatter);
//
//            Event event = new Event(detail, dateTime);
//            taskList.addTask(event);
//
//            System.out.println("Got it. I've added this task:\n" +
//                    event + "\nNow you have " + taskList.size() + " in the list");
//        } catch (MissingTimingException | InvalidDescriptionException e) {
//            System.out.println(e.getMessage());
//        } catch (DateTimeParseException e) {
//            System.out.println("Please enter timing in '/by 12-30-2020 23:59' format");
//        }
//    }

//    public String[] formatTimingInput(String format, String input) throws MissingTimingException {
//        if (!input.contains(format)) {
//            String message = "Don't forget to add a timing in '"
//                    + format + " 12-12-2020 23:59' format";
//            throw new MissingTimingException(message);
//        }
//        String[] parts = input.trim().split(format);
//        return parts;
//    }
//}
