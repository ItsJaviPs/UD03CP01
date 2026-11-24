package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEco {

    public static void main(String[] args) {
        int puerto = 5001;


        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor eco escuchando en puerto " + puerto);


            while (true) {

                try (Socket cliente = servidor.accept();
                     BufferedReader entrada = new BufferedReader(
                             new InputStreamReader(cliente.getInputStream()));
                     PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)) {

                    System.out.println("Cliente conectado: " + cliente.getInetAddress());

                    String linea;

                    while ((linea = entrada.readLine()) != null) {
                        if (linea.equals("FIN")) {
                            System.out.println("Cliente solicita cierre.");
                            break;
                        }
                        System.out.println("Recibido: " + linea);
                        salida.println(linea.toUpperCase());
                    }

                } catch (IOException e) {
                    System.out.println("Error con el cliente: " + e.getMessage());
                }

                System.out.println("Esperando nuevo cliente...");
            }

        } catch (IOException e) {
            System.out.println("No se pudo iniciar el servidor: " + e.getMessage());
        }
    }
}