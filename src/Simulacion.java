
public class Simulacion {

    private final double[] ri1, ri2, ri3, ri4, ri5, ri6;
    private double[] xllegadas, xllegadasCuadruple, horaLlegadas, horaLlegadasCuadrupe, salidaInsp, entradas, tInsp,
            horaEntradaRep, horaSalidaRep, tiempoReparacion, tiempoTE, timepoTotalSistema;
    private double[] xUDiscreta;
    private String[] descompostura;
    private double[] tiempoEspInsp;

    public Simulacion(double[] ri1, double[] ri2, double[] ri3, double[] ri4, double[] ri5, double[] ri6) {
        this.ri1 = ri1;// llegadas
        this.ri2 = ri2;// Inspeccion
        this.ri3 = ri3;// Descompostura
        this.ri4 = ri4;// Reparacion
        this.ri5 = ri5;// Llegadas cuadruples
        this.ri6 = ri6;// Generamos variables Uniformes discretas(2,8)
        // Arreglos de llegadas
        xllegadas = new double[ri1.length];
        horaLlegadas = new double[ri1.length];
        horaLlegadasCuadrupe = new double[ri1.length];
        // Arreglos inspeccion
        salidaInsp = new double[ri2.length];
        entradas = new double[ri2.length];
        tInsp = new double[ri2.length];
        // Arreglo de que autos necesitaron compostura
        descompostura = new String[ri3.length];
        // Arreglos de reparacion
        horaEntradaRep = new double[ri4.length];
        horaSalidaRep = new double[ri4.length];
        tiempoReparacion = new double[ri4.length];
        tiempoTE = new double[ri4.length];
        // Llegadas cuadruples
        xllegadasCuadruple = new double[ri5.length];
        // Arreglo para Uniforme Discreta
        xUDiscreta = new double[ri6.length];

        // Tiempo en inspeccion
        tiempoEspInsp = new double[ri1.length];
        // Tiempo total en el sistema
        timepoTotalSistema = new double[ri1.length];

    }

    public void run() {
        llegadas();
        inspeccion();
        tiempoEspInsp();
        descompostura();
        reparación();

        impresionSimulacion();
        impresionReparacion();
        tiempoEnSistema();
        impresionTimeSistema();
    }

    /*
     * ===============================================OPERACIONES===================
     * ==============================================================
     */
    // Llegadas con una exponencial de 2 minutos
    private void llegadas() {
        int i = 0;

        while (i < ri1.length) {
            xllegadas[i] = -2 * Math.log(ri1[i]);

            // Vamos sumando el anterior
            if (i == 0) {
                horaLlegadas[i] = xllegadas[i];
            } else {
                horaLlegadas[i] = horaLlegadas[i - 1] + xllegadas[i];
            }
            i++;
        }
    }

    private void inspeccion() {

        // Calculo de tiempo de inspeccion
        // ri2 son nuestro nuevos numeros aleatorios

        entradas[0] = horaLlegadas[0];
        int i = 0;
        while (i < horaLlegadas.length) {
            if (i == 0) {
                tInsp[i] = 0.25 + (1.05 - 0.25) * ri2[i];
                salidaInsp[i] = entradas[i] + tInsp[i];
                // Calculamos la variable aleatoria

            } else {
                if (horaLlegadas[i] >= salidaInsp[i - 1]) {
                    tInsp[i] = 0.25 + (1.05 - 0.25) * ri2[i];
                    entradas[i] = horaLlegadas[i];
                    salidaInsp[i] = entradas[i] + tInsp[i];

                } else {
                    tInsp[i] = 0.25 + (1.05 - 0.25) * ri2[i];
                    entradas[i] = salidaInsp[i - 1];
                    salidaInsp[i] = entradas[i] + tInsp[i];

                }
            }

            i++;
        }

    }

    private void tiempoEspInsp() {
        int i = 0;
        while (i < ri1.length) {
            if (i != 0) {
                if (salidaInsp[i - 1] < horaLlegadas[i]) {
                    tiempoEspInsp[i] = 0;
                } else {
                    tiempoEspInsp[i] = salidaInsp[i - 1] - horaLlegadas[i];
                }
            } else {
                tiempoEspInsp[i] = 0;
            }

            i++;
        }
    }

