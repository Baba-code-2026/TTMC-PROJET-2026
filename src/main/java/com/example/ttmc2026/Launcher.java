package com.example.ttmc2026;

/**
 * Classe de lancement secondaire de l'application.
 *
 * Cette classe est conservée pour éviter de casser d'anciens points d'entrée éventuels du projet pendant la réorganisation.
 *
 * Désormais, le vrai démarrage de l'application est centralisé dans la classe AppTTMC.
 * Cette classe ne fait donc que rediriger vers elle.
 */
public class Launcher {

    /** Cette méthode redirige l'exécution vers la véritable classe principale de l'application.
     *
     * @param arguments arguments de lancement
     */
    public static void main(String[] arguments) {
        AppTTMC.main(arguments);
    }
}