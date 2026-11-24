package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEco {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 5001);
             BufferedReader entrada = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             Scanner teclado = new Scanner(System.in)) {

            System.out.println("Conectado al servidor eco. Escribe mensajes (FIN para salir):");

            while (true) {
                String mensaje = teclado.nextLine();
                salida.println(mensaje);

                if (mensaje.equals("FIN")) {
                    break;
                }

                String respuesta = entrada.readLine();
                System.out.println("Servidor: " + respuesta);
            }

            System.out.println("Cliente finalizado.");

        } catch (IOException e) {
            System.out.println("No se pudo conectar con el servidor: " + e.getMessage());
        }
    }
}