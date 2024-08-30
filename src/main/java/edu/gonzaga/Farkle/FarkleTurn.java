/*
 Name: James Hagan
 Class: CPSC 224, Spring 2024
 Farkle 2
 Description: Runs a singleplayer multi-turned farkle
*/
package edu.gonzaga.Farkle;
import java.util.Scanner;

public class FarkleTurn {
    public Player runTurn(int numDice, Scanner myObj, int scoreStarter, String userName) {
        //Start
        Player player = new Player();
        player.setName(userName);
        player.setNumDice(0);
        FarkleScoreChecker myCheck = new FarkleScoreChecker();
        TurnHand hand = new TurnHand(numDice);
        System.out.println("Starting Turn. Number of dice: " + numDice + "\nPress Enter to Continue");
        String tempString = myObj.nextLine();
        String bankString = "";
        //Roll/Print Dice
        System.out.println("Now rolling our die!");
        hand.rollDice(numDice);
        hand.printDice(numDice);
        //Check Farkle/Bank Dice
        System.out.println("Now checking for Farkle");
        if (myCheck.checkFarkle(hand.getDice(), numDice)) {
            System.out.println("You did not roll a Farkle!");
            System.out.println("Would you like to see bank controls? (y for yes, anything else for no)");
            bankString = myObj.nextLine();
            printBankControls(bankString, myObj);
            while (!bankString.equals("Z")) {
                System.out.println("What dice would you like to bank? (press z to bank)");
                player.setScore(myCheck.scoreCalculator(hand.getDice(), hand.getIsInMeld(), numDice) + scoreStarter);
                System.out.println("Current score if you bank: " + player.getScore());
                hand.printBank(numDice);
                bankString = myObj.nextLine();
                if (bankString.isEmpty()) {

                }
                else {
                    bankString = bankString.toUpperCase();
                    if(bankString.equals("Z")){
                        if(hand.getNumInMeld(numDice) == 0) {
                            System.out.println("Cannot bank with nothing in meld");
                            bankString = "J";
                        }
                        else if(myCheck.scoreCalculator(hand.getDice(), hand.getIsInMeld(), numDice) == 0) {
                            System.out.println("Cannot bank with invalid meld");
                            bankString = "J";
                        }
                    }
                    if(bankString.equals("X")){
                        player.setScore(myCheck.scoreCalculator(hand.getDice(), hand.getIsInMeld(), numDice) + scoreStarter);;
                        return player;
                    }
                    if(bankString.equals("Q")){
                        player.setScore(myCheck.scoreCalculator(hand.getDice(), hand.getIsInMeld(), numDice) + scoreStarter);;
                        player.setNumDice(-10);
                        return player;
                    }
                    hand.bankDice(bankString);
                }
            }
        }
        else {
            System.out.println("You rolled a Farkle, Better luck next time!");
            return player;
        }
        player.setScore(myCheck.scoreCalculator(hand.getDice(), hand.getIsInMeld(), numDice) + scoreStarter);
        player.setNumDice(calculateNumDice(numDice,hand,myObj));
        return player;
    }
    //Prints bank turn controls
    private void printBankControls (String choice, Scanner myObj) {
        if (choice.isEmpty())
            return;
        choice = choice.toUpperCase();
        char c = (choice.charAt(0));
        if (c == 'Y'){
            System.out.println("Controls:\nEnter letter that corresponds with number you want to bank (Ex. A)");
            System.out.println("Press z to bank your dice and reroll with unused dice");
            System.out.println("Press X to end your turn early");
            System.out.println("Press Q to quit");
            System.out.println("Press Enter to continue");
            choice = myObj.nextLine();
        }
    }
    //Calculates the num of dice to return
    public int calculateNumDice(int numDice, TurnHand hand,Scanner myObj) {
        int numMelded = hand.getNumInMeld(numDice);
        String choice;
        int finalNum = numDice - numMelded;
        if (finalNum == 0){
            System.out.println("    __  __      __     __  __                __ ");
            System.out.println("   / / / /___  / /_   / / / /___ _____  ____/ /");
            System.out.println("  / /_/ / __ \\/ __/  / /_/ / __ `/ __ \\/ __  /");
            System.out.println(" / __  / /_/ / /_   / __  / /_/ / / / / /_/ /");
            System.out.println("/_/ /_/\\____/\\__/  /_/ /_/\\__,_/_/ /_/\\__,_/");
            System.out.println("Enter y to roll 6 new dice, or anything else to bank and end your turn");
            choice = myObj.nextLine();
            if (choice.isEmpty()) {
                return 0;
            }
            else {
                choice = choice.toUpperCase();
                if (choice.equals("Y")){
                    return 6;
                }
                else {
                    return 0;
                }
            }
        }
        else {
            return finalNum;
        }
    }
}