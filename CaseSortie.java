public class CaseSortie extends Case{
    public CaseSortie(Plateau p){
        super(p.getNbLig()-2, p.getNbCol()-1, p, ">");
    }

    public Case getCaseHaut(){
        return this.getPlateau().getCase(this.nLig-1, this.nCol);
    }
    public Case getCaseDroite(){
        return null;
    }
    public Case getCaseBas(){
        return this.getPlateau().getCase(this.nLig+1, this.nCol);
    }
    public Case getCaseGauche(){
        return this.getPlateau().getCase(this.nLig, this.nCol-1);
    }
}
