import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MicroDVD microDVD = new MicroDVD();
        final String inputFile = "input.txt";
        final String outputFile = "output.txt";
        try {
            microDVD.delay(inputFile, outputFile, 2000, 50);
        }catch(Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
