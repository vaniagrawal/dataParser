/***
 * Main class for data parser
 * @Author: Vani Agrawal
 */
public class Main {
    public static void main(String[] args) {
        String data = Utils.readFileAsString("data/2016_Presidential_Results.csv");
        System.out.println(data);

    }
}
