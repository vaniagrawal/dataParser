import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;

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
            if(indexOfQuote != -1){
                int indexOf2ndQuote = lines[i].indexOf("\"", indexOfQuote+1);
                String originalWord = lines[i].substring(indexOfQuote, indexOf2ndQuote+1);
                String newWord = originalWord.replaceAll("\"", "").replaceAll(",", "");

                lines[i] = lines[i].replace(originalWord, newWord);

            }

            linesArray = lines[i].split(",");
            double votes_dem = Double.parseDouble(linesArray[1]);
            double votes_gop = Double.parseDouble(linesArray[2]);
            double total_votes = Double.parseDouble(linesArray[3]);
            double per_dem = Double.parseDouble(linesArray[4]);
            double per_gop = Double.parseDouble(linesArray[5]);

            double diff = Double.parseDouble(linesArray[6]);
            double per_point_diff = Double.parseDouble(linesArray[7].replaceAll("%", ""));
            String state_abbr = linesArray[8];
            String county_name = linesArray[9];
            double combined_fips = Double.parseDouble(linesArray[10]);
            ElectionResult electionResultObject = new ElectionResult(votes_dem, votes_gop, total_votes, per_dem, per_gop, diff, per_point_diff, state_abbr, county_name, combined_fips);
            result.add(electionResultObject);


        }


        return result;


    }

    public static ArrayList<Education2016> parseEducationData(String data) {
        ArrayList<Education2016> results = new ArrayList<>();
        String[] rows = data.split("\n");
        ArrayList<String> rowsList = new ArrayList<>();

        for (String s : rows) {
            rowsList.add(s);
        }

        ArrayList<String> labels = formatArray(rowsList.get(4));

        for (int i = 5; i < labels.size(); i++) {
            ArrayList<String> valsList = formatArray(rowsList.get(i));
            double noHighSchool = Double.parseDouble(valsList.get(labels.indexOf("Percent of adults with less than a high school diploma 2012-2016")));
            double onlyHighSchool = Double.parseDouble(valsList.get(labels.indexOf("Percent of adults with a high school diploma only 2012-2016")));
            double someCollege = Double.parseDouble(valsList.get(labels.indexOf("Percent of adults completing some college or associate's degree 2012-2016")));
            double bachelors = Double.parseDouble(valsList.get(labels.indexOf("Percent of adults with a bachelor's degree or higher 2012-2016")));
            Education2016 educationObject = new Education2016(noHighSchool, onlyHighSchool, someCollege, bachelors);
            results.add(educationObject);
        }
        return results;    }

    public static ArrayList<Employment2016> parseEmploymentData(String data) {
        ArrayList<Employment2016> results = new ArrayList<>();
        String[] rows = data.split("\n");
        ArrayList<String> list = new ArrayList<>();

        for (String piece : rows) {
            list.add(piece);
        }

        ArrayList<String> labels = formatArray(list.get(7));

        for (int i = 8; i < labels.size(); i++) {
            ArrayList<String> vals = formatArray(list.get(i));
            double totalLaborForce = Double.parseDouble(vals.get(labels.indexOf("Civilian_labor_force_2016")));
            double unemployedPercentage = Double.parseDouble(vals.get(labels.indexOf("Unemployment_rate_2016")));
            double employedNum = Double.parseDouble(vals.get(labels.indexOf("Employed_2016")));
            double unemployedNum = Double.parseDouble(vals.get(labels.indexOf("Unemployed_2016")));
            Employment2016 employmentObject = new Employment2016(totalLaborForce, unemployedPercentage, employedNum, unemployedNum);
            results.add(employmentObject);
        }
        return results;    }
    private static ArrayList<String> formatArray(String word) {
        word.replace("\"", "");
        String[] arr = word.split(",", -1);
        ArrayList<String> vals = new ArrayList<>();
        for (String string : arr) {
            vals.add(word);
        }
        for (int i = 0; i < vals.size(); i++) {
            vals.get(i).trim();
            if(vals.get(i).isEmpty()){
                vals.set(i, "0");
            }
        }
        return vals;
    }

}
