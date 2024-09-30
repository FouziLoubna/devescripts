
package devscrpts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class GestionaireBD {
    
    // Informations de connexion à la base de données
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/devscrpts"; // Remplacer avec votre URL
    static final String USER = "root"; // Remplacer avec votre nom d'utilisateur
    static final String PASS = ""; // Remplacer avec votre mot de passe

    private Connection conn;
    private Statement stmt; // Déclaration de Statement comme attribut de classe

    // Méthode pour ouvrir la connexion à la base de données
    public void connect() {
        try {
            // Charger le driver JDBC
            Class.forName(JDBC_DRIVER);
            // Ouvrir une connexion
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connexion établie avec succès.");
            stmt = conn.createStatement(); // Initialisation de Statement ici
        } catch (ClassNotFoundException ex) {
        System.out.println("Driver JDBC non trouvé.");
      
    } catch (SQLException se) {
        System.out.println("Erreur de connexion à la base de données.");
     
    }
        
    }

    // Méthode pour créer la table DevData
    public void createDevDataTable() {
        try {
            String sql = "CREATE TABLE  DevData (" +
                         "Developpeurs VARCHAR(32), " +
                         "Jour CHAR(11), " +
                         "NbScripts INTEGER)";
            stmt.executeUpdate(sql);
            System.out.println("Table DevData créée ou déjà existante.");
        } catch (SQLException se) {
            System.out.println("Erreur de connexion à la base de données.");
        }
    }

    // Méthode pour insérer un développeur
    public void insertDeveloppeur(String developpeur, String jour, int nbScripts) {
        try {
            String insertSQL = "INSERT INTO DevData (Developpeurs, Jour, NbScripts) VALUES ('" 
                               + developpeur + "', '" + jour + "', " + nbScripts + ")";
            stmt.executeUpdate(insertSQL);
            System.out.println("Développeur inséré : " + developpeur + ", Jour : " + jour + ", NbScripts : " + nbScripts);
        } catch (SQLException se) {
           System.out.println("Erreur de connexion à la base de données.");
        }
    }

    // Méthode pour fermer la connexion
    public void closeConnection() {
        try {
            if (stmt != null) stmt.close(); // Fermer le Statement
            if (conn != null) conn.close(); // Fermer la connexion
        } catch (SQLException se) {
           
        }
    }
}
