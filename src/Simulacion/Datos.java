package Simulacion;

public class Datos {
    private double[] pruebas1, pruebas2, pruebas3, pruebas4, pruebas5, pruebas6;
    private int contador = 0, i, a;

    public Datos(int p1, int p2, int p3, int p4) {
        // inicializamos los el arreglo
        pruebas1 = new double[p1];
        pruebas2 = new double[p2];
        pruebas3 = new double[p3];
        pruebas4 = new double[p4];
    }

    public void run() {
        generacionDatos();
    }

    // Generamos los numeros aleatorios
    private void generacionDatos() {
        // Datos de nuestras pruebas
        Generador g1 = new Generador();
        Generador g2 = new Generador();
        Generador g3 = new Generador();
        Generador g4 = new Generador();

        // Datos para generar los numeros con las convinaciones ya dadas
        // generamos 256 numeros con las siguientes convinaciones


        g1.datos(41, 1203, 16);
        g2.datos(295, 419, 16);
        g3.datos(43, 1003, 16);
        g4.datos(121, 653, 16);


        asignacionArreglos(g1.getNumGenerados(), g2.getNumGenerados(), g3.getNumGenerados(), g4.getNumGenerados());
    }

    // el metodo resive como datos los arreglos con las numeros generados
    private void asignacionArreglos(double[] g1, double[] g2, double[] g3, double[] g4) {
        // llamamos al metodo para que
        // le demos arreglo, y tamaño de prueba
        pruebas1 = tomaPrueba(g1, pruebas1);
        pruebas2 = tomaPrueba(g2, pruebas2);
        pruebas3 = tomaPrueba(g3, pruebas3);
        pruebas4 = tomaPrueba(g4, pruebas4);

    }

    // enviamos arreglo y arreglo para tomar el tamaño de la muestra a tomar
    // arreglo completo, tamaño de la muestra
    private double[] tomaPrueba(double[] arreglo, double[] prueba) {
        i = 0;
        a = 0;
        contador = 0;
        while (i < arreglo.length) {
            // como son 256 numeros tomamos desde 19
            if (i > 20) {
                if (contador < prueba.length) {
                    prueba[a] = arreglo[i];
                    a++;
                    contador++;
                }
            }

            i++;
        }
        return prueba;
    }

    public double[] getPruebas1() {
        return pruebas1;
    }

    public double[] getPruebas2() {
        return pruebas2;
    }

    public double[] getPruebas3() {
        return pruebas3;
    }

    public double[] getPruebas4() {
        return pruebas4;
    }

}
