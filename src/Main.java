//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        char[][] echiquier = new char[8][8];
        String[][] echiquierString = new String[8][8];

        // Première case pour les lignes, deuxième pour les colonnes
        Plateau.insertionDesPieces(echiquier);
        echiquierString = Plateau.transformationDuCharEnAscii(echiquier);
        Plateau.contoursechiquierString(echiquierString);
        }
    }
