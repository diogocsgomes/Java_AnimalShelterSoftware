package com.example.gps_g21;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Calculadora");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        //launch();
        Scanner in = new Scanner(System.in);
        String input = new String();
        int opcao = 1, i, r, a;
        float resultFlo = 0;
        String resultStr = new String();

        while (true)
        {
            System.out.print("\n\n->0:Terminar\n");
            System.out.print("->1:Operacoes basicas calculadora\n");
            System.out.print("->2:Adicionar/subtrair dias a uma data\n");
            System.out.print("->3:Diferenca entre datas\n");
            System.out.print("->4:Conversacao binario para hexadecimal\n");
            System.out.print("->5:Conversacao hexadecimal para binario\n");
            System.out.print("->6:Numero Fibonacci\n");
            System.out.print("->7:Fatorial\n");
            System.out.print("->8:Volume do cilindro\n");
            System.out.print("->9:Volume do cone\n");
            System.out.print("\n-> ");
            opcao = in.nextInt();

            switch (opcao){
                case 1:
                    in.nextLine();
                    System.out.print("-> ");
                    input = in.nextLine();
                    resultFlo = operacoesCalculadora.operacoesBasicas(input); //Retorna float
                    System.out.println("Resultado: " + resultFlo);
                    break;
                case 2:
                    in.nextLine();
                    System.out.print("Introduza a data (01/01/2023)-> ");
                    input = in.nextLine();
                    System.out.print("Introduza os dias a somar ou subtrair (2 || -2)-> ");
                    int days = in.nextInt();
                    resultStr = operacoesCalculadora.addSubtractDaysToDate(input, days); //Retorna string
                    System.out.println("Resultado: " + resultStr);
                    break;
                case 3:
                    System.out.print("-> ");
                    input = in.nextLine();
                    break;
                case 4:
                    System.out.print("-> ");
                    input = in.nextLine();
                    System.out.println("Resultado: " + operacoesCalculadora.binaryToHexadecimal(input));
                    break;
                case 5:
                    System.out.print("-> ");
                    input = in.nextLine();
                    System.out.println("Resultado: " + operacoesCalculadora.hexadecimalToBinary(input));
                    break;
                case 6:
                    System.out.print("-> ");
                    i = in.nextInt();
                    System.out.println("Resultado: " + operacoesCalculadora.fibonacci(i));
                    break;
                case 7:
                    System.out.print("-> ");
                    i = in.nextInt();
                    System.out.println("Resultado: " + operacoesCalculadora.factorial(i));
                    break;
                case 8:
                    System.out.print("-> Indique o raio do cilindro: ");
                    r = in.nextInt();
                    System.out.print("-> Indique a altura do cilindro: ");
                    a = in.nextInt();
                    System.out.println("Resultado: " + operacoesCalculadora.volumeOfCylinder(r, a) + " cm^3");
                    break;
                case 9:
                    System.out.print("-> Indique o raio do cone: ");
                    r = in.nextInt();
                    System.out.print("-> Indique a altura do cone: ");
                    a = in.nextInt();
                    System.out.println("Resultado: " + operacoesCalculadora.volumeOfCone(r, a) + " cm^3");
                    break;
                default:
                    System.exit(0);
                    break;
            }
        }
    }
}