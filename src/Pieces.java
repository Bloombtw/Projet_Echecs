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

    public static void deplacementPionNoir(char[][] echiquier, int[] coordonnées, int[] nouvellesCoordonnées) {
        int[] anciennesCoordonnées = {coordonnées[0], coordonnées[1]};
        int ecart = nouvellesCoordonnées[0] - coordonnées[0];
        boolean peutAvancerDeDeuxCases = (coordonnées[0] == 1);

        // pieces allié ?
        if (echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] != ' '
                && estPieceAlliee(echiquier[coordonnées[0]][coordonnées[1]], echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]])) {
            System.out.println("Mouvement impossible, la case désirée est occupée par une pièce alliée.");
            return;
        }

        // Capture en diagonale seuelement
        if (Math.abs(nouvellesCoordonnées[1] - coordonnées[1]) == 1
                && ecart == 1
                && echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] != ' '
                && !estPieceAlliee(echiquier[coordonnées[0]][coordonnées[1]], echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]])) {

            mangePieceAdverse(echiquier, coordonnées, nouvellesCoordonnées);
            echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = echiquier[coordonnées[0]][coordonnées[1]];
            echiquier[anciennesCoordonnées[0]][anciennesCoordonnées[1]] = ' ';
            System.out.println("Le pion noir capture une pièce ennemie.");
            return;
        }

        // Mouvement en arrière
        if (ecart < 0) {
            System.out.println("Mouvement impossible, un pion noir ne peut pas reculer.");
            return;
        }

        // plus d'une case et pas la premiere partie
        if (ecart > 1 && !peutAvancerDeDeuxCases) {
            System.out.println("Mouvement impossible, un pion noir ne peut pas avancer de plus d'une case.");
            return;
        }

        // Vérification des obstacles pour un déplacement de deux cases
        if (ecart == 2 && obstacle(echiquier, coordonnées[0], coordonnées[1], nouvellesCoordonnées[0], nouvellesCoordonnées[1])) {
            System.out.println("Mouvement impossible, il y a un obstacle sur le chemin.");
            return;
        }

        // Déplacement vers l'avant
        if (ecart == 1 && echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] == ' ') {
            echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = echiquier[coordonnées[0]][coordonnées[1]];
            echiquier[anciennesCoordonnées[0]][anciennesCoordonnées[1]] = ' ';
            System.out.println("Le pion noir avance.");
            return;
        }

        // Mouvement invalide
        System.out.println("Mouvement invalide pour un pion noir.");
    }

    public static void deplacementPionBlanc(char[][] echiquier, int[] coordonnées, int[] nouvellesCoordonnées) {
        int[] anciennesCoordonnées = {coordonnées[0], coordonnées[1]};
        int ecart = coordonnées[0] - nouvellesCoordonnées[0]; // Inverse pour les blancs
        boolean peutAvancerDeDeuxCases = (coordonnées[0] == 6); // Ligne initiale pour les blancs

        // Vérifier si une pièce alliée est sur la case cible
        if (echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] != ' '
                && estPieceAlliee(echiquier[coordonnées[0]][coordonnées[1]], echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]])) {
            System.out.println("Mouvement impossible, la case désirée est occupée par une pièce alliée.");
            return;
        }

        // Capture en diagonale
        if (Math.abs(nouvellesCoordonnées[1] - coordonnées[1]) == 1
                && ecart == 1
                && echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] != ' '
                && !estPieceAlliee(echiquier[coordonnées[0]][coordonnées[1]], echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]])) {
            mangePieceAdverse(echiquier, coordonnées, nouvellesCoordonnées);
            echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = echiquier[coordonnées[0]][coordonnées[1]];
            echiquier[anciennesCoordonnées[0]][anciennesCoordonnées[1]] = ' ';
            System.out.println("Le pion blanc capture une pièce ennemie.");
            return;
        }

        // Mouvement en arrière
        if (ecart < 0) {
            System.out.println("Mouvement impossible, un pion blanc ne peut pas reculer.");
            return;
        }

        // Déplacement de plus d'une case hors de la ligne initiale
        if (ecart > 1 && !peutAvancerDeDeuxCases) {
            System.out.println("Mouvement impossible, un pion blanc ne peut pas avancer de plus d'une case.");
            return;
        }

        // Vérification des obstacles pour un déplacement de deux cases
        if (ecart == 2 && obstacle(echiquier, coordonnées[0], coordonnées[1], nouvellesCoordonnées[0], nouvellesCoordonnées[1])) {
            System.out.println("Mouvement impossible, il y a un obstacle sur le chemin.");
            return;
        }

        // Déplacement simple vers l'avant
        if (ecart == 1 && echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] == ' ') {
            echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = echiquier[coordonnées[0]][coordonnées[1]];
            echiquier[anciennesCoordonnées[0]][anciennesCoordonnées[1]] = ' ';
            System.out.println("Le pion blanc avance.");
            return;
        }

        // Mouvement invalide
        System.out.println("Mouvement invalide pour un pion blanc.");
    }

    public static boolean deplacementTour(char[][] echiquier, int[] coordonnees, int[] nouvellesCoordonnees) {
        char piece = echiquier[coordonnees[0]][coordonnees[1]];
        char destination = echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]];

        //occupée par une pièce alliée ?
        if (estPieceAlliee(piece,destination)) {
            System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            return false;
        }
        //obstacle ?
        if (coordonnees[0] == nouvellesCoordonnees[0]) { // Déplacement horizontal
            int directionColonne = (nouvellesCoordonnees[1] > coordonnees[1]) ? 1 : -1;
            if (obstacle(echiquier, coordonnees[0], coordonnees[1], nouvellesCoordonnees[0], nouvellesCoordonnees[1])) {
                System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
                return false;
            }
        } else if (coordonnees[1] == nouvellesCoordonnees[1]) { // Déplacement vertical
            int directionLigne = (nouvellesCoordonnees[0] > coordonnees[0]) ? 1 : -1;
            if (obstacle(echiquier, coordonnees[0], coordonnees[1], nouvellesCoordonnees[0], nouvellesCoordonnees[1])) {
                System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
                return false;
            }
        } else { // Ni horizontal ni vertical
            System.out.println("Mouvement invalide pour une tour : uniquement horizontal ou vertical.");
            return false;
        }
        mangePieceAdverse(echiquier,coordonnees, nouvellesCoordonnees);
        echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]] = piece;
        echiquier[coordonnees[0]][coordonnees[1]] = ' ';
        return true;
    }

    public static boolean deplacementFou(char[][] echiquier, int[] coordonnees, int[] nouvellesCoordonnees) {
        char piece = echiquier[coordonnees[0]][coordonnees[1]];
        char destination = echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]];

        //diagonale : soustraction positionLigne = soustraction positionColonne, valeur abs pour eviter les cas erreur
        //ligneArrivé - ligneDépart = colonneDépart - colonneArrivé
        if (Math.abs(nouvellesCoordonnees[0] - coordonnees[0]) != Math.abs(nouvellesCoordonnees[1] - coordonnees[1])) {
            System.out.println("Mouvement invalide : le fou ne peut se déplacer que sur une diagonale.");
            return false;
        }

        // Vérification si la case de destination est occupée par une pièce alliée
        if (estPieceAlliee(piece, destination)) {
            System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            return false;
        }

        // Vérification des obstacles (diagonalement)
        if (obstacle(echiquier, coordonnees[0], coordonnees[1], nouvellesCoordonnees[0], nouvellesCoordonnees[1])) {
            System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
            return false;
        }
        mangePieceAdverse(echiquier,coordonnees, nouvellesCoordonnees);
        echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]] = piece;
        echiquier[coordonnees[0]][coordonnees[1]] = ' ';

        return true;
    }


    public static boolean deplacementCavalier(char[][] echiquier, int[] coordonnées, int[] nouvellesCoordonnées) {
        char piece = echiquier[coordonnées[0]][coordonnées[1]];
        char destination = echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]];

        if (estPieceAlliee(piece, destination)) {
            System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            return false;
        }
        mangePieceAdverse(echiquier,coordonnées, nouvellesCoordonnées);
        //mouvement en "L"
        int deltaLigne = Math.abs(nouvellesCoordonnées[0] - coordonnées[0]);
        int deltaColonne = Math.abs(nouvellesCoordonnées[1] - coordonnées[1]);
        if ((deltaLigne == 2 && deltaColonne == 1) || (deltaLigne == 1 && deltaColonne == 2)) {
            echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = piece;
            echiquier[coordonnées[0]][coordonnées[1]] = ' ';
        } else {
            System.out.println("Mouvement invalide : un cavalier se déplace en 'L'.");
            return false;
        }
        return true;

    }

    public static boolean deplacementReine(char[][] echiquier, int[] coordonnées, int[] nouvellesCoordonnées) {
        char piece = echiquier[coordonnées[0]][coordonnées[1]];
        char destination = echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]];

        if (estPieceAlliee(piece,destination)) {
            System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            return false;
        }
        // mangePieceAdverse(echiquier,coordonnées, nouvellesCoordonnées);
        // Verticale/horizontale ==Tour
        if (coordonnées[0] == nouvellesCoordonnées[0] || coordonnées[1] == nouvellesCoordonnées[1]) {
            if (obstacle(echiquier, coordonnées[0], coordonnées[1], nouvellesCoordonnées[0], nouvellesCoordonnées[1])) {
                System.out.println("Mouvement impossible : une pièce bloque le chemin.");
                return false;
            }
            mangePieceAdverse(echiquier, coordonnées, nouvellesCoordonnées);
            deplacementTour(echiquier, coordonnées, nouvellesCoordonnées);
        }
        //diagonale == Fou
        else if (Math.abs(nouvellesCoordonnées[0] - coordonnées[0]) == Math.abs(nouvellesCoordonnées[1] - coordonnées[1])) {
            if (obstacle(echiquier, coordonnées[0], coordonnées[1], nouvellesCoordonnées[0], nouvellesCoordonnées[1])) {
                System.out.println("Mouvement impossible : une pièce bloque le chemin.");
                return false;
            }
            mangePieceAdverse(echiquier, coordonnées, nouvellesCoordonnées);
            deplacementFou(echiquier, coordonnées,nouvellesCoordonnées);
            return true;
        }
        else {
            System.out.println("Mouvement invalide : la reine ne peut se déplacer que horizontalement, verticalement ou en diagonale.");
            return false;
        }
        return false;

    }

    public static boolean deplacementRoi(char[][] echiquier, int[] coordonnées, int[] nouvellesCoordonnées) {
        char piece = echiquier[coordonnées[0]][coordonnées[1]];
        char destination = echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]];

        if (estPieceAlliee(piece,destination)) {
            System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            return false;
        }

        int deltaLigne = Math.abs(nouvellesCoordonnées[0] - coordonnées[0]);
        int deltaColonne = Math.abs(nouvellesCoordonnées[1] - coordonnées[1]);

        //se deplace d'une seule case peu importe la direction
        if (deltaLigne <= 1 && deltaColonne <= 1) {
            if (deltaLigne == 0 || deltaColonne == 0) {
                if (obstacle(echiquier, coordonnées[0], coordonnées[1], nouvellesCoordonnées[0], nouvellesCoordonnées[1])) {
                    System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
                    return false;
                }
            }
            if (deltaLigne == deltaColonne) {
                if (obstacle(echiquier, coordonnées[0], coordonnées[1], nouvellesCoordonnées[0], nouvellesCoordonnées[1])) {
                    System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
                    return false;
                }
            }
            mangePieceAdverse(echiquier,coordonnées, nouvellesCoordonnées);
            /*deplacement qui le met en echec : pas de déplacement, faire une méthode boolean*/
            echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = piece;
            echiquier[coordonnées[0]][coordonnées[1]] = ' ';
            return true;
        } else {
            System.out.println("Mouvement invalide : le roi se déplace d'une seule case dans toutes les directions.");
            return false;
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


