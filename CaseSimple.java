public class CaseSimple extends Case{
    public CaseSimple(int nbLig, int nbCol, Plateau p){
        super(nbLig, nbCol, p, " ");
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

}
