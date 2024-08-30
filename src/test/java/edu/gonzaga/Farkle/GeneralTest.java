package edu.gonzaga.Farkle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

// Example of a test. It's just always true, but it also runs as an example.
public class GeneralTest {
    @Test
    void alwaysTrue() {
        Assertions.assertTrue(true);
    }
    @Test
    void farkleTest() {
        int numDice = 6;
        FarkleScoreChecker myCheck = new FarkleScoreChecker();

        ArrayList<Die> dice1 = new ArrayList<>();
        ArrayList<Die> dice2 = new ArrayList<>();
        ArrayList<Die> dice3 = new ArrayList<>();
        ArrayList<Die> dice4 = new ArrayList<>();
        ArrayList<Die> dice5 = new ArrayList<>();
        for (int i = 0; i < numDice; i++){
            dice1.add(new Die(6,6));
            dice2.add(new Die(6,4));
            dice3.add(new Die(6,3));
            dice4.add(new Die(6,2));
            dice5.add(new Die(6,7));
        }
        Assertions.assertTrue(myCheck.checkFarkle(dice1, numDice));
        Assertions.assertTrue(myCheck.checkFarkle(dice2, numDice));
        Assertions.assertTrue(myCheck.checkFarkle(dice3, numDice));
        Assertions.assertTrue(myCheck.checkFarkle(dice4, numDice));
        Assertions.assertFalse(myCheck.checkFarkle(dice5, numDice));
    }
    @Test
    void scoreSingleKindTest() {
        int numDice = 6;
        FarkleScoreChecker myCheck = new FarkleScoreChecker();
        ArrayList<Boolean> isInMeld = new ArrayList<>();
        ArrayList<Die> dice1 = new ArrayList<>();
        ArrayList<Die> dice2 = new ArrayList<>();
        ArrayList<Die> dice3 = new ArrayList<>();
        ArrayList<Die> dice4 = new ArrayList<>();
        ArrayList<Die> dice5 = new ArrayList<>();
        ArrayList<Die> dice6 = new ArrayList<>();
        for (int i = 0; i < numDice; i++){
            isInMeld.add(true);
            dice1.add(new Die(6,1));
            dice2.add(new Die(6,2));
            dice3.add(new Die(6,3));
            dice4.add(new Die(6,4));
            dice5.add(new Die(6,5));
            dice6.add(new Die(6,6));
        }
        Assertions.assertEquals(1300,myCheck.scoreCalculator(dice1,isInMeld,numDice));
        Assertions.assertEquals(800,myCheck.scoreCalculator(dice2,isInMeld,numDice));
        Assertions.assertEquals(1200,myCheck.scoreCalculator(dice3,isInMeld,numDice));
        Assertions.assertEquals(1600,myCheck.scoreCalculator(dice4,isInMeld,numDice));
        Assertions.assertEquals(2000,myCheck.scoreCalculator(dice5,isInMeld,numDice));
        Assertions.assertEquals(2400,myCheck.scoreCalculator(dice6,isInMeld,numDice));
    }
    @Test
    void score3ofKindTest() {
        int numDice = 6;
        FarkleScoreChecker myCheck = new FarkleScoreChecker();
        ArrayList<Boolean> isInMeld = new ArrayList<>();
        ArrayList<Die> dice1 = new ArrayList<>();
        ArrayList<Die> dice2 = new ArrayList<>();
        ArrayList<Die> dice3 = new ArrayList<>();
        for (int i = 0; i < numDice; i++){
            isInMeld.add(true);
            if (i % 2 == 0) {
                dice1.add(new Die(6,1));
                dice2.add(new Die(6,2));
                dice3.add(new Die(6,3));
            }
            else {
                dice1.add(new Die(6,4));
                dice2.add(new Die(6,5));
                dice3.add(new Die(6,6));
            }
        }
        Assertions.assertEquals(1400,myCheck.scoreCalculator(dice1,isInMeld,numDice));
        Assertions.assertEquals(700,myCheck.scoreCalculator(dice2,isInMeld,numDice));
        Assertions.assertEquals(900,myCheck.scoreCalculator(dice3,isInMeld,numDice));
    }
    @Test
    void bankTest(){
        TurnHand hand = new TurnHand(6);
        hand.getDice().add(new Die(6,1));
        hand.getDice().add(new Die(6,1));
        hand.getDice().add(new Die(6,1));
        hand.getDice().add(new Die(6,1));
        hand.getDice().add(new Die(6,1));
        hand.getDice().add(new Die(6,1));

        hand.bankDice("A");
        hand.bankDice("B");
        hand.bankDice("C");
        hand.bankDice("D");
        hand.bankDice("E");
        hand.bankDice("F");

        Assertions.assertTrue(hand.getIsInMeld().get(0));
        Assertions.assertTrue(hand.getIsInMeld().get(1));
        Assertions.assertTrue(hand.getIsInMeld().get(2));
        Assertions.assertTrue(hand.getIsInMeld().get(3));
        Assertions.assertTrue(hand.getIsInMeld().get(4));
        Assertions.assertTrue(hand.getIsInMeld().get(5));
    }
    @Test
    void nameTest(){
        PlayFarkle tester = new PlayFarkle();
        String user1 = "\n";
        String user2 = "Turn\n";
        String user3 = "James\n";
        Scanner myObj = new Scanner(user1);
        Scanner myObj2 = new Scanner(user2);
        Scanner myObj3= new Scanner(user3);
        String name1 = tester.enterName(myObj, 9);
        String name2 = tester.enterName(myObj2, 0);
        String name3 = tester.enterName(myObj3, 0);
        Assertions.assertEquals("Unknown Player 10",name1);
        Assertions.assertEquals("Turn",name2);
        Assertions.assertEquals("James",name3);
    }
    @Test
    void calculateTest(){
        FarkleTurn turn = new FarkleTurn();
        Scanner myObj1 = new Scanner("\n");
        Scanner myObj2 = new Scanner("y\n");
        Assertions.assertEquals(0,turn.calculateNumDice(0,new TurnHand(0),myObj1));
        Assertions.assertEquals(6,turn.calculateNumDice(0,new TurnHand(0),myObj2));
    }
    @Test
    void straightTest(){
        int numDice = 6;
        FarkleScoreChecker myCheck = new FarkleScoreChecker();
        ArrayList<Boolean> isInMeld = new ArrayList<>();
        ArrayList<Die> dice = new ArrayList<>();
        dice.add(new Die(6,1));
        dice.add(new Die(6,2));
        dice.add(new Die(6,3));
        dice.add(new Die(6,4));
        dice.add(new Die(6,5));
        dice.add(new Die(6,6));
        isInMeld.add(true);
        isInMeld.add(true);
        isInMeld.add(true);
        isInMeld.add(true);
        isInMeld.add(true);
        isInMeld.add(true);
        Assertions.assertEquals(1000,myCheck.scoreCalculator(dice,isInMeld,numDice));
    }
    @Test
    void threePairsTest(){
        int numDice = 6;
        FarkleScoreChecker myCheck = new FarkleScoreChecker();
        ArrayList<Boolean> isInMeld = new ArrayList<>();
        ArrayList<Die> dice1 = new ArrayList<>();
        ArrayList<Die> dice2 = new ArrayList<>();
        ArrayList<Die> dice3 = new ArrayList<>();
        dice1.add(new Die(6,1));
        dice1.add(new Die(6,1));
        dice1.add(new Die(6,3));
        dice1.add(new Die(6,3));
        dice1.add(new Die(6,5));
        dice1.add(new Die(6,5));

        dice2.add(new Die(6,2));
        dice2.add(new Die(6,2));
        dice2.add(new Die(6,4));
        dice2.add(new Die(6,4));
        dice2.add(new Die(6,6));
        dice2.add(new Die(6,6));

        dice3.add(new Die(6,1));
        dice3.add(new Die(6,1));
        dice3.add(new Die(6,3));
        dice3.add(new Die(6,3));
        dice3.add(new Die(6,6));
        dice3.add(new Die(6,6));

        isInMeld.add(true);
        isInMeld.add(true);
        isInMeld.add(true);
        isInMeld.add(true);
        isInMeld.add(true);
        isInMeld.add(true);
        Assertions.assertEquals(750,myCheck.scoreCalculator(dice1,isInMeld,numDice));
        Assertions.assertEquals(750,myCheck.scoreCalculator(dice2,isInMeld,numDice));
        Assertions.assertEquals(750,myCheck.scoreCalculator(dice3,isInMeld,numDice));
    }
    @Test
    void fullHouseTest(){
        int numDice = 6;
        FarkleScoreChecker myCheck = new FarkleScoreChecker();
        ArrayList<Boolean> isInMeld = new ArrayList<>();
        ArrayList<Die> dice1 = new ArrayList<>();
        ArrayList<Die> dice2 = new ArrayList<>();
        ArrayList<Die> dice3 = new ArrayList<>();
        dice1.add(new Die(6,1));
        dice1.add(new Die(6,1));
        dice1.add(new Die(6,3));
        dice1.add(new Die(6,3));
        dice1.add(new Die(6,3));
        dice1.add(new Die(6,5));

        dice2.add(new Die(6,2));
        dice2.add(new Die(6,4));
        dice2.add(new Die(6,4));
        dice2.add(new Die(6,4));
        dice2.add(new Die(6,2));
        dice2.add(new Die(6,6));

        dice3.add(new Die(6,1));
        dice3.add(new Die(6,1));
        dice3.add(new Die(6,5));
        dice3.add(new Die(6,5));
        dice3.add(new Die(6,6));
        dice3.add(new Die(6,5));

        isInMeld.add(true);
        isInMeld.add(true);
        isInMeld.add(true);
        isInMeld.add(true);
        isInMeld.add(true);
        isInMeld.add(true);
        Assertions.assertEquals(1500,myCheck.scoreCalculator(dice1,isInMeld,numDice));
        Assertions.assertEquals(1500,myCheck.scoreCalculator(dice2,isInMeld,numDice));
        Assertions.assertEquals(1500,myCheck.scoreCalculator(dice3,isInMeld,numDice));
    }
    @Test
    void starterScoreTest(){
        PlayFarkle tester = new PlayFarkle();
        String user1 = "\n";
        String user2 = "10\n";
        String user3 = "100000\n";
        Scanner myObj = new Scanner(user1);
        Scanner myObj2 = new Scanner(user2);
        Scanner myObj3= new Scanner(user3);
        int name1 = tester.enterScore(myObj);
        int name2 = tester.enterScore(myObj2);
        int name3 = tester.enterScore(myObj3);
        Assertions.assertEquals(10000,name1);
        Assertions.assertEquals(10,name2);
        Assertions.assertEquals(100000,name3);
    }
    @Test
    void starterNumTest(){
        PlayFarkle tester = new PlayFarkle();
        String user1 = "\n";
        String user2 = "10\n";
        String user3 = "100000\n";
        Scanner myObj = new Scanner(user1);
        Scanner myObj2 = new Scanner(user2);
        Scanner myObj3= new Scanner(user3);
        int name1 = tester.enterNum(myObj);
        int name2 = tester.enterNum(myObj2);
        int name3 = tester.enterNum(myObj3);
        Assertions.assertEquals(1,name1);
        Assertions.assertEquals(10,name2);
        Assertions.assertEquals(100000,name3);
    }
    @Test
    void enderTest(){
        PlayFarkle tester = new PlayFarkle();
        ArrayList <Player> players = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            players.add(new Player());
        }
        players.get(4).setScore(100);
        Assertions.assertEquals(4, tester.findEnder(players,100,5));
    }
}
