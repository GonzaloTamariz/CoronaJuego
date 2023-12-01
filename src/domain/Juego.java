package domain;
import ui.JVentanaJuego;
import util.Util;
import java.awt.event.KeyEvent;

/**
 * Clase controladora. Gestiona las reglas del juego y el movimiento del jugador y coronavirus. Además, decide cuando se
 * debe modificar los datos del modelo y se comunica con la vista.
 * @author Gonzalo Tamariz-Martel Sanchez
 * @version 1.0
 */
public class Juego{
    private int victorias;
    private int derrotas;
    private int total;
    private Tablero tablero;
    private JVentanaJuego ventanaJuego;
    private int movimientoX=0;
    private int movimientoY=0;
    private int fin; //-1 SI PIERDO, 0 SI CONTINUA EL JUEGO; +1 SI GANO
    public static final int COLUMNAS = 16;
    public static final int FILAS= 12;
    public int npiedras;
    public static void main(String argv[]){
        new Juego();
    }

    /**Construtor de la clase Juego. No recibe ningun parametro.
     *Genera un nuevo tablero y una nueva JVentanaJuego. Inicializa los parámetros del juego
     * Tiene un game loop infinito para que el juego se repita indefinidamente.
     */
    public Juego(){
        this.tablero = new Tablero(COLUMNAS, FILAS, 40);
        this.ventanaJuego = new JVentanaJuego(this);
        this.fin = 0;
        this.victorias=0;
        this.derrotas=0;
        this.npiedras=40;
        while(true){

            //GAME LOOP
            while (fin == 0) {
                Util.wait((double) 0.3);
                this.moverJugador();
                fin = this.moverCoronavirus();
            }
            if(fin==-1){
                derrotas++;
            }else if(fin==1){
                victorias++;
                if(npiedras>4) {
                    npiedras = npiedras - 2;
                }
            }
            ventanaJuego.actualizarResultados(victorias,derrotas);
            while(fin!=0){
                Util.wait((double) 0.3);
            }

            this.tablero.inicializar(npiedras);



        }
    }

    /**Este método decide las modificaciones oportunas pora el juego en funcion del evento de teclado recibido
     * @param ke Recibe un evento de teclado
     */
    public void gestionarEventosTeclado(KeyEvent ke){
        int tecla=ke.getKeyCode();
        if(fin==0){
            if (tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_A) {
                this.movimientoX = -1;
            }
            if (tecla == KeyEvent.VK_RIGHT || tecla == KeyEvent.VK_D) {
                this.movimientoX = 1;
            }
            if (tecla == KeyEvent.VK_UP || tecla == KeyEvent.VK_W) {
                this.movimientoY = -1;
            }
            if (tecla == KeyEvent.VK_DOWN || tecla == KeyEvent.VK_S) {
                this.movimientoY = 1;
            }
        }else if(fin==1 || fin==-1){
            if (tecla == KeyEvent.VK_SPACE || tecla == KeyEvent.VK_ENTER)
                this.fin = 0;

        }
    }

    /**Metodo para obtener el objeto tablero del Juego
     * @return Devuelve el objeto tablero
     */
    public Tablero getTablero(){
        return this.tablero;
    }


