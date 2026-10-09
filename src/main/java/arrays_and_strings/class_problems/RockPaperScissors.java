package arrays_and_strings.class_problems;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] moves = {"Rock", "Paper", "Scissors"};

        int totalRounds = 5;
        String[] playerMoves = new String[totalRounds];
        String[] computerMoves = new String[totalRounds];
        String[] roundResults = new String[totalRounds];

        int wins = 0;
        int losses = 0;
        int draws = 0;

        System.out.println("=== Rock-Paper-Scissors Simulator (5 Rounds) ===");

        for (int i = 0; i < totalRounds; i++) {
            System.out.print("Enter your move for Round " + (i + 1) + " (Rock, Paper, Scissors): ");
            String playerMove = scanner.hasNext() ? scanner.next().trim() : "Rock";
            String computerMove = moves[random.nextInt(moves.length)];

            String result = playRound(playerMove, computerMove);

            playerMoves[i] = playerMove;
            computerMoves[i] = computerMove;
            roundResults[i] = result;

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }

            System.out.println("Round " + (i + 1) + " - Player: " + playerMove + ", Computer: " + computerMove + " -> " + result);
        }

        System.out.println("\n----------------- Summary Table -----------------");
        System.out.printf("%-8s | %-12s | %-14s | %-14s%n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < totalRounds; i++) {
            System.out.printf("%-8d | %-12s | %-14s | %-14s%n", (i + 1), playerMoves[i], computerMoves[i], roundResults[i]);
        }
        System.out.println("-------------------------------------------------");

        double winPercentage = ((double) wins / totalRounds) * 100.0;
        System.out.printf("Final Summary (after %d rounds) -> Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n",
                totalRounds, wins, losses, draws, winPercentage);

        scanner.close();
    }
}
