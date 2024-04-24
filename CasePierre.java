public class CasePierre extends Case{
    private int nbPierre;
    private boolean pierrePrise = false;

    public CasePierre(Plateau p, int nbLig, int nbCol){
        super(nbLig, nbCol, p, "O");
        nbPierre = (int)(Math.random()*3) + 1;
    }

    public Case getCaseHaut(){
        return this.getPlateau().getCase(this.nLig-1, this.nCol);
    }
    public Case getCaseDroite(){
        return this.getPlateau().getCase(this.nLig, this.nCol+1);
    }
    public Case getCaseBas(){
        return this.getPlateau().getCase(this.nLig+1, this.nCol);
    }
    public Case getCaseGauche(){
        return this.getPlateau().getCase(this.nLig, this.nCol-1);
    }

    public int getNbPierre(){
        return this.nbPierre;
    }
    public void setNbPierre(int nbPierre){
        this.nbPierre = nbPierre;
    }

    public boolean estPrise(){
        return this.pierrePrise;
    }
    public void setPrise(boolean bool){
        this.pierrePrise = bool;
    }
}
