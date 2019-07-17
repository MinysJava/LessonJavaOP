package Lesson_1.Marathon;

import Lesson_1.Act.*;
import Lesson_1.Members.*;

public class Team {
    int number;
    Competitor[] competitors;

    public Team(int numberMember) {
        this.number = numberMember;

        competitors = new Competitor[number];

        for (int i = 0; i < number; i++) {
            int randomMember = 0 + (int) (Math.random() * 3);

            switch (randomMember) {
                case 0:
                    competitors[i] = new Human("Человек" + i);
                    break;
                case 1:
                    competitors[i] = new Cat("Кот" + i);
                    break;
                case 2:
                    competitors[i] = new Dog("Собака" + i);
                    break;
            }
        }
    }

    public void showResult() {
        for (Competitor c : competitors) {
            c.info();
        }
    }
}