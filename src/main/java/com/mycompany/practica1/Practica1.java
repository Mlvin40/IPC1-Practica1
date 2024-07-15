/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.practica1;

import java.awt.Color;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Phoenix
 */
public class Practica1 {

    //COLORES
    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String ROJO = "\u001B[31m";
    public static final String AZUL = "\u001B[34m";
    public static final String GRIS = "\u001B[90m";
    public static final String CELESTE = "\u001B[96m";
    public static final String NARANJA = "\u001B[33m";

    //PARA LAS ESTADISTICAS DE WORDLE
    static int wordleIniciados = 0;
    static int wordlePerdidos = 0;
    static int wordleGanados = 0;
    static int intentosadivinarPalabra = 0;

    //PARA LAS ESTADISTICAS DE BASKETBALL
    static int basketballIniciados;

    //PARA LAS ESTADISTICAS DE 2048
    static int veintecuatroochoIniciados;
    static int veintecuatroochoGanado;
    static int veintecuatroochoSalir;

    static String juegoActual = "";

    //METODO MAIN DONDE VERIFIACA SI AL EJECUTARSE DESDE CONSOLA TIENE PARAMETROS SE INICIALIZA UN JUEGO CON EL PARAMETRO 
    //ENVIADO, DE LO CONTRARIO EJECUTARA EL METODO MENU PRINCIPAL
    public static void main(String[] args) {

        if (args.length > 0) {

            //conviertte el parametro enviado a minuscula.
            String parametro = args[0].toLowerCase();

            switch (parametro) {
                case "wordle":
                    System.out.println("EL JUEGO SE HA INICIADO CON EL PARAMETRO: " + parametro);
                    wordleIniciados++;
                    Wordle();
                    break;

                case "basketball":
                    System.out.println("EL JUEGO SE HA INICIADO CON EL PARAMETRO: " + parametro);
                    basketballIniciados++;
                    Basketball();
                    break;

                case "2048":
                    System.out.println("EL JUEGO SE HA INICIADO CON EL PARAMETRO: " + parametro);
                    veintecuatroochoIniciados++;
                    iniciarJuego2048();
                    break;

                default:
            }
        }
        menuPrincipal();
    }

    static void menuPrincipal() {
        //MENU PRINCIPAL
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        do {
            System.out.println("\n-----MENU PRINCIPAL-----\n");
            System.out.println("1.----Wordle----");
            System.out.println("2.----Basketball----");
            System.out.println("3.----2048----");
            System.out.println("4.----Reportes----");
            System.out.println("5.----Salir----");

            opcion = Integer.valueOf(scanner.nextLine());

            switch (opcion) {
                //Inicia el Juego
                case 1:
                    wordleIniciados++;
                    Wordle();
                    limpiarPantalla();

                    break;

                case 2:
                    basketballIniciados++;
                    Basketball();
                    limpiarPantalla();

                    break;

                case 3:
                    veintecuatroochoIniciados++;
                    iniciarJuego2048();
                    limpiarPantalla();
                    break;

                case 4:
                    limpiarPantalla();
                    reportes();
                    break;

                case 5:
                    finalizarPrograma();
                    break;

                default:
                    limpiarPantalla();
                    System.out.println("Elija una opcion Valida");
            }
        } while (opcion != 5);

    }

