import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.length;

public class Utils {
    public static String readFileAsString(String filepath) {
        StringBuilder output = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filepath))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public static ArrayList<ElectionResult> parse2018ElectionResults(String data) {
        ArrayList<ElectionResult> result = new ArrayList<>();
        String[] lines = data.split("\n");
        String[] linesArray;

        for (int i = 1; i < lines.length; i++) {
            int indexOfQuote = lines[i].indexOf("\"");
            if(indexOfQuote == -1){
                int indexOf2ndQuote = lines[i].indexOf("\"", indexOfQuote+1);
                String originalWord = lines[i].substring(indexOfQuote, indexOf2ndQuote + 1);
                String newWord = originalWord.replaceAll("\"", "").replaceAll(",", "");

                lines[i] = lines[i].replace(originalWord, newWord);

            }

            linesArray = lines[i].split(",");


        }
        double votes_dem = Double.parseDouble(linesArray[1]);
        double votes_gop = Double.parseDouble(linesArray[2]);
        double total_votes = Double.parseDouble(linesArray[3]);
        double per_dem = Double.parseDouble(linesArray[4]);
        double per_gop = Double.parseDouble(linesArray[5]);
        String diffString = replaceAll(",",linesArray[6]);
        double diff = Double.parseDouble(diffString);
        String per_diff_String = replaceAll("%",linesArray[7]);
        double per_point_diff = Double.parseDouble(per_diff_String);
        String state_abbr = linesArray[8];
        String county_name = linesArray[9];
        double combined_fips = Double.parseDouble(linesArray[10]);
        ElectionResult electionResultObject = new ElectionResult(votes_dem, votes_gop, total_votes, per_dem, per_gop, diff, per_point_diff, state_abbr, county_name, combined_fips);
        result.add(electionResultObject);


        return result;
    }

}
