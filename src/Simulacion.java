
public class Simulacion {

    private final double[] ri1, ri2, ri3, ri4, ri5, ri6;
    private double[] xllegadas, xllegadasCuadruple, horaLlegadas, horaLlegadasCuadrupe, salidaInsp, entradas, tInsp,
            horaEntradaRep, horaSalidaRep, tiempoReparacion, timepoTotalSistema;
    private int[] autobusesRep, usoEstacion;

    private double[] xUDiscreta;
    private String[] descompostura;
    private double[] tiempoEspInsp, tiempoEspReparacion, autobusDescompuesto;
    // Estaciones de reparacion
    private double A = 0, B = 0, usoA, usoB;
    private double contA = 0, contB = 0, cont = 0;

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
        // Llegadas cuadruples
        xllegadasCuadruple = new double[ri5.length];
        // Arreglo para Uniforme Discreta
        xUDiscreta = new double[ri6.length];
        // Tiempo en inspeccion
        tiempoEspInsp = new double[ri1.length];
        // Tiempo de reparacion
        tiempoEspReparacion = new double[ri1.length];
        // Tiempo total en el sistema
        timepoTotalSistema = new double[ri1.length];

        // Autobuses
        autobusesRep = new int[ri1.length];
        autobusDescompuesto = new double[ri1.length];
        // Estaciones
        usoEstacion = new int[ri1.length];

    }

    // llamamos a los metodos que vamos a usar
    public void run() {
        // llegadas();
        llegadasCuadruple();
        inspeccion();
        // tiempoEspInsp();
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
    // Llegadas con una exponencial de 2 horas
    private void llegadas() {
        int i = 0;
        // realizamos las operaciones
        while (i < ri1.length) {
            // las guardamos en un arreglo para los tiempo
            xllegadas[i] = -2 * Math.log(ri1[i]);

            // Vamos sumando el anterior
            if (i == 0) {
                // La primera ves pasa directamente
                horaLlegadas[i] = xllegadas[i];
            } else {
                // La segunda vez vamos sumando la anterior
                horaLlegadas[i] = horaLlegadas[i - 1] + xllegadas[i];
            }
            i++;
        }
    }

    // llegadas con una media de 30 minutos
    private void llegadasCuadruple() {
        int i = 0;

        while (i < ri1.length) {
            xllegadasCuadruple[i] = -0.5 * Math.log(ri1[i]);
            // Vamos sumando el anterior
            if (i == 0) {
                horaLlegadasCuadrupe[i] = xllegadasCuadruple[i];
            } else {
                horaLlegadasCuadrupe[i] = horaLlegadasCuadrupe[i - 1] + xllegadasCuadruple[i];
            }
            i++;
        }
    }

    private void inspeccion() {

        // Calculo de tiempo de inspeccion
        // ri2 son nuestro nuevos numeros aleatorios diferentes a los ri1
        // Inicializamos la primera hora de llegada con la primera hora de entrada
        entradas[0] = horaLlegadas[0];
        int i = 0;
        while (i < horaLlegadas.length) {
            // El bucle repite hasta terminar con todas las llegadas
            if (i == 0) {
                // Calculamos el tiempo de inspeccion
                tInsp[i] = 0.25 + (1.05 - 0.25) * ri2[i];
                // Luego sumamos la hora de entrada a inspeecion y la hora de entrada del
                // autobus
                salidaInsp[i] = entradas[i] + tInsp[i];

            } else {
                /*
                 * Para el primer caso vemos si hora de llegada es mayor o igual a la hora
                 * ultima
                 * hora de salida
                 */
                if (horaLlegadas[i] >= salidaInsp[i - 1]) {
                    /* Calculamos el tiempo en la inspeccion */
                    tInsp[i] = 0.25 + (1.05 - 0.25) * ri2[i];
                    // asignamos ala hora de entrada la hora de llegada del autobus
                    // esto en caso de que no haya tenido que esperar el autobus
                    tiempoEspInsp[i] = 0;// el tiempo de espera es cero
                    entradas[i] = horaLlegadas[i];
                    // calculamos el tiempo de salida sumando la de entrada y el tiempo de
                    // inspeccion
                    salidaInsp[i] = entradas[i] + tInsp[i];

                } else {
                    // calculamos el tiempo de inspeccion
                    tInsp[i] = 0.25 + (1.05 - 0.25) * ri2[i];
                    /*
                     * calculamos el tiempo de espera en este caso ya que el tiempo de salida de el
                     * anterior autobus es mayor entonces el autobus actual entra cuando salda el
                     * que esta
                     * en la inspeccion
                     */

                    /*
                     * asignamos a nuestro arreglo el tiempo de espera= salida del autobus en
                     * estacion- hora de llegada de autobus
                     */
                    tiempoEspInsp[i] = salidaInsp[i - 1] - horaLlegadas[i];
                    /* Hora de entrada saria la hora de salida del anterior autobus */
                    entradas[i] = salidaInsp[i - 1];
                    // calculamos el tiempo de salida sumando la de entrada y el tiempo de
                    // inspeccion
                    salidaInsp[i] = entradas[i] + tInsp[i];

                }
            }

            i++;
        }

    }

    private void descompostura() {
        // Las probabilidades que un autobus este descompuesto son las siguientes
        // si 0.0 0.3 [0-0.3]
        // no 0.7 1 0.3-1

        int i = 0, a = 0;
        // ciclo que va recorrer todos nuestros numeros aleatorios
        while (i < ri3.length) {
            /*
             * En el caso que nuestro numero aleatorio este en el rango
             * [0.0,0.3] si necesita reparacion
             */
            if (ri3[i] >= 0.0 && ri3[i] <= 0.3) {
                descompostura[i] = "Si";
                // Guardamos el numero del autobus que necesito reparacion
                autobusesRep[a] = i + 1;
                // Guardamos la hora de salida de los autobuses descompuestos
                autobusDescompuesto[a] = salidaInsp[i];
                a++;
            } /* En caso contrario no */
            else if (ri3[i] >= 0.3 && ri3[i] <= 1) {
                descompostura[i] = "No";
            }
            i++;
        }

    }

    private void reparación() {

        int i = 0;
        // Recorremos los estatus de los autobuses que necesitaron reparacion
        while (i < descompostura.length) {
            if (autobusDescompuesto[i] != 0) {
                // En el caso de que sea diferente de cero se hacen las operaciones
                if (i > 0) {
                    // Entra a reparacion
                    // Asignamos la hora de entrada del auto descompuesto a la hora de entrada a
                    // reparacion
                    horaEntradaRep[i] = autobusDescompuesto[i];
                    // vemos si el a<b, osea si el tiempo del auto que entro en al estacion a es
                    // menor que la b
                    // Ó si A< a la hora de entrada del siguiente auto
                    if (A < B || A < horaEntradaRep[i]) {
                        /*
                         * En el caso de que esten ocupadas las dos estaciones, pero el tiempo de
                         * la estacion a es menor que la b se pone en cola el siquiente auto y
                         * calculamos el tiempo de espera
                         */
                        if (A > horaEntradaRep[i]) {
                            // Calculamos el tiempo de espera
                            tiempoEspReparacion[i] = Math.abs(A - horaEntradaRep[i]);
                            // La hora de entrada va ser igual ala salida del auto en la inspeccion
                            horaEntradaRep[i] = A;
                            // Realizamos las operaciones
                            operacionReparacion(i);
                        } /*
                           * En caso de que no sea la primera condicion pasa directamente
                           * ya que estaria desocupada la estacion
                           */
                        else {
                            /* Realizamos las operaciones */
                            operacionReparacion(i);
                            /* A toma el tiempo de salida del acarro actual */
                            A = horaSalidaRep[i];
                        }
                        // Guardamos en el arreglo que estacion se uso, en este caso fue la 1
                        usoEstacion[i] = 1;
                        // Y un contador para sacar la estadistica de uso
                        contA++;
                    } // Esta condicion es igual a la primera solo que estamos tratando con la
                      // estacion B
                    else if (B < A || B < horaEntradaRep[i]) {
                        /*
                         * las operaicones y comentarios son las misma que en la primera condicion,
                         * solo que aqui trabajamos con la estacion B
                         */
                        if (B > horaEntradaRep[i]) {
                            tiempoEspReparacion[i] = Math.abs(A - horaEntradaRep[i]);
                            horaEntradaRep[i] = A;
                            operacionReparacion(i);

                        } else {
                            operacionReparacion(i);
                            B = horaSalidaRep[i];
                        }
                        // Guardamos en el arreglo que estacion se uso, en este caso fue la 2
                        usoEstacion[i] = 2;
                        // Y un contador para sacar la estadistica de uso
                        contB++;
                    }
                } else {
                    horaEntradaRep[i] = autobusDescompuesto[i];
                    operacionReparacion(i);
                    A = horaSalidaRep[i];
                    usoEstacion[i] = 1;
                    contA++;
                }
                i++;
                cont++;
            } // En caso contrario rompemos el bucle
            else {
                break;
            }
        }

    }

    private void operacionReparacion(int i) {
        // Calculamos el tiempo
        tiempoReparacion[i] = 2.1 + (4.5 - 2.1) * ri4[i];
        // Guardamos la hora de salida
        horaSalidaRep[i] = (horaEntradaRep[i] + tiempoReparacion[i]);
    }

    private void tiempoEnSistema() {

        int i = 0, a = 0;

        while (i < ri1.length) {
            // Si el autobus tiene compostura
            if (descompostura[i] == "Si") {
                // Calculamos el tiempo total en el sistema sumando las siguientes tiempos
                // Tiempo de espera inspeccion+ Tiempo de inpeccion + Tiempo en reparacion
                timepoTotalSistema[i] = tiempoEspInsp[i] + tInsp[i] + tiempoReparacion[a];
                a++;
            } else {//En cambio de no haber tenido reparacion
                //Solo calculamos el tiempo de espera en inspeccion y tiempo de la inpeccion
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
        while (i < ri1.length) {
            // // Impresiones llegadas
            // System.out.print(
            //         String.format("%d", (i + 1)) + String.format("%15f", ri1[i]) + String.format("%14f", xllegadas[i])
            //                 + String.format("%15f", horaLlegadas[i]));
            // Impresiones llegadas
            System.out.print(
                    String.format("%d", (i + 1)) + String.format("%15f", ri1[i]) + String.format("%14f", xllegadasCuadruple[i])
                            + String.format("%15f", horaLlegadasCuadrupe[i]));

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

    // Operacion + impresion de los tiempos de reparacion
    private void impresionReparacion() {
        System.out.println(" ");
        System.out.printf("%90s", "------------------------------REPARACION------------------------------\n");
        System.out.println(String.format("%s", "Autobus") +
                String.format("%25s", "Hora de entrada") + String.format("%18s", "Ri")
                + String.format("%21s", "X") + String.format("%28s", "Hora salida")
                + String.format("%28s", "Tiempo de espera") + String.format("%25s", "Uso de estacion"));
        int i = 0;

        // operaciones
        while (i < ri1.length) {
            if (horaEntradaRep[i] != 0.0) {
                System.out.println(String.format("%d", autobusesRep[i]) + String.format("%25f", horaEntradaRep[i])
                        + String.format("%27f", ri4[i])
                        + String.format("%23f", tiempoReparacion[i]) + String.format("%23f", horaSalidaRep[i])
                        + String.format("%23f", tiempoEspReparacion[i]) + String.format("%23d", usoEstacion[i]));
            } else {
                break;
            }
            i++;
        }

        double porcentaje = (double) ((contA) / cont);
        System.out.printf("%s", "Uso de la estacion A es: % " + porcentaje);

        porcentaje = (double) ((contB) / cont);
        System.out.printf("\n%s", "Uso de la estacion B es: % " + porcentaje);

    }

    private void impresionTimeSistema() {
        System.out
                .println("\n" + String.format("%5s", "No autobus")
                        + String.format("%30s", "Tiempo total en el sistema"));
        int i = 0;
        while (i < ri1.length) {
            System.out.println(String.format("%5d", (i + 1)) + String.format("%25f", timepoTotalSistema[i]));
            i++;
        }
    }

}