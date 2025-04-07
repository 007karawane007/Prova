import puzzleboard.*;
public class rompicapo {

    private final int dimensione;
    private final int m[][];
    private final String config[][];
    private final PuzzleBoard gui;

    public rompicapo(int n){
        dimensione=n;
        m = new int [n+1][n+1];
        config = new String [n+1][n+1];
        int[]v = new int[n*n];
        int k = 0;
        gui = new PuzzleBoard( dimensione );
        for(int i=0; i<=(dimensione*dimensione)-1; i++){
            v[i]=-1;
        }
        while(k!=(dimensione*dimensione)){
            double srand = Math.random()*(dimensione*dimensione);
            int random = (int)srand;
            if(v[random] == -1){
                v[random]=k;
                k++;
            }
        }
        int z=0;
        while(z!=(dimensione*dimensione)){
            for(int i=1; i<=dimensione; i++){
                for(int j=1; j<=dimensione; j++){
                       m[i][j]=v[z];
                       z++;
                }
            }
        }
        for(int i=1; i<=dimensione; i++){
            for(int j=1; j<=dimensione; j++){
                k=m[i][j];
                gui.setNumber( i,j, k );
            }
        }
        for(int i=1; i<=dimensione; i++){
            for(int j=1; j<=dimensione; j++){
                config[i][j]="" + m[i][j];
            }
        }
        gui.display();
    }

    public boolean ordinato(){
        int mem = m[1][1];
        for(int i=1; i<dimensione; i++){
            for(int j=1; j<=dimensione; j++){
                if(mem<=m[i][j]){
                    mem=m[i][j];
                }else{
                    return false;
                }
            }
        }
        for(int j=1; j<dimensione; j++){
            if(mem<=m[dimensione][j]){
                    mem=m[dimensione][j];
                }else{
                    return false;
                }
        }
        if(m[dimensione][dimensione] != 0){
            return false;
        }
        return true;
    }

    public boolean possibileSpostare(){
        int k = gui.get();
        System.out.println("k=" + k);
        int i=0; 
        int j=0;
        for(int x=1; x<=dimensione; x++){
            for(int y=1; y<=dimensione; y++){
                if(k == m[x][y]){
                    i=x;
                    j=y;
                }
            }
        }
        if(i == 1 && j == 1){//casi angolo
            if(m[i+1][j]>0 && m[i][j+1]>0){
                return false;
            }else{
                return true;
            }
        }
        if(i == 1 && j == dimensione){//casi angolo
            if(m[i+1][j]>0 && m[i][j-1]>0){
                return false;
            }else{
                return true;
            }
        }
        if(i == dimensione && j == 1){//casi angolo
            if(m[i-1][j]>0 && m[i][j+1]>0){
                return false;
            }else{
                return true;
            }
        }
        if(i == dimensione && j == dimensione){//casi angolo
            if(m[i-1][j]>0 && m[i][j-1]>0){
                return false;
            }else{
                return true;
            }
        }
        if(i == 1){//casi lato
            if(m[i+1][j]>0 && m[i][j+1]>0 && m[i][j-1]>0){
                return false;
            }else{
                return true;
            }
        }
        if(i == dimensione){//casi lato
            if(m[i-1][j]>0 && m[i][j+1]>0 && m[i][j-1]>0){
                return false;
            }else{
                return true;
            }
        }
        if(j == 1){//casi lato
            if(m[i+1][j]>0 && m[i][j+1]>0 && m[i-1][j]>0){
                return false;
            }else{
                return true;
            }
        }
        if(j == dimensione){//casi lato
            if(m[i+1][j]>0 && m[i][j-1]>0 && m[i-1][j]>0){
                return false;
            }else{
                return true;
            }
        }
        if(m[i+1][j]>0 && m[i-1][j]>0 && m[i][j+1]>0 && m[i][j-1]>0){//caso generico
            return false;
        }else{
            return true;
        }
    }

    public void configurazione(){
        for(int i=1; i<=this.dimensione; i++){
            for(int j=1; j<=this.dimensione; j++){
                System.out.print("[" + this.config[i][j] + "] ");
            }
        }
    }

