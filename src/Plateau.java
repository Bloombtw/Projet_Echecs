public class Plateau {

    public static void afficherEnLigne(String c, int longueur) {
        if (longueur < 0) {
            System.out.println("ERREUR! afficherEnLigne : longueur négative ("
                    + longueur + ")");
        } else {
            for (int colonne = 1; colonne <= longueur; colonne++) {
                System.out.print(c);
            }
        }
    }
    // Création des contours de l'échiquier
    public static void échiquier() {
        int colonnes = 8;
        int lignes = 8;
        char[][] échiquier = new char[lignes][colonnes];
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                System.out.print("--------------");
            }
            System.out.println("+");
            for (int j = 0; j < colonnes; j++) {
                System.out.print("|             ");
            }
            System.out.println("|");
        }
        afficherEnLigne("-",112);
        System.out.println("+");
        insertionDesPieces(échiquier);
}

    public static void nomDesPieces() { // Cette méthode n'est pas utilisée mais sert d'index pour savoir les valeurs des pièces

        // Déf des valeurs des cases
        char caseBlanc = 'B';
        char caseNoir = 'N';
        char caseVide = ' ';

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

    public static void metLesCouleursDesCasesSurEchiquier(char[][] échiquier) {
        char caseBlanc = 'B';
        char caseNoir = 'N';

        for (int ligne = 0; ligne < 8; ligne++) {
            for (int colonne = 0; colonne < 8; colonne++) {

            }
        }
    }


    // Mise en place des pièces dans les bonnes cases au début de la partie
    public static void insertionDesPieces(char[][] échiquier) {

        // Mise en place du jeu noir (tour cavalier fou dame roi fou cavalier tour)
        String premiereLigneNoire = "TCFDRCFT";
        String deuxiemeLigneNoire = "PPPPPPPP";
        for ( int ligne =0; ligne< 8; ligne++){ // Remplissage des deux premières lignes ( en partant du haut, le jeu noir)
            échiquier[0][ligne] = premiereLigneNoire.charAt(ligne); // Pièces
            échiquier[1][ligne] = deuxiemeLigneNoire.charAt(ligne); // Pions
        }

        // Mise en place du jeu blanc (tour cavalier fou dame roi fou cavalier tour)
        String premiereLigneBlanche = "tcfdrcft";
        String deuxiemeLigneBlanche = "ppppppppp";
        for (int ligne =0; ligne< 8; ligne++){ // Remplissage des deux dernières lignes ( en partant du haut, le jeu blanc)
            échiquier[7][ligne] = premiereLigneBlanche.charAt(ligne); // Pièces
            échiquier[6][ligne] = deuxiemeLigneBlanche.charAt(ligne); // Pions
        }

        // Remplir le reste des cases du tableau en vide
        char caseVide = ' ';
        for (int ligne = 2; ligne < 6 ; ligne++) {
            for ( int colonne = 0; colonne < 8; colonne++) {
                échiquier[ligne][colonne] = caseVide;
            }
        }
    }
}
