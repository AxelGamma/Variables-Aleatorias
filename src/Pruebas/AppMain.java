package Pruebas;

public class AppMain {

    public static void main(String[] args) throws Exception {

        int[][] pruebas;

        inputDatos datos = new inputDatos();
        datos.input();

        pruebas = datos.getPruebas();
        boolean[] estados = new boolean[4];

        int i = 0;
        while (i < pruebas.length) {
            // Creamos objeto de la clase generador
            Generador generador = new Generador();
            generador.datos(pruebas[i][0], pruebas[i][1], pruebas[i][2], datos.getSizeMuestra());

            generador.generador();
            estados[0] = generador.getEsCompleto();
            // Fin de la generador

            // Prueba de la chiCuadrada
            ChiCuadrada chi = new
                    ChiCuadrada(generador.getMuestraA(), datos.getNumPruebas());
            chi.resultados();
            estados[1] = chi.getEstadoPrueba();
            //Prueba de huecos
            Huecos huecos = new Huecos(generador.getMuestraA());
            huecos.pruebaH();
            estados[2] = huecos.getEstadoPrueba();

            // Prueba de poker
            Poker poker = new Poker(generador.getMuestraA());
            poker.muestra();
            estados[3] = poker.getEstadoPrueba();
            //Nos imprime si paso la prueba o no
            System.out.println("Prueba: "+(i+1));
            int a = 0;
            while (a < 4) {
                System.out.print(String.format("%d", a + 1) + String.format("%10b\n",
                        estados[a]));
                a++;
            }


            i++;

        }


    }

}
