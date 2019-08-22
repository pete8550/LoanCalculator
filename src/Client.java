import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 8000);

            DataInputStream in = new DataInputStream(socket.getInputStream());

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);

            double annualInterestRate = 0.0;
            int numberOfYears = 0;
            double loanAmount = 0;

            while (true) {
                //Ask client for Annual Interest Rate as a double
                System.out.println("Annual Interest Rate: ");
                annualInterestRate = scanner.nextDouble();
                out.writeDouble(annualInterestRate);

                //Ask client for Number of Years as an int
                System.out.println("Number of Years: ");
                numberOfYears = scanner.nextInt();
                out.writeInt(numberOfYears);

                //Ask client for Loan Amount as a double
                System.out.println("Loan Amount: ");
                loanAmount = scanner.nextDouble();
                out.writeDouble(loanAmount);

                //Show the client the results
                System.out.println("Annual Interest Rate: " + in.readDouble());
                System.out.println("Number of Years: " + in.readInt());
                System.out.println("Loan Amount: " + in.readDouble());
                System.out.println("Total Payment: " + in.readDouble());
                System.out.println("Monthly Payment: " + in.readDouble());
                System.out.println("---------------------------------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
