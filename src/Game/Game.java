package Game;

import java.util.Random;

class Game {
    public static void main(String[] args) {



        Team[] team1 = new Team[]{new Warrior("Тигрил")
                , new Assasin( "Акали")
                , new Doctor("Жанна")};

        Hero[] team2 = new Hero[]{new Warrior(290, "Минотавр", 60, 0)
                , new Assasin(160, "Джинкс", 90, 0)
                , new Doctor(110, "Зои", 0, 80)};

        Battle battle = new Battle(team1, team2);



        System.out.println("---------------");

        for (Hero t1: team1) {
            t1.info();
        }

        for (Hero t2: team2) {
            t2.info();
        }

    }

}
