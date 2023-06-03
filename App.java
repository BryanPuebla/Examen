package com.pp2;

public class App {
	//Declaramos nuestras variables y declaramos un array de tipo double llamado "Distancias"
	//la cual tendra valores ya definidos
	
	private static double[] distancias = {5.30, 5.73, 6.77, 5.26, 4.33, 5.45, 6.09, 5.64, 5.81, 5.75};
    private static double mediaAritmetica;
    private static double[] desviaciones;
    
    
    //Este método calcula la media aritmética de las distancias y las arroja mediante la consola
    public synchronized static void calcularMedia() {
        mediaAritmetica = calcularMediaAritmetica(distancias);
        System.out.println("Media Aritmética: " + mediaAritmetica);
    }

    public synchronized static void calcularDesviaciones() {
        desviaciones = calcularDesviaciones(distancias, mediaAritmetica);
        System.out.println("Desviaciones:");
        for (double desviacion : desviaciones) {
            System.out.println(desviacion);
        }
    }

    
    //Este metodo crea dos hilos y los inicializa 
    public static void main(String[] args){
        Thread mediaThread = new Thread(new Runnable() {
            @Override
            public void run() {
                    calcularMedia();
                }
        });

        Thread desviacionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                    calcularDesviaciones();
                }
        });

        //Inicia la ejecucion del hilo
        mediaThread.start();
        try {
        	mediaThread.join();
        } catch(InterruptedException e) {
        	e.printStackTrace();
        }
   
        //Inicia la ejecucion del hilo
        desviacionThread.start();
        try {
        	desviacionThread.join();
        } catch(InterruptedException e) {
        	e.printStackTrace();
        }
    }

    private static double calcularMediaAritmetica(double[] distancias) {
        double suma = 0;
        for (double distancia : distancias) {
            suma += distancia;
        }
        return suma / distancias.length;
    }

    //Calcula y devuelve la media aritmetica
    private static double[] calcularDesviaciones(double[] distancias, double mediaAritmetica) {
        double[] desviaciones = new double[distancias.length];
        for (int i = 0; i < distancias.length; i++) {
            desviaciones[i] = distancias[i] - mediaAritmetica;
        }
        return desviaciones;
    }
}