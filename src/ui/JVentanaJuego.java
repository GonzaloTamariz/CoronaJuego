package ui;

import domain.Juego;
import domain.Tablero;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class JVentanaJuego extends JFrame {
    private PanelJuego paneljuego;

    private Tablero tablero;
    private Juego juego;
    private JLabel lblresultados;
    public JVentanaJuego(Juego juego){

        super("Coronajuego");
        this.setSize(1200, 1030);
        this.juego=juego;
        //PANEL NORTE
        JPanel pnlNorte = new JPanel();
        lblresultados= new JLabel("Victorias 0   -   0 Derrotas");

        JButton btnayuda = new JButton("Ayuda");
        btnayuda.setFocusable(false);

        btnayuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(
                        JVentanaJuego.this,
                        "¡Bienvenido a Coronajuego! Tu objetivo es encerrar al coronavirus para librar al" +
                                " mundo.\nPara ello deberás mover las piedras hasta conseguir que este totalmente" +
                                " encerrado\n" +
                                "El virus es débil y, por lo tanto, el no podrá mover las piedras. Ten cuidado porque" +
                                " él tratará de perseguirte.\n" +
                                "¡Para moverte debes pulsar las teclas de direccion! \n ↑ o W    Arriba\n ↓ o S   " +
                                " Abajo" +
                                "\n → o D    Derecha\n ← o A    Izquierda");  }
        });
        pnlNorte.add(lblresultados, BorderLayout.CENTER);
        pnlNorte.add(btnayuda, BorderLayout.EAST);
        //PANEL PRINCIPAL-PANEL JUEGO
        paneljuego=new PanelJuego(juego);
        //Panel sur
        JPanel pnlSur = new JPanel(new GridLayout(1,2));
        JLabel lblMensaje1 = new JLabel("Coronajuego");
        JLabel lblMensaje2 = new JLabel(" Gonzalo Tamariz-Martel Sánchez 2ºB GITT");
        pnlSur.add(lblMensaje1);
        pnlSur.add(lblMensaje2);
        //KEY LISTENERS
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke){
                JVentanaJuego.this.juego.gestionarEventosTeclado(ke);
            }



        });
        //Añado paneles
        this.add(pnlNorte,BorderLayout.NORTH);
        this.add(paneljuego, BorderLayout.CENTER);
        this.add(pnlSur, BorderLayout.SOUTH);
        //Mostramos ventana
        this.requestFocus();
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }


    public PanelJuego getPaneljuego() {
        return paneljuego;
    }
    public void actualizarResultados(int victorias, int derrotas){
        lblresultados.setText("Victorias "+victorias+"   -   "+derrotas+" Derrotas");
    }


}