    //Juego Wordle
    static void Wordle() {
        juegoActual = "wordle";
        limpiarPantalla();
        Scanner scanner = new Scanner(System.in);

        //lista de palabras ingresadas
        String[] listado = new String[6];

        //contador nos indicara si el jugador gana o pierde
        int contador = 0;
        int intentos = 6;
        String palabraOculta;
        String adivinarPalabra;

        System.out.println("BIENVENIDO A WORDLE");

        do {
            System.out.println("OCULTE UNA PALABRA");
            palabraOculta = scanner.nextLine();

        } while (verificadorPalabra(palabraOculta) != true);
        limpiarPantalla();

        //gurda las letras de la palabra oculta en un arreglo
        char[] letras = new char[5];

        //Almacenar cada letra en un arreglo
        for (int i = 0; i < palabraOculta.length(); i++) {
            //el metodo charAt obtiene el caracter en una posicion especifica de una cadena de texto
            letras[i] = palabraOculta.charAt(i);
        }

        do {
            do {
                //SE EJECUTARA MIENTRAS LA PALABRA QUE INGRESE EL JUGADOR NO TENGA 5 DIGITOS          
                System.out.println("INTENTOS RESTANTES: " + intentos);
                System.out.println("Ingrese una Palabra para adivinar");

                adivinarPalabra = scanner.nextLine();
                borrarLinea();

            } while (verificadorPalabra(adivinarPalabra) != true);

            //Guarda las letras para adivinar en otro arreglo
            char[] adLetra = new char[5];

            //Guarda la palabra ingresada en otra cadena 
            for (int i = 0; i < adivinarPalabra.length(); i++) {
                adLetra[i] = adivinarPalabra.charAt(i);
            }

            //Validaciones de Letras
            if (palabraOculta.equalsIgnoreCase(adivinarPalabra)) {
                System.out.println(AMARILLO + "\nGanaste\n" + RESET);
                System.out.println(VERDE + "******************************" + RESET);
                System.out.println("Adivinaste la palabra: " + VERDE + palabraOculta + RESET);
                System.out.println(VERDE + "******************************" + RESET);
                intentos = 0;
                wordleGanados++;
            } else {

                //se suma un inteto a la cantidad de veces que se ha intendado adivinar la palabra
                intentosadivinarPalabra++;

                //arreglo temporal para mostrar las palabras ingresadas anteriormente
                char[] letraTemp = new char[5];

                //con la variable contador se guardara la palabra del turno en cada posicion del arreglo listado
                listado[contador] = adivinarPalabra;

                intentos = intentos - 1;
                contador = contador + 1;

                //Empieza a leer todas las palabras ingresadas con sus colores en intentos anteriores
                //se empieza en contador -1 para leer la palabra en la posicion 0 del arreglo
                for (int i = 0; i < contador - 1; i++) {
                    //este for recorre 5 letras, en este caso la palabras ingresadas por el usuario
                    for (int j = 0; j < 5; j++) {

                        letraTemp[j] = listado[i].charAt(j);
                    }
                    palabraAnterior(palabraOculta, letras, letraTemp);
                    System.out.println("");
                }
                palabraAnterior(palabraOculta, letras, adLetra);
                System.out.println("\n");
            }

        } while (intentos != 0);
        // Agrega una derrrota al historial
        if (contador == 6) {
            System.out.println("INTENTOS RESTANTES: " + intentos + "\n");
            System.out.println(ROJO + "******************************************" + RESET);
            System.out.println("Perdiste... la palabra corecta es: " + GRIS + palabraOculta + RESET);
            System.out.println(ROJO + "******************************************" + RESET);
            wordlePerdidos++;

        }
        System.out.println("\n");
        menujugardeNuevo();
    }

    /**
     * ESTE METODO DEVUELVE VERDADERO SI LA PALABRA INGRESADA ES DE 5 DIGITOS,
     * DE LO CONTRARIO DEVOLVERA FALSO
     *
     * @param palabraOculta
     * @return
     */
    static boolean verificadorPalabra(String palabraOculta) {

        if (palabraOculta.length() == 5) {
            //LA PALABRA INGRESADA ES DE 5 DIGITOS O MENOS
            return true;
        } else {
            System.out.println("INGRESE UNA PALABRA DE 5 DIGITOS");
            return false;
        }
    }