    private void moverJugador(){
        int x = tablero.getJugador().getX();
        int y = tablero.getJugador().getY();
        //MOVIMIENTO HACIA LA DERECHA
        if (movimientoX>0) {
            int x2 = x + 1;
            int flag=0;
            while (x2 < tablero.getColumnas() && flag!=1){
                if (tablero.existeElemento(x2, y)) {
                    x2++;
                }else{
                    flag=1;
                }
            }
            if (x2 == tablero.getColumnas()) {
                // Movimiento imposible pierde turno
            } else {
                for (int i = x2; i > x; i--) {
                    tablero.getElemento(i-1, y).setX(i);
                }
            }
        }
        //MOVIMIENTO HACIA LA IZQUIERDA
        else if (movimientoX < 0) {
            int x2 = x - 1;
            int flag=0;
            while (x2 >= 0 && flag!=1) {
                if (tablero.existeElemento(x2, y)) {
                    x2--;
                }else{
                    flag=1;
                }
            }
            if (x2 == -1) {
                // Movimiento imposible - pierde turno
            } else {
                for (int i = x2; i < x; i++) {
                    tablero.getElemento(i +1, y).setX(i);
                }
            }
        }
        //MOVIMIENTO HACIA ARRIBA
        else if (movimientoY < 0) {
            int y2 = y - 1;
            int flag=0;
            while (y2 >=0 && flag!=1) {
                if (tablero.existeElemento(x, y2)) {
                    y2--;
                }else{
                    flag=1;
                }
            }
            if (y2 == -1) {
                // Movimiento imposible - pierde turno

            } else {
                for (int j = y2; j < y; j++) {
                    tablero.getElemento(x, j + 1).setY(j);
                }
            }
        }
        //MOVIMIENTO HACIA ABAJO
        else if (movimientoY > 0) {
            int y2 = y + 1;
            int flag=0;
            while (y2 < tablero.getFilas() && flag!=1) {
                if (tablero.existeElemento(x, y2)) {
                    y2++;
                }else{
                    flag=1;
                }

            }
            if (y2 == tablero.getFilas()) {
                // Movimiento imposible - pierde turno
            } else {
                for (int j = y2; j > y; j--) {
                    tablero.getElemento(x, j - 1).setY(j);
                }
            }
        }
        movimientoX=0;
        movimientoY=0;
        this.ventanaJuego.getPaneljuego().repaint();
    }
    private int moverCoronavirus (){


        int x=tablero.getCoronavirus().getX();
        int y=tablero.getCoronavirus().getY();
        int x1=tablero.getJugador().getX();
        int y1=tablero.getJugador().getY();
        int dirX=0;
        int dirY=0;
        if(x-x1!=0){
             dirX = ((x - x1) / Math.abs(x - x1));
        }
        if(y-y1!=0){
             dirY = ((y - y1) / Math.abs(y - y1));
        }
        int movido=0;
        //TRATA DE ACERCARSE
        if(dirX!=0) {
            if (!tablero.existeElemento(x - dirX, y)) {
                    tablero.getCoronavirus().setX(x - dirX);
                    movido=1;
            }
        }
        if(dirY!=0 && movido!=1){
            if(!tablero.existeElemento(x , y-dirY)){

                tablero.getCoronavirus().setY(y - dirY);
                movido=1;
            }
        }

        //TRATA DE ALEJARSE (NO HA PODIDO ACERCARSE
        if(movido!=1){
            if (!tablero.existeElemento(x +1, y)  && (x+1)<COLUMNAS){

                tablero.getCoronavirus().setX(x + 1);
                movido = 1;
            }else if(!tablero.existeElemento(x -1, y) && x-1>=0){
                tablero.getCoronavirus().setX(x - 1);
                movido = 1;
            }else if(!tablero.existeElemento(x, y+1) && y+1<FILAS){
                tablero.getCoronavirus().setY(y + dirY);
                movido = 1;
            }else if(!tablero.existeElemento(x,y-1) && y-1>=0) {
                tablero.getCoronavirus().setY(y + dirY);
                movido = 1;
            }
        }
        //COMPRUEBO DERROTA
        if(movido==1){
            int xfinal=tablero.getCoronavirus().getX();
            int yfinal=tablero.getCoronavirus().getY();

            if(tablero.getElemento(xfinal+1, yfinal) instanceof Jugador){
                return -1;
            }else if(tablero.getElemento(xfinal-1, yfinal) instanceof Jugador){
                return -1;
            }else if(tablero.getElemento(xfinal, yfinal+1) instanceof Jugador){
                return -1;
            }else if(tablero.getElemento(xfinal, yfinal-1) instanceof Jugador){
                return -1;
            }
        }

        //SI NO HA PODIDO MOVER ESTA ENCERRADO, HE GANADO
        if(movido==0){
            return 1;
        }
        //En otro caso devuelvo 0, el juego continua
        return 0;

    }
    public int getFin(){
        return this.fin;
    }


}