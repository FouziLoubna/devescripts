
package devscrpts;

import java.util.Scanner;

public class Test {
   
    public static void main(String[] args) {
        // Créer une instance de DatabaseManager
        //GestionaireBD GB = new GestionaireBD();

        // Ouvrir la connexion
     // GB.connect();

        //Créer la table DevData
      // GB.createDevDataTable();

        // Insertion des données via objets DevData
       //GB.insertDeveloppeur("ALAMI", "Lundi", 1);
       // GB.insertDeveloppeur("WAFI", "Lundi", 2);
       // GB.insertDeveloppeur("SLAMI", "Mardi", 9);
        //GB.insertDeveloppeur("SAFI", "Mardi", 2);
        //GB.insertDeveloppeur("ALAMI", "Mardi", 2);
        //GB.insertDeveloppeur("SEBIHI", "Mercredi", 2);
        //GB.insertDeveloppeur("WAFI", "Jeudi", 3);
        //GB.insertDeveloppeur("ALAOUI", "Vendredi", 9);
        //GB.insertDeveloppeur("WAFI", "Vendredi", 3);
        //GB.insertDeveloppeur("SEBIHI", "Vendredi", 4);
        // Fermer la connexion
        //GB.closeConnection();
       
        // Créer une instance de ExoJDBC
        ExoJDBC exoJDBC = new ExoJDBC();

        // Ouvrir la connexion
        exoJDBC.connect() ;

        // Appeler les méthodes pour exécuter des requêtes SQL
        exoJDBC.getMaxScriptsJOUR();            // Personne ayant réalisé le plus de scripts en une journée
        exoJDBC.getScriptsDeveloppeur();          // Liste triée par nombre total de scripts
        exoJDBC.getTotalScriptsparsomaine();         // Total de scripts en une semaine
        exoJDBC.getScriptsBySpecificDeveloper("WAFI"); // Total de scripts par programmeur donné
        // Lire la requête SQL à partir de l'entrée clavier
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez une requête SQL : ");
        String sql = scanner.nextLine();

        // Exécuter la requête
        exoJDBC.executerequêtelibre(sql);
        // Fermer la connexion après avoir exécuté toutes les requêtes
        exoJDBC.closeConnection();
    



        System.out.println("Fin du programme.");
    }
}

