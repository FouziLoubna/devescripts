package devscrpts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
public class ExoJDBC {
    // Informations de connexion à la base de données
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/devscrpts"; 
    static final String USER = "root"; 
    static final String PASS = ""; 
    private Connection conn = null;
    private Statement stmt = null;
    // Méthode pour se connecter à la base de données
    public void connect() {
        try {
            // Charger le driver JDBC
            Class.forName(JDBC_DRIVER);
            // Ouvrir une connexion
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            System.out.println("Connexion établie avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour fermer la connexion à la base de données
    public void closeConnection() {
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // Méthode (a) : Trouver la personne ayant réalisé le nombre maximum de scripts en une journée
    public void getMaxScriptsJOUR() {
        try {
            String sql = "SELECT Developpeurs, Jour, MAX(NbScripts) AS MaxScripts " +
                         "FROM DevData " +
                         "GROUP BY Jour " +
                         "ORDER BY MaxScripts DESC ";
            ResultSet rs = stmt.executeQuery(sql);
       
            if (rs.next()) {
                System.out.println("Personne ayant réalisé le nombre maximum de scripts en une journée : " +
                                   rs.getString("Developpeurs") + " - Jour : " + 
                                   rs.getString("Jour") + " - MaxScripts : " + 
                                   rs.getInt("MaxScripts"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // Méthode (b) : Obtenir la liste des personnes triée par nombre total de scripts
    public void getScriptsDeveloppeur() {
        try {
            String sql = "SELECT Developpeurs, SUM(NbScripts) AS TotalScripts " +
                         "FROM DevData " +
                         "GROUP BY Developpeurs " +
                         "ORDER BY TotalScripts DESC";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("\nListe des personnes triée par nombre total de scripts :");
            while (rs.next()) {
                System.out.println(rs.getString("Developpeurs") + " - Total Scripts : " + 
                                   rs.getInt("TotalScripts"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // Méthode pour calculer le nombre total de scripts réalisés en une semaine
    public void getTotalScriptsparsomaine() {
        try {
            String sql = "SELECT SUM(NbScripts) AS TotalScriptsSem FROM DevData";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println("\nNombre total de scripts réalisés en une semaine : " + 
                                   rs.getInt("TotalScriptsSem"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // Méthode pour calculer le nombre total de scripts réalisés par un programmeur donné
    public void getScriptsBySpecificDeveloper(String nomdev) {
        try {
            String sql = "SELECT SUM(NbScripts) " +
                         "FROM DevData " +
                         "WHERE Developpeurs = '" + nomdev + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println("\nNombre total de scripts réalisés par " + nomdev + " : " + 
                                   rs.getInt("SUM(NbScripts)"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public void executerequêtelibre(String sql) {
        try {
            boolean reponse = stmt.execute(sql); // Exécute la requête SQL

            if (reponse) {  // Si un ResultSet est retourné (par exemple pour SELECT)
                ResultSet rs = stmt.getResultSet();
                ResultSetMetaData rsmd = rs.getMetaData();

                int columnCount = rsmd.getColumnCount(); // Nombre de colonnes
                System.out.println("Nombre de colonnes : " + columnCount);

                // Afficher les noms et types des colonnes
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = rsmd.getColumnName(i);
                    String columnType = rsmd.getColumnTypeName(i);
                    System.out.println("Colonne " + i + " : " + columnName + " (Type: " + columnType + ")");
                }

                // Afficher le contenu ligne par ligne
                System.out.println("Résultats :");
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println();
                }
                rs.close();
            } else {  // Si la requête modifie des lignes (INSERT, UPDATE, DELETE)
                int rowsAffected = stmt.getUpdateCount();
                System.out.println("Nombre de lignes modifiées : " + rowsAffected);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    

