package Pruebas;

public class ChiCuadrada {
        private double[] muestras;
        private int contador, esperado, rango1, rango2, rango3, rango4, rango5;
        private int sumaObserv = 0;
        private double chiCalculada;
        private int numeroPruebas, conteoPruebas = 0;
        private boolean estadoPrueba = false;

        public ChiCuadrada(double[] muestraA, int numeroPruebas) {
                this.muestras = muestraA;
                this.numeroPruebas = numeroPruebas;
        }

        public int contar(double a, double b) {
                int i = 0;
                contador = 0;
                while (i < muestras.length) {
                        if (muestras[i] >= a && muestras[i] < b) {
                                contador++;
                        }
                        i++;

                }
                sumaObserv += contador;

                return contador;
        }

        public void resultados() {
                this.esperado = muestras.length / 5;
                rango1 = contar(0.000, 0.200);
                rango2 = contar(0.200, 0.400);
                rango3 = contar(0.400, 0.600);
                rango4 = contar(0.600, 0.800);
                rango5 = contar(0.800, 1);
                sumaObserv = rango1 + rango2 + rango3 + rango4 + rango5;
                // Formato de salida
                /*System.out.printf("\n%62s", "-Prueba chiCuadrada-\n");
                System.out.println(String.format("%s", "i") + "" + String.format("%23s", "Rangos") + ""
                                + String.format("%22s", "Observado") + "" + String.format("%22s", "Esperado") + ""
                                + String.format("%22s", "((O-E)^2)/E"));// <-Estadistico de la prueba
                // Valores generados
                System.out.println(String.format("%s", 1) + String.format("%29s", "0.000>= ri < 0.200")
                                + String.format("%12s", rango1) + String.format("%22s", esperado)
                                + String.format("%25f", Math.pow(rango1 - esperado, 2) / rango1));

                System.out.println(String.format("%s", 2) + String.format("%29s", "0.200>= ri < 0.400")
                                + String.format("%12s", rango2) + String.format("%22s", esperado)
                                + String.format("%25f", Math.pow(rango2 - esperado, 2) / rango2));

                System.out.println(String.format("%s", 3) + String.format("%29s", "0.400>= ri < 0.600")
                                + String.format("%12s", rango3) + String.format("%22s", esperado)
                                + String.format("%25f", Math.pow(rango3 - esperado, 2) / rango3));

                System.out.println(String.format("%s", 4) + String.format("%29s", "0.600>= ri < 0.800")
                                + String.format("%12s", rango4) + String.format("%22s", esperado)
                                + String.format("%25f", Math.pow(rango4 - esperado, 2) / rango4));

                System.out.println(String.format("%s", 5) + String.format("%25s", "0.800>= ri < 1")
                                + String.format("%16s", rango5) + String.format("%22s", esperado)
                                + String.format("%25f", Math.pow(rango5 - esperado, 2) / rango5));*/

                chiCalculada = (Math.pow(rango1 - esperado, 2) / rango1) + (Math.pow(rango2 - esperado, 2) / rango2)
                                + (Math.pow(rango3 - esperado, 2) / rango3) + (Math.pow(rango4 - esperado, 2) / rango4)
                                + (Math.pow(rango5 - esperado, 2) / rango5);

                /*System.out.println(String.format("%42d", sumaObserv) + String.format("%22d", esperado * 5)
                                + String.format("%25f = ChiCalculada", chiCalculada));
                System.out.println(estadoPrueba());*/
                estadoPrueba();
        }

        // valor teorico
        // Chicuadrada= (0.05,4)=9.488 <---- grados de livertada que es 5-1=4
        // Chicalculada ó estadistico= sumaObservada < Chi de Tablas, no se rechaza la
        // hipotesis nula
        // (ri~U(0,1))
        private String estadoPrueba() {

                if (chiCalculada < 9.488) {
                        setEstadoPrueba(true);
                        return "Ha pasado la prueba";
                }
                setEstadoPrueba(false);
                return "No pasa la prueba";
        }

        private void setEstadoPrueba(boolean estadoPrueba) {
                this.estadoPrueba = estadoPrueba;
        }

        public boolean getEstadoPrueba() {
                return estadoPrueba;
        }

}
