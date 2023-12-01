package ui;

import domain.Elemento;
import domain.Juego;
import domain.Tablero;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase para el panel principal. Este es el panel gráfico donde se dibujan los componentes del juego. Actualmente, el
 * ancho y alto son fijos, pero en versiones futuras sería facil redimensionar el panel si fuera necesario.
 * @author Gonzalo Tamariz-Martel Sanchez
 * @version 1.0
 */
public class PanelJuego extends JPanel {
    private Juego juego;
    private Tablero tablero;
    private int ancho=800;
    private int alto=600;
    private int anchoCelda;
    private int  altoCelda;

    /**Construtor de la clase juego. Mantiene una referencia del juego para poder pintar sus componentes.
     * @param juego Objeto tipo Juego
     */
    public PanelJuego(Juego juego){
        super();
        this.juego=juego;
        this.tablero=juego.getTablero();
        this.anchoCelda=(ancho/tablero.getColumnas());
        this.altoCelda=(alto/tablero.getFilas());
        this.setBackground(Color.LIGHT_GRAY);

        this.setPreferredSize(new Dimension(ancho,alto));


}
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        ArrayList<Elemento> elementos=tablero.getElementos();
        Iterator it= elementos.iterator();
        while(it.hasNext()){
            Elemento e=(Elemento)it.next();
            if(e!=null){
                Image imagen = e.getImagen();
                imagen =imagen.getScaledInstance(anchoCelda, altoCelda, 1);
                g.drawImage(imagen, e.getX() * anchoCelda, e.getY() * altoCelda, this);
                //Toolkit.getDefaultToolkit().sync(); //Se recomienda poner para ciertos sistemas operativos
            }

        }
        Font f1= new Font("Dialog", Font.BOLD,ancho/10);
        g.setFont(f1);
        if(juego.getFin()==1){
            g.drawString("¡Has ganado!",ancho/2 - 7*ancho/20,alto/2 );

        }else if(juego.getFin()==-1){
            g.drawString("¡Has perdido!",ancho/2 - 7*ancho/20,alto/2 );
        }
        Font f2= new Font("Dialog", Font.PLAIN,ancho/20);
        g.setFont(f2);
        if(juego.getFin()==1 || juego.getFin()==-1){
            g.drawString("(Pulsa enter o espacio)",ancho/2 - 5*ancho/20,alto/2 +ancho/10 );
        }


    }






}
