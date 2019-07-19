package Lesson_1.Marathon;

public class Main {
    public static void main(String[] args) {
        Course line = new Course(10);
        Team team = new Team(10);
        line.doIt(team);
        team.showResult();
    }
}