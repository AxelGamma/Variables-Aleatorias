public class Simulacion {
    private double[] ri1, ri2, ri3, ri4, ri5, ri6;
    private double[] xllegadas, horaLlegadas, salidaInsp, entradas, tInsp;// salida,entrada,tiempo de inspeccion
    private double[] horaEntradaRep, horaSalidaRep, XRep, tiempoTE;
    private String[] descompostura;

    public Simulacion(double[] ri1, double[] ri2, double[] ri3, double[] ri4) {
        this.ri1 = ri1;
        this.ri2 = ri2;
        this.ri3 = ri3;
        this.ri4 = ri4;
        // Arreglos de llegadas
        xllegadas = new double[ri1.length];
        horaLlegadas = new double[ri1.length];
        // Arreglos inspeccion
        salidaInsp = new double[ri2.length];
        entradas = new double[ri2.length];
        tInsp = new double[ri2.length];

        descompostura = new String[ri3.length];
        // Arreglos de reparacion
        horaEntradaRep = new double[ri4.length];
        horaSalidaRep = new double[ri4.length];
        XRep = new double[ri4.length];
        tiempoTE = new double[ri4.length];

    }

    public void run() {

        // llegadas(); // 150
        llegadasCuadruple();
        impresionLlegadas();
        inspeccion();// 150
        descompostura();// 75
        reparación();// 75

    }
    //Llegadas con una exponencial de 2 minutos
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
    //Llegadas con un a exponencial de 30 minutos

    private void llegadasCuadruple() {
        int i = 0;

        while (i < ri1.length) {
            xllegadas[i] = -30 * Math.log(ri1[i]);

            // Vamos sumando el anterior
            if (i == 0) {
                horaLlegadas[i] = xllegadas[i];
            } else {
                horaLlegadas[i] = horaLlegadas[i - 1]+xllegadas[i];
            }
            i++;
        }
    }

    private void impresionLlegadas() {
        System.out.println(
                String.format("%5s", "Ri") + String.format("%18s", "-2*LN(Ri)") +
                        String.format("%14s", "Llegada"));
        int i = 0;
        while (i < ri1.length) {
            System.out.print(String.format("%5f", ri1[i]) + String.format("%14f", xllegadas[i])
                    + String.format("%15f", horaLlegadas[i]));
            i++;
            System.out.println(" ");
        }
    }

    private void inspeccion() {

        // Calculo de tiempo de inspeccion
        // ri2 son nuestro nuevos numeros aleatorios

        entradas[0] = xllegadas[0];
        int i = 0;
        while (i < xllegadas.length) {
            // Calculamos la variable aleatoria
            tInsp[i] = 0.25 + (1.05 - 0.25) * ri2[i];

            if (i == 0) {
                salidaInsp[i] = entradas[i] + tInsp[i];
            } else {
                if (xllegadas[i] >= salidaInsp[i - 1]) {
                    entradas[i] = xllegadas[i];
                    salidaInsp[i] = entradas[i] + tInsp[i];

                } else {
                    entradas[i] = salidaInsp[i - 1];
                    salidaInsp[i] = entradas[i] + tInsp[i];

                }
            }

            i++;

        }

    }

    private void impresionInspeccion() {
        System.out.println(
                String.format("%5s", "Entrada") + String.format("%14s", "Ri") + String.format("%14s", "X")
                        + String.format("%14s", "Hora salida"));

        int i;
        i = 0;
        while (i < salidaInsp.length) {
            System.out.println(String.format("%5f", entradas[i]) + String.format("%14f", ri2[i])
                    + String.format("%14f", tInsp[i]) + String.format("%14f", salidaInsp[i]));
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

    private void impresionDespom() {
        System.out.println(
                String.format("%5s", "Ri") + String.format("%21s", "¿Necesita?"));
        int i;
        i = 0;
        while (i < ri3.length) {
            System.out.println(String.format("%5f", ri3[i]) + String.format("%14s", descompostura[i]));
            i++;
        }

    }

    private void reparación() {

        int i = 0, a = 0;
        while (i < descompostura.length) {
            if (descompostura[i] == "Si") {
                horaEntradaRep[a] = salidaInsp[i];
                XRep[a] = 2.1 + (4.5 - 2.1) * ri4[a];
                horaSalidaRep[a] = (horaEntradaRep[a] + XRep[a]);
                tiempoTE[a] = Math.abs(xllegadas[a] - horaSalidaRep[a]);
                a++;
            } else {

            }
            i++;
        }

    }

    private void impresionReparacion() {
        System.out.println(
                String.format("%5s", "Hora de entrada") + String.format("%18s", "Ri")
                        + String.format("%21s", "X") + String.format("%24s", "Hora salida")
                        + String.format("%26s", "Tiempo Estimado"));
        int i = 0;
        while (i < ri4.length) {
            System.out.println(String.format("%7f", horaEntradaRep[i]) + String.format("%27f", ri4[i])
                    + String.format("%23f", XRep[i]) + String.format("%23f", horaSalidaRep[i])
                    + String.format("%23f", tiempoTE[i]));
            i++;
        }
    }

    private void dUD() {
        
    }
}
