public class Jeu {
    public static void main(String[] args) {
        String choix = "";
        int nbCol = 10;
        int nbLig = 10;
        Plateau p = new Plateau(nbCol, nbLig, 10, "Jo", 3);
        
        if(p.getSommeProba()<=1){
            do{
                System.out.println();

                System.out.println(p.toStringJ());

                System.out.println();

                System.out.println(p.toString());

                if(p.getJoueur().getCaseSur().nCol == nbCol+1 && p.getJoueur().getCaseSur().nLig== nbLig){
                    choix = "0";
                    System.out.println("Vous avez gagné, bravo");
                }
                else if(p.getJoueur().getCaseSur().toString().equals("X")){
                    CaseMine caseMine = (CaseMine) p.getJoueur().getCaseSur();
                    if(caseMine.getActive()){
                        System.out.println("Le joueur s'est fait péter");
                        choix = "0";
                    }else{
                        System.out.println("Cette mine a été désactivé");
                        choix = menu(p);
                        bougeJ(choix, p);
                    }
                }
                else if(p.getJoueur().getCaseSur().toString().equals("O")){
                    CasePierre casePierre = (CasePierre) p.getJoueur().getCaseSur();
                    if(!casePierre.estPrise()){
                        if(p.getJoueur().getNbPierre()<p.getMaxPierre()){
                            int nbPierre = casePierre.getNbPierre();
                            if(p.getJoueur().getNbPierre()+nbPierre > p.getMaxPierre()){
                                casePierre.setNbPierre(p.getMaxPierre() - p.getJoueur().getNbPierre());
                                System.out.println("Le joueur a ramassé le plus de pierre possible. Il en a maintenant " + p.getMaxPierre());
                                p.getJoueur().setNbPierre(p.getJoueur().getNbPierre() - p.getMaxPierre());
                            }else{
                                casePierre.setNbPierre(0);
                                p.getJoueur().setNbPierre(nbPierre);
                                System.out.println("Le joueur a ramassé " + nbPierre + " pierre(s)\nIl en a maintenant " + p.getJoueur().getNbPierre());
                            }
                        }else{
                            System.out.println("Le joueur possède trop de pierre, il ne peut pas en porter plus");
                        }
                        System.out.println("");
                        casePierre.setPrise(true);
                    }
                    choix = menu(p);
                    if(choix.equals("t")){
                        String dir = tireMenu(p);
                        tireAction(dir, p);
                    }else{
                        bougeJ(choix, p);
                    }
                }
                else if(p.getJoueur().getCaseSur().toString().equals("|")){
                    switch(choix){
                        case "z": p.getJoueur().deplaceBas(); break;
                        case "q": p.getJoueur().deplaceDroite(); break;
                        case "s": p.getJoueur().deplaceHaut(); break;
                        case "d": p.getJoueur().deplaceGauche(); break;
                    }
                    System.out.println("Le joueur a rencontré un obstacle");
                }
                else if(p.getJoueur().getCaseSur().toString().equals("=")){
                    CasePassage casePassage = (CasePassage) p.getJoueur().getCaseSur();
                    p.getJoueur().setCaseSur(casePassage.getCaseLiee());
                    System.out.println("Le joueur a rencontré un passage");
                }
                else{
                    choix = menu(p);
                    if(choix.equals("t")){
                        if(p.getJoueur().getNbPierre()!=0){
                            p.getJoueur().setNbPierre(-1);
                            String dir = tireMenu(p);
                            tireAction(dir, p);
                        }else{
                            System.out.println("Vous n'avez plus de pierre");
                        }
                    }else{
                        bougeJ(choix, p);
                    }
                }

            }while(!choix.equals("0"));
        }else{
            System.out.println("Les probabilités d'apparition des obstacles/mines/passages/pierres ne sont pas valides");
        }
    }

    public static void bougeJ(String choix, Plateau p){
        if(choix.equals("z")){
            p.getJoueur().deplaceHaut();
        }else if(choix.equals("d")){
            p.getJoueur().deplaceDroite();
        }else if(choix.equals("q")){
            p.getJoueur().deplaceGauche();
        }else if(choix.equals("s")){
            p.getJoueur().deplaceBas();
        }
    }

    public static String menu(Plateau p){
        System.out.println("Vous possédez " + p.getJoueur().getNbPierre() + " pierre(s)");
        System.out.println();
        System.out.println("###############################################################");
        System.out.println("0 pour quitter");
        System.out.println("z pour monter d'une case");
        System.out.println("d pour bouger d'une case à droite");
        System.out.println("q pour bouger d'une case à gauche");
        System.out.println("s pour descendre d'une case");
        System.out.println("t pour tirer une pierre");
        System.out.println("###############################################################");
        System.out.println();
        return Lire.S();
    }

