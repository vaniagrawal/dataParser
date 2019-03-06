/***
 * Main class for data parser
 * @Author: Vani Agrawal
 */
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        String data = Utils.readFileAsString("data/2016_Presidential_Results.csv");
        ArrayList<ElectionResult> results = Utils.parse2018ElectionResults(data);
        System.out.println(results);

    }
}
