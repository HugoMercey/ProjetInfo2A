public class CaseBordure extends Case {
    public CaseBordure(int nbLig, int nbCol, Plateau p){
        super(nbLig, nbCol, p, "■");
    }

    public Case getCaseHaut(){
        return null;
    }
    public Case getCaseDroite(){
        return null;
    }
    public Case getCaseBas(){
        return null;
    }
    public Case getCaseGauche(){
        return null;
    }
    
}