    public static String tireMenu(Plateau p){
        System.out.println();
        System.out.println("###############################################################");
        System.out.println("0 pour quitter");
        System.out.println("z pour tirer en haut");
        System.out.println("d pour tirer à droite");
        System.out.println("q pour tirer à gauche");
        System.out.println("s pour tirer en bas");
        System.out.println("###############################################################");
        System.out.println();
        return Lire.S();
    }

    public static void tireAction(String direction, Plateau p){
        switch(direction){ 
            case "z":
                propage(direction, p.getJoueur().getCaseSur());
                break;
            case "q":
                if(!p.getJoueur().getCaseSur().toString().equals(">")){
                    propage(direction, p.getJoueur().getCaseSur());
                }
                break;
            case "s":
                propage(direction, p.getJoueur().getCaseSur());
                break;
            case "d":
                propage(direction, p.getJoueur().getCaseSur());
                break;
        }
    }

    public static void propage(String direction, Case caseP){
        int utilPassage = 0;
        switch(direction){ 
            case "z":
                do{
                    if(utilPassage < 2){
                        if(caseP.toString().equals("=")){
                            utilPassage+=1;
                            CasePassage casePassage = (CasePassage) caseP;
                            caseP = casePassage.getCaseLiee();
                        }else{
                            caseP = caseP.getCaseHaut();
                        }
                    }else{
                        System.out.println("La pierre s'est perdue dans les méandres des passages");
                    }
                }while(caseP.toString().equals(" ") && caseP.nCol == 0 || caseP.toString().equals("=") && utilPassage < 2);
                break;

            case "q":
                do{
                    if(utilPassage < 2){
                        if(caseP.toString().equals("=")){
                            utilPassage+=1;
                            CasePassage casePassage = (CasePassage) caseP;
                            caseP = casePassage.getCaseLiee();
                        }else{
                            caseP = caseP.getCaseGauche();
                        }
                    }else{
                        System.out.println("La pierre s'est perdue dans les méandres des passages");
                    } 
                }while(caseP.toString().equals(" ") || caseP.toString().equals("=") && caseP.nCol == 0 && utilPassage < 2 && !caseP.getCaseGauche().toString().equals(">"));
                break;

            case "s":
                do{
                    if(utilPassage < 2){
                        if(caseP.toString().equals("=")){
                            utilPassage+=1;
                            CasePassage casePassage = (CasePassage) caseP;
                            caseP = casePassage.getCaseLiee();
                        }else{
                            caseP = caseP.getCaseBas();
                        }
                    }else{
                        System.out.println("La pierre s'est perdue dans les méandres des passages");
                    }  
                }while(caseP.toString().equals(" ") && (caseP.nCol == 0 || caseP.nCol != caseP.getPlateau().getNbCol()-1) || caseP.toString().equals("=") && utilPassage < 2);
                break;

            case "d":
            do{
                if(utilPassage < 2){
                    if(caseP.toString().equals("=")){
                        utilPassage+=1;
                        CasePassage casePassage = (CasePassage) caseP;
                        caseP = casePassage.getCaseLiee();
                    }else{
                        caseP = caseP.getCaseDroite();
                    }
                }else{
                    System.out.println("La pierre s'est perdue dans les méandres des passages");
                }
            }while(caseP.toString().equals(" ") && (caseP.nCol == 0 || caseP.nCol != caseP.getPlateau().getNbCol()-1) || caseP.toString().equals("=") && utilPassage < 2);
            break;
        }
        if(utilPassage<2){
            switch(caseP.toString()){
                case "|":
                    System.out.println("La pierre a rencontré un obstacle"); 
                    break;
                case "X":
                    System.out.println("La pierre a rencontré une mine. Elle a explosée et est donc désactivée"); 
                    caseP.setTraverse();
                    break;
                case "O":
                    System.out.println("La pierre a rencontré tas de caillou, elle est temporairement perdue");
                    CasePierre casePierre = (CasePierre) caseP; 
                    casePierre.setNbPierre(casePierre.getNbPierre()+1);
                    casePierre.setPrise(false);
                    break;
                case "■":
                    System.out.println("Il ne s'est rien passé");
                    break;
                case ">":
                    System.out.println("Il ne s'est rien passé");
                    break;
            }
        }        
    }
}