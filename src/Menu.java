import java.util.Scanner;

public class Menu {
    public static void demandeLeCoup(){
        Scanner sc = new Scanner(System.in);
        boolean stop = false;
        int[] nouvellesCoordonnees = new int[2];
        //Affiche le plateau initial
        Plateau.insertionDesPieces(Main.echiquier);
        Main.echiquierString = Plateau.transformationDuCharEnAscii(Main.echiquier);
        Plateau.afficherEchiquierString(Main.echiquierString);

        while (stop == false) {
            System.out.println("C'est le tour des noirs.");
            System.out.println("Quel est la pièce que vous souhaitez jouer ? Tapez 'STOP' pour arrêter le jeu");
            String choix = sc.nextLine();
            if (choix.equals("STOP")) {
                System.out.println("Vous avez souhaité arrêter le programme.");
                break;
            }
            Plateau.coordonneeEstJuste(choix);
            int[] coordonnees = Plateau.traduisCoordonnées(choix);
            char pieceSelectionnee = Main.echiquier[coordonnees[0]][coordonnees[1]];
            switch (pieceSelectionnee){
                case ' ' :
                    System.out.println("Vous n'avez sélectionné aucune pièce, veuillez réessayer.");
                    break;

                case 'P' :
                    System.out.println("Ou souhaitez vous jouer votre pion noir positionnée en " + choix + " ?");
                    choix = sc.nextLine();
                    Plateau.coordonneeEstJuste(choix);
                    nouvellesCoordonnees = Plateau.traduisCoordonnées(choix);
                    Pieces.Pion.deplacementPionNoir(Main.echiquier, coordonnees, nouvellesCoordonnees);
                    Plateau.metAJourLePlateauEnAsciiEtLaffiche();
                    break;

                case 'C' :
                    System.out.println("Ou souhaitez vous jouer votre cavalier noir positionnée en " + choix + " ?");
                    choix = sc.nextLine();
                    Plateau.coordonneeEstJuste(choix);
                    nouvellesCoordonnees = Plateau.traduisCoordonnées(choix);
                    Pieces.Pion.deplacementCavalier(Main.echiquier, coordonnees, nouvellesCoordonnees);
            }



        }

    }
}


