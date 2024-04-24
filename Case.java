public abstract class Case {
    public int nCol;
    public int nLig;
    private String chara;
    private Plateau p;
    private boolean traverse;

    public Case(int nLig, int nCol, Plateau p, String chara){
        this.nCol = nCol;
        this.nLig = nLig;
        this.p = p;
        this.chara = chara;
        this.traverse = false;
    }

    public String toString(){
        return this.chara;
    }

    public Plateau getPlateau(){
        return this.p;
    }
    public boolean getTraverse(){
        return this.traverse;
    }
    public void setTraverse(){
        this.traverse = true;
    }
    public abstract Case getCaseHaut();
    public abstract Case getCaseDroite();
    public abstract Case getCaseBas();
    public abstract Case getCaseGauche();

}
