import java.util.Scanner;

public class Pieces {

    public static class Pion {

        public static void deplacementPionNoir(char[][] échiquier, int[] coordonnées, int[] nouvellesCoordonnées) { // Méthode pour faire avancer le pion d'une case
            int anciennescoordonnées[] = {coordonnées[0], coordonnées[1]};
            int ecart = nouvellesCoordonnées[0] - coordonnées[0];
            boolean peutAvancerDeDeuxCases = false;
            if (coordonnées[0] == 1) {
                peutAvancerDeDeuxCases = true;
            }
            if (échiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] != ' ') {
                System.out.println("Mouvement impossible, la case désirée est occupée.");
                return;
            }
            else if (nouvellesCoordonnées[1] != coordonnées[1]) {
                System.out.println("Mouvement impossible, un pion ne peut avancer qu'en ligne.");
                return;
            }
            else if (ecart < 0) {
                System.out.println("Mouvement impossible, votre pion ne peut pas reculer.");
                return;
            }
            else if (ecart > 1 && peutAvancerDeDeuxCases == false) {
                System.out.println("Mouvement impossible, votre pion ne peut pas avancer de plus d'une case");
                return;
            }
            else if (ecart > 2 && peutAvancerDeDeuxCases == true) {
                System.out.println("Votre pion ne peut pas avancer de plus de deux cases lorsqu'il n'a jamais été bougé.");
                return;
            }
            else {
                échiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = échiquier[coordonnées[0]][coordonnées[1]];
                échiquier[anciennescoordonnées[0]][anciennescoordonnées[1]] = ' ';
            }
        }
    }



    //TOUR
    public static void deplacementTour(char[][] echiquier, int[] coordonnees, int[] nouvellesCoordonnees) {
        // Vérification est occupée par une pièce alliée
        if (echiquier[coordonnees[0]][coordonnees[1]] == echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]]) {
            System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            return;
        }
        // Vérification des obstacles sur le chemin
        if (coordonnees[0] == nouvellesCoordonnees[0]) { // Mouvement horizontal
            int direction = (nouvellesCoordonnees[1] > coordonnees[1]) ? 1 : -1;
            for (int i = coordonnees[1] + direction; i != nouvellesCoordonnees[1]; i += direction) {
                if (echiquier[coordonnees[0]][i] != ' ') {
                    System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
                    return;
                }
            }
        } else if (coordonnees[1] == nouvellesCoordonnees[1]) { // Mouvement vertical
            int direction = (nouvellesCoordonnees[0] > coordonnees[0]) ? 1 : -1;
            for (int i = coordonnees[0] + direction; i != nouvellesCoordonnees[0]; i += direction) {
                if (echiquier[i][coordonnees[1]] != ' ') {
                    System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
                    return;
                }
            }
        }
        // Déplacement de la tour
        echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]] = echiquier[coordonnees[0]][coordonnees[1]];
        echiquier[coordonnees[0]][coordonnees[1]] = ' ';
    }


    //FOU
    public static int[] deplacementFou(char[][] echiquier, int[] coordonnees, int nouvelleLigne, int nouvelleColonne) {
        int[] anciennesCoordonnees = {coordonnees[0], coordonnees[1]};
        int ligne =coordonnees[0];
        int colonne =coordonnees[1];

        //mouvement diagonale
        if (Math.abs(nouvelleLigne - ligne) != Math.abs(nouvelleColonne - colonne)) {
            System.out.println("Mouvement invalide : le fou ne peut se déplacer qu'en diagonale.");
            return anciennesCoordonnees;
        }
        // Vérification si la case de destination est occupée par une pièce alliée
        if (echiquier[ligne][colonne]== echiquier[nouvelleLigne][nouvelleColonne]
                && echiquier[nouvelleLigne][nouvelleColonne] != ' ') {
            System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            return anciennesCoordonnees;
        }
        // Déplacement de la pièce
        echiquier[nouvelleLigne][nouvelleColonne] = echiquier[ligne][colonne];
        echiquier[ligne][colonne] = ' ';
        ligne = nouvelleLigne;
        colonne = nouvelleColonne;

        return coordonnees;
    }

    //Cavalier
    public static int[] deplacementCavalier(char[][] echiquier, int[] coordonnees, int nouvelleLigne, int nouvelleColonne){
        int[] anciennesCoordonnees = {coordonnees[0], coordonnees[1]};
        int ligne =coordonnees[0];
        int colonne =coordonnees[1];


        //2 case horizontale ou verticale et 1 vers la droite ou la gauche
        // Déplacement de la pièce
        echiquier[nouvelleLigne][nouvelleColonne] = echiquier[ligne][colonne];
        echiquier[ligne][colonne] = ' ';
        ligne = nouvelleLigne;
        colonne = nouvelleColonne;

        return coordonnees;
    }
}

