package bookCourt.sport.utils;

public class Utility {
    public  static String normalizeTime(String time) {
        if (time != null && time.matches("\\d+:\\d")) {
            String[] parts = time.split(":");
            String hour = String.format("%02d", Integer.parseInt(parts[0]));
            String minute = String.format("%02d", Integer.parseInt(parts[1]));
            return hour + ":" + minute;
        }
        return time;
    }
}
