public class Ascii {

    // Déclaration caractères de l'échiquier
    public static final String caseNoirAscii = "■";
    public static final String caseBlancAscii = "⬜";

    // Déclaration pions blancs
    public static final String pionBlancAscii = "♙";
    public static final String cavalierBlancAscii = "♘";
    public static final String fouBlancAscii = "♗";
    public static final String tourBlancAscii = "♖";
    public static final String dameBlancAscii = "♕";
    public static final String roiBlancAscii = "♔";

    // Déclaration pions noirs
    public static final String pionNoirAscii = "♟";
    public static final String cavalierNoirAscii = "♞";
    public static final String fouNoirAscii = "♝";
    public static final String tourNoirAscii = "♜";
    public static final String dameNoirAscii = "♛";
    public static final String roiNoirAscii = "♚";


    public static void remplirEchiquierString(String[][] échiquier) {
        String caseNoir = "■";
        String caseBlanc = "⬜";
        for (int ligne = 0; ligne < 8; ligne++) { // Si case blanche alors somme de ligne + colonne est pair, sinon c'est une case noire.
            for (int colonne = 0; colonne < 8; colonne++) {
                if ((ligne + colonne) % 2 == 0) {
                    échiquier[ligne][colonne] = caseBlanc;
                } else {
                    échiquier[ligne][colonne] = caseNoir;
                }
            }
        }
    }

    public static void afficherEchiquierString(String[][] échiquier)  {
        for (int ligne = 0; ligne < échiquier.length; ligne++) {
            for (int colonne = 0; colonne < échiquier[ligne].length; colonne++) {
                System.out.print(échiquier[ligne][colonne]);
            }
            System.out.println();
        }
    }
}
