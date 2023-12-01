package domain;
import util.Util;

import java.util.ArrayList;

/**Clase perteneciente al modelo. Contiene los datos del jugador, el coronavirus y las piedras.
 * @author Gonzalo Tamariz-Martel Sanchez
 * @version 1.0
 */

public class Tablero {
    private int columnas;
    private int filas;
    public Jugador jugador;
    public Coronavirus coronavirus;
    private ArrayList<Elemento> elementos;

    /**Construtor de la clase Tablero. Recibe las filas y columnas que tendra dicho tablero y el número de piedras que
     * debe generar de forma aleatoria. Inicializa El jugador y el coronavirus.
     *
     * @param columnas numero de columnas del tablero
     * @param filas numero de filas del tablero
     * @param npiedras numero de piedras a generar de forma aleatoria
     */
    public Tablero(int columnas, int filas,int npiedras){
        this.elementos= new ArrayList<Elemento>();
        this.filas=filas;
        this.columnas=columnas;
        //Genero jugador y coronavirus (parte superior izquierda e infierior derecha)
        this.jugador=new Jugador(0,0);
        this.elementos.add(this.jugador);
        this.coronavirus=new Coronavirus(this.columnas-1, this.filas-1);
        this.elementos.add(this.coronavirus);
        //Genero los obstaculos de forma aleatoria
        this.generarPiedras(npiedras);
    }

    /**Método para destruir la información del tablero e inicializarlo de nuevo. Recibe el numero de piedras
     * que generará de forma aleatoria
     * @param npiedras numero de piedras
     */
    public void inicializar(int npiedras){
        //Me cargo el array anterior
        this.elementos=null;
        elementos=new ArrayList<Elemento>();
        //Genero jugador y coronavirus (parte superior izquierda e infierior derecha)
        this.jugador=new Jugador(0,0);
        this.elementos.add(this.jugador);
        this.coronavirus=new Coronavirus(this.columnas-1, this.filas-1);
        this.elementos.add(this.coronavirus);
        //Genero los obstaculos de forma aleatoria
        this.generarPiedras(npiedras);
    }


    public int getColumnas() {
        return this.columnas;
    }
    public int getFilas() {
        return this.filas;
    }
    public ArrayList<Elemento> getElementos(){
        return elementos;
    }
    public Jugador getJugador(){
        return this.jugador;
    }
    public Coronavirus getCoronavirus(){
        return this.coronavirus;
    }


    private void generarPiedras(int npiedras){
        int contador=0;
        while(contador<npiedras){
            int x=Util.generarPosicionAleatoria(columnas);
            int y=Util.generarPosicionAleatoria(filas);
            if(!this.existeElemento(x,y)){
                Piedra p= new Piedra(x,y);
                elementos.add(p);
                contador++;
            }
       }
        return;
    }
    public Elemento getElemento(int x, int y){
        for(Elemento e: this.elementos){
            if(e.getX()==x && e.getY()==y){
                return e;
            }
        }
        return null;
    }

    /**Método para saber si existe un elemento en la posicion indicada
     * @param x posicion x a buscar
     * @param y posicion y a buscar
     * @return Devuelve true si hay un elemento y false en caso contrario
     */
    public boolean existeElemento(int x, int y){
        for(Elemento e: this.elementos){
            if(e.getX()==x && e.getY()==y){
                return true;
            }
        }
        return false;
    }
}
