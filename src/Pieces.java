import java.util.Scanner;

public class Pieces {

    public static class Pion {

        public static int[] mouvementBasiquePionBlanc(char[][] échiquier, int[] coordonnées) { // Méthode pour faire avancer le pion d'une case
            int anciennescoordonnées[] = {coordonnées[0], coordonnées[1]};
            int ligne = coordonnées[0];
            int colonne = coordonnées[1];

            if (échiquier[ligne - 1][colonne] != ' ') {
                System.out.println("Mouvement impossible, la case désirée est occupée.");
                return anciennescoordonnées;
            } else {
                échiquier[ligne - 1][colonne] = échiquier[ligne][colonne];
                échiquier[ligne][colonne] = ' ';
                coordonnées[0] = ligne;
                return coordonnées;
            }
        }

        public static int[] mouvementBasiquePionNoir(char[][] échiquier, int[] coordonnées) {
            int anciennescoordonnées[] = {coordonnées[0], coordonnées[1]};
            int ligne = coordonnées[0];
            int colonne = coordonnées[1];

            if (échiquier[ligne - 1][colonne] != ' ') {
                System.out.println("Mouvement impossible, la case désirée est occupée.");
                return anciennescoordonnées;
            } else {
                échiquier[ligne - 1][colonne] = échiquier[ligne][colonne];
                échiquier[ligne][colonne] = ' ';
                coordonnées[0] = ligne;
                return coordonnées;
            }
        }

    }
}

