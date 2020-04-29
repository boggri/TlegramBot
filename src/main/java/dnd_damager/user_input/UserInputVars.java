package dnd_damager.user_input;


public class UserInputVars {
    private static int numOfAttack;
    private static int diceOfDamage;
    private static int dmgModifier;
    private static int masteryClass;
    private static int resist;
    private static int valueForСritical;
    private static int armorClass;
    private static int numOfDamageDices;

    public static void setAllVariables(String userInput) {
        String[] parsedInput;
        parsedInput = userInput.split("\\s");

        int[] nums = new int[parsedInput.length];

        for (int i = 0; i < parsedInput.length; i++) {
            nums[i] = Integer.parseInt(parsedInput[i]);
        }

        //Parsing user input
        numOfAttack = nums[0];

        //Parsing with or without numOfDamageDice value)
        final int maxNumOfArguments = 8;
        int indexShift;
        if (parsedInput.length == maxNumOfArguments) {
            numOfDamageDices = nums[1];
            indexShift = 1;
        } else {
            numOfDamageDices = 1; // 1 dice by default
            indexShift = 0;
        }

        diceOfDamage = nums[1 + indexShift];
        dmgModifier = nums[2 + indexShift];
        masteryClass = nums[3 + indexShift];
        resist = nums[4 + indexShift];
        valueForСritical = nums[5 + indexShift];
        armorClass = nums[6 + indexShift];
    }


    ///GETTERS
    public static int getNumOfAttack() {
        return numOfAttack;
    }

    public static int getDiceOfDamage() {
        return diceOfDamage;
    }

    public static int getDmgModifier() {
        return dmgModifier;
    }

    public static int getMasteryClass() {
        return masteryClass;
    }

    public static int getResist() {
        return resist;
    }

    public static int getValueForСritical() {
        return valueForСritical;
    }

    public static int getArmorClass() {
        return armorClass;
    }

    public static int getNumOfDamageDices() {
        return numOfDamageDices;
    }
}
