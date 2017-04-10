package com.andersenlab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Main {

    public static void main(String[] args) {
        DatagramSocket clientSocket = null;
        try {
            BufferedReader readerFromConsole = new BufferedReader(new InputStreamReader(System.in));  // Создаем ридер данных с консоли
            clientSocket = new DatagramSocket();                                                      // Создаем клиентский датаграм соккет
            InetAddress IPAddress = InetAddress.getByName("localhost");                               // Указываем адрес куда будем коннектиться

            byte[] sendData = new byte[1024];                                                         // Представляем наши данные в виде массива байтов
            byte[] receiveData = new byte[1024];

            String sentence = readerFromConsole.readLine();                                           // Считываем строку с консоли
            sendData = sentence.getBytes();                                                           // Парсим строку в байты

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 5555); // Создаем пакет данных который будем отправлять
            clientSocket.send(sendPacket);                                                                   // Отправляем через соккет пакет данных
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);              // Создаем пакет данных который будем получать
             clientSocket.receive(receivePacket);                                                            // Получаем пакет данных

            String modifiedSentence = new String(receivePacket.getData());                                   // Парсим строку из полученного байтового пакета
            System.out.println("FROM SERVER: " + modifiedSentence);                                          // Печатаем полученную строку
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            clientSocket.close();
        }
    }
}
