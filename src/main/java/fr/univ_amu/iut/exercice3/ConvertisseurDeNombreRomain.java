package fr.univ_amu.iut.exercice3;

/**
 * Exercice 3 - Convertisseur de chiffres romains en nombres arabes.
 *
 * <p>Règles :
 *
 * <ul>
 *   <li>Les symboles valides sont {@code I=1, V=5, X=10, L=50, C=100, D=500, M=1000}
 *   <li>Lus de gauche à droite, les symboles s'additionnent : {@code VI = 5 + 1 = 6}
 *   <li>Un symbole placé avant un symbole de valeur supérieure se soustrait : {@code IV = 5 - 1 =
 *       4}
 *   <li>Les seules soustractions valides sont :
 *       <ul>
 *         <li>I avant V ou X ({@code IV = 4}, {@code IX = 9})
 *         <li>X avant L ou C ({@code XL = 40}, {@code XC = 90})
 *         <li>C avant D ou M ({@code CD = 400}, {@code CM = 900})
 *       </ul>
 *       Toute autre soustraction doit lever {@link IllegalArgumentException}.
 * </ul>
 *
 * <p>Conseils TDD : commencez par gérer uniquement {@code I}, puis {@code II} / {@code III} (fake
 * it), puis {@code V} (triangulation), puis {@code VI} (addition de deux symboles différents), puis
 * {@code IV} (introduction de la soustraction). À ce moment-là, <b>extrayez une méthode</b> pour
 * calculer la valeur d'un symbole - vous en aurez besoin pour les symboles suivants.
 */
public class ConvertisseurDeNombreRomain {

  /**
   * Convertit une chaîne de chiffres romains en valeur entière.
   *
   * @param chiffreRomain chaîne composée de symboles romains (par exemple {@code "XLIX"})
   * @return la valeur entière correspondante
   * @throws IllegalArgumentException si la chaîne contient un symbole invalide ou une soustraction
   *     interdite
   */
  public int enNombreArabe(String chiffreRomain) {
    if (chiffreRomain == null || chiffreRomain.isEmpty()) {
      throw new IllegalArgumentException("Chiffre romain vide ou nul");
    }

    int total = 0;
    int precedent = 0;
    for (int i = chiffreRomain.length() - 1; i >= 0; i--) {
      char symbole = chiffreRomain.charAt(i);
      int valeur = valeurDe(symbole);
      if (valeur < precedent) {
        if (!soustractionValide(valeur, precedent)) {
          throw new IllegalArgumentException("Soustraction invalide : " + chiffreRomain);
        }
        total -= valeur;
      } else {
        total += valeur;
        precedent = valeur;
      }
    }
    return total;
  }

  private int valeurDe(char symbole) {
    return switch (symbole) {
      case 'I' -> 1;
      case 'V' -> 5;
      case 'X' -> 10;
      case 'L' -> 50;
      case 'C' -> 100;
      case 'D' -> 500;
      case 'M' -> 1000;
      default -> throw new IllegalArgumentException("Symbole romain inconnu : " + symbole);
    };
  }

  private boolean soustractionValide(int valeurCourante, int valeurSuivante) {
    return switch (valeurCourante) {
      case 1 -> valeurSuivante == 5 || valeurSuivante == 10;
      case 10 -> valeurSuivante == 50 || valeurSuivante == 100;
      case 100 -> valeurSuivante == 500 || valeurSuivante == 1000;
      default -> false;
    };
  }
}
