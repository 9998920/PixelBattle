package pixelbattle.heroes;

import pixelbattle.core.Hero;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.engine.Sprite;
import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

/** Лучниця — швидка й спритна. */
public class Archer extends Hero {

    public Archer() {
        super("Лучниця", 80, new Sprite("elf_f_idle_anim", 4, 4));

        addSkill(new Skill("Постріл", Target.ENEMY, 0, (user, target) ->
                user.getName() + " стріляє в " + target.getName() + ": "
                        + target.takeDamage(random(10, 16)) + " шкоди"));

        addSkill(new Skill("Подвійний постріл", Target.ENEMY, 1, (user, target) -> {
            int dmg1 = target.takeDamage(random(7, 11));
            int dmg2 = target.takeDamage(random(7, 11));
            return user.getName() + " випускає дві стріли, завдаючи загалом " + (dmg1 + dmg2) + " шкоди!";
        }));

        setUltimate(new Ultimate() {
            private int arrowCount = 3;
            @Override public String getName() { return "Злива стріл"; }
            @Override public String getDescription() { return "Кожен живий ворог отримує стріли по 4-8 шкоди. Кількість зростає."; }
            @Override public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                int totalDamage = 0;
                for (Fighter e : enemies) {
                    if (e.isAlive()) {
                        for (int i = 0; i < arrowCount; i++) {
                            if (e.isAlive()) totalDamage += e.takeDamage(user.random(4, 8));
                        }
                    }
                }
                arrowCount++;
                return user.getName() + " випускає Зливу стріл, загальна шкода: " + totalDamage + "!";
            }
        });
    }

    @Override
    protected int modifyIncomingDamage(int damage) {
        return chance(25) ? 0 : damage;
    }
}
