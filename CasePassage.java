public class CasePassage extends Case{
    private Case caseLiee;

    public CasePassage(Plateau p, int nbLig, int nbCol){
        super(nbLig, nbCol, p, "=");
        this.setCaseLiee();
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

    public Case getCaseLiee(){
        return this.caseLiee;
    }
    private void setCaseLiee(){
        int col;
        int lig;
        do{
            col = (int)(Math.random() * (this.getPlateau().getNbCol()-2))+1;
            lig = (int)(Math.random() * (this.getPlateau().getNbLig()-2))+1;
        }while(this.getPlateau().getCase(lig, col).getClass().getName().equals("CaseObstacle") && (col == this.nCol && lig ==this.nLig));
        this.caseLiee = this.getPlateau().getCase(lig, col);
    }
}
