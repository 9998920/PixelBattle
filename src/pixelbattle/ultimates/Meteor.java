package pixelbattle.ultimates;

import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

public class Meteor implements Ultimate {
    @Override public String getName() { return "Метеорит"; }
    @Override public String getDescription() { return "Завдає кожному ворогу від 20 до 30 шкоди."; }
    @Override public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
        for (Fighter e : enemies) {
            if (e.isAlive()) { e.takeDamage(Fighter.random(20, 30)); }
        }
        return user.getName() + " викликає руйнівний Метеорит з небес!";
    }
}