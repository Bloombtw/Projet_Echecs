public class Main {

    public static char[][] echiquier = new char[8][8];
    public static String[][] echiquierString;
    public static void main(String[] args) {

        Menu.demandeLeCoup();

/*
        //Plateau initiale
        Plateau.insertionDesPieces(echiquier);
        echiquierString = Plateau.transformationDuCharEnAscii(echiquier);
        Plateau.contoursechiquierString(echiquierString);

        // Déplacement d'un pion blanc
        int[] coordonneesPion = Plateau.traduisCoordonnées("A7");
        int[] nouvellesCoordonneesPion = Plateau.traduisCoordonnées("A5");
        int[] coordonneesPion2 = nouvellesCoordonneesPion;
        int[] nouvellesCoordonneesPion2 = Plateau.traduisCoordonnées("A3");
        System.out.println("Déplacement du pion...");
        coordonneesPion = Pieces.Pion.deplacementPionNoir(echiquier, coordonneesPion, nouvellesCoordonneesPion);

        echiquierString = Plateau.transformationDuCharEnAscii(echiquier);
        Plateau.contoursechiquierString(echiquierString);
        coordonneesPion = Pieces.Pion.deplacementPionNoir(echiquier, coordonneesPion2, nouvellesCoordonneesPion2);
        echiquierString = Plateau.transformationDuCharEnAscii(echiquier);
        Plateau.contoursechiquierString(echiquierString);


        // Déplacement de la tour vers une nouvelle position
        int[] coordonneesTour = Plateau.traduisCoordonnées("A8");
        System.out.println(coordonneesTour[0] + " " + coordonneesTour[1]);
        int[] nouvellesCoordonnées = Plateau.traduisCoordonnées("A4");
        System.out.println("Déplacement de la tour...");
        System.out.println();
        coordonneesTour = Pieces.deplacementTour(echiquier, coordonneesTour, nouvellesCoordonnées); // Déplacement vers (3, 0)

        // Affichage de l'échiquier après le déplacement
        echiquierString = Plateau.transformationDuCharEnAscii(echiquier);
        Plateau.contoursechiquierString(echiquierString);


        int[] coordonneesFou = {0, 2}; // Fou noir en haut à droite
        int[] nouvellesCoordonnéesFou = Plateau.traduisCoordonnées("A7");
        System.out.println("Déplacement du fou...");
        System.out.println();
        coordonneesFou = Pieces.deplacementFou(echiquier, coordonneesFou, nouvellesCoordonnéesFou); // Déplacement vers (2, 4)

        // Affichage de l'échiquier après le déplacement
        Plateau.contoursechiquierString(echiquierString);
*/
    }
}