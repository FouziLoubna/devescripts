
package devscrpts;

public class DevData {
    private String developpeur;
    private String jour;
    private int nbScripts;

    // Constructeur
    public DevData(String developpeur, String jour, int nbScripts) {
        this.developpeur = developpeur;
        this.jour = jour;
        this.nbScripts = nbScripts;
    }

    // Getters et Setters
    public String getDeveloppeur() {
        return developpeur;
    }

    public void setDeveloppeur(String developpeur) {
        this.developpeur = developpeur;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public int getNbScripts() {
        return nbScripts;
    }

    public void setNbScripts(int nbScripts) {
        this.nbScripts = nbScripts;
    }

    @Override
    public String toString() {
        return "DevData{" +
                "developpeur='" + developpeur + '\'' +
                ", jour='" + jour + '\'' +
                ", nbScripts=" + nbScripts +
                '}';
    }
}

    

