import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;
public class Pieces {

    public static void nomDesPieces() { // Cette méthode n'est pas utilisée mais sert d'index pour savoir les valeurs des pièces

        // Déf des valeurs des pièces
        char pionBlanc = 'p';
        char cavalierBlanc ='c';
        char fouBlanc = 'f';
        char tourBlanc = 't';
        char dameBlanc = 'd';
        char roiBlanc = 'r';

        char pionNoir = 'P';
        char cavalierNoir = 'C';
        char fouNoir = 'F';
        char tourNoir = 'T';
        char dameNoir = 'D';
        char roiNoir = 'R';
    }


    // Toutes les méthodes de déplacement des pièces
    public static boolean deplacementPionBlanc(char[][] echiquier, int[] coordonnées, int[] nouvellesCoordonnées, boolean verification) {
        Scanner sc = new Scanner(System.in);
        int[] anciennesCoordonnées = {coordonnées[0], coordonnées[1]};
        int ecart = coordonnées[0] - nouvellesCoordonnées[0]; // Inverse pour les blancs
        boolean peutAvancerDeDeuxCases = (coordonnées[0] == 6); // Ligne initiale pour les blancs

        if (echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] != ' '
                && estPieceAlliee(echiquier[coordonnées[0]][coordonnées[1]], echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]])) {
            if (!verification) {
                System.out.println("Mouvement impossible, la case désirée est occupée par une pièce alliée.");
            }
            return false;
        }
        // Capture en diagonale seuelement
        if (Math.abs(nouvellesCoordonnées[1] - coordonnées[1]) == 1
                && ecart == 1
                && echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] != ' '
                && !estPieceAlliee(echiquier[coordonnées[0]][coordonnées[1]], echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]])) {

            if (verification) {
                mangePieceAdverse(echiquier, coordonnées, nouvellesCoordonnées, true);
            }
            else {
                mangePieceAdverse(echiquier, coordonnées, nouvellesCoordonnées, false);
            }
            echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = echiquier[coordonnées[0]][coordonnées[1]];
            echiquier[anciennesCoordonnées[0]][anciennesCoordonnées[1]] = ' ';
            promotionBlanc(echiquier, nouvellesCoordonnées, false);
            if (!verification) {
                return true;
            }

        }

        // Vérification des obstacles pour un déplacement de deux cases
        if (ecart == 2 && obstacle(echiquier, coordonnées[0], coordonnées[1], nouvellesCoordonnées[0], nouvellesCoordonnées[1])) {
            if (!verification) {
                System.out.println("Mouvement impossible, il y a un obstacle sur le chemin.");
            }
            return false;
        }
        else if (nouvellesCoordonnées[1] != coordonnées[1]) {
            if (!verification) {
                System.out.println("Mouvement impossible, un pion ne peut avancer qu'en ligne.");
            }
            return false;
        }
        else if (ecart < 0) {
            if (!verification) {
                System.out.println("Mouvement impossible, votre pion ne peut pas reculer.");
            }
            return false;
        }
        else if (ecart > 1 && peutAvancerDeDeuxCases == false) {
            if (!verification) {
                System.out.println("Mouvement impossible, votre pion ne peut pas avancer de plus d'une case");
            }
            return false;
        }
        else if (ecart > 2 && peutAvancerDeDeuxCases == true) {
            if (!verification) {
                System.out.println("Votre pion ne peut pas avancer de plus de deux cases lorsqu'il n'a jamais été bougé.");
            }
            return false;
        }
        else {
            echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = echiquier[coordonnées[0]][coordonnées[1]];
            echiquier[anciennesCoordonnées[0]][anciennesCoordonnées[1]] = ' ';
            promotionBlanc(echiquier, nouvellesCoordonnées, false);
            return true;
        }
    }
    public static boolean deplacementPionNoir(char[][] echiquier, int[] coordonnées, int[] nouvellesCoordonnées, boolean verification) {
        Scanner sc = new Scanner(System.in);
        int[] anciennesCoordonnées = {coordonnées[0], coordonnées[1]};
        int ecart = nouvellesCoordonnées[0] - coordonnées[0];
        boolean peutAvancerDeDeuxCases = (coordonnées[0] == 1);

        // pieces allié ?
        if (echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] != ' '
                && estPieceAlliee(echiquier[coordonnées[0]][coordonnées[1]], echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]])) {
            if (!verification) {
                System.out.println("Mouvement impossible, la case désirée est occupée par une pièce alliée.");
            }
            return false;
        }

        // Capture en diagonale seuelement
        if (Math.abs(nouvellesCoordonnées[1] - coordonnées[1]) == 1
                && ecart == 1
                && echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] != ' '
                && !estPieceAlliee(echiquier[coordonnées[0]][coordonnées[1]], echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]])) {

            if (verification) {
                mangePieceAdverse(echiquier, coordonnées, nouvellesCoordonnées, true);
            }
            else {
            mangePieceAdverse(echiquier, coordonnées, nouvellesCoordonnées, false);
            }
            echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = echiquier[coordonnées[0]][coordonnées[1]];
            echiquier[anciennesCoordonnées[0]][anciennesCoordonnées[1]] = ' ';
            promotionNoir(echiquier, nouvellesCoordonnées, false);
            if (!verification) {
               return true;
            }

        }

        // Vérification des obstacles pour un déplacement de deux cases
        if (ecart == 2 && obstacle(echiquier, coordonnées[0], coordonnées[1], nouvellesCoordonnées[0], nouvellesCoordonnées[1])) {
            if (!verification) {
                System.out.println("Mouvement impossible, il y a un obstacle sur le chemin.");
            }
            return false;
        }
        else if (nouvellesCoordonnées[1] != coordonnées[1]) {
            if (!verification) {
                System.out.println("Mouvement impossible, un pion ne peut avancer qu'en ligne.");
            }
            return false;
        }
        else if (ecart < 0) {
            if (!verification) {
                System.out.println("Mouvement impossible, votre pion ne peut pas reculer.");
            }
            return false;
        }
        else if (ecart > 1 && peutAvancerDeDeuxCases == false) {
            if (!verification) {
            System.out.println("Mouvement impossible, votre pion ne peut pas avancer de plus d'une case");
            }
            return false;
        }
        else if (ecart > 2 && peutAvancerDeDeuxCases == true) {
            if (!verification) {
                System.out.println("Votre pion ne peut pas avancer de plus de deux cases lorsqu'il n'a jamais été bougé.");
            }
            return false;
        }
        else {
            echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = echiquier[coordonnées[0]][coordonnées[1]];
            echiquier[anciennesCoordonnées[0]][anciennesCoordonnées[1]] = ' ';
            promotionNoir(echiquier, nouvellesCoordonnées, false);
            return true;
        }



    }
    public static boolean deplacementTour(char[][] echiquier, int[] coordonnees, int[] nouvellesCoordonnees, boolean verification) {
        char piece = echiquier[coordonnees[0]][coordonnees[1]];
        char destination = echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]];


        //occupée par une pièce alliée ?
        if (estPieceAlliee(piece,destination)) {
            if (!verification) {
                System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            }
            return false;
        }
        //obstacle ?
        if (coordonnees[0] == nouvellesCoordonnees[0]) { // Déplacement horizontal
            int directionColonne = (nouvellesCoordonnees[1] > coordonnees[1]) ? 1 : -1;
            if (obstacle(echiquier, coordonnees[0], coordonnees[1], nouvellesCoordonnees[0], nouvellesCoordonnees[1])) {
                if (!verification) {
                    System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
                }
                return false;
            }
        } else if (coordonnees[1] == nouvellesCoordonnees[1]) { // Déplacement vertical
            int directionLigne = (nouvellesCoordonnees[0] > coordonnees[0]) ? 1 : -1;
            if (obstacle(echiquier, coordonnees[0], coordonnees[1], nouvellesCoordonnees[0], nouvellesCoordonnees[1])) {
                if (!verification) {
                    System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
                }
                return false;
            }
        } else { // Ni horizontal ni vertical
            if (!verification) {
                System.out.println("Mouvement invalide pour une tour : uniquement horizontal ou vertical.");
            }
            return false;
        }
        if (verification) {
            mangePieceAdverse(echiquier, coordonnees, nouvellesCoordonnees, true);

        }
        else {
            mangePieceAdverse(echiquier,coordonnees, nouvellesCoordonnees, false);
        }
        echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]] = piece;
        echiquier[coordonnees[0]][coordonnees[1]] = ' ';
        return true;
    }
    public static boolean deplacementFou(char[][] echiquier, int[] coordonnees, int[] nouvellesCoordonnees, boolean verification) {
        char piece = echiquier[coordonnees[0]][coordonnees[1]];
        char destination = echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]];

        //diagonale : soustraction positionLigne = soustraction positionColonne, valeur abs pour eviter les cas erreur
        //ligneArrivé - ligneDépart = colonneDépart - colonneArrivé
        if (Math.abs(nouvellesCoordonnees[0] - coordonnees[0]) != Math.abs(nouvellesCoordonnees[1] - coordonnees[1])) {
            if (!verification) {
                System.out.println("Mouvement invalide : le fou ne peut se déplacer que sur une diagonale.");
            }
            return false;
        }

        // Vérification si la case de destination est occupée par une pièce alliée
        if (estPieceAlliee(piece, destination)) {
            if (!verification) {
                System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            }
            return false;
        }

        // Vérification des obstacles (diagonalement)
        if (obstacle(echiquier, coordonnees[0], coordonnees[1], nouvellesCoordonnees[0], nouvellesCoordonnees[1])) {
            if (!verification) {
                System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
            }
            return false;
        }
        if (verification) {
            mangePieceAdverse(echiquier,coordonnees, nouvellesCoordonnees, true);
        }
        else {
            mangePieceAdverse(echiquier,coordonnees, nouvellesCoordonnees, false);
        }
        echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]] = piece;
        echiquier[coordonnees[0]][coordonnees[1]] = ' ';

        return true;
    }
    public static boolean deplacementCavalier(char[][] echiquier, int[] coordonnées, int[] nouvellesCoordonnées, boolean verification) {
        char piece = echiquier[coordonnées[0]][coordonnées[1]];
        char destination = echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]];

        if (estPieceAlliee(piece, destination)) {
            if (!verification) {
                System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            }
            return false;
        }
        if (verification) {
            mangePieceAdverse(echiquier,coordonnées, nouvellesCoordonnées, true);
        }
        else {
            mangePieceAdverse(echiquier,coordonnées, nouvellesCoordonnées, false);
        }

        //mouvement en "L"
        int deltaLigne = Math.abs(nouvellesCoordonnées[0] - coordonnées[0]);
        int deltaColonne = Math.abs(nouvellesCoordonnées[1] - coordonnées[1]);
        if ((deltaLigne == 2 && deltaColonne == 1) || (deltaLigne == 1 && deltaColonne == 2)) {
            echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = piece;
            echiquier[coordonnées[0]][coordonnées[1]] = ' ';
        } else {
            if (!verification) {
                System.out.println("Mouvement invalide : un cavalier se déplace en 'L'.");
            }
            return false;
        }
        return true;

    }
    public static boolean deplacementReine(char[][] echiquier, int[] coordonnées, int[] nouvellesCoordonnées, boolean verification) {
        char piece = echiquier[coordonnées[0]][coordonnées[1]];
        char destination = echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]];

        if (estPieceAlliee(piece, destination)) {
            if (!verification) {
                System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            }
            return false;
        }

        // Verticale/horizontale == Tour
        if (coordonnées[0] == nouvellesCoordonnées[0] || coordonnées[1] == nouvellesCoordonnées[1]) {
            if (obstacle(echiquier, coordonnées[0], coordonnées[1], nouvellesCoordonnées[0], nouvellesCoordonnées[1])) {
                if (!verification) {
                    System.out.println("Mouvement impossible : une pièce bloque le chemin.");
                }
                return false;
            }

            // Gestion de la capture, avec la prise en compte de la vérification
            if (verification) {
                mangePieceAdverse(echiquier, coordonnées, nouvellesCoordonnées, true);  // Sans affichage, vérification uniquement
            } else {
                mangePieceAdverse(echiquier, coordonnées, nouvellesCoordonnées, false); // Affichage et gestion des captures
            }

            // Déplacement de la Reine comme Tour
            deplacementTour(echiquier, coordonnées, nouvellesCoordonnées,true);
            return true;
        }

        // Diagonale == Fou
        else if (Math.abs(nouvellesCoordonnées[0] - coordonnées[0]) == Math.abs(nouvellesCoordonnées[1] - coordonnées[1])) {
            if (obstacle(echiquier, coordonnées[0], coordonnées[1], nouvellesCoordonnées[0], nouvellesCoordonnées[1])) {
                if (!verification) {
                    System.out.println("Mouvement impossible : une pièce bloque le chemin.");
                }
                return false;
            }

            // Gestion de la capture, avec la prise en compte de la vérification
            if (verification) {
                mangePieceAdverse(echiquier, coordonnées, nouvellesCoordonnées, true);  // Sans affichage, vérification uniquement
            } else {
                mangePieceAdverse(echiquier, coordonnées, nouvellesCoordonnées, false); // Affichage et gestion des captures
            }

            // Déplacement de la Reine comme Fou
            deplacementFou(echiquier, coordonnées, nouvellesCoordonnées,true);
            return true;
        }

        // Si le mouvement n'est pas valide
        else {
            if (!verification) {
                System.out.println("Mouvement invalide : la reine ne peut se déplacer que horizontalement, verticalement ou en diagonale.");
            }
            return false;
        }
    }


    private static final Scanner sc = new Scanner(System.in); // Déclaré comme membre statique

    public static boolean deplacementRoi(char[][] echiquier, int[] coordonnees, int[] nouvellesCoordonnees, boolean verification) {
        char piece = echiquier[coordonnees[0]][coordonnees[1]];
        char destination = echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]];

        if (estPieceAlliee(piece, destination)) {
            if (!verification) {
                System.out.println("Mouvement impossible : une pièce alliée occupe la case de destination.");
            }
            return false;
        }

        int deltaLigne = Math.abs(nouvellesCoordonnees[0] - coordonnees[0]);
        int deltaColonne = Math.abs(nouvellesCoordonnees[1] - coordonnees[1]);

        if (deltaLigne <= 1 && deltaColonne <= 1) {
            if (deltaLigne == 0 || deltaColonne == 0 || deltaLigne == deltaColonne) {
                if (obstacle(echiquier, coordonnees[0], coordonnees[1], nouvellesCoordonnees[0], nouvellesCoordonnees[1])) {
                    if (!verification) {
                        System.out.println("Mouvement impossible : obstacle détecté sur le chemin.");
                    }
                    return false;
                }
            }
            mangePieceAdverse(echiquier, coordonnees, nouvellesCoordonnees, verification);
            echiquier[nouvellesCoordonnees[0]][nouvellesCoordonnees[1]] = piece;
            echiquier[coordonnees[0]][coordonnees[1]] = ' ';

            // Demander le roque après le déplacement
            if (!verification) {
                if (demanderRoque(echiquier)) {
                    return true;  // Si le roque a été effectué, on retourne true
                }
            }
            return true;  // Si le roque n'a pas été effectué, on retourne true pour signifier que le déplacement est valide
        } else {
            if (!verification) {
                System.out.println("Mouvement invalide : le roi se déplace d'une seule case dans toutes les directions.");
            }
            return false;
        }
    }

    public static int[] coordonneesRoiBlanc(char[][] echiquier) {
        int[] coordonneesRoi = new int[2];
        for (int i = 0; i < echiquier.length; i++) {
            for (int j = 0; j < echiquier[0].length; j++) {
                if (echiquier[i][j] == 'r') {
                    coordonneesRoi[0] = i;
                    coordonneesRoi[1] = j;
                    return coordonneesRoi;
                }
            }
        }
        return null;
    }

    public static int[] coordonneesRoiNoir(char[][] echiquier) {
        int[] coordonneesRoi = new int[2];
        for (int i = 0; i < echiquier.length; i++) {
            for (int j = 0; j < echiquier[i].length; j++) {
                if (echiquier[i][j] == 'R') {
                    coordonneesRoi[0] = i;
                    coordonneesRoi[1] = j;
                    return coordonneesRoi;
                }
            }
        }
        return null;
    }

    public static int[] estAttaqueBlanc(char[][] echiquier, int[] coordonneesPieceAttaquee) {
        boolean verification = true;
        char[][] echiquierDeTest = Plateau.copieEchiquier(echiquier);
        int[] resultat = {0, -1, -1}; // {enEchec, i, j}

        for (int i = 0; i < echiquier.length; i++) {
            for (int j = 0; j < echiquier[i].length; j++) {
                if (Character.isUpperCase(echiquier[i][j])) {
                    int[] positionPiece = {i, j};
                    boolean menace = false;

                    switch (echiquier[i][j]) {
                        case 'T' :
                            menace = Pieces.deplacementTour(echiquierDeTest, positionPiece, coordonneesPieceAttaquee, verification);
                            break;
                        case 'F' :
                            menace = Pieces.deplacementFou(echiquierDeTest, positionPiece, coordonneesPieceAttaquee, verification);
                            break;
                        case 'P' :
                            menace = Pieces.deplacementPionNoir(echiquierDeTest, positionPiece, coordonneesPieceAttaquee, verification);
                            break;
                        case 'D' :
                            menace = Pieces.deplacementReine(echiquierDeTest, positionPiece, coordonneesPieceAttaquee, verification);
                            break;
                        case 'C' :
                            menace = Pieces.deplacementCavalier(echiquierDeTest, positionPiece, coordonneesPieceAttaquee, verification);
                            break;
                        default :
                            break;
                    }

                    if (menace) {
                        resultat[0] = 1; // est attaqué
                        resultat[1] = i; // Coordonnée i de l'attaquant
                        resultat[2] = j; // Coordonnée j de l'attaquant
                        return resultat;
                    }
                }
            }
        }

        return resultat; // Pas d'attaque, reste {0, -1, -1}
    }

    public static int[] estAttaqueNoir(char[][] echiquier, int[] coordonneesPieceAttaquee) {
        boolean verification = true;
        char[][] echiquierDeTest = Plateau.copieEchiquier(echiquier);
        int[] resultat = {0, -1, -1}; // {enEchec, i, j}

        for (int i = 0; i < echiquier.length; i++) {
            for (int j = 0; j < echiquier[i].length; j++) {
                if (Character.isLowerCase(echiquier[i][j])) {
                    int[] positionPiece = {i, j};
                    boolean menace = false;

                    switch (echiquier[i][j]) {
                        case 't' :
                            menace = Pieces.deplacementTour(echiquierDeTest, positionPiece, coordonneesPieceAttaquee, verification);
                            break;
                        case 'f' :
                            menace = Pieces.deplacementFou(echiquierDeTest, positionPiece, coordonneesPieceAttaquee, verification);
                            break;
                        case 'p' :
                            menace = Pieces.deplacementPionBlanc(echiquierDeTest, positionPiece, coordonneesPieceAttaquee, verification);
                            break;
                        case 'd' :
                            menace = Pieces.deplacementReine(echiquierDeTest, positionPiece, coordonneesPieceAttaquee, verification);
                            break;
                        case 'c' :
                            menace = Pieces.deplacementCavalier(echiquierDeTest, positionPiece, coordonneesPieceAttaquee, verification);
                            break;
                        default :
                            break;
                    }

                    if (menace) {
                        resultat[0] = 1; // est attaqué
                        resultat[1] = i; // Coordonnée i de l'attaquant
                        resultat[2] = j; // Coordonnée j de l'attaquant
                        return resultat;
                    }
                }
            }
        }

        return resultat; // Pas d'attaque, reste {0, -1, -1}
    }

    public static boolean estEnMatBlanc(char[][] echiquier, int[] coordonneesRoi) {


        int[] infoSurEchec = estAttaqueBlanc(echiquier, coordonneesRoi);

        int[] coordonneesAttaquant = { infoSurEchec[1], infoSurEchec[2] }; // On a la position X et Y de l'attaquant
        boolean menace = false;
        /* Schéma illustrant la position des variables dessous avec coo[0] étant la ligne et coo[1] la colonne
        Ces variables représentent les 8 cases autours du roi.
        -------------
        | 1 | 2 | 3 |
        -------------
        | 8 | R | 4 |
        -------------
        | 7 | 6 | 5 |
        -------------
         */
        int[] position1 = {coordonneesRoi[0]-1, coordonneesRoi[1]-1 };
        if (position1[0] < 0) {
            position1[0] = coordonneesRoi[0];
        }
        if (position1[1] < 0) {
            position1[1] = coordonneesRoi[1];
        }
        int[] position2 = {coordonneesRoi[0]-1, coordonneesRoi[1]   };
        if (position2[0] < 0) {
            position2[0] = coordonneesRoi[0];
        }
        int[] position3 = {coordonneesRoi[0]-1, coordonneesRoi[1]+1 };
        if (position3[0] < 0) {
            position3[0] = coordonneesRoi[0];
        }
        if (position3[1] > 8) {
            position3[1] = coordonneesRoi[1];
        }
        int[] position4 = {coordonneesRoi[0]  , coordonneesRoi[1]+1 };
        if (position4[1] > 8) {
            position4[1] = coordonneesRoi[1];
        }
        int[] position5 = {coordonneesRoi[0]+1, coordonneesRoi[1]+1 };
        if (position5[0] > 8) {
            position5[0] = coordonneesRoi[0];
        }
        if (position5[1] > 8) {
            position5[1] = coordonneesRoi[0];
        }
        int[] position6 = {coordonneesRoi[0]+1, coordonneesRoi[1]   };
        if (position6[0] > 8) {
            position6[0] = coordonneesRoi[0];
        }
        int[] position7 = {coordonneesRoi[0]+1, coordonneesRoi[1]-1 };
        if (position7[0] > 8) {
            position7[0] = coordonneesRoi[0];
        }
        if (position7[1] < 0) {
            position7[1] = coordonneesRoi[1];
        }
        int[] position8 = {coordonneesRoi[0]  , coordonneesRoi[1]-1 };
        if (position8[1] < 0) {
            position8[1] = coordonneesRoi[1];
        }



        if (deplacementRoi(echiquier, coordonneesRoi, position1, true) &&  // Regarde si toutes les cases autours du roi sont en échec, si une n'est pas en échec
                deplacementRoi(echiquier, coordonneesRoi, position2, true) &&  // cela signifie qu'il peut bouger donc pas d'échec et mat
                deplacementRoi(echiquier, coordonneesRoi, position3, true) &&
                deplacementRoi(echiquier, coordonneesRoi, position4, true) &&
                deplacementRoi(echiquier, coordonneesRoi, position5, true) &&
                deplacementRoi(echiquier, coordonneesRoi, position6, true) &&
                deplacementRoi(echiquier, coordonneesRoi, position7, true) &&
                deplacementRoi(echiquier, coordonneesRoi, position8, true)) {
            return false;
        }

    int[] infoSurAttaquant = estAttaqueNoir(echiquier, coordonneesAttaquant); // Regarde si l'attaquant peut être mangé, si il peut etre mangé alors on retourne faux
        if (infoSurAttaquant[0] == 1) {                                       // car pas d'échec et mat (on peut manger l'attaquant)
            return false;
        }

    if (!(echecEstImparable(echiquier, coordonneesRoi, coordonneesAttaquant))) {
        return false;
    }


    return menace;
    }

    public static boolean estEnMatNoir(char[][] echiquier, int[] coordonneesRoi) {
        int[] infoSurEchec = estAttaqueNoir(echiquier, coordonneesRoi);
        if (infoSurEchec[0] != 1) { // Si n'est pas en échec renvoie faux
            return false;
        }

        int[] coordonneesAttaquant = { infoSurEchec[1], infoSurEchec[2] }; // On a la position X et Y de l'attaquant
        boolean menace = false;
        /* Schéma illustrant la position des variables dessous avec coo[0] étant la ligne et coo[1] la colonne
        Ces variables représentent les 8 cases autours du roi.
        -------------
        | 1 | 2 | 3 |
        -------------
        | 8 | R | 4 |
        -------------
        | 7 | 6 | 5 |
        -------------
         */
        int[] position1 = {coordonneesRoi[0]-1, coordonneesRoi[1]-1 };
        if (position1[0] < 0) {
            position1[0] = coordonneesRoi[0];
        }
        if (position1[1] < 0) {
            position1[1] = coordonneesRoi[1];
        }
        int[] position2 = {coordonneesRoi[0]-1, coordonneesRoi[1]   };
        if (position2[0] < 0) {
            position2[0] = coordonneesRoi[0];
        }
        int[] position3 = {coordonneesRoi[0]-1, coordonneesRoi[1]+1 };
        if (position3[0] < 0) {
            position3[0] = coordonneesRoi[0];
        }
        if (position3[1] > 8) {
            position3[1] = coordonneesRoi[1];
        }
        int[] position4 = {coordonneesRoi[0]  , coordonneesRoi[1]+1 };
        if (position4[1] > 8) {
            position4[1] = coordonneesRoi[1];
        }
        int[] position5 = {coordonneesRoi[0]+1, coordonneesRoi[1]+1 };
        if (position5[0] > 8) {
            position5[0] = coordonneesRoi[0];
        }
        if (position5[1] > 8) {
            position5[1] = coordonneesRoi[0];
        }
        int[] position6 = {coordonneesRoi[0]+1, coordonneesRoi[1]   };
        if (position6[0] > 8) {
            position6[0] = coordonneesRoi[0];
        }
        int[] position7 = {coordonneesRoi[0]+1, coordonneesRoi[1]-1 };
        if (position7[0] > 8) {
            position7[0] = coordonneesRoi[0];
        }
        if (position7[1] < 0) {
            position7[1] = coordonneesRoi[1];
        }
        int[] position8 = {coordonneesRoi[0]  , coordonneesRoi[1]-1 };
        if (position8[1] < 0) {
            position8[1] = coordonneesRoi[1];
        }


        if (deplacementRoi(echiquier, coordonneesRoi, position1, true) &&  // Regarde si toutes les cases autours du roi sont en échec, si une n'est pas en échec
                deplacementRoi(echiquier, coordonneesRoi, position2, true) &&  // cela signifie qu'il peut bouger donc pas d'échec et mat
                deplacementRoi(echiquier, coordonneesRoi, position3, true) &&
                deplacementRoi(echiquier, coordonneesRoi, position4, true) &&
                deplacementRoi(echiquier, coordonneesRoi, position5, true) &&
                deplacementRoi(echiquier, coordonneesRoi, position6, true) &&
                deplacementRoi(echiquier, coordonneesRoi, position7, true) &&
                deplacementRoi(echiquier, coordonneesRoi, position8, true)) {
            return false;
        }

        int[] infoSurAttaquant = estAttaqueBlanc(echiquier, coordonneesAttaquant); // Regarde si l'attaquant peut être mangé, si il peut etre mangé alors on retourne faux
        if (infoSurAttaquant[0] == 1) {                                       // car pas d'échec et mat (on peut manger l'attaquant)
            return false;
        }

        if (!(echecEstImparable(echiquier, coordonneesRoi, coordonneesAttaquant))) {
            return false;
        }

        menace = true;
        return menace;
    }

    public static List<int[]> casesEntreAttaquantEtRoi(char[][] echiquier, int[] coordonneesRoi, int[] coordonneesAttaquant) {
        char attaquant = echiquier[coordonneesAttaquant[0]][coordonneesAttaquant[1]];
        List<int[]> casesEntre = new ArrayList<>();

        int xRoi = coordonneesRoi[0];
        int yRoi = coordonneesRoi[1];
        int xAttaquant = coordonneesAttaquant[0];
        int yAttaquant = coordonneesAttaquant[1];

        // Dans le cas où c'est un cavalier, il attaque à travers, donc il n'y a pas de cases entre
        if (attaquant == 'C' || attaquant == 'c') {
            return casesEntre; // Retourne la liste vide pour le cavalier
        }

        // Déterminer la direction de l'attaque
        if (xRoi == xAttaquant) {
            // Attaque verticale
            int direction = (yRoi > yAttaquant) ? 1 : -1;
            for (int i = yAttaquant + direction; i != yRoi; i += direction) {
                casesEntre.add(new int[] {xAttaquant, i});
            }
        } else if (yRoi == yAttaquant) {
            // Attaque horizontale
            int direction = (xRoi > xAttaquant) ? 1 : -1;
            for (int i = xAttaquant + direction; i != xRoi; i += direction) {
                casesEntre.add(new int[] {i, yAttaquant});
            }

        } else if (Math.abs(xRoi - xAttaquant) == Math.abs(yRoi - yAttaquant)) {
            // Attaque diagonale
            int directionX = (xRoi > xAttaquant) ? 1 : -1;
            int directionY = (yRoi > yAttaquant) ? 1 : -1;

            int i = xAttaquant + directionX;
            int j = yAttaquant + directionY;
            while (i != xRoi && j != yRoi) {
                casesEntre.add(new int[] {i, j});
                i += directionX;
                j += directionY;
            }
        }

        return casesEntre; // Retourne la liste des cases entre l'attaquant et le roi
    }

    public static boolean echecEstImparable(char[][] echiquier, int[] coordonneesRoi, int[] coordonneesAttaquant) {
            List<int[]> casesEntre = casesEntreAttaquantEtRoi(echiquier, coordonneesRoi, coordonneesAttaquant);

            if (casesEntre.size() == 0) {
                return true;
            } else {
                for (int indiceDeLaListe = 0; indiceDeLaListe < casesEntre.size(); indiceDeLaListe++) {
                    for (int i = 0; i < echiquier.length; i++) {
                        for (int j = 0; j < echiquier[i].length; j++) {
                            char piece = echiquier[i][j];
                            int[] coordonnées = new int[]{i, j};
                            int[] nouvellesCoordonnées = casesEntre.get(indiceDeLaListe);
                            switch (piece) {
                                case ' ':
                                    break;

                                case 'p':
                                    if (Pieces.deplacementPionBlanc(echiquier, coordonnées, nouvellesCoordonnées, true)) {
                                        return false;
                                    }
                                    break;

                                case 'P' :
                                    if (Pieces.deplacementPionNoir(echiquier, coordonnées, nouvellesCoordonnées, true)) {
                                        return false;
                                    }
                                    break;

                                case 'f':
                                case 'F':
                                    if (Pieces.deplacementFou(echiquier,coordonnées,nouvellesCoordonnées, true)) {
                                        return false;
                                    }
                                    break;

                                case 't':
                                case 'T':
                                    if (Pieces.deplacementTour(echiquier,coordonnées,nouvellesCoordonnées, true)) {
                                        return false;
                                    }
                                    break;

                                case 'd':
                                case 'D':
                                    if (Pieces.deplacementReine(echiquier,coordonnées,nouvellesCoordonnées, true)) {
                                        return false;
                                    }
                                    break;
                            }
                        }
                    }
                }
                return true;
            }
        }

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

    private static int piecesNoiresMangees = 0;
    private static int piecesBlanchesMangees = 0;

    public static void afficherCompteurs() {
        System.out.println("                                                                                                                Pièces noires mangées : " + piecesNoiresMangees);
        System.out.println("                                                                                                                Pièces blanches mangées : " + piecesBlanchesMangees);
    }


    public static boolean mangePieceAdverse(char[][] echiquier, int[] coordonnées, int[] nouvellesCoordonnées,boolean verification) {
        char piece = echiquier[coordonnées[0]][coordonnées[1]];
        char destination = echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]];


        if (destination != ' ' && !estPieceAlliee(piece, destination)) {
            if (!verification) {
                if (Character.isUpperCase(destination)) {
                    piecesNoiresMangees++;
                } else {
                    piecesBlanchesMangees++;
                }
                afficherCompteurs();
            }
            echiquier[nouvellesCoordonnées[0]][nouvellesCoordonnées[1]] = ' ';
            return true;
        }

        return false;
    }
    public static void promotionBlanc(char[][] echiquier, int[] coordonnées, boolean verification) {
        Scanner sc = new Scanner(System.in);
        if (coordonnées[0] == 0) {
            if (!verification) {
                System.out.println("Votre pion vient d'arriver à la 8ème rangée. Comment voulez-vous le transformer ? "
                        + "Tapez 1 pour une dame, 2 pour une tour, 3 pour un cavalier et 4 pour un fou.");
            }
            int transformationPion = sc.nextInt();
            while (transformationPion < 1 || transformationPion > 4) {
                if (!verification) {
                    System.out.println("Saisie incorrecte. Veuillez reessayer : ");
                }
                transformationPion = sc.nextInt();
                }
            switch (transformationPion) {
                case 1:
                    echiquier[coordonnées[0]][coordonnées[1]] = 'd';
                    break;
                case 2:
                    echiquier[coordonnées[0]][coordonnées[1]] = 't';
                    break;
                case 3:
                    echiquier[coordonnées[0]][coordonnées[1]] = 'c';
                    break;
                case 4:
                    echiquier[coordonnées[0]][coordonnées[1]] = 'f';
                    break;
            }
        }
    }

    public static void promotionNoir(char[][] echiquier, int[] coordonnées, boolean verification) {
        Scanner sc = new Scanner(System.in);
        if (coordonnées[0] == 7) {
            if (!verification) {
                System.out.println("Votre pion vient d'arriver à la 8ème rangée. Comment voulez-vous le transformer ? "
                        + "Tapez 1 pour une dame, 2 pour une tour, 3 pour un cavalier et 4 pour un fou.");
            }
            String saisie = sc.nextLine();
            while (saisie.length() != 1 || saisie.charAt(0) < '1' || saisie.charAt(0) > '4') {
                if (!verification) {
                    System.out.println("Saisie incorrecte. Veuillez reessayer : ");
                }
                saisie = sc.nextLine();
            }
            char transformationPion = saisie.charAt(0);
            switch (transformationPion) {
                case '1':
                    echiquier[coordonnées[0]][coordonnées[1]] = 'D';
                    break;
                case '2':
                    echiquier[coordonnées[0]][coordonnées[1]] = 'T';
                    break;
                case '3':
                    echiquier[coordonnées[0]][coordonnées[1]] = 'C';
                    break;
                case '4':
                    echiquier[coordonnées[0]][coordonnées[1]] = 'F';
                    break;
            }
        }
    }

    public static boolean obstacleRoque(char[][] echiquier, int ligne, int colonneDebut, int colonneFin) {
        // Vérifier la direction du déplacement (droit ou gauche)
        int directionColonne = (colonneDebut > colonneFin) ? -1 : 1; // Si colonneDebut > colonneFin, on se déplace vers la gauche

        // Parcours des cases entre colonneDebut et colonneFin, incluant ces deux positions
        int colonneCourante = colonneDebut;

        while (colonneCourante != colonneFin) {
            if (echiquier[ligne][colonneCourante] != ' ') {
                return true; // Obstacle détecté
            }
            colonneCourante += directionColonne; // Déplacer vers la gauche ou la droite en fonction de la direction
        }

        // Vérifier également la dernière case (colonneFin)
        if (echiquier[ligne][colonneFin] != ' ') {
            return true; // Obstacle détecté à la colonne de fin
        }

        return false; // Pas d'obstacle
    }
    private static boolean demanderRoque(char[][] echiquier) {
        System.out.println("Voulez-vous effectuer un roque ? Tapez 'OUI' pour confirmer ou 'NON' pour jouer normalement.");
        String roqueChoix = sc.nextLine();

        while (!roqueChoix.equalsIgnoreCase("OUI") && !roqueChoix.equalsIgnoreCase("NON")) {
            System.out.println("Entrée invalide. Tapez 'OUI' ou 'NON'.");
            roqueChoix = sc.nextLine();
        }

        if (roqueChoix.equalsIgnoreCase("OUI")) {
            return choisirTypeRoque(echiquier);
        }
        return false;
    }

    private static boolean choisirTypeRoque(char[][] echiquier) {
        String typeRoque;
        do {
            System.out.println("Souhaitez-vous un grand roque (Tapez 'GRAND') ou un petit roque (Tapez 'PETIT') ?");
            typeRoque = sc.nextLine();
        } while (!typeRoque.equalsIgnoreCase("GRAND") && !typeRoque.equalsIgnoreCase("PETIT"));

        if (typeRoque.equalsIgnoreCase("GRAND")) {
            if (grandRoque(echiquier)) {
                return true;
            } else {
                System.out.println("Impossible d'effectuer le grand roque.");
            }
        } else {
            if (petitRoque(echiquier)) {
                return true;
            } else {
                System.out.println("Impossible d'effectuer le petit roque.");
            }
        }
        return false;
    }

    public static boolean petitRoque(char[][] echiquier) {
        int colonneRoi = 4; // Colonne initiale du roi
        int colonneTour = 7; // Tour située à la colonne 7 pour le petit roque
        int direction = 1;   // Direction du déplacement du roi pour le petit roque

        // Déterminer la ligne où les pièces se trouvent
        int ligne = (echiquier[0][colonneRoi] == 'R' || echiquier[0][colonneTour] == 'T') ? 0 :
                (echiquier[7][colonneRoi] == 'r' || echiquier[7][colonneTour] == 't') ? 7 : -1;

        if (ligne == -1) {
            System.out.println("Erreur : le roi ou la tour n'est pas correctement positionné(e).");
            return false;
        }

        char roi = echiquier[ligne][colonneRoi];
        char tour = echiquier[ligne][colonneTour];


        if (ligne == 0) {
            // Pour la ligne 0 (noirs), vérification des pièces majuscules
            if (roi != 'R' || tour != 'T') {
                System.out.println("Le roi ou la tour ne sont pas en position pour le petit roque (noirs, ligne 0).");
                return false;
            }
        } else if (ligne == 7) {
            // Pour la ligne 7 (blancs), vérification des pièces minuscules
            if (roi != 'r' || tour != 't') {
                System.out.println("Le roi ou la tour ne sont pas en position pour le petit roque (blancs, ligne 7).");
                return false;
            }
        }

        // Vérifier si le chemin est dégagé pour le petit roque
        int colonneDebut = 5; // La colonne juste après le roi
        int colonneFin = 6;   // La colonne juste avant la tour
        if (obstacleRoque(echiquier, ligne, colonneDebut, colonneFin)) {
            System.out.println("Le chemin est bloqué pour le petit roque.");
            return false;
        }

        // Effectuer le petit roque
        echiquier[ligne][colonneRoi + 2 * direction] = roi;
        echiquier[ligne][colonneRoi] = ' ';
        echiquier[ligne][colonneRoi + direction] = tour;
        echiquier[ligne][colonneTour] = ' ';
        return true;
    }

    public static boolean grandRoque(char[][] echiquier) {
        int colonneRoi = 4; // Colonne initiale du roi
        int colonneTour = 0; // Tour située à la colonne 0 pour le grand roque
        int direction = -1; // Direction du déplacement du roi pour le grand roque

        // Déterminer la ligne où les pièces se trouvent
        int ligne = (echiquier[0][colonneRoi] == 'R' || echiquier[0][colonneTour] == 'T') ? 0 :
                (echiquier[7][colonneRoi] == 'r' || echiquier[7][colonneTour] == 't') ? 7 : -1;

        if (ligne == -1) {
            System.out.println("Erreur : le roi ou la tour n'est pas correctement positionné(e).");
            return false;
        }

        char roi = echiquier[ligne][colonneRoi];
        char tour = echiquier[ligne][colonneTour];

        // Vérifier la bonne position des pièces
        if ((ligne == 0 && (roi != 'R' || tour != 'T')) || (ligne == 7 && (roi != 'r' || tour != 't'))) {
            System.out.println("Le roi ou la tour ne sont pas en position pour le grand roque.");
            return false;
        }

        // Vérifier si le chemin est dégagé pour le grand roque
        int colonneDebut = 1; // La colonne juste après la tour
        int colonneFin = 3;   // La colonne juste avant le roi
        if (obstacleRoque(echiquier, ligne, colonneDebut, colonneFin)) {
            System.out.println("Le chemin est bloqué pour le grand roque.");
            return false;
        }

        // Effectuer le grand roque
        echiquier[ligne][colonneRoi + 2 * direction] = roi;
        echiquier[ligne][colonneRoi] = ' ';
        echiquier[ligne][colonneRoi + direction] = tour;
        echiquier[ligne][colonneTour] = ' ';

        System.out.println("Grand roque effectué avec succès !");
        return true;
    }


}