public class TestManuel {

    public static void testPromotion() {
        char[][] echiquier = {
                {' ', ' ', ' ', 't', 'r', 't', ' ', ' '},
                {'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'T', ' ', ' ', ' ', ' ', ' ', ' ', 'R'}
        };

        int[] coordonnee1 = {1, 1};
        int[] coordonnee2 = {3, 6};

        int[] coordonnee3 = {0, 1};
        int[] coordonnee4 = {3, 7};

        String[][] echiquier1 = Plateau.transformationDuCharEnAscii(echiquier);
        Plateau.afficherEchiquierString(echiquier1);
        Pieces.deplacementPionBlanc(echiquier, coordonnee1, coordonnee3,false);
        echiquier1 = Plateau.transformationDuCharEnAscii(echiquier);
        Plateau.afficherEchiquierString(echiquier1);
    }
}


/*les test primordiale on été fait ici, en modifiant le tableau ci-dessus*/

