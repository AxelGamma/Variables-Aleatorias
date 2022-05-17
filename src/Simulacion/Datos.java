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

        //Replica 1
        
       /*  g1.datos(41, 1203, 16);
        g2.datos(295, 419, 16);
        g3.datos(43, 1003, 16);
        g4.datos(121, 653, 16); */

        //Replica 2

        g1.datos(223, 733, 16);
        g2.datos(377, 611, 16);
        g3.datos(541, 1013, 16);
        g4.datos(357, 283, 16);

        //Replica3

        /* g1.datos(295, 339, 16);
        g2.datos(129, 779, 16);
        g3.datos(71, 709, 16);
        g4.datos(237, 477, 16); */

        //Replica 4

        /* g1.datos(105, 117, 16);
        g2.datos(193, 237, 16);
        g3.datos(341, 2803, 16);
        g4.datos(467, 245, 16); */

        //Replica 5

        /* g1.datos(533, 6803, 16);
        g2.datos(685, 5997, 16);
        g3.datos(717, 8043, 16);
        g4.datos(777, 20003, 16); */
        
        //Replica 6
        
        /* g1.datos(697, 14157, 16);
        g2.datos(839, 15187, 16);
        g3.datos(35, 13941, 16);
        g4.datos(509, 1109, 16); */
        
        //Replica 7
        
        /* g1.datos(469, 9843, 16);
        g2.datos(17, 24981, 16);
        g3.datos(679, 25227, 16);
        g4.datos(111, 21349, 16); */

        //Replica 8

        /* g1.datos(191, 7101, 16);
        g2.datos(245, 11515, 16);
        g3.datos(749, 31563, 16);
        g4.datos(291, 9035, 16); */

        //Replica 9

        /* g1.datos(823, 18723, 16);
        g2.datos(195, 27603, 16);
        g3.datos(993, 6387, 16);
        g4.datos(321, 10437, 16); */

        //Replica 10
        /* g1.datos(361, 13421, 16);
        g2.datos(487, 19827, 16);
        g3.datos(635, 7467, 16);
        g4.datos(535, 8755, 16); */



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
