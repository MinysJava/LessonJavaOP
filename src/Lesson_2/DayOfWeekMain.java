package Lesson_2;

public class DayOfWeekMain {
    public static void main(final String[] args) {
        System.out.println(getWorkingHours(DayOfWeek.SATURDAY));
    }

    private static String getWorkingHours(DayOfWeek day) {
        int hours;
        String result;

        hours = 40 - (day.ordinal() * 8);
        if(hours > 0) {
            return result = ("Осталось " + hours + " часов до конца рабочей недели");
        }
        else return result = "Выходной";
    }
}

enum DayOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}