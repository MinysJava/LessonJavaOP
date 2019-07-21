package Game;

import java.util.Random;
import Game.Hero.*;

public class Battle {
    Random randomStep = new Random();
    Random randomHealing = new Random();
    Hero[] team1, team2;

    public Battle(Hero[] team1, Hero[] team2) {
        this.team1 = team1;
        this.team2 = team2;


    }

   while(team1.getHealTeam1 >= 0 || team2.getHealTeam2 >= 0) {
        for (int i = 0; i < team1.length; i++) {
            if (randomStep.nextInt(2) == 0) {
                if (team1[i] instanceof Doctor) {
                    team1[i].healing(team1[randomHealing.nextInt(2)]);
                } else {
                    team1[i].hit(team2[i]);
                }
            } else {
                if (team2[i] instanceof Doctor) {
                    team2[i].healing(team2[randomHealing.nextInt(2)]);
                } else {
                    team2[i].hit(team1[i]);
                }
            }
        }
    }


}
