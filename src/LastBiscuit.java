import java.util.Scanner;

public class LastBiscuit {
    public static void main(String[] args) {

        final String BARR1_TOT = "Biscuits Left - Barrel 1: ";
        final String BARR2_TOT = "Biscuits Left - Barrel 2: ";
        final String INP_BARR = "From barrel1 (one), barrel2 (two), or both (both)? ";
        final String ERR_BISC = "Error: enter a valid number of biscuits.";
        final String ERR_BARR = "Error: enter a valid barrel number.";

        Scanner input = new Scanner(System.in);
        int barrel1 = 6;
        int barrel2 = 8;
        int numBiscuits = -1;   // Stores number of biscuits being taken.
        String numBarrel;   // Stores number of chosen barrel(s).
        int player = 1;     // Stores which player is taking their turn.
        boolean validBiscuit = false;
        boolean validBarrel = false;

        // Loop while at least one barrel isn't empty.
        while ((barrel1 > 0) || (barrel2 > 0)) {

            // Output barrel totals and ask player to take biscuits.
            System.out.println(BARR1_TOT + barrel1);
            System.out.println(BARR2_TOT + barrel2);
            System.out.print("Biscuits taken by player " + player + ": ");

            // Loop while the biscuit value is invalid.
            while (validBiscuit == false) {
                // Loop while biscuit value isn't an integer.
                while (!input.hasNextInt()) {
                    System.out.println(ERR_BISC);
                    System.out.print("Biscuits taken by player " + player + ": ");
                    String delete = input.next();
                }
                numBiscuits = input.nextInt();
                // Check the validated integer is within the accepted range.
                if ((numBiscuits <= Math.max(barrel1, barrel2)) && (numBiscuits > 0)) {
                    validBiscuit = true;
                } else {
                    System.out.println(ERR_BISC);
                    System.out.print("Biscuits taken by player " + player + ": ");
                    validBiscuit = false;
                }
            }

            // Ask player to choose a barrel.
            System.out.print(INP_BARR);
            input.nextLine();

            // Loop while the barrel choice is invalid.
            while (validBarrel == false) {

                numBarrel = input.nextLine();

                /*
                    Check, for each barrel, the amount taken is possible.
                    If both is chosen, check the amount taken is no bigger
                    than the minimum of the two.
                 */
                switch (numBarrel) {
                    case "one":
                        if ((numBiscuits <= barrel1) && (barrel1 != 0)) {
                            barrel1 = barrel1 - numBiscuits;
                            validBarrel = true;
                        } else {
                            System.out.println(ERR_BARR);
                            System.out.print(INP_BARR);
                            validBarrel = false;
                        }
                        break;
                    case "two":
                        if ((numBiscuits <= barrel2) && (barrel2 != 0)) {
                            barrel2 = barrel2 - numBiscuits;
                            validBarrel = true;
                        } else {
                            System.out.println(ERR_BARR);
                            System.out.print(INP_BARR);
                            validBarrel = false;
                        }
                        break;
                    case "both":
                        if ((numBiscuits <= Math.min(barrel1, barrel2))
                                && (barrel1 != 0) && (barrel2 != 0)) {
                            barrel1 = barrel1 - numBiscuits;
                            barrel2 = barrel2 - numBiscuits;
                            validBarrel = true;
                        } else {
                            System.out.println(ERR_BARR);
                            System.out.print(INP_BARR);
                            validBarrel = false;
                        }
                        break;
                    default:
                        System.out.println(ERR_BARR);
                        System.out.print(INP_BARR);
                        validBarrel = false;
                        break;
                }
            }

            // Player switcher and validation reset.
            switch (player) {
                case 1:
                    player = 2;
                    validBiscuit = false;
                    validBarrel = false;
                    break;
                case 2:
                    player = 1;
                    validBiscuit = false;
                    validBarrel = false;
                    break;
            }
        }

        // Output empty barrel totals.
        System.out.println(BARR1_TOT + "0");
        System.out.println(BARR2_TOT + "0");

        // Output winner string based on current player value.
        switch (player) {
            case 1:
                System.out.println("Winner is player 2");
                break;
            case 2:
                System.out.println("Winner is player 1");
                break;
        }
    }
}