    /**
     * ESTE METODO RECIBE LA PABRA INGRESADA POR EL USUARIO Y LA COMPARA CON LA
     * PALABRA OCULTA LETRA POR LETRA PINTADO CADA UNA DE ELLOS CON SUS
     * RESPECTIVOS COLORES
     *
     * @param palabraOculta
     * @param letras
     * @param adLetras
     * @return
     */
    static String palabraAnterior(String palabraOculta, char[] letras, char[] adLetras) {

        //STRINGBUILDER CONSTRUYE UNA CADENA DE TEXTO CONCATENADA...RESULTADO.APPEND
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            if (letras[i] == adLetras[i]) {
                System.out.print(VERDE + adLetras[i] + RESET);
            } else if (palabraOculta.contains(String.valueOf(adLetras[i]))) {
                System.out.print(AMARILLO + adLetras[i] + RESET);
            } else {
                System.out.print(ROJO + adLetras[i] + RESET);
            }
        }
        return resultado.toString();
    }

    //Fin Juego Wordle
    //*************************************************************************************************//
    //Juego Basketball
    static void Basketball() {
        juegoActual = "basketball";

        limpiarPantalla();

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int cantidadTurnos = 0;
        int accion = 0;

        String[] nombreJugador = new String[2];
        int[] puntosJugador = new int[2];

        //ARREGLOS QUE SE UTILIZAN PARA GUARDAR ATAQUE O DEFENSA DEL JUGADO 1,2 EN UN TURNO 
        int[] probabilidadAnotar = new int[2];
        int[] probabilidadDefensa = new int[2];
        int[] posiblesPuntos = new int[2];
        int[] probabilidadFalta = new int[2];

        String ataqueSeleccionado[] = new String[2];
        String defensaSeleccionado[] = new String[2];

        System.out.println("BIENVENIDO A BASKETBALL");
        System.out.println("Ingrese la cantidad de turno que tendra el juego");
        cantidadTurnos = Integer.valueOf(scanner.nextLine());

        System.out.println("Ingrese el Nombre del Jugador1");
        nombreJugador[0] = scanner.nextLine();

        System.out.println("Ingrese el Nombre del Jugador2");
        nombreJugador[1] = scanner.nextLine();

        limpiarPantalla();
        do {
            for (int i = 0; i < 2; i++) {
                probabilidadAnotar[i] = 0;
                probabilidadDefensa[i] = 0;
                posiblesPuntos[i] = 0;
                probabilidadFalta[i] = 0;
            }
            System.out.println(ROJO + "********************" + RESET);
            System.out.println(ROJO + "TURNOS RESTANTES " + cantidadTurnos + RESET);
            System.out.println(ROJO + "********************" + RESET);

            for (int i = 0; i < 2; i++) {
                //Empieza el juego
                System.out.println("\n");
                System.out.println(AMARILLO + "Turno del jugador: " + nombreJugador[i] + RESET);

                do {
                    System.out.println("Elija un Movimiento");
                    System.out.println("1---Lanzamiento");
                    System.out.println("2---Defensa");

                    accion = Integer.valueOf(scanner.nextLine());
                } while (accionValida(accion) != true);

//ATACAR
                switch (accion) {
                    //atacar
                    case 1:

                        int opcionAtaque = 0;

                        do {
                            System.out.println("Que lanzamiento desea hacer");
                            System.out.println("1. Salto largo desde 5 metros --- 3 puntos --- Probabilidad de anotar 65%");
                            System.out.println("2. Salto corto desde 3 metros --- 2 puntos --- Probabilidad de anotar 80%");

                            opcionAtaque = Integer.valueOf(scanner.nextLine());
                        } while (accionValida(opcionAtaque) != true);
                        //elije un tipo de ataque
                        switch (opcionAtaque) {
                            case 1:
                                ataqueSeleccionado[i] = "Salto Largo";
                                probabilidadAnotar[i] = 65;
                                posiblesPuntos[i] = 3;
                                break;

                            case 2:
                                ataqueSeleccionado[i] = "Salto Corto";
                                probabilidadAnotar[i] = 80;
                                posiblesPuntos[i] = 2;
                                break;

                            default:
                                throw new AssertionError();
                        }
                        break;
//DEFENDER
                    case 2:

                        /////
                        int opcionDefensa = 0;

                        do {
                            System.out.println("Elija un tipo de defensa");
                            System.out.println("1. Defensa cuerpo a cuerpo --- reduce la probabilidad de anotar en 15% --- Probabilidad de hacer falta 35%");
                            System.out.println("2. Defensa fuerte --- reduce la probabilidad de anotar en 30% --- probabilidad de hacer falta 65%");
                            opcionDefensa = Integer.valueOf(scanner.nextLine());
                        } while (accionValida(opcionDefensa) != true);
                        //elije tipo de defensa
                        switch (opcionDefensa) {
                            case 1:
                                defensaSeleccionado[i] = "Defensa Cuerpo a Cuerpo";
                                probabilidadDefensa[i] = 15;
                                probabilidadFalta[i] = 35;
                                break;
                            case 2:
                                defensaSeleccionado[i] = "Defensa Fuerte";
                                probabilidadDefensa[i] = 30;
                                probabilidadFalta[i] = 65;
                                break;

                            default:
                                System.out.println("Opcion invalida");
                        }
                        break;

                    default:
                        System.out.println("Opcion invalida");
                }
            }
            System.out.println("\n");
            limpiarPantalla();
            System.out.println(AZUL + "***********************************" + RESET);
            //EMPIEZAN LAS VERIFICACIONES UTILIZANDO LOS ARREGLOS QUE CONTIENEN LOS VALORRES DEL TURNO ACTUAL 

            //si el jugador uno ataca y jugador 2 defiende
            if (probabilidadAnotar[0] != 0 && probabilidadDefensa[1] != 0) {
                probabilidadAnotar[0] = probabilidadAnotar[0] - probabilidadDefensa[1];

                int aleatorio = random.nextInt(101);
                if (aleatorio < probabilidadFalta[1]) {
                    // si hay falta
                    System.out.println("El jugador " + nombreJugador[1] + " le hizo una falta al jugador " + nombreJugador[0] + " con " + defensaSeleccionado[1]);
                    System.out.println(AMARILLO + "TIRO LIBRE PARA: " + nombreJugador[0] + RESET);
                    teclaContinuar();
                    puntosJugador[0] = puntosJugador[0] + tiroLibre();
                } else if (lanzamiento(probabilidadAnotar[0])) {
                    System.out.println("El jugador " + nombreJugador[1] + " fallo " + defensaSeleccionado[1]);
                    System.out.println("El jugador " + nombreJugador[0] + " anoto con " + ataqueSeleccionado[0]);
                    puntosJugador[0] = puntosJugador[0] + posiblesPuntos[0];

                } else {
                    System.out.println("El jugador " + nombreJugador[1] + " defendio la jugada " + ataqueSeleccionado[0] + " del jugador " + nombreJugador[0] + " con " + defensaSeleccionado[1]);

                }
            }

            //si el jugador dos ataca y el jugador uno defiende
            if (probabilidadAnotar[1] != 0 && probabilidadDefensa[0] != 0) {
                probabilidadAnotar[1] = probabilidadAnotar[1] - probabilidadDefensa[0];

                int aleatorio = random.nextInt(101);
                if (aleatorio < probabilidadFalta[0]) {
                    //si hay falta
                    System.out.println("El jugador " + nombreJugador[0] + " le hizo una falta al jugador " + nombreJugador[1] + " con " + defensaSeleccionado[0]);
                    System.out.println(AMARILLO + "TIRO LIBRE PARA: " + nombreJugador[1] + RESET);
                    teclaContinuar();
                    puntosJugador[1] = puntosJugador[1] + tiroLibre();
                } else if (lanzamiento(probabilidadAnotar[1]) == true) {
                    System.out.println("El jugador " + nombreJugador[0] + " fallo " + defensaSeleccionado[0]);
                    System.out.println("El jugador " + nombreJugador[1] + " anoto con " + ataqueSeleccionado[1]);

                    puntosJugador[1] = puntosJugador[1] + posiblesPuntos[1];
                } else {
                    System.out.println("El jugador " + nombreJugador[0] + " defendio la jugada " + ataqueSeleccionado[1] + " del jugador " + nombreJugador[1] + " con " + defensaSeleccionado[0]);

                }
            }
            //Si ambos juadores atacan
            if (probabilidadAnotar[0] != 0 && probabilidadAnotar[1] != 0) {
                int aleatorio = random.nextInt(101);

                if (lanzamiento(probabilidadAnotar[0])) {
                    System.out.println("El jugador " + nombreJugador[0] + " anoto con " + ataqueSeleccionado[0]);
                    puntosJugador[0] = puntosJugador[0] + posiblesPuntos[0];

                }
                if (lanzamiento(probabilidadAnotar[1])) {
                    System.out.println("El jugador " + nombreJugador[1] + " anoto con " + ataqueSeleccionado[1]);
                    puntosJugador[1] = puntosJugador[1] + posiblesPuntos[1];

                }
            }
            System.out.println(AZUL + "***********************************" + RESET);
            System.out.println("\n");
            cantidadTurnos--;

            //VA MOSTRANDO LOS PUNTOS EN CADA JUGADA
            for (int i = 0; i < 2; i++) {
                System.out.println(GRIS + "Puntos jugador " + nombreJugador[i] + " :" + puntosJugador[i] + RESET);
            }// 
            System.out.println("\n");
        } while (cantidadTurnos != 0);

        //AL FINALIZAR LOS TURNOS ESTE METODO VERIFICA QUE JUGADOR TIENE MAS PUNTOS. ES EL GANADOR. O HAY UN EMPATE
        if (puntosJugador[0] > puntosJugador[1]) {
            System.out.println("GANA EL JUGADOR: " + AMARILLO + nombreJugador[0] + RESET);
        } else if (puntosJugador[0] < puntosJugador[1]) {
            System.out.println("GANA EL JUGADOR: " + AMARILLO + nombreJugador[1] + RESET);
        } else {
            System.out.println(AMARILLO + "EMPATE!!!" + RESET);
        }
        System.out.println("\n");
        menujugardeNuevo();
    }

    static void teclaContinuar() {
        System.out.println(GRIS + "Probabilidad de anotar 90%" + "\n" + "Presiona una tecla para ejecutar el tiro libre..." + RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * METODO QUE VERIFICA SI UN ATAQUE ES EXITOSO O FALLIDO
     *
     * @param parametro
     * @return
     */
    static boolean lanzamiento(int parametro) {
        Random random = new Random();

        int aleatorio = random.nextInt(101);
        if (aleatorio < parametro) {
            System.out.println("Lanzamiento exitoso");
            return true;
        } else {
            System.out.println("Lanzamiento fallido");
            return false;
        }
    }

    /**
     * METODO QUE VERIFICA SI UN TIRO LIBRE ES EXITOSO O FALLIDO
     *
     * @return
     */
    static int tiroLibre() {
        //90% DE PROBABILIDAD DE ANOTAR LA JUGADA
        if (lanzamiento(90)) {
            System.out.println("tiro libre anotado :) ");
            return 2;
        } else {
            System.out.println("tiro libre fallado :( ");
            return 0;
        }
    }

    static boolean accionValida(int accion) {
        if (accion == 1 || accion == 2) {
            return true;

        } else {
            System.out.println("Ingrese una opcion valida");
            return false;
        }

    }

    static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void borrarLinea() {
        System.out.print("\033[F\033[2K");
    }

    static void reportes() {
        System.out.println(ROJO + "\n*********REPORTES*********\n" + RESET);
        System.out.println(GRIS + "---------WORDLE----------" + RESET);
        System.out.println("Juegos Iniciados de Worlde: " + wordleIniciados);
        System.out.println("Juegos Ganados en Wordle: " + wordleGanados);
        System.out.println("Juegos perdidos en Wordle: " + wordlePerdidos);
        System.out.println("Cantidad de veces que se ha intentado adivinar una palabra: " + intentosadivinarPalabra);
        System.out.println("\n");
        System.out.println(GRIS + "---------BASKETBALL-------" + RESET);
        System.out.println("Juegos Iniciados de Basketball: " + basketballIniciados);
        System.out.println("\n");
        System.out.println(GRIS + "-----------2048----------" + RESET);
        System.out.println("Juegos Iniciados de 2048: " + veintecuatroochoIniciados);
        System.out.println("Juegos Ganados de 2048: " + veintecuatroochoGanado);
        System.out.println("Juegos Abandonados de 2048: " + veintecuatroochoSalir);

    }

    //MENU QUE SE EJECUTA AL FINALIZAR CUALQUIER JUEGO Y TIENE LA OPCION DE VOLVER A JUGAR O SALIR AL MENU PRINCIPAL
    static void menujugardeNuevo() {

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        System.out.println(GRIS + "\n-----------------------" + RESET);
        System.out.println("Desea Jugar de nuevo");
        System.out.println("1...Jugar de Nuevo");
        System.out.println("2...Menu Principal");
        System.out.println("3...Finalizar Programa");
        System.out.println(GRIS + "-----------------------\n" + RESET);
        opcion = Integer.valueOf(scanner.nextLine());

        switch (opcion) {
            case 1:
                if (juegoActual == "wordle") {
                    wordleIniciados++;
                    Wordle();
                } else if (juegoActual == "basketball") {
                    basketballIniciados++;
                    Basketball();
                } else if (juegoActual == "2048") {
                    veintecuatroochoIniciados++;
                    iniciarJuego2048();
                }
                break;

            case 2:
                limpiarPantalla();
                break;

            case 3:
                finalizarPrograma();
                break;

            default:
                limpiarPantalla();
                System.out.println("Elija una Opcion Valida");
        }
    }

    static void finalizarPrograma() {
        System.out.println(AZUL + "HASTA PRONTO :)" + RESET);
        System.exit(0);
    }

    //******************************************************************************************
    //*************************AQUI EMPIEZA LO DE 2048******************************************
    //******************************************************************************************
    static void iniciarJuego2048() {
        juegoActual = "2048";
        limpiarPantalla();

        Random random = new Random();

        //DIMENSIONES DEL TABLENO N*M
        final int tamFILAS = 4;
        final int tamCOLUMNAS = 4;
        //Crea un nuevo tablero
        int[][] tablero = new int[tamFILAS][tamCOLUMNAS];

        crearTablero(tamFILAS, tamCOLUMNAS, tablero);

        //NUMERO DOS INICIAL EN UNA CASILLA RANDOM 
        int filarandom = random.nextInt(4);
        int columnarandom = random.nextInt(4);
        tablero[filarandom][columnarandom] = 2;

        //FIN PROCESO 2 ALEATORIO
        mostrarTablero(tamFILAS, tamCOLUMNAS, tablero);

        System.out.println("\n");

        bulclePartida2048(tamFILAS, tamCOLUMNAS, tablero);

        menujugardeNuevo();

    }

    static void bulclePartida2048(int tamfilas, int tamcolumnas, int[][] tablero) {

        Scanner scanner = new Scanner(System.in);
        String direccion;

        do {
            System.out.println("               Arriba (w)" + "               ");
            System.out.println("Izquieda(a)  " + " Abajo (s)" + "  Derecha(d)" + ROJO + "                Abandonar Partida(p)" + RESET);
            direccion = scanner.nextLine();
            System.out.println("\n");
            System.out.println("\n");

            switch (direccion) {
                case "w":
                    moverTodoHaciaArriba(tamfilas, tamcolumnas, tablero);
                    mostrarTablero(tamfilas, tamcolumnas, tablero);
                    break;

                case "s":
                    moverTodoHaciaAbajo(tamfilas, tamcolumnas, tablero);
                    mostrarTablero(tamfilas, tamcolumnas, tablero);
                    break;

                case "a":
                    moverTodoHaciaLaIzquierda(tamfilas, tamcolumnas, tablero);
                    mostrarTablero(tamfilas, tamcolumnas, tablero);
                    break;
                case "d":
                    moverTodoHaciaLaDerecha(tamfilas, tamcolumnas, tablero);
                    mostrarTablero(tamfilas, tamcolumnas, tablero);
                    break;

                case "p":
                    System.out.println("Ha decidido salir de la partida");
                    veintecuatroochoSalir++;
                    break;

                default:
                    System.out.println("Elija una opcion Valida");

            }
        } while (!direccion.equals("p") && !finPartida(tamfilas, tamcolumnas, tablero));

        if (finPartida(tamfilas, tamcolumnas, tablero)) {
            System.out.println("");

            if (ganador(tamfilas, tamcolumnas, tablero)) {
                veintecuatroochoGanado++;
                System.out.println(AZUL + "***Has ganado***" + RESET);
            } else {
                System.out.println(ROJO + "***Has Perdido***" + RESET);
            }
        }
    }

    /**
     * PARA VERIFICAR LAS CASILLAS QUE AUN ESTAN VACIAS
     *
     * @param tamfilas
     * @param tamcolumnas
     * @param tablero
     * @return
     */
    static int casillasVacias(int tamfilas, int tamcolumnas, int[][] tablero) {
        int contadorCasillas = 0;

        for (int filas = 0; filas < tamfilas; filas++) {
            for (int columnas = 0; columnas < tamcolumnas; columnas++) {
                if (tablero[filas][columnas] == 0) {
                    contadorCasillas++;
                }
            }
        }
        return contadorCasillas;
    }

    /**
     * METODOD QUE CREA EL TABLERO CON EL CARACTER ESPECIFICADO EN ESTE CASO 0
     *
     * @param tamfilas
     * @param tamcolumnas
     * @param tablero
     */
    static void crearTablero(int tamfilas, int tamcolumnas, int[][] tablero) {
        for (int filas = 0; filas < tamfilas; filas++) {
            for (int columnas = 0; columnas < tamcolumnas; columnas++) {
                tablero[filas][columnas] = 0;
            }
        }
    }

    static void mostrarTablero(int tamfilas, int tamcolumnas, int[][] tablero) {
        for (int filas = 0; filas < tamfilas; filas++) {

            for (int columnas = 0; columnas < tamcolumnas; columnas++) {
                if (tablero[filas][columnas] > 1000) {
                    System.out.print("|" + ROJO + "  " + tablero[filas][columnas] + "   " + RESET);
                } else if (tablero[filas][columnas] > 100) {
                    System.out.print("|" + AMARILLO + "   " + tablero[filas][columnas] + "   " + RESET);
                } else if (tablero[filas][columnas] > 10) {
                    System.out.print("|" + VERDE + "    " + tablero[filas][columnas] + "   " + RESET);
                } else if (tablero[filas][columnas] > 0) {
                    System.out.print("|" + AMARILLO + "     " + tablero[filas][columnas] + "   " + RESET);
                } else {
                    System.out.print("|" + CELESTE + "     " + tablero[filas][columnas] + "   " + RESET);
                }
            }
            System.out.print("|");
            System.out.println("\n");
        }

    }

    /**
     * MIRA SI EN EL TABLERO HAY UNA CASILLA 2048 SIGNIFICA QUE EL JUGADOR HA
     * GANADO
     *
     * @param tamfilas
     * @param tamcolumnas
     * @param tablero
     * @return
     */
    static boolean ganador(int tamfilas, int tamcolumnas, int[][] tablero) {
        for (int fila = 0; fila < tamfilas; fila++) {
            for (int columna = 0; columna < tamcolumnas; columna++) {
                if (tablero[fila][columna] == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * METODO QUE VA AUMENTANDO EN UNO CADA VEZ QUE ENCUENTRA UNA CASILLA VACIA
     * EN ESTE CASO CASILLA VACIA =0
     *
     * @param verificarfila
     * @param tamcolumnas
     * @param tablero
     * @return
     */
    static int vaciaEnFila(int verificarfila, int tamcolumnas, int tablero[][]) {
        int contador = 0;
        for (int columna = 0; columna < tamcolumnas; columna++) {
            if (tablero[verificarfila][columna] == 0) {
                contador++;
            }
        }

        return contador;
    }

    /**
     * VEIFICA CUANTAS CASILLA VACIAS HAY EN UNA COLUMNA Y RETORNA ESA CANTIDAD
     *
     * @param verificarcolumna
     * @param tamfilas
     * @param tablero
     * @return
     */
    static int vaciaEnColumna(int verificarcolumna, int tamfilas, int tablero[][]) {
        int contador = 0;
        for (int fila = 0; fila < tamfilas; fila++) {
            if (tablero[fila][verificarcolumna] == 0) {
                contador++;
            }
        }
        return contador;
    }

    static boolean finPartida(int tamfilas, int tamcolumnas, int[][] tablero) {
        //ganador devuelve true si hay alguna casilla con el numero 2048 y casillas vacias devuelve true si las casillas vacias es igual a 0
        return (ganador(tamfilas, tamcolumnas, tablero) || casillasVacias(tamfilas, tamcolumnas, tablero) == 0);

    }

    /**
     * AGREGA UN NUMERO 2 ALEATORIAMENTE AL TABLERO EN UN CASILLA QUE NO ESTE
     * OCUPADA
     */
    static void numeroDosOrCuatroAleatorio(int tamfilas, int tamcolumnas, int[][] tablero) {
        Random random = new Random();
        int rndFila;
        int rndColumna;

        do {
            rndFila = random.nextInt(tamfilas);
            //BUSCA UNA FILA DONDE HAYA AL MENOS UNA CELDA VACIA
        } while (vaciaEnFila(rndFila, tamcolumnas, tablero) == 0);

        //BUSCA UNA COLUMNA VACIA
        do {
            rndColumna = random.nextInt(tamfilas);
        } while (tablero[rndFila][rndColumna] != 0);
        //AGREGA EL NUMERO DOS O CUATRO EN LA POSICION VACIA

        int dosOcuatro = random.nextInt(2);

        //SI EL NUMERO ALEATORIO ES 0 SE GENERA UN NUMERO DOS, SI EL NUMERO ALEATORIO ES 1 SE GENERA UN CUATRO  
        switch (dosOcuatro) {
            case 0:
                System.out.println(AZUL + "------------------------------------------------------------------" + RESET);
                System.out.println("Numero 2 generado en la fila: " + rndFila + " Columna: " + rndColumna);
                System.out.println(AZUL + "------------------------------------------------------------------" + RESET);
                tablero[rndFila][rndColumna] = 2;

                break;
            case 1:

                System.out.println(AZUL + "------------------------------------------------------------------" + RESET);
                System.out.println("Numero 4 generado en la fila: " + rndFila + " Columna: " + rndColumna);
                System.out.println(AZUL + "------------------------------------------------------------------" + RESET);
                tablero[rndFila][rndColumna] = 4;
                break;
            default:
                throw new AssertionError();
        }
    }

    //*******************************PARA EL MOVIMIENTO HACIA ARRIBA*******************************
    /**
     * ESTE METODO MUEVE TODA LAS CASILLAS HACIA ARRIBA Y LAS COMBINA
     *
     * @param tamfilas
     * @param tamcolumnas
     * @param tablero
     */
    static void moverTodoHaciaArriba(int tamfilas, int tamcolumnas, int[][] tablero) {
        for (int columna = 0; columna < tamcolumnas; columna++) {
            colocarArriba(tamfilas, tamcolumnas, tablero, columna);
            sumarHaciaArriba(tamfilas, tamcolumnas, tablero, columna);
            colocarArriba(tamfilas, tamcolumnas, tablero, columna);
        }
        //Luego de realizar el movimiento se genera un numero aleatoriamente en una casilla en blanco "0"
        //ver si hay espacio para generar un dos automaticamente
        if (!finPartida(tamfilas, tamcolumnas, tablero)) //sino
        {
            numeroDosOrCuatroAleatorio(tamfilas, tamcolumnas, tablero);
        }
    }

    static void colocarArriba(int tamfilas, int tamcolumnas, int[][] tablero, int colocarColumna) {
        if (vaciaEnColumna(colocarColumna, tamfilas, tablero) < tamcolumnas) {
            for (int veces = 0; veces < tamcolumnas - 1; veces++) {
                for (int fila = 0; fila < tamfilas - 1; fila++) {
                    if (tablero[fila][colocarColumna] == 0) {
                        tablero[fila][colocarColumna] = tablero[fila + 1][colocarColumna];
                        tablero[fila + 1][colocarColumna] = 0;
                    }

                }

            }
        }
    }

    /**
     * ESTE METODO VERIFICA SI DOS NUMEROS SON IGUALES Y LOS SUMA
     *
     * @param tamfilas
     * @param tamcolumnas
     * @param tablero
     * @param colocarColumna
     */
    static void sumarHaciaArriba(int tamfilas, int tamcolumnas, int[][] tablero, int colocarColumna) {
        if (vaciaEnColumna(colocarColumna, tamfilas, tablero) < 3) {
            for (int fila = 0; fila < tamfilas - 1; fila++) {
                if (tablero[fila][colocarColumna] == tablero[fila + 1][colocarColumna]) {
                    //DUPLICA EL VALOR DE LA CASILLA
                    tablero[fila][colocarColumna] *= 2;
                    tablero[fila + 1][colocarColumna] = 0;

                }

            }
        }
    }

    //*******************************PARA EL MOVIMIENTO HACIA ABAJO***************************************************************************
    static void moverTodoHaciaAbajo(int tamfilas, int tamcolumnas, int[][] tablero) {
        for (int colocar = 0; colocar < tamcolumnas; colocar++) {
            colocarAbajo(tamfilas, tamcolumnas, tablero, colocar);
            sumarHaciaAbajo(tamfilas, tamcolumnas, tablero, colocar);
            colocarAbajo(tamfilas, tamcolumnas, tablero, colocar);
        }
        if (!finPartida(tamfilas, tamcolumnas, tablero));
        numeroDosOrCuatroAleatorio(tamfilas, tamcolumnas, tablero);

    }

    static void colocarAbajo(int tamfilas, int tamcolumnas, int[][] tablero, int colocarColumna) {
        if (vaciaEnColumna(colocarColumna, tamfilas, tablero) < tamcolumnas) {
            for (int veces = 0; veces < tamcolumnas - 1; veces++) {
                //al contrario entonces se empieza desde la casilla la fila 4
                for (int fila = tamfilas - 1; fila > 0; fila--) {
                    if (tablero[fila][colocarColumna] == 0) {
                        tablero[fila][colocarColumna] = tablero[fila - 1][colocarColumna];
                        tablero[fila - 1][colocarColumna] = 0;
                    }
                }
            }
        }
    }

    static void sumarHaciaAbajo(int tamfilas, int tamcolumnas, int[][] tablero, int colocarColumna) {
        if (vaciaEnColumna(colocarColumna, tamfilas, tablero) < tamfilas - 1) {
            for (int fil = tamfilas - 1; fil > 0; fil--) {
                if (tablero[fil][colocarColumna] == tablero[fil - 1][colocarColumna]) {
                    tablero[fil][colocarColumna] *= 2;
                    tablero[fil - 1][colocarColumna] = 0;
                }
            }
        }
    }

    //*******************************PARA EL MOVIMIENTO HACIA LA DERECHA********************************************************
    /**
     * ESTE METODO SE ENCARGA DE RECORRER TODA LA MATRIZ Y PASAR LOS NUMEROS A
     * LA FILA SIGUIENTE Y SUMA LOS NUMEROS IGUALES
     *
     * @param tamfilas
     * @param tamcolumnas
     * @param tablero
     */
    static void moverTodoHaciaLaDerecha(int tamfilas, int tamcolumnas, int[][] tablero) {
        for (int colocar = 0; colocar < tamcolumnas; colocar++) {
            colocarDerecha(tamfilas, tamcolumnas, tablero, colocar);
            sumarDerecha(tamfilas, tamcolumnas, tablero, colocar);
            colocarDerecha(tamfilas, tamcolumnas, tablero, colocar);
        }
        if (!finPartida(tamfilas, tamcolumnas, tablero));
        numeroDosOrCuatroAleatorio(tamfilas, tamcolumnas, tablero);

    }

    static void colocarDerecha(int tamfilas, int tamcolumnas, int[][] tablero, int colocarFila) {
        if (vaciaEnFila(colocarFila, tamcolumnas, tablero) < tamfilas) {
            //se le resta 1 porque no puede verificar una casilla fuera del tablero 
            for (int veces = 0; veces < tamcolumnas - 1; veces++) {
                for (int col = tamcolumnas - 1; col > 0; col--) { //> --
                    //ESTE IF VERIFICA QUE SI LA ULTIMA COLUMNA TIENE ESPACIO
                    if (tablero[colocarFila][col] == 0) {
                        tablero[colocarFila][col] = tablero[colocarFila][col - 1];
                        tablero[colocarFila][col - 1] = 0;
                    }
                }

            }
        }
    }

    static void sumarDerecha(int tamfilas, int tamcolumnas, int[][] tablero, int colocarFila) {

        if (vaciaEnFila(colocarFila, tamcolumnas, tablero) < tamfilas - 1) {
            for (int col = tamcolumnas - 1; col > 0; col--) {
                if (tablero[colocarFila][col] == tablero[colocarFila][col - 1]) {
                    tablero[colocarFila][col] *= 2;
                    tablero[colocarFila][col - 1] = 0;
                }
            }
        }
    }

    //*******************************PARA EL MOVIMIENTO HACIA LA IZQUIERDA************
    /**
     * MUEVE TODAS LAS CASILLAS A LA IZQUIERDA Y SI DOS NUMEROS IGUALES CHOCAN
     * SE SUMAN
     *
     * @param tamfilas
     * @param tamcolumnas
     * @param tablero
     */
    static void moverTodoHaciaLaIzquierda(int tamfilas, int tamcolumnas, int[][] tablero) {
        //colocar va a decile a los metodos que columnas y casiilas de recorrer
        for (int colocar = 0; colocar < tamfilas; colocar++) {
            moverIzquierda(tamfilas, tamcolumnas, tablero, colocar);
            sumarIzquierda(tamfilas, tamcolumnas, tablero, colocar);
            //para rellenar posibles espacios vacios
            moverIzquierda(tamfilas, tamcolumnas, tablero, colocar);
        }
        if (finPartida(tamfilas, tamcolumnas, tablero));
        numeroDosOrCuatroAleatorio(tamfilas, tamcolumnas, tablero);

    }

    /**
     * EMPIEZA DESDE LA COLUMNA CERO Y VERIFICA HASTA LA COLUMNA 2 PARA QUE AL
     * SUMARLE UNO NO SALGA DE LA MATRIZ Y VERIFIQUE COMO MAXIMO 4 COLUMNAS Y
     * FILAS
     *
     * @param tamfilas
     * @param tamcolumnas
     * @param tablero
     * @param colocarFila
     */
    static void moverIzquierda(int tamfilas, int tamcolumnas, int[][] tablero, int colocarFila) {

        if (vaciaEnFila(colocarFila, tamcolumnas, tablero) < tamfilas) {
            for (int veces = 0; veces < tamcolumnas - 1; veces++) {
                for (int col = 0; col < tamcolumnas - 1; col++) {
                    if (tablero[colocarFila][col] == 0) {
                        tablero[colocarFila][col] = tablero[colocarFila][col + 1];
                        tablero[colocarFila][col + 1] = 0;
                    }
                }

            }
        }
    }

    static void sumarIzquierda(int tamfilas, int tamcolumnas, int[][] tablero, int colocarFila) {
        if (vaciaEnFila(colocarFila, tamcolumnas, tablero) < tamfilas - 1) {
            for (int col = 0; col < tamcolumnas - 1; col++) {
                if (tablero[colocarFila][col] == tablero[colocarFila][col + 1]) {
                    tablero[colocarFila][col] *= 2;
                    tablero[colocarFila][col + 1] = 0;
                }
            }
        }
    }
}
