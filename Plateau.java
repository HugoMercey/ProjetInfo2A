import java.util.*;

public class Plateau{
    private int nbCol;
    private int nbLig;

    private int maxPierre;
    private Joueur joueur;
    private int joueurPierre;

    private double pMine = 0.01;
    private double pPierre = 0.01;
    private double pPassage = 0.01;
    private double pObstacle = 0.01;

    private Case[][] plateau;


    public int getNbCol(){
        return this.nbCol;
    }
    public int getNbLig(){
        return this.nbLig;
    }
    public int getMaxPierre(){
        return this.maxPierre;
    }
    public Joueur getJoueur(){
        return this.joueur;
    }
    public Case getCaseDepart(){
        return this.getCase(1, 0);
    }
    public double getSommeProba(){
        return this.pMine + this.pPierre + this.pPassage + this.pObstacle;
    }

    public Plateau(int C, int L, int maxPierre, String NomJ, int joueurPierre){
        this.nbCol = C+2;
        this.nbLig = L+2;
        this.maxPierre = maxPierre;
        this.plateau = new Case[nbLig][nbCol];
        this.initPlateau();
        this.joueur = new Joueur(NomJ, joueurPierre, this);
        this.joueurPierre = joueur.getNbPierre();
    }

    private void initPlateau(){
        for(int i=0; i<this.nbLig; i++){
            for(int j=0; j<this.nbCol; j++){
                if(j==0 || i==0 || i==this.nbLig-1 || j==this.nbCol-1){
                    plateau[i][j] = new CaseBordure(i, j, this);
                }else{
                    plateau[i][j] = new CaseSimple(i, j, this);
                }
            }
        }
        plateau[1][0] = new CaseEntree(this);
        plateau[this.getNbLig()-2][this.getNbCol()-1] = new CaseSortie(this);
        placeAutreCase();
    }

    private void placeAutreCase(){
        int col; 
        int lig;
        int range = (this.getNbCol()-2) * (this.getNbLig()-2);

        ArrayList<ArrayList<Integer>> arrayArrayCaseObstacle = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> arrayCaseObstacle = new ArrayList<Integer>();
        for(int i=0; i<(pObstacle*range); i++){
            do{
                arrayCaseObstacle = new ArrayList<Integer>();
                col = (int)(Math.random() * (this.getNbCol()-2))+1;
                arrayCaseObstacle.add(col);
                lig = (int)(Math.random() * (this.getNbLig()-2))+1;
                arrayCaseObstacle.add(lig);
            }while(arrayArrayCaseObstacle.contains(arrayCaseObstacle));
            arrayArrayCaseObstacle.add(arrayCaseObstacle);
            plateau[arrayArrayCaseObstacle.get(i).get(1)][arrayArrayCaseObstacle.get(i).get(0)] = new CaseObstacle(this, lig, col);
        }

        ArrayList<ArrayList<Integer>> arrayArrayCaseMine = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> arrayCaseMine = new ArrayList<Integer>();
        for(int i=0; i<(pMine*range); i++){
            do{
                arrayCaseMine = new ArrayList<Integer>();
                col = (int)(Math.random() * (this.getNbCol()-2))+1;
                arrayCaseMine.add(col);
                lig = (int)(Math.random() * (this.getNbLig()-2))+1;
                arrayCaseMine.add(lig);
            }while(arrayArrayCaseMine.contains(arrayCaseMine) || arrayArrayCaseObstacle.contains(arrayCaseMine));
            arrayArrayCaseMine.add(arrayCaseMine);
            plateau[arrayArrayCaseMine.get(i).get(1)][arrayArrayCaseMine.get(i).get(0)] = new CaseMine(this, lig, col);
        }

        ArrayList<ArrayList<Integer>> arrayArrayCasePierre = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> arrayCasePierre = new ArrayList<Integer>();
        for(int i=0; i<(pPierre*range); i++){
            do{
                arrayCasePierre = new ArrayList<Integer>();
                col = (int)(Math.random() * (this.getNbCol()-2))+1;
                arrayCasePierre.add(col);
                lig = (int)(Math.random() * (this.getNbLig()-2))+1;
                arrayCasePierre.add(lig);
            }while(arrayArrayCasePierre.contains(arrayCasePierre) || arrayArrayCaseObstacle.contains(arrayCasePierre) || arrayArrayCaseMine.contains(arrayCasePierre));
            arrayArrayCasePierre.add(arrayCasePierre);
            plateau[arrayArrayCasePierre.get(i).get(1)][arrayArrayCasePierre.get(i).get(0)] = new CasePierre(this, lig, col);
        }

        ArrayList<ArrayList<Integer>> arrayArrayCasePassage = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> arrayCasePassage = new ArrayList<Integer>();
        for(int i=0; i<(pPassage*range); i++){
            do{
                arrayCasePassage = new ArrayList<Integer>();
                col = (int)(Math.random() * (this.getNbCol()-2))+1;
                arrayCasePassage.add(col);
                lig = (int)(Math.random() * (this.getNbLig()-2))+1;
                arrayCasePassage.add(lig);
            }while(arrayArrayCasePierre.contains(arrayCasePassage) || arrayArrayCaseObstacle.contains(arrayCasePassage) || arrayArrayCaseMine.contains(arrayCasePassage) ||  arrayArrayCasePassage.contains(arrayCasePassage));
            arrayArrayCasePassage.add(arrayCasePassage);
            plateau[arrayArrayCasePassage.get(i).get(1)][arrayArrayCasePassage.get(i).get(0)] = new CasePassage(this, lig, col);
        }
    }

    public Case getCase(int i, int j){
        return this.plateau[i][j];
    }

    public String toString(){
        boolean boolCol = false;
        boolean boolLig = false;
        String visu = "";
        for(int i=0; i<this.nbLig; i++){
            if(i==this.joueur.getCaseSur().nLig){
                boolLig = true;
            }
            for(int j=0; j<this.nbCol; j++){
                if(j==this.joueur.getCaseSur().nCol){
                    boolCol = true;
                }
                if(boolCol && boolLig){
                    visu += plateau[i][j].toString() + this.joueur.getChara() + " ";
                    boolCol = false;
                    boolLig = false;
                }else{
                    visu += plateau[i][j].toString() + plateau[i][j].toString() + " ";
                }
            }
            boolCol = false;
            visu += "\n";
        }
        return visu;
    }

    public String toStringJ(){
        boolean boolCol = false;
        boolean boolLig = false;
        String visu = "";
        for(int i=0; i<this.nbLig; i++){
            if(i==this.joueur.getCaseSur().nLig){
                boolLig = true;
            }
            for(int j=0; j<this.nbCol; j++){
                if(j==this.joueur.getCaseSur().nCol){
                    boolCol = true;
                }
                if(boolCol && boolLig){
                    visu += plateau[i][j].toString() + this.joueur.getChara() + " ";
                    boolCol = false;
                    boolLig = false;
                }else{
                    if(plateau[i][j].toString().equals("■") || plateau[i][j].toString().equals(">")){
                        visu += plateau[i][j].toString() + plateau[i][j].toString() + " ";
                    }else{
                        if(plateau[i][j].getTraverse()){
                            if(this.getCase(i, j).toString().equals(" ")){
                                visu +=  " . ";
                            }else{
                                visu += plateau[i][j].toString() + plateau[i][j].toString() + " ";
                            }
                        }else{
                            visu +=  "   ";
                        }
                    }
                }
            }
            boolCol = false;
            visu += "\n";
        }
        return visu;
    }
}