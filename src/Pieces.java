import java.util.Scanner;
public class Pieces {
    //ajt des constantes pieces et destination ici
    public static boolean estPieceAlliee(char piece, char destination) {
        //piece allié si deux pions majuscules(noirs) ou minuscules(blancs)
        return destination != ' ' && ((Character.isUpperCase(piece) && Character.isUpperCase(destination))
                || (Character.isLowerCase(piece) && Character.isLowerCase(destination)));
    }

    public static boolean obstacle(char[][] echiquier, int ligneD, int colonneD, int ligneA, int colonneA) {
        int directionLigne = (ligneA > ligneD) ? 1 : (ligneA < ligneD) ? -1 : 0;
        int directionColonne = (colonneA > colonneD) ? 1 : (colonneA < colonneD) ? -1 : 0;

        int ligneCourante = ligneD + directionLigne;
        int colonneCourante = colonneD + directionColonne;

        // Parcours des cases entre la position actuelle et la destination
        while (ligneCourante != ligneA || colonneCourante != colonneA) {
            if (echiquier[ligneCourante][colonneCourante] != ' ') {
                return true; // Obstacle détecté
            }
            ligneCourante += directionLigne;
            colonneCourante += directionColonne;
        }
        return false; // Pas d'obstacle
    }

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


    public static void deplacementTour(char[][] echiquier, int[] coordonnees, int[] nouvellesCoordonnees) {
        char piece = echiquier[coordonnees[0]][coordonnees[1]];
        char destination = echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]];

        //occupée par une pièce alliée ?
        if (destination != ' ' && ((Character.isUpperCase(piece) && Character.isUpperCase(destination))
                || (Character.isLowerCase(piece) && Character.isLowerCase(destination)))) {
            System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            return;
        }

        //obstacle ?
        if (coordonnees[0] == nouvellesCoordonnees[0]) { // Déplacement horizontal
            int directionColonne = (nouvellesCoordonnees[1] > coordonnees[1]) ? 1 : -1;
            if (obstacle(echiquier, coordonnees[0], coordonnees[1], nouvellesCoordonnees[0], nouvellesCoordonnees[1])) {
                System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
                return;
            }
        } else if (coordonnees[1] == nouvellesCoordonnees[1]) { // Déplacement vertical
            int directionLigne = (nouvellesCoordonnees[0] > coordonnees[0]) ? 1 : -1;
            if (obstacle(echiquier, coordonnees[0], coordonnees[1], nouvellesCoordonnees[0], nouvellesCoordonnees[1])) {
                System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
                return;
            }
        } else { // Ni horizontal ni vertical
            System.out.println("Mouvement invalide pour une tour : uniquement horizontal ou vertical.");
            return;
        }
        echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]] = piece;
        echiquier[coordonnees[0]][coordonnees[1]] = ' ';
    }

    public static void deplacementFou(char[][] echiquier, int[] coordonnees, int[] nouvellesCoordonnees) {
        char piece = echiquier[coordonnees[0]][coordonnees[1]];
        char destination = echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]];

        //diagonale : soustraction positionLigne = soustraction positionColonne, valeur abs pour eviter les cas erreur
        //ligneArrivé - ligneDépart = colonneDépart - colonneArrivé
        if (Math.abs(nouvellesCoordonnees[0] - coordonnees[0]) != Math.abs(nouvellesCoordonnees[1] - coordonnees[1])) {
            System.out.println("Mouvement invalide : le fou ne peut se déplacer que sur une diagonale.");
            return;
        }

        // Vérification si la case de destination est occupée par une pièce alliée
        if (estPieceAlliee(piece, destination)) {
            System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            return;
        }

        // Vérification des obstacles (diagonalement)
        if (obstacle(echiquier, coordonnees[0], coordonnees[1], nouvellesCoordonnees[0], nouvellesCoordonnees[1])) {
            System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
            return;
        }
        echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]] = piece;
        echiquier[coordonnees[0]][coordonnees[1]] = ' ';
    }


    public static void deplacementCavalier(char[][] echiquier, int[] coordonnées, int[] nouvellesCoordonnées) {
        char piece = echiquier[coordonnées[0]][coordonnées[1]];
        char destination = echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]];

        if (estPieceAlliee(piece, destination)) {
            System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            return;
        }

        //mouvement en "L"
        int deltaLigne = Math.abs(nouvellesCoordonnées[0] - coordonnées[0]);
        int deltaColonne = Math.abs(nouvellesCoordonnées[1] - coordonnées[1]);

        if ((deltaLigne == 2 && deltaColonne == 1) || (deltaLigne == 1 && deltaColonne == 2)) {
            echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = piece;
            echiquier[coordonnées[0]][coordonnées[1]] = ' ';
        } else {
            System.out.println("Mouvement invalide : un cavalier se déplace en 'L'.");
        }
    }

    public static void deplacementReine(char[][] echiquier, int[] coordonnées, int[] nouvellesCoordonnées) {
        char piece = echiquier[coordonnées[0]][coordonnées[1]];
        char destination = echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]];

        if (estPieceAlliee(piece,destination)) {
            System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            return;
        }

        // Verticale/horizontale ==Tour
        if (coordonnées[0] == nouvellesCoordonnées[0] || coordonnées[1] == nouvellesCoordonnées[1]) {
            if (obstacle(echiquier, coordonnées[0], coordonnées[1], nouvellesCoordonnées[0], nouvellesCoordonnées[1])) {
                System.out.println("Mouvement impossible : une pièce bloque le chemin.");
                return;
            }
            deplacementTour(echiquier, coordonnées, nouvellesCoordonnées);
        }
        //diagonale == Fou
        else if (Math.abs(nouvellesCoordonnées[0] - coordonnées[0]) == Math.abs(nouvellesCoordonnées[1] - coordonnées[1])) {
            if (obstacle(echiquier, coordonnées[0], coordonnées[1], nouvellesCoordonnées[0], nouvellesCoordonnées[1])) {
                System.out.println("Mouvement impossible : une pièce bloque le chemin.");
                return;
            }
            deplacementFou(echiquier, coordonnées,nouvellesCoordonnées);
        }
        else {
            System.out.println("Mouvement invalide : la reine ne peut se déplacer que horizontalement, verticalement ou en diagonale.");
        }
    }

    public static void deplacementRoi(char[][] echiquier, int[] coordonnées, int[] nouvellesCoordonnées) {
        char piece = echiquier[coordonnées[0]][coordonnées[1]];
        char destination = echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]];

        if (estPieceAlliee(piece,destination)) {
            System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            return;
        }

        int deltaLigne = Math.abs(nouvellesCoordonnées[0] - coordonnées[0]);
        int deltaColonne = Math.abs(nouvellesCoordonnées[1] - coordonnées[1]);

        //se deplace d'une seule case peu importe la direction
        if (deltaLigne <= 1 && deltaColonne <= 1) {
            if (deltaLigne == 0 || deltaColonne == 0) {
                if (obstacle(echiquier, coordonnées[0], coordonnées[1], nouvellesCoordonnées[0], nouvellesCoordonnées[1])) {
                    System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
                    return;
                }
            }
            if (deltaLigne == deltaColonne) {
                if (obstacle(echiquier, coordonnées[0], coordonnées[1], nouvellesCoordonnées[0], nouvellesCoordonnées[1])) {
                    System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
                    return;
                }
            }
            /*deplacement qui le met en echec : pas de déplacement, faire une méthode boolean*/

            echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = piece;
            echiquier[coordonnées[0]][coordonnées[1]] = ' ';
        } else {
            System.out.println("Mouvement invalide : le roi se déplace d'une seule case dans toutes les directions.");
        }
    }

    public static void mangePieceAdverse(char[][] echiquier, int[] coordonnées, int[] nouvellesCoordonnées) {
        char piece = echiquier[coordonnées[0]][coordonnées[1]];
        char destination = echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]];

        if (destination != ' ' && !estPieceAlliee(piece,destination)) {
            System.out.println("Une pièce ennemie a été capturée !");
            echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = ' ';
        } else {
            System.out.println("Aucune pièce adverse à capturer.");
        }
    }

}


