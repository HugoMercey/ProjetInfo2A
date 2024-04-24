public class CaseMine extends Case{
    private boolean estActive;
    public CaseMine(Plateau p, int nbLig, int nbCol){
        super(nbLig, nbCol, p, "X");
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


    public boolean getActive(){
        return this.estActive;
    }
    public void setActive(boolean active){
        this.estActive = active;
    }
    public void setTraverse(){
        super.setTraverse();
        setActive(false);
    }
}
