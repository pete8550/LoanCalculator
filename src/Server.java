import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(8000);

            System.out.println("Serveren er oppe!");

            Socket socket = server.accept();

            System.out.println("Serveren har modtaget en forbindelse fra " + socket.getRemoteSocketAddress().toString());

            while (true) {

                DataInputStream in = new DataInputStream(socket.getInputStream());

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                double annualInterestRate = in.readDouble();
                System.out.println("Annual Interest Rate: " + annualInterestRate);
                out.writeDouble(annualInterestRate);

                int numberOfYears = in.readInt();
                System.out.println("Number of Years: " + numberOfYears);
                out.writeInt(numberOfYears);

                double loanAmount = in.readDouble();
                System.out.println("Loan Amount: " + loanAmount);
                out.writeDouble(loanAmount);

                double totalPayment = loanAmount * (Math.pow(1 + (annualInterestRate / 100), numberOfYears));
                System.out.println("Total Payment: " + totalPayment);
                out.writeDouble(totalPayment);

                double monthlyPayment = totalPayment / (numberOfYears * 12);
                System.out.println("Monthly Payment: " + monthlyPayment);
                out.writeDouble(monthlyPayment);
            }

        } catch (IOException e) {
            System.out.println("Serveren oplever teknisk fejl");

            e.printStackTrace();
        }


    }
}
