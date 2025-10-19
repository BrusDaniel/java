import java.io.*;
import java.net.*;

public class CCS {

    static int clientsAmount = 0;
    static int solvingOperationsAmount = 0;
    static int errorOperationsAmount = 0;
    static int allOperationsAmount = 0;
    static int resultsSum = 0;


    public static void main(String[] args) {

        //zapobiegamy przekazywaniu wielu argumentow
        if (args.length != 1) {
            System.err.println("Wpisz numer portu. Nie uzywaj bialych znakow!");
            System.exit(1);
        }

        //otaczamy wyjatkiem, zeby zapobiec bledom
        try {
            //otwieramy zadany przez klienta port
            int port = Integer.parseInt(args[0]);

            //watki
            Thread detectionThread = new Thread(() -> detectionThread(port));
            detectionThread.start();
            Thread severalClientsThread = new Thread(() -> severalClientsThread(port));
            severalClientsThread.start();
            Thread statisticsThread = new Thread(() -> statistiscThread());
            statisticsThread.start();

        } catch (NumberFormatException e) {
            System.err.println("Podany numer nie jest liczba! Wpisz dodatnia liczbe calkowita!");
            System.exit(1);
        }

    }

    private static void detectionThread(int port) {
        while (true) {
            //gniazdo
            try (DatagramSocket socket = new DatagramSocket(port)) {
                //bufor dla danych w z znaczeniu binarnym
                byte[] buffer = new byte[1024];

                //mamy do czynienia z UDP - uzywamy abstrakcji pakietu, dostajemy wiadomosc
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());

                //wysylamy wiadomosc zwrotna, jesli dostarczona zawiera CCS DISCOVER
                if (message.startsWith("CCS DISCOVER")) {
                    byte[] echo = "CCS FOUND".getBytes();
                    InetAddress klientAdress = packet.getAddress();
                    int klientPort = packet.getPort();
                    DatagramPacket responcePacket = new DatagramPacket(echo, echo.length, klientAdress, klientPort);
                    socket.send(responcePacket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void severalClientsThread(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> communicationThread(clientSocket)).start();
                clientsAmount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void communicationThread(Socket clientSocket) {

        //socket serwera
        try (OutputStream outputStream = clientSocket.getOutputStream()) {

            //flaga bledow, wynik
            boolean error = false;
            int result = 0;

            while (true) {


                //OUTPUT (dla poprzedniej iteracji zakonczonej bledem)

                String response = "";
                if (error) {
                    response = "ERROR";
                    outputStream.write(response.getBytes());
                    errorOperationsAmount++;
                    error = false;
                }


                //INPUT

                //1)odbior polecenia
                InputStream inputStream = clientSocket.getInputStream();

                byte[] buffer = new byte[1024];
                int bufferLength = inputStream.read(buffer);
                String message = new String(buffer, 0, bufferLength);
                String[] messages = message.split(" ");

                //3) errory
                //argumentow jest 3?
                if (messages.length != 3) {
                    System.err.println("Wpisz dokladnie 3 argumenty: OPERACJA LICZBA LICZBA, oddzielone spacjami");
                    error = true;
                    errorOperationsAmount++;
                    continue;
                }
                //liczby sa calkowite?
                if ((Float.parseFloat(messages[1]) % 1 != 0) ||
                        (Float.parseFloat(messages[2]) % 1 != 0)) {
                    System.err.println("Wpisz liczby calkowite");
                    error = true;
                    errorOperationsAmount++;
                    continue;
                }

                int firstNumber = Integer.parseInt(messages[1]);
                int secondNumber = Integer.parseInt(messages[2]);

                //2) obliczenie wyniku
                switch (messages[0]) {
                    case "ADD": {
                        result = firstNumber + secondNumber;
                        solvingOperationsAmount++;
                        break;
                    }
                    case "SUB": {
                        result = firstNumber - secondNumber;
                        solvingOperationsAmount++;
                        break;
                    }
                    case "MUL": {
                        result = firstNumber * secondNumber;
                        solvingOperationsAmount++;
                        break;
                    }
                    case "DIV": {
                        //dzielenie przez 0?
                        if (secondNumber == 0) {
                            System.err.println("Wpisz dokladnie 3 argumenty: OPERACJA LICZBA LICZBA, oddzielone spacjami");
                            error = true;
                            errorOperationsAmount++;
                            continue;
                        }
                        result = firstNumber / secondNumber;
                        solvingOperationsAmount++;
                        break;
                    }
                    default: {
                        System.err.println("Wpisz zadana operacje w nastepujacy sposob: " +
                                "ADD [dodawanie], SUB [odejmowanie], MUL [mnozenie], DIV [dzielenie]");
                        error = true;
                        errorOperationsAmount++;
                        continue;
                    }
                }

                //OUTPUT (iteracja pomyslna)

                //3) odeslanie wyniku
                response = String.valueOf(result);
                outputStream.write(response.getBytes());
                resultsSum += result;
                allOperationsAmount = errorOperationsAmount + solvingOperationsAmount;

                //4) wypisanie wyniku i operacji na ekran
                System.out.println("Przeprowadzona operacja: " + messages[1] + '\n' + "Wynik: " + response);

                //5) raportowanie statystyk
                //6) powrot
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void statistiscThread() {
        while (true) {

            int oldClientsAmount = clientsAmount;
            int oldSolvingOperationsAmount = solvingOperationsAmount;
            int oldErrorOperationsAmount = errorOperationsAmount;
            int oldAllOperationsAmount = allOperationsAmount;
            int oldResultsSum = resultsSum;

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int newClientsAmount = clientsAmount;
            int newSolvingOperationsAmount = solvingOperationsAmount;
            int newErrorOperationsAmount = errorOperationsAmount;
            int newAllOperationsAmount = allOperationsAmount;
            int newResultsSum = resultsSum;

            System.out.println(
                    "Liczba operacji/Zmiana w ciagu ostatnich 10 sek:\n" +
                            "Liczba klientow: " + newClientsAmount + "/+" + (newClientsAmount - oldClientsAmount) + '\n' +
                            "Liczba obliczonych: " + newSolvingOperationsAmount + "/+" + (newSolvingOperationsAmount - oldSolvingOperationsAmount) + '\n' +
                            "Liczba bledow: " + newErrorOperationsAmount + "/+" + (newErrorOperationsAmount - oldErrorOperationsAmount) + '\n' +
                            "Liczba wszystkich operacji: " + newAllOperationsAmount + "/+" + (newAllOperationsAmount - oldAllOperationsAmount) + '\n' +
                            "Suma wynikow: " + newResultsSum + "/+" + (newResultsSum - oldResultsSum) + '\n'
            );
        }
    }
}