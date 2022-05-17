package Pruebas;

public class Huecos {
    // Ho:ri~Independientes
    // H1:ri~Dependientes
    // (α,β)∈(0,1)
    //
    /*
     * La prueba funciona de esta manera: se inicia al crear una gráfica de
     * dispersión entre los números consecutivos (ri ri+1): luego se divide la
     * gráfica en m casillas, como m como el valor entero más cercano a √n que
     * permita formar de preferencia, aunque no necesariamente, una matriz cuadrada.
     */

    // definimos el intervalo (0.6 - 0.7)

    private double[] muestra;
    private int[] uNosCeros, sizeHuecos;
    private double a = 0.6, b = 0.7, sumaAnteriores = 0, Ei, estadistico, chiCalculada = 0;
    private int i, conteoCero = 0, sumahuecos;
    private boolean estadoPrueba;

    public Huecos(double[] muestra) {
        this.muestra = muestra;
        uNosCeros = new int[muestra.length];
        sizeHuecos = new int[6];
    }

    public void pruebaH() {

        i = 0;
        while (i < muestra.length) {
            /*System.out.println(muestra[i]);*/
            i++;
        }
        i = 0;
        while (i < muestra.length) {
            if (muestra[i] > a && muestra[i] < b) {
                uNosCeros[i] = 1;
            } else {
                uNosCeros[i] = 0;
            }
            i++;
        }
        i = 0;
        while (i < uNosCeros.length) {
            /*System.out.print(" " + uNosCeros[i]);*/
            i++;
            if (i == uNosCeros.length) {
                /*System.out.println(" ");*/
            }
        }

        conteoHuecos();
        contarHuecos();
        /*System.out.println(String.format("%5s", "i") + String.format("%10s", " Oi") + String.format("%15s", "Ei")
                + String.format("%17s", "(0i-Ei)^2/E"));*/
        i = 0;
        while (i < sizeHuecos.length) {

            Ei = (getSumahuecos() * (b - a) * Math.pow((1 - (b - a)), i));
            estadistico = Math.pow((sizeHuecos[i] - Ei), 2) / Ei;

            /*System.out.println(String.format("%5d", (i)) + String.format("%10d", sizeHuecos[i])
                    + String.format("%15f", Ei) + String.format("%15f", estadistico));*/
            sumaAnteriores += Ei;
            chiCalculada += estadistico;
            i++;
            if (i + 1 == sizeHuecos.length) {
                Ei = (getSumahuecos() - sumaAnteriores);
                estadistico = Math.pow(sizeHuecos[i] - Ei, 2) / Ei;
                chiCalculada += estadistico;
                /*System.out.println(String.format("%5d", (i)) + String.format("%10d", sizeHuecos[i])
                        + String.format("%15f", Ei)
                        + String.format("%15f", estadistico));*/

                sumaAnteriores += (getSumahuecos() - sumaAnteriores);
                i++;
            }
        }
        /*System.out.println(String.format("%5s", " ") + String.format("%10d", getSumahuecos())
                + String.format("%15f", sumaAnteriores) + String.format("%15f", chiCalculada)
                + " = chiCuadrada Calculada");*/

        estadoPrueba();

    }

    private void conteoHuecos() {
        int j = 1;
        i = 0;
        while (i < uNosCeros.length) {

            if (j == muestra.length) {
                j = muestra.length-1;
            }
            if (uNosCeros[i] == 0) {
                conteoCero++;
                if (i + 1 == uNosCeros.length) {
                    SizeHueco(conteoCero, i);
                }
            } else if (uNosCeros[i] == 1 && uNosCeros[j] == 1) {
                if ((i + 1) != muestra.length) {
                    SizeHueco(0, i);
                }
            } else {
                SizeHueco(conteoCero, i);
                conteoCero = 0;

            }
            j++;
            i++;

        }
    }

    private void SizeHueco(int conteo, int lugar) {
        if (conteo == 0) {
            sizeHuecos[0] += 1;
        } else if (conteo == 1) {
            sizeHuecos[1] += 1;
        } else if (conteo == 2) {
            sizeHuecos[2] += 1;
        } else if (conteo == 3) {
            sizeHuecos[3] += 1;
        } else if (conteo == 4) {
            sizeHuecos[4] += 1;
        } else if (conteo >= 5) {
            sizeHuecos[5] += 1;
        }
    }

    private void contarHuecos() {
        i = 0;
        while (i < sizeHuecos.length) {
            sumahuecos += sizeHuecos[i];
            i++;
        }
    }

    public int getSumahuecos() {
        return sumahuecos;
    }

    private void estadoPrueba() {

        if (chiCalculada < 11.070) {
            setEstadoPrueba(true);
        } else {
            setEstadoPrueba(false);
        }

    }

    private void setEstadoPrueba(boolean estadoPrueba) {
        this.estadoPrueba = estadoPrueba;
    }

    public boolean getEstadoPrueba() {
        return estadoPrueba;
    }

}
