package Pruebas;

import java.util.Scanner;

public class Generador {
    // Declaramos las variables x0= semilla
    private int xo, a, m, numeroG, semilla, b;
    private int j = 0, i = 0;
    private double[] muestraA;
    private double uniformidad;
    private boolean EsCompleto = false;
    Scanner sc = new Scanner(System.in);

    // metodo que es llamado por el main para introducir los datos: xo,a,b
    public void datos(int xo, int a, int b, int sizeMuestra) {

        this.xo = xo;
        semilla = xo;
        this.a = a;
        this.b = b;
        this.muestraA = new double[sizeMuestra];
    }

    // Creamos un metodo para poder generar los numeros
    public void generador() {
        m = (int) Math.pow(2, b);// 2^10
        i = 0;
        // Imprimimos los numeros generados
        // System.out.println(String.format("%s", "i") + String.format("%10s", "Xo")
        // + String.format("%25s", "Numeros generados") + String.format("%20s",
        // "Uniformidad"));
        // Nuestro bucle empieza en 0 hasta m, en la linea 40 donde se rompe si los se
        // llega a encontrar la semilla
        while (i < m) {
            numeroG = (a * xo) % m;
            uniformidad = (float) xo / m;

            // System.out.println(String.format("%d", i + 1) + String.format("%10d", xo) +
            // String.format("%15d", numeroG)
            // + String.format("%27f", uniformidad));

            // Condicional que hace que el ciclo se detenga al encontar la semilla
            if (numeroG == semilla) {
                break;
            }
            xo = numeroG;
            // arreglo que mas adelante se usa para la
            if (i > 30 && j < muestraA.length) {
                muestraA[j] = uniformidad;
                j++;
            }

            i++;
        } // Si i es igual a los numeros generados entonces se dice que es un periodo
          // completo, en caso contrario no
        if (m / 4 == i + 1) {

            setEsCompleto(true);
        } else {

            setEsCompleto(false);
        }
    }

    public boolean getEsCompleto() {
        return EsCompleto;
    }

    public void setEsCompleto(boolean esCompleto) {
        EsCompleto = esCompleto;
    }

    public double[] getMuestraA() {
        return muestraA;
    }
    
}