    private void descompostura() {
        // reparación prob
        // si 0.3 0.3 0-0.3
        // no 0.7 1 0.3-1

        int i = 0;
        while (i < ri3.length) {
            if (ri3[i] >= 0.0 && ri3[i] <= 0.3) {
                descompostura[i] = "Si";
            } else if (ri3[i] >= 0.3 && ri3[i] <= 1) {
                descompostura[i] = "No";
            }
            i++;
        }

    }

    private void reparación() {

        int i = 0, a = 0;
        while (i < descompostura.length) {
            if (descompostura[i] == "Si") {
                horaEntradaRep[a] = salidaInsp[i];
                tiempoReparacion[a] = 2.1 + (4.5 - 2.1) * ri4[a];
                horaSalidaRep[a] = (horaEntradaRep[a] + tiempoReparacion[a]);
                tiempoTE[a] = Math.abs(xllegadas[a] - horaSalidaRep[a]);
                a++;
            } else {

            }
            i++;
        }

    }

    private void tiempoEnSistema() {

        int i = 0, a = 0;

        while (i < ri1.length) {
            if (descompostura[i] == "Si") {
                timepoTotalSistema[i] = tiempoEspInsp[i] + tInsp[i] + tiempoReparacion[a];
                a++;
            } else {
                timepoTotalSistema[i] = tiempoEspInsp[i] + tInsp[i];
            }
            i++;
        }
        
    }

    /*
     * ===============================================FIN
     * OPERACIONES==================================================================
     * ===========
     */

    private void impresionSimulacion() {
        System.out.printf("%33s", "----------Llegadas----------");
        System.out.printf("%65s", "--------------------Inspeccion--------------------");
        System.out.printf("%59s", "----------Descompostura----------\n");
        // Cabezera de llegadas
        System.out.print(String.format("%s", "No") +
                String.format("%10s", "Ri") + String.format("%18s", "-2*LN(Ri)") +
                String.format("%15s", "Llegada") + " | ");
        // Cabezera de inpeccion
        System.out.print(
                String.format("%5s", "Entrada") + String.format("%24s", "Tiempo de espera")
                        + String.format("%11s", "Ri") + String.format("%14s", "X")
                        + String.format("%18s", "Hora salida") + " | ");
        System.out.print(
                String.format("%5s", "Ri") + String.format("%21s", "¿Necesita?"));
        System.out.println(" ");
        int i = 0;
        int a = 0;
        while (i < ri1.length) {
            // Impresiones llegadas
            System.out.print(
                    String.format("%d", (i + 1)) + String.format("%15f", ri1[i]) + String.format("%14f", xllegadas[i])
                            + String.format("%15f", horaLlegadas[i]));

            System.out.print(" | ");
            // Impresion Inspeccion
            System.out.print(String.format(String.format("%5f", entradas[i]) + "%20f", tiempoEspInsp[i])
                    + String.format("%18f", ri2[i])
                    + String.format("%14f", tInsp[i]) + String.format("%14f", salidaInsp[i]));

            System.out.print(" | ");
            // Impresion de descompostura
            System.out.print(String.format("%5f", ri3[i]) + String.format("%14s", descompostura[i]));
            System.out.print(" | ");

            System.out.println(" ");
            i++;
        }
    }

    private void impresionReparacion() {
        System.out.println(" ");
        System.out.printf("%25s", "------------------------------REPARACION------------------------------\n");
        System.out.println(
                String.format("%5s", "Hora de entrada") + String.format("%18s", "Ri")
                        + String.format("%21s", "X") + String.format("%28s", "Hora salida")
                        + String.format("%26s", "Tiempo Estimado"));
        int i = 0;
        while (i < ri1.length) {
            if (horaEntradaRep[i] != 0.0) {
                System.out.println(String.format("%7f", horaEntradaRep[i]) + String.format("%27f", ri4[i])
                        + String.format("%23f", tiempoReparacion[i]) + String.format("%23f", horaSalidaRep[i])
                        + String.format("%23f", tiempoTE[i]));
            } else {
                break;
            }
            i++;
        }
    }

    private void impresionTimeSistema() {
        System.out
                .println("\n" + String.format("%5s", "No carro") + String.format("%30s", "Tiempo total en el sistema"));
        int i = 0;
        while (i < ri1.length) {
            System.out.println(String.format("%5d", (i + 1)) + String.format("%25f", timepoTotalSistema[i]));
            i++;
        }
    }

}