    private void spostamento(rompicapo r){
        if(r.ordinato()){
            System.out.println("Hai vinto!");
        }else{
            int k = r.gui.get();
            //System.out.println("k=" + k);
            int i=0; 
            int j=0;
            for(int x=1; x<=r.dimensione; x++){
                for(int y=1; y<=r.dimensione; y++){
                    if(k == r.m[x][y]){
                        i=x;
                        j=y;
                    }
                }
            }
            if(i == 1 && j == 1){//casi angolo
                if(r.m[i+1][j]==0){
                    gui.setNumber( i+1,j, k );
                    r.m[i+1][j]=r.m[i][j];
                    gui.clear( i,j );
                    r.m[i][j]=0;
                    r.config[i+1][j] = r.config[i][j];
                    r.config[i][j]="0";
                }else if(i == 1 && j == 1){
                    if(r.m[i][j+1]==0){
                        gui.setNumber( i,j+1, k );
                        r.m[i][j+1]=r.m[i][j];
                        gui.clear( i,j );
                        r.m[i][j]=0;
                        r.config[i][j+1] = r.config[i][j];
                        r.config[i][j]="0";
                    }
                }
            }
            if(i == 1 && j == r.dimensione){//casi angolo
                if(r.m[i+1][j]==0){
                    gui.setNumber( i+1,j, k );
                    r.m[i+1][j]=r.m[i][j];
                    gui.clear( i,j );
                    r.m[i][j]=0;
                    r.config[i+1][j] = r.config[i][j];
                    r.config[i][j]="0";
                }else if(i == 1 && j == r.dimensione){
                    if(r.m[i][j-1]==0){
                        gui.setNumber( i,j-1, k );
                        r.m[i][j-1]=r.m[i][j];
                        gui.clear( i,j );
                        r.m[i][j]=0;
                        r.config[i][j-1] = r.config[i][j];
                        r.config[i][j]="0";                   
                    }
                }
            }
            if(i == r.dimensione && j == 1){//casi angolo
                if(r.m[i-1][j]==0){
                    gui.setNumber( i-1,j, k );
                    r.m[i-1][j]=r.m[i][j];
                    gui.clear( i,j );
                    r.m[i][j]=0;
                    r.config[i-1][j] = r.config[i][j];
                    r.config[i][j]="0";
                }else if(i == r.dimensione && j == 1){
                    if(r.m[i][j+1]==0){
                        gui.setNumber( i,j+1, k );
                        r.m[i][j+1]=r.m[i][j];
                        gui.clear( i,j );
                        r.m[i][j]=0;
                        r.config[i][j+1] = r.config[i][j];
                        r.config[i][j]="0";
                    }
                }
            }
            if(i == r.dimensione && j == r.dimensione){//casi angolo
                if(r.m[i-1][j]==0){
                    gui.setNumber( i-1,j, k );
                    r.m[i-1][j]=r.m[i][j];
                    gui.clear( i,j );
                    r.m[i][j]=0;
                    r.config[i-1][j] = r.config[i][j];
                    r.config[i][j]="0";
                }else if(i == r.dimensione && j == r.dimensione){
                    if(r.m[i][j-1]==0){
                        gui.setNumber( i,j-1, k );
                        r.m[i][j-1]=r.m[i][j];
                        gui.clear( i,j );
                        r.m[i][j]=0;
                        r.config[i][j-1] = r.config[i][j];
                        r.config[i][j]="0";
                    }
                }
            }
            if(i == 1 && j!=1 && j!=r.dimensione){//casi lato
                if(r.m[i+1][j]==0){
                    gui.setNumber( i+1,j, k );
                    r.m[i+1][j]=r.m[i][j];
                    gui.clear( i,j );
                    r.m[i][j]=0;
                    r.config[i+1][j] = r.config[i][j];
                    r.config[i][j]="0";
                }else if(i == 1 && j!=1 && j!=r.dimensione){
                    if(r.m[i][j+1]==0){
                        gui.setNumber( i,j+1, k );
                        r.m[i][j+1]=r.m[i][j];
                        gui.clear( i,j );
                        r.m[i][j]=0;
                        r.config[i][j+1] = r.config[i][j];
                        r.config[i][j]="0";
                    }else if(i == 1 && j!=1 && j!=r.dimensione){
                        if(r.m[i][j-1]==0){
                            gui.setNumber( i,j-1, k );
                            r.m[i][j-1]=r.m[i][j];
                            gui.clear( i,j );
                            r.m[i][j]=0;
                            r.config[i][j-1] = r.config[i][j];
                            r.config[i][j]="0";
                        }
                    }
                }
            }
            if(i == r.dimensione && j!=1 && j!=r.dimensione){//casi lato
                if(r.m[i-1][j]==0){
                    gui.setNumber( i-1,j, k );
                    r.m[i-1][j]=r.m[i][j];
                    gui.clear( i,j );
                    r.m[i][j]=0;
                    r.config[i-1][j] = r.config[i][j];
                    r.config[i][j]="0";
                }else if(i == r.dimensione && j!=1 && j!=r.dimensione){
                    if(r.m[i][j+1]==0){
                        gui.setNumber( i,j+1, k );
                        r.m[i][j+1]=r.m[i][j];
                        gui.clear( i,j );
                        r.m[i][j]=0;
                        r.config[i][j+1] = r.config[i][j];
                        r.config[i][j]="0";
                    }else if(i == r.dimensione && j!=1 && j!=r.dimensione){
                        if(r.m[i][j-1]==0){
                            gui.setNumber( i,j-1, k );
                            r.m[i][j-1]=r.m[i][j];
                            gui.clear( i,j );
                            r.m[i][j]=0;
                            r.config[i][j-1] = r.config[i][j];
                            r.config[i][j]="0";
                        }
                    }
                }
            }
            if(j == 1 && i!=1 && i!=r.dimensione){//casi lato
                if(r.m[i+1][j]==0){
                    gui.setNumber( i+1,j, k );
                    r.m[i+1][j]=r.m[i][j];
                    gui.clear( i,j );
                    r.m[i][j]=0;
                    r.config[i+1][j] = r.config[i][j];
                    r.config[i][j]="0";
                }else if(j == 1 && i!=1 && i!=r.dimensione){
                    if(r.m[i][j+1]==0){
                        gui.setNumber( i,j+1, k );
                        r.m[i][j+1]=r.m[i][j];
                        gui.clear( i,j );
                        r.m[i][j]=0;
                        r.config[i][j+1] = r.config[i][j];
                        r.config[i][j]="0";
                    }else if(j == 1 && i!=1 && i!=r.dimensione){
                        if(r.m[i-1][j]==0){
                            gui.setNumber( i-1,j, k );
                            r.m[i-1][j]=r.m[i][j];
                            gui.clear( i,j );
                            r.m[i][j]=0;
                            r.config[i-1][j] = r.config[i][j];
                            r.config[i][j]="0";   
                        }
                    }
                }
            }
            if(j == r.dimensione && i!=1 && i!=r.dimensione){//casi lato
                if(r.m[i+1][j]==0){
                    gui.setNumber( i+1,j, k );
                    r.m[i+1][j]=r.m[i][j];
                    gui.clear( i,j );
                    r.m[i][j]=0;
                    r.config[i+1][j] = r.config[i][j];
                    r.config[i][j]="0";
                }else if(j == r.dimensione && i!=1 && i!=r.dimensione){
                    if(r.m[i][j-1]==0){
                        gui.setNumber( i,j-1, k );
                        r.m[i][j-1]=r.m[i][j];
                        gui.clear( i,j );
                        r.m[i][j]=0;
                        r.config[i][j-1] = r.config[i][j];
                        r.config[i][j]="0";
                    }else if(j == r.dimensione && i!=1 && i!=r.dimensione){
                        if(r.m[i-1][j]==0){
                            gui.setNumber( i-1,j, k );
                            r.m[i-1][j]=r.m[i][j];
                            gui.clear( i,j );
                            r.m[i][j]=0;
                            r.config[i-1][j] = r.config[i][j];
                            r.config[i][j]="0";
                        }
                    }
                }
            }
            if(!(i==1 && j==1) && !(i==1 && j==r.dimensione) && !(i==r.dimensione && j==1) && !(i==r.dimensione && j==r.dimensione)
                && i!=1 && j!=1 && i!=r.dimensione && j!=r.dimensione){//caso generico
                if(r.m[i+1][j]==0){
                    gui.setNumber( i+1,j, k );
                    r.m[i+1][j]=r.m[i][j];
                    gui.clear( i,j );
                    r.m[i][j]=0;
                    r.config[i+1][j] = r.config[i][j];
                    r.config[i][j]="0";
                }else if((!((i==1 && j==1) && (i==1 && j==r.dimensione) && (i==r.dimensione && j==1) &&
                (i==r.dimensione && j==r.dimensione) && i==1 && j==1 && i==r.dimensione && j==r.dimensione))){
                    if(r.m[i-1][j]==0){
                        gui.setNumber( i-1,j, k );
                        r.m[i-1][j]=r.m[i][j];
                        gui.clear( i,j );
                        r.m[i][j]=0;
                        r.config[i-1][j] = r.config[i][j];
                        r.config[i][j]="0";
                    }else if((!((i==1 && j==1) && (i==1 && j==r.dimensione) && (i==r.dimensione && j==1) &&
                (i==r.dimensione && j==r.dimensione) && i==1 && j==1 && i==r.dimensione && j==r.dimensione))){
                        if(r.m[i][j+1]==0){
                            gui.setNumber( i,j+1, k );
                            r.m[i][j+1]=r.m[i][j];
                            gui.clear( i,j );
                            r.m[i][j]=0;
                            r.config[i][j+1] = r.config[i][j];
                            r.config[i][j]="0";
                        }else if((!((i==1 && j==1) && (i==1 && j==r.dimensione) && (i==r.dimensione && j==1) &&
                (i==r.dimensione && j==r.dimensione) && i==1 && j==1 && i==r.dimensione && j==r.dimensione))){
                        if(r.m[i][j-1]==0){
                            gui.setNumber( i,j-1, k );
                            r.m[i][j-1]=r.m[i][j];
                            gui.clear( i,j );
                            r.m[i][j]=0;
                            r.config[i][j-1] = r.config[i][j];
                            r.config[i][j]="0";
                        }
                    }
                }
            }}
            gui.display();
        }
    }
    public void iterazione(){
        while(!ordinato()){
            spostamento(this);
        }
    }
}//class rompicapo
