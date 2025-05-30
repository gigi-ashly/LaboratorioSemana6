/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package laboratoriosemana6;
import javax.swing.*;
import java.awt.event.*;

import java.util.Random;
/**
 *
 * @author Kurtg
 */

    /**
     */

public class Martinez_Ashly_IDClase {
    private JFrame ventana;
    private JTextArea areaTexto;
    private JTextField campoEntrada;
    private JButton btnJugar, btnLetra, btnCambiar;

    private final String[] palabras;
    private String palabra;
    private char[] oculta;
    private int oportunidades;

    public Martinez_Ashly_IDClase() {
        palabras = new String[10];
        palabra = "";
        oculta = new char[0];
        oportunidades = 5;

        agregarPalabrasIniciales();
        crearVentana();
    }

    private void agregarPalabrasIniciales() {
        palabras[0] = "Honduras";
        palabras[1] = "Programar";
        palabras[2] = "Java";
        palabras[3] = "Amor";
        palabras[4] = "Teclado";
        palabras[5] = "Italia";
        palabras[6] = "Recursos";
        palabras[7] = "Cansancio";
        palabras[8] = "Palabra";
        palabras[9] = "Compatible";
    }

    private void crearVentana() {
        ventana = new JFrame("Juego de Adivinar Palabra");
        ventana.setSize(400, 400);
        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        areaTexto = new JTextArea();
        areaTexto.setBounds(20, 20, 340, 150);
        areaTexto.setEditable(false);
        ventana.add(areaTexto);

        campoEntrada = new JTextField();
        campoEntrada.setBounds(20, 180, 100, 30);
        ventana.add(campoEntrada);

        btnJugar = new JButton("Jugar");
        btnJugar.setBounds(140, 180, 100, 30);
        ventana.add(btnJugar);

        btnLetra = new JButton("Enviar Letra");
        btnLetra.setBounds(250, 180, 120, 30);
        ventana.add(btnLetra);

        btnCambiar = new JButton("Cambiar Palabras");
        btnCambiar.setBounds(20, 230, 350, 30);
        ventana.add(btnCambiar);

        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });

        btnLetra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enviarLetra();
            }
        });

        btnCambiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarPalabras();
            }
        });

        areaTexto.setText("Bienvenido.\nPresiona 'Jugar' para comenzar.");
        ventana.setVisible(true);
    }

    private void iniciarJuego() {
        Random random = new Random();
        int indice = random.nextInt(10); // Siempre hay 10 palabras
        palabra = palabras[indice];
        oculta = new char[palabra.length()];
        int i = 0;
        while (i < palabra.length()) {
            oculta[i] = '_';
            i++;
        }
        oportunidades = 5;
        mostrarEstado("¡Comienza el juego!\n");
    }

    private void enviarLetra() {
        if (palabra.equals("")) {
            areaTexto.setText("Primero presiona 'Jugar'.");
            return;
        }

        String texto = campoEntrada.getText().toUpperCase();
        campoEntrada.setText("");

        if (texto.length() != 1) {
            areaTexto.setText("Por favor, escribe solo una letra.");
            return;
        }

        char letra = texto.charAt(0);
        int aciertos = 0;

        int i = 0;
        while (i < palabra.length()) {
            if (palabra.charAt(i) == letra && oculta[i] == '_') {
                oculta[i] = letra;
                aciertos++;
            }
            i++;
        }

        if (aciertos == 0) {
            oportunidades--;
            mostrarEstado("¡Fallaste! Esa letra no está.\n");
        } else {
            mostrarEstado("¡Le pegaste a una letra!\n");
        }

        String descubierta = new String(oculta);
        if (descubierta.equals(palabra)) {
            areaTexto.append("\n¡Ganaste! La palabra era: " + palabra);
            palabra = "";
        } else {
            if (oportunidades == 0) {
                areaTexto.append("\nPerdiste. La palabra era: " + palabra);
                palabra = "";
            }
        }
    }

    private void cambiarPalabras() {
        String entrada = JOptionPane.showInputDialog("Ingrese 10 palabras separadas por comas:");
        if (entrada != null) {
            String[] nuevas = entrada.toUpperCase().split(",");
            int i = 0;
            while (i < nuevas.length && i < 10) {
                palabras[i] = nuevas[i].trim();
                i++;
            }
            areaTexto.setText("¡Palabras actualizadas!\n");
            for (int j = 0; j < 10; j++) {
                areaTexto.append((j + 1) + ": " + palabras[j] + "\n");
            }
        }
    }

    private void mostrarEstado(String mensajeInicial) {
        String texto = mensajeInicial + "Palabra: ";
        int i = 0;
        while (i < oculta.length) {
            texto += oculta[i] + " ";
            i++;
        }
        texto += "\nOportunidades: " + oportunidades;
        areaTexto.setText(texto);
    }

    public static void main(String[] args) {
        new Martinez_Ashly_IDClase();
    }
}