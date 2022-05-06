
import java.util.Scanner;

public class Generador {
    // Declaramos las variables x0= semilla
    private int xo, a, m, numeroG, semilla, b;
    private int i = 0;
    private double uniformidad = 0;
    private double[] numGenerados;

    Scanner sc = new Scanner(System.in);

    public Generador() {

    }

    // metodo que es llamado por el main para introducir los datos: xo,a,b
    public void datos(int xo, int a, int b) {

        this.xo = xo;
        semilla = xo;
        this.a = a;
        this.b = b;
        generador();

    }

    // Creamos un metodo para poder generar los numeros
    public void generador() {
        m = (int) Math.pow(2, b);// 2^10 <- cantidad de numeros a generar
        int size = (int) m / 4;
        numGenerados = new double[size];
        i = 0;

        while (i < m / 4) {
            numeroG = (a * xo) % m;
            uniformidad = (float) xo / m;
            numGenerados[i] = uniformidad;
            // Parte del codigo con el cual vemos los numeros
            // System.out.println(String.format("%d", i + 1) + String.format("%10d", xo) +
            // String.format("%15d", numeroG)
            // + String.format("%27f", uniformidad));

            // Condicional que hace que el ciclo se detenga al encontar la semilla
            if (numeroG == semilla) {
                break;
            }
            xo = numeroG;
            i++;
        } // Si i es igual a los numeros generados entonces se dice que es un periodo
          // completo, en caso contrario no
    }

    public double[] getNumGenerados() {
        return numGenerados;
    }
}
