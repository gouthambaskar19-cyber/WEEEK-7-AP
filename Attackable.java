public interface Attackable {
    String attack();
    String attack(String weaponName);
}

interface Defendable {
    String defend();
}

abstract class GameCharacter {
    private static int counter = 0;
    private final String characterId;

    public GameCharacter() {
        counter++;
        this.characterId = "CHAR-" + counter;
    }

    public abstract String getSpecialMove();

    public String getCharacterId() {
        return characterId;
    }
}
class Warrior extends GameCharacter implements Attackable, Defendable {
    private final String name;

    public Warrior(String name) {
        super();
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        this.name = name;
    }

    @Override
    public String attack() {
        return name + " strikes with a blade";
    }

    @Override
    public String attack(String weaponName) {
        return name + " strikes with an " + weaponName;
    }

    @Override
    public String defend() {
        return name + " raises a shield";
    }

    @Override
    public String getSpecialMove() {
        return name + " unleashes Whirlwind Slash";
    }

    public static void resolveDefense(Defendable[] combatants) {
        if (combatants != null) {
            for (Defendable combatant : combatants) {
                if (combatant != null) {
                    System.out.println(combatant.defend());
                }
            }
        }
    }
}
class Trap implements Defendable {
    private final String trapType;

    public Trap(String trapType) {
        if (trapType == null || trapType.trim().isEmpty()) {
            throw new IllegalArgumentException("Trap type cannot be blank.");
        }
        this.trapType = trapType;
    }

    @Override
    public String defend() {
        return trapType + " triggers automatically";
    }
}

class AttackableDemo {
    public static void main(String[] args) {
        Warrior warrior = new Warrior("Ares");
        Trap trap = new Trap("Spike Trap");

        System.out.println(warrior.attack());
        System.out.println(warrior.attack("spear"));
        System.out.println(warrior.defend());
        System.out.println(warrior.getSpecialMove());

        Defendable[] defenders = {warrior, trap};
        Warrior.resolveDefense(defenders);
    }
}