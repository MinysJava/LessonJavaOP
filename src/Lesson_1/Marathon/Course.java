package Lesson_1.Marathon;

import Lesson_1.Act.Competitor;
import Lesson_1.Obstacles.*;

public class Course {
    int number;
    Obstacle[] course;

    public Course(int numberObstacle){
        this.number = numberObstacle;

        course = new Obstacle[number];

        for (int i = 0; i < number; i++) {
            int randomMember = 0 + (int) (Math.random() * 3);

            switch (randomMember) {
                case 0:
                    course[i] = new Wall(2);
                    break;
                case 1:
                    course[i] = new Cross(100);
                    break;
                case 2:
                    course[i] = new Water(50);
                    break;
            }
        }
    }

    public void doIt(Team competitors) {
        for (Competitor c : competitors.competitors) {
            for (Obstacle o : course) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }

    }
}
