package pixelbattle.heroes;

import pixelbattle.core.Hero;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.engine.Sprite;

/** Маг — потужні заклинання та лікування. */
public class Mage extends Hero {

    public Mage() {
        super("Маг", 70, new Sprite("wizzard_m_idle_anim", 4, 4));

        addSkill(new Skill("Удар посохом", Target.ENEMY, 0, (user, target) ->
                user.getName() + " б'є посохом " + target.getName() + ": "
                        + target.takeDamage(random(6, 10)) + " шкоди"));

        addSkill(new Skill("Вогняна куля", Target.ENEMY, 1, (user, target) -> {
            int dmg = target.takeDamage(random(22, 30));
            return user.getName() + " кидає вогняну кулю: " + dmg + " шкоди по " + target.getName();
        }));

        addSkill(new Skill("Зцілення", Target.ALLY, 2, (user, target) -> {
            int healed = target.heal(25);
            return user.getName() + " лікує " + target.getName() + " на " + healed + " HP!";
        }));

        // TODO 8: ультимейт «Метеорит» — окремий клас Meteor
        //   у файлі pixelbattle/ultimates/Meteor.java.
        //   Тут лише підключіть його: setUltimate(new Meteor());
        //   (потрібен import pixelbattle.ultimates.Meteor;)
    }
}
