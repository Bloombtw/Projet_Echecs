import java.util.Scanner;

public class Menu {

    public static String demandePseudo1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel est le pseudo du joueur 1 ?");
        String pseudoJ1 = sc.nextLine();
        return pseudoJ1;
    }
    public static String demandePseudo2() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel est le pseudo du joueur 2 ?");
        String pseudoJ2 = sc.nextLine();
        return pseudoJ2;
    }

    public static void InitialisePlateau() {
        Plateau.insertionDesPieces(Main.echiquier);
        Main.echiquierString = Plateau.transformationDuCharEnAscii(Main.echiquier);
        Plateau.afficherEchiquierString(Main.echiquierString);
    }

    // tire au sort et renvoie vrai si blanc commence, faux si noir commence
    public static boolean j1Commence() {
        boolean j1 = false;
        if (Math.random() < 0.5) {
            j1 = true;
        }
        return j1;
    }

    public static void demandeLeCoupNoir(String pseudo2) {
        Scanner sc = new Scanner(System.in);
        int[] nouvellesCoordonnees = new int[2];
        //Affiche le plateau initial

        while (true) {
            System.out.println("C'est le tour de " + pseudo2 + ", qui joue les noirs.");
            int[] infoSurEchec = Pieces.estAttaqueNoir(Main.echiquier, Pieces.coordonneesRoiNoir(Main.echiquier));
            if ( infoSurEchec[0] == 1) {
                if (Pieces.estEnMatNoir(Main.echiquier, Pieces.coordonneesRoiNoir(Main.echiquier))) {
                    System.out.println("Vous avez perdu, vous êtes en échec et mat.");
                    System.out.println("Les noirs ont gagné !");
                    System.exit(0);
                } else {
                    System.out.println("Vous êtes en échec, veuillez le parer.");
                }
            }
            System.out.println("Quelle est la pièce que vous souhaitez jouer ? Tapez 'STOP' pour arrêter le jeu. Exemple de sélection, 'A4'. ");
            String choix = sc.nextLine();
            if (choix.equals("STOP")) {
                System.out.println("Vous avez souhaité arrêter le programme.");
                System. exit(0);
            }

                choix = Plateau.coordonneeEstJuste(choix);
                int[] coordonnees = Plateau.traduisCoordonnées(choix);
                char pieceSelectionnee = Main.echiquier[coordonnees[0]][coordonnees[1]];
                char[][] ancienEchiquier = Plateau.copieEchiquier(Main.echiquier);
                switch (pieceSelectionnee) {
                    case ' ':
                        System.out.println("Vous n'avez sélectionné aucune pièce, veuillez réessayer.");
                        break;

                    case 'P':
                        System.out.println("Où souhaitez-vous jouer votre pion noir positionné en " + choix + " ?");
                        choix = sc.nextLine();
                        choix = Plateau.coordonneeEstJuste(choix);
                        nouvellesCoordonnees = Plateau.traduisCoordonnées(choix);
                        if (!Pieces.deplacementPionNoir(Main.echiquier, coordonnees, nouvellesCoordonnees, false)) {
                            System.out.println("Déplacement invalide. Veuillez de nouveau saisir une pièce puis sa destination.");
                            break;
                        }
                        if (!IlYAToujoursEchecNoir(ancienEchiquier,pseudo2)){
                            Plateau.metAJourLePlateauEnAsciiEtLaffiche();
                        }
                        return;


                    case 'C':
                        System.out.println("Ou souhaitez vous jouer votre cavalier noir positionné en " + choix + " ?");
                        choix = sc.nextLine();
                        choix = Plateau.coordonneeEstJuste(choix);
                        nouvellesCoordonnees = Plateau.traduisCoordonnées(choix);
                        if (!Pieces.deplacementCavalier(Main.echiquier, coordonnees, nouvellesCoordonnees,false)) {
                            System.out.println("Déplacement invalide. Veuillez de nouveau saisir une pièce puis sa destination.");
                            break;
                        }
                        if (!IlYAToujoursEchecNoir(ancienEchiquier,pseudo2)){
                            Plateau.metAJourLePlateauEnAsciiEtLaffiche();
                        }
                        return;

                    case 'F':
                        System.out.println("Ou souhaitez vous jouer votre fou noir positionné en " + choix + " ?");
                        choix = sc.nextLine();
                        choix = Plateau.coordonneeEstJuste(choix);
                        nouvellesCoordonnees = Plateau.traduisCoordonnées(choix);
                        if (!Pieces.deplacementFou(Main.echiquier, coordonnees, nouvellesCoordonnees,false)) {
                            System.out.println("Déplacement invalide. Veuillez de nouveau saisir une pièce puis sa destination.");
                            break;
                        }
                        if (!IlYAToujoursEchecNoir(ancienEchiquier,pseudo2)){
                            Plateau.metAJourLePlateauEnAsciiEtLaffiche();
                        }
                        return;

                    case 'T':
                        System.out.println("Ou souhaitez vous jouer votre tour noire positionnée en " + choix + " ?");
                        choix = sc.nextLine();
                        choix = Plateau.coordonneeEstJuste(choix);
                        nouvellesCoordonnees = Plateau.traduisCoordonnées(choix);
                        if (!Pieces.deplacementTour(Main.echiquier, coordonnees, nouvellesCoordonnees,false)) {
                            System.out.println("Déplacement invalide. Veuillez de nouveau saisir une pièce puis sa destination.");
                            break;
                        }
                        if (!IlYAToujoursEchecNoir(ancienEchiquier,pseudo2)){
                            Plateau.metAJourLePlateauEnAsciiEtLaffiche();
                        }
                        return;

                    case 'D':
                        System.out.println("Ou souhaitez vous jouer votre dame noire positionnée en " + choix + " ?");
                        choix = sc.nextLine();
                        choix = Plateau.coordonneeEstJuste(choix);
                        nouvellesCoordonnees = Plateau.traduisCoordonnées(choix);
                        if (!Pieces.deplacementReine(Main.echiquier, coordonnees, nouvellesCoordonnees,false)) {
                            System.out.println("Déplacement invalide. Veuillez de nouveau saisir une pièce puis sa destination.");
                            break;
                        }
                        if (!IlYAToujoursEchecNoir(ancienEchiquier,pseudo2)){
                            Plateau.metAJourLePlateauEnAsciiEtLaffiche();
                        }
                        return;

                    case 'R':
                        System.out.println("Ou souhaitez vous jouer votre roi noir positionné en " + choix + " ?");
                        choix = sc.nextLine();
                        choix = Plateau.coordonneeEstJuste(choix);
                        nouvellesCoordonnees = Plateau.traduisCoordonnées(choix);
                        if (!Pieces.deplacementRoi(Main.echiquier, coordonnees, nouvellesCoordonnees,false)) {
                            System.out.println("Déplacement invalide. Veuillez de nouveau saisir une pièce puis sa destination.");
                            break;
                        }
                        if (!IlYAToujoursEchecNoir(ancienEchiquier,pseudo2)){
                            Plateau.metAJourLePlateauEnAsciiEtLaffiche();
                        }
                        return;

                    default:
                        System.out.println("Vous n'avez pas sélectionné une pièce noire, veuillez réessayer.");
                        break;

                }



            }
    }

    public static void demandeLeCoupBlanc(String pseudo1){
            Scanner sc = new Scanner(System.in);
            int[] nouvellesCoordonnees = new int[2];
            //Affiche le plateau initial

            while (true) {
                System.out.println("C'est le tour de " + pseudo1 + ", qui joue les blancs.");
                int[] infoSurEchec = Pieces.estAttaqueBlanc(Main.echiquier, Pieces.coordonneesRoiBlanc(Main.echiquier));
                if (infoSurEchec[0] == 1) {
                    if (Pieces.estEnMatBlanc(Main.echiquier, Pieces.coordonneesRoiBlanc(Main.echiquier))) {
                        System.out.println("Vous avez perdu, vous êtes en échec et mat.");
                        System.out.println("Les noirs ont gagné !");
                        System.exit(0);
                    } else {
                        System.out.println("Vous êtes en échec, veuillez le parer.");
                    }
                }
                System.out.println("Quelle est la pièce que vous souhaitez jouer ? Tapez 'STOP' pour arrêter le jeu. Exemple de sélection, 'A4'.");
                String choix = sc.nextLine();
                if (choix.equals("STOP")) {
                    System.out.println("Vous avez souhaité arrêter le programme.");
                    System.exit(0);
                }

                choix = Plateau.coordonneeEstJuste(choix);
                int[] coordonnees = Plateau.traduisCoordonnées(choix);
                char pieceSelectionnee = Main.echiquier[coordonnees[0]][coordonnees[1]];
                char[][] ancienEchiquier = Plateau.copieEchiquier(Main.echiquier);
                switch (pieceSelectionnee){
                    case ' ' :
                        System.out.println("Vous n'avez sélectionné aucune pièce, veuillez réessayer.");
                        break;

                    case 'p' :
                        System.out.println("Ou souhaitez vous jouer votre pion blanc positionné en " + choix + " ?");
                        choix = sc.nextLine();
                        choix = Plateau.coordonneeEstJuste(choix);
                        nouvellesCoordonnees = Plateau.traduisCoordonnées(choix);
                        if (!Pieces.deplacementPionBlanc(Main.echiquier, coordonnees, nouvellesCoordonnees,false)) {
                            System.out.println("Déplacement invalide. Veuillez de nouveau saisir une pièce puis sa destination.");
                            break;
                        }
                        if (!IlYAToujoursEchecBlanc(ancienEchiquier,pseudo1)){
                            Plateau.metAJourLePlateauEnAsciiEtLaffiche();
                        }
                        return;

                    case 'c' :
                        System.out.println("Ou souhaitez vous jouer votre cavalier blanc positionné en " + choix + " ?");
                        choix = sc.nextLine();
                        choix = Plateau.coordonneeEstJuste(choix);
                        nouvellesCoordonnees = Plateau.traduisCoordonnées(choix);
                        if (!Pieces.deplacementCavalier(Main.echiquier, coordonnees, nouvellesCoordonnees,false)) {
                            System.out.println("Déplacement invalide. Veuillez de nouveau saisir une pièce puis sa destination.");
                            break;
                        }
                        if (!IlYAToujoursEchecBlanc(ancienEchiquier,pseudo1)){
                            Plateau.metAJourLePlateauEnAsciiEtLaffiche();
                        }
                        return;

                    case 'f' :
                        System.out.println("Ou souhaitez vous jouer votre fou blanc positionné en " + choix + " ?");
                        choix = sc.nextLine();
                        choix = Plateau.coordonneeEstJuste(choix);
                        nouvellesCoordonnees = Plateau.traduisCoordonnées(choix);
                        if (!Pieces.deplacementFou(Main.echiquier, coordonnees, nouvellesCoordonnees,false)) {
                            System.out.println("Déplacement invalide. Veuillez de nouveau saisir une pièce puis sa destination.");
                            break;
                        }
                        if (!IlYAToujoursEchecBlanc(ancienEchiquier,pseudo1)){
                            Plateau.metAJourLePlateauEnAsciiEtLaffiche();
                        }
                        return;

                    case 't' :
                        System.out.println("Ou souhaitez vous jouer votre tour blanche positionnée en " + choix + " ?");
                        choix = sc.nextLine();
                        choix = Plateau.coordonneeEstJuste(choix);
                        nouvellesCoordonnees = Plateau.traduisCoordonnées(choix);
                        if (!Pieces.deplacementTour(Main.echiquier, coordonnees, nouvellesCoordonnees,false)) {
                            System.out.println("Déplacement invalide. Veuillez de nouveau saisir une pièce puis sa destination.");
                            break;
                        }

                        if (!IlYAToujoursEchecBlanc(ancienEchiquier,pseudo1)){
                            Plateau.metAJourLePlateauEnAsciiEtLaffiche();
                        }
                        return;

                    case 'd' :
                        System.out.println("Ou souhaitez vous jouer votre dame blanche positionnée en " + choix + " ?");
                        choix = sc.nextLine();
                        choix = Plateau.coordonneeEstJuste(choix);
                        nouvellesCoordonnees = Plateau.traduisCoordonnées(choix);
                        if (!Pieces.deplacementReine(Main.echiquier, coordonnees, nouvellesCoordonnees,false)) {
                            System.out.println("Déplacement invalide. Veuillez de nouveau saisir une pièce puis sa destination.");
                            break;
                        }
                        if (!IlYAToujoursEchecBlanc(ancienEchiquier,pseudo1)){
                            Plateau.metAJourLePlateauEnAsciiEtLaffiche();
                        }
                        return;

                    case 'r' :
                        System.out.println("Ou souhaitez vous jouer votre roi blanc positionné en " + choix + " ?");
                        choix = sc.nextLine();
                        choix = Plateau.coordonneeEstJuste(choix);
                        nouvellesCoordonnees = Plateau.traduisCoordonnées(choix);
                        if (!Pieces.deplacementRoi(Main.echiquier, coordonnees, nouvellesCoordonnees,false)) {
                            System.out.println("Déplacement invalide. Veuillez de nouveau saisir une pièce puis sa destination.");
                            break;
                        }
                        Plateau.metAJourLePlateauEnAsciiEtLaffiche();
                        IlYAToujoursEchecBlanc(ancienEchiquier,pseudo1);
                        return;

                    default :
                        System.out.println("Vous n'avez pas sélectionné une pièce blanche, veuillez réessayer.");
                        break;

                }

            }
        }

    public static boolean IlYAToujoursEchecNoir (char[][] ancienEchiquier, String pseudo2) { // verif si apres le tour du joueur il est tjrs en echec
        int[] infoSurEchec = Pieces.estAttaqueNoir(Main.echiquier, Pieces.coordonneesRoiNoir(Main.echiquier));
            if (infoSurEchec[0] == 1) { // Si oui le fait rejouer
                Main.echiquier = Plateau.copieEchiquier(ancienEchiquier);
                Plateau.metAJourLePlateauEnAsciiEtLaffiche();
                System.out.println("Votre coup n'a pas paré l'échec, veuillez en saisir un nouveau.");
                demandeLeCoupNoir(pseudo2);
            }
            return false;
        }

    public static boolean IlYAToujoursEchecBlanc (char[][] ancienEchiquier, String pseudo1) { // verif si apres le tour du joueur il est tjrs en echec
        int[] infoSurEchec = Pieces.estAttaqueBlanc(Main.echiquier, Pieces.coordonneesRoiBlanc(Main.echiquier));
        if (infoSurEchec[0] == 1) { // Si oui le fait rejouer
            Main.echiquier = Plateau.copieEchiquier(ancienEchiquier);
            Plateau.metAJourLePlateauEnAsciiEtLaffiche();
            System.out.println("Votre coup n'a pas paré l'échec, veuillez en saisir un nouveau.");
            demandeLeCoupBlanc(pseudo1);
        }
        return false;
    }
}



