public class CaseEntree extends Case{
    public CaseEntree(Plateau p){
        super(1, 0, p, ">");
    }

    public Case getCaseHaut(){
        return this;
    }
    public Case getCaseDroite(){
        return this.getPlateau().getCase(1, 1);
    }
    public Case getCaseBas(){
        return this;
    }
    public Case getCaseGauche(){
        return this;
    }

}
