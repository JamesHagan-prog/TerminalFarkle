/*
 Name: James Hagan
 Class: CPSC 224, Spring 2024
 Farkle 2
 Description: Runs a singleplayer multi-turned farkle
*/
package edu.gonzaga.Farkle;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayFarkle {
    public void playGame() {
        //Setup Game
        Scanner myObj = new Scanner(System.in);
        FarkleTurn turn = new FarkleTurn();
        displayTitle();
        int score = enterScore(myObj);
        int players = enterNum(myObj);
        if (players == 1) {
            singlePlayer(myObj, turn, score);
        }
        else {
            multiPlayer(myObj, turn, score, players);
        }
    }
    private void displayTitle(){
        System.out.println("Welcome to");
        System.out.println("          ___           ___           ___           ___                         ___");
        System.out.println("         /\\__\\         /\\  \\         /\\  \\         /|  |                       /\\__\\    ");
        System.out.println("        /:/ _/_       /::\\  \\       /::\\  \\       |:|  |                      /:/ _/_");
        System.out.println("       /:/ /\\__\\     /:/\\:\\  \\     /:/\\:\\__\\      |:|  |                     /:/ /\\__\\ ");
        System.out.println("      /:/ /:/  /    /:/ /::\\  \\   /:/ /:/  /    __|:|  |      ___     ___   /:/ /:/ _/_");
        System.out.println("     /:/_/:/  /    /:/_/:/\\:\\__\\ /:/_/:/__/___ /\\ |:|__|____ /\\  \\   /\\__\\ /:/_/:/ /\\__\\             ");
        System.out.println("     \\:\\/:/  /     \\:\\/:/  \\/__/ \\:\\/:::::/  / \\:\\/:::::/__/ \\:\\  \\ /:/  / \\:\\/:/ /:/  /");
        System.out.println("      \\::/__/       \\::/__/       \\::/~~/~~~~   \\::/~~/~      \\:\\  /:/  /   \\::/_/:/  / ");
        System.out.println("       \\:\\  \\        \\:\\  \\        \\:\\~~\\        \\:\\~~\\        \\:\\/:/  /     \\:\\/:/  /  ");
        System.out.println("        \\:\\__\\        \\:\\__\\        \\:\\__\\        \\:\\__\\        \\::/  /       \\::/  /   ");
        System.out.println("         \\/__/         \\/__/         \\/__/         \\/__/         \\/__/         \\/__/");
        System.out.println("Copyright 2024 by James Hagan");
    }
    public String enterName(Scanner myObj, int i){
        String name;
        System.out.print("Enter Username for player " + (i + 1) + ": ");
        name = myObj.nextLine();
        if (name.isEmpty()){
            return "Unknown Player " + (i + 1);
        }
        else {
            return name;
        }
    }
    public int enterScore(Scanner myObj){
        int score = 0;
        System.out.print("Enter score to play to: ");
        String temp = myObj.nextLine();
        if (temp.isEmpty()){
            score = 10000;
        }
        else {
            score = Integer.parseInt(temp);
        }
        if (score <= 0) {
            score = 10000;
        }
        return score;
    }
    public int enterNum(Scanner myObj){
        int score = 0;
        System.out.print("Enter Number of players: ");
        String temp = myObj.nextLine();
        if (temp.isEmpty()){
            score = 1;
        }
        else {
            score = Integer.parseInt(temp);
        }
        if (score <= 0) {
            score = 1;
        }
        return score;
    }
    private void singlePlayer (Scanner myObj, FarkleTurn turn, int scoreToPlay){
        Player player1 = new Player();
        player1.setName(enterName(myObj, 0));
        int temp = 0;
        System.out.println(player1.getName() + "'s turn");
        while (player1.getScore() < scoreToPlay) {
            temp = singlePlayerTurn(player1,turn,myObj);
            if (temp != 0) {
                if (temp == -1){
                    System.out.println("You quit. Final Score: " + player1.getScore());
                    return;
                }
                player1.setScore(temp);
            }
            System.out.println("Your turns score: " + player1.getScore());
        }
        displayFinish(player1.getScore());
    }
    private int singlePlayerTurn (Player player1, FarkleTurn turn, Scanner myObj){
        player1 = turn.runTurn(player1.getNumDice(), myObj, player1.getScore(), player1.getName());
        while (player1.getNumDice() > 0){
            player1 = turn.runTurn(player1.getNumDice(), myObj, player1.getScore(), player1.getName());
        }
        if (player1.getNumDice() == -10) {
            return -1;
        }
        return player1.getScore();
    }
    private void displayFinish(int score) {
        System.out.println("Congrats!");
        System.out.println("\\   \\  /   / /  __  \\  |  |  |  |    \\   \\  /  \\  /   / |  | |  \\ |  | |  | ");
        System.out.println(" \\   \\/   / |  |  |  | |  |  |  |     \\   \\/    \\/   /  |  | |   \\|  | |  | ");
        System.out.println("  \\_    _/  |  |  |  | |  |  |  |      \\            /   |  | |  . `  | |  | ");
        System.out.println("    |  |    |  `--'  | |  `--'  |       \\    /\\    /    |  | |  |\\   | |__| ");
        System.out.println("    |__|     \\______/   \\______/         \\__/  \\__/     |__| |__| \\__| (__)");
        System.out.println("Final Score: " + score);
    }
    private void multiPlayer (Scanner myObj, FarkleTurn turn, int scoreToPlay, int numPlayers){
        Boolean endGame = false;
        int indexOfEnder = -1;
        int indexOfWinner = 0;
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player());
            players.get(i).setName(enterName(myObj,i));
        }
        while (endGame.equals(false)){
            for (int i = 0; i < numPlayers; i++) {
                int temp = 0;
                System.out.println(players.get(i).getName() + "'s turn");
                temp = singlePlayerTurn(players.get(i),turn,myObj);
                if (temp != 0) {
                    if (temp == -1){
                        System.out.println("You quit. Final scores:");
                        for (int j = 0; j < numPlayers; j++){
                            System.out.println(players.get(j).getName() + ": " + players.get(j).getScore());
                        }
                        return;
                    }
                    players.get(i).setScore(temp);
                }
                System.out.println("Your turns score: " + players.get(i).getScore());
                if (players.get(i).getScore() >= scoreToPlay) {
                    endGame = true;
                    break;
                }
            }
            if (endGame.equals(true)) {
                break;
            }
            System.out.println("Turn over, end of turn scores:");
            for (int i = 0; i < numPlayers; i++){
                System.out.println(players.get(i).getName() + ": " + players.get(i).getScore());
            }
            System.out.println("");
        }
        indexOfEnder = findEnder(players,scoreToPlay,numPlayers);
        System.out.println("Score to play reached, starting final turn. Scores going in:");
        for (int i = 0; i < numPlayers; i++){
            if (i == indexOfEnder) {
                System.out.print("*");
            }
            System.out.print(players.get(i).getName() + ": " + players.get(i).getScore() + "\n");
        }
        System.out.println("");
        for (int i = 0; i < numPlayers; i++) {
            if (i == indexOfEnder) {
            }
            else {
                int temp = 0;
                System.out.println(players.get(i).getName() + "'s turn");
                temp = singlePlayerTurn(players.get(i), turn, myObj);
                if (temp != 0) {
                    if (temp == -1) {
                        System.out.println("You quit. Final scores:");
                        for (int j = 0; j < numPlayers; j++) {
                            System.out.println(players.get(j).getName() + ": " + players.get(j).getScore());
                        }
                        return;
                    }
                    players.get(i).setScore(temp);
                }
                System.out.println("Your turns score: " + players.get(i).getScore());
            }
        }
        System.out.println("Game over, final scores:");
        for (int i = 0; i < numPlayers; i++){
            System.out.println(players.get(i).getName() + ": " + players.get(i).getScore());
        }
        System.out.println("");
        indexOfWinner = findWinner(players,scoreToPlay,numPlayers);
        System.out.println(players.get(indexOfWinner).getName());
        displayFinish(players.get(indexOfWinner).getScore());
    }
    public int findEnder (ArrayList <Player> players, int scoreToPlay, int numPlayers) {
        for (int i = 0; i < numPlayers; i++){
            if (players.get(i).getScore() >= scoreToPlay) {
                return i;
            }
        }
        return 0;
    }
    public int findWinner(ArrayList <Player> players, int scoreToPlay, int numPlayers) {
        int maxIndex = 0;
        int max = scoreToPlay;
        for (int i = 0; i < numPlayers; i++){
            if (players.get(i).getScore() >= max) {
                max = players.get(i).getScore();
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
