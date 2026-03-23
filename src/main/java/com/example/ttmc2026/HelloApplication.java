package com.example.ttmc2026;

/**
 * Classe de compatibilité temporaire.
 *
 * Maintenant que la classe principale réelle est AppTTMC, cette classe ne doit plus contenir une deuxième logique de démarrage JavaFX.
 * Son seul rôle est donc de rediriger vers AppTTMC afin de conserver une structure stable pendant la phase de réorganisation du projet.
 */
public class HelloApplication {

    /** Point d'entrée de compatibilité.
     * Cette méthode redirige simplement l'exécution vers la véritable classe principale de l'application.
     *
     * @param arguments arguments de lancement
     */
    public static void main(String[] arguments) {
        AppTTMC.main(arguments);
    }
}