package dnd_damager;

import interfaceReplier.CustomBotReplier;

public class DndDamager implements CustomBotReplier {

    private final int numOfAttack;
    private final int diceOfDamage;
    private final int dmgModifier;
    private final int masteryClass;
    private final int resist;
    private final int critValue;
    private final int armorClass;
    private final int numOfDamageDice;

    public DndDamager(String userInput) {
        String[] str;
        str = userInput.split("\\s");

        int[] nums = new int[str.length];

        for (int i = 0; i < str.length; i++) {
            nums[i] = Integer.parseInt(str[i]);
        }

        //Parsing user input (with or without numOfDamageDice)
        numOfAttack = nums[0];

        final int maxNumOfArguments = 8;
        int indexShift;
        if (str.length == maxNumOfArguments) {
            numOfDamageDice = nums[1];
            indexShift = 1;
        } else {
            numOfDamageDice = 1; // 1 dice by default
            indexShift = 0;
        }

        diceOfDamage = nums[1 + indexShift];
        dmgModifier = nums[2 + indexShift];
        masteryClass = nums[3 + indexShift];
        resist = nums[4 + indexShift];
        critValue = nums[5 + indexShift];
        armorClass = nums[6 + indexShift];
    }

    @Override
    public String start() {
        ActionDice actionDice = new ActionDice();
        actionDice.throwDice(numOfAttack, critValue);

        DamageDice damageDice = new DamageDice(resist);
        damageDice.throwDice(actionDice.getDiceThrows(), actionDice.getDiceCriticals().size(), dmgModifier, armorClass, diceOfDamage, masteryClass, numOfDamageDice);

        String dndResponse = String.format("You made: %d damage \n" +
                        "%d of %d attacks was successful \n" +
                        "%d attacks was critical and made: %d damage \n\n",
                damageDice.getUserDmg(),
                damageDice.getNumOfSucceedAttacks(),
                this.numOfAttack,
                actionDice.getDiceCriticals().size(),
                damageDice.getCriticalDmg()
                ) +
                "Your normal throws (without masteryClass): \n" +
                actionDice.getDiceThrows() + "\n\n";

        if (actionDice.getDiceCriticals().size() != 0) {
            dndResponse += "Your critical throws: \n" +
                    actionDice.getDiceCriticals();

        }

        return dndResponse;
    }
}
