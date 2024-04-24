public class Joueur {
    private String nom;
    private int nbPierre;
    private Case estSurCase;
    private String chara = "π";

    public Joueur(String nom, int nbPierre, Plateau p){
        this.nom = nom; 
        this.nbPierre = nbPierre;
        this.setCaseSur(p.getCaseDepart());
    }
    public Joueur(String nom, Plateau p){
        this.nom = nom; 
        this.nbPierre = 3;
        this.setCaseSur(p.getCaseDepart());
    }

    public String getNom(){
        return this.nom;
    }
    public int getNbPierre(){
        return this.nbPierre;
    }
    public Case getCaseSur(){
        return this.estSurCase;
    }

    public void setNbPierre(int plus){
        this.nbPierre += plus;
    }
    public void setCaseSur(Case c){
        this.estSurCase = c;
        this.getCaseSur().setTraverse();
    }

    public void deplaceHaut(){
        if(this.getCaseSur().getCaseHaut().nCol != 0 && this.getCaseSur().getCaseHaut().nLig != 0 && this.getCaseSur().getCaseHaut().nCol != this.getCaseSur().getPlateau().getNbCol()-1 && this.getCaseSur().getCaseHaut().nLig != this.getCaseSur().getPlateau().getNbLig()-1){
            this.setCaseSur(this.getCaseSur().getCaseHaut());
        }
    }
    public void deplaceBas(){
        if(!(this.getCaseSur().getCaseBas().nCol == 0 || this.getCaseSur().getCaseBas().nLig == 0 || this.getCaseSur().getCaseBas().nCol == this.getCaseSur().getPlateau().getNbCol()-1 || this.getCaseSur().getCaseBas().nLig == this.getCaseSur().getPlateau().getNbLig()-1)){
            this.setCaseSur(this.getCaseSur().getCaseBas());
        }
    }
    public void deplaceGauche(){
        if(!(this.getCaseSur().getCaseGauche().nCol == 0 || this.getCaseSur().getCaseGauche().nLig == 0 || this.getCaseSur().getCaseGauche().nCol == this.getCaseSur().getPlateau().getNbCol()-1 || this.getCaseSur().getCaseGauche().nLig == this.getCaseSur().getPlateau().getNbLig()-1)){
            this.setCaseSur(this.getCaseSur().getCaseGauche());
        }
    }
    public void deplaceDroite(){
        if(this.getCaseSur().getCaseDroite().nCol != 0 && this.getCaseSur().getCaseDroite().nLig != 0 && this.getCaseSur().getCaseDroite().nCol != this.getCaseSur().getPlateau().getNbCol()-1 && this.getCaseSur().getCaseDroite().nLig != this.getCaseSur().getPlateau().getNbLig()-1){
            this.setCaseSur(this.getCaseSur().getCaseDroite());
        }else if(this.getCaseSur().nCol == this.getCaseSur().getPlateau().getNbCol()-2 && this.getCaseSur().nLig == this.getCaseSur().getPlateau().getNbLig()-2){
            this.setCaseSur(this.getCaseSur().getCaseDroite());
        }
    }

    public String getChara(){
        return this.chara;
    }

}
