/*
 Name: James Hagan
 Class: CPSC 224, Spring 2024
 Farkle 2
 Description: Runs a singleplayer multi-turned farkle
*/

package edu.gonzaga.Farkle;

/*
*  This is the main class for the Farkle project.
*  It really should just instantiate another class and run
*   a method of that other class.
*/

/** Main program class for launching Farkle program. */
public class Farkle {
    // This main is where your Farkle game starts execution for general use.
    public static void main(String[] args) {
        PlayFarkle game = new PlayFarkle();
        game.playGame();
    }
}
