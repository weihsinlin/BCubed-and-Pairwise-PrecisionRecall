package PrecisionR;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.bag.HashBag;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class ReadData {

    private static HashMap<String, String> readGold(String goldPath) {
        File file = new File(goldPath);
        HashMap<String, String> utToId = new HashMap<>();

        try {
            LineIterator it = FileUtils.lineIterator(file);
            while (it.hasNext()) {
                String line = it.nextLine();
                int k = line.indexOf('|');
                if (k >= 0) {
                    utToId.put(line.substring(0, k).toLowerCase(), line.substring(k+1));
                }
            }
        } catch (IOException x) {
            System.out.println(x.getMessage());
        }

        return utToId;
    }


    public static Bag readAndJoin(String outputPath, String goldPath) {
        HashMap<String, String> goldData = readGold(goldPath);
        File file = new File(outputPath);
        HashBag result = new HashBag();
        try {
            LineIterator it = FileUtils.lineIterator(file);
            while (it.hasNext()) {
                String line = it.nextLine();
                String[] s = line.split("\t");
                if (goldData.containsKey(s[0])) {
                    result.add(goldData.get(s[0]) + "~" + s[1]);
                }
            }
        } catch (IOException x) {
            System.out.println(x.getMessage());
        }
        return result;
    }

    public static Bag[] readAndJoin1(String outputPath, String goldPath) {
        HashMap<String, String> goldData = readGold(goldPath);
        File file = new File(outputPath);
        Bag intersect = new HashBag();
        Bag goldId = new HashBag();
        Bag clusterId = new HashBag();

        try {
            LineIterator it = FileUtils.lineIterator(file);
            while (it.hasNext()) {
                String line = it.nextLine();
                String[] s = line.split("\t");
                if (goldData.containsKey(s[0])) {
                    intersect.add(goldData.get(s[0]) + "~" + s[1]);
                    goldId.add(goldData.get(s[0]));
                    clusterId.add(s[1]);
                }
            }
        } catch (IOException x) {
            System.out.println(x.getMessage());
        }
        return new Bag[] {intersect, goldId, clusterId};
    }


    public static String[][] readTo2DStringArray(String outputPath, String goldPath) {
        HashMap<String, String> goldData = readGold(goldPath);
        File file = new File(outputPath);
        int nrows = readAndJoin(outputPath, goldPath).size();
        String[][] result = new String[nrows][3];
        int row_i = 0;
        try {
            LineIterator it = FileUtils.lineIterator(file);
            while (it.hasNext()) {
                String line = it.nextLine();
                String[] s = line.split("\t");
//                System.out.println(goldData.containsKey(s[0]));
                if (goldData.containsKey(s[0])) {
                    String item = s[0];
                    String gID = goldData.get(s[0]);
                    String sID = s[1];
                    result[row_i][0] = item; result[row_i][1] = gID; result[row_i][2] = sID;
                    row_i++;
                }
            }
        } catch (IOException x) {
            System.out.println(x.getMessage());
        }
        return result;
    }




    public static void main(String[] args) {

        String gold_path = "/Users/Philip/Clarivate/Command/PDE_Gold.2columnpure.txt";
        String output_path = "/Users/Philip/Clarivate/Command/output/output-2-col.txt";

        String testGoldPath = "/Users/Philip/Clarivate/testData/testGold";
        String testOutputPath = "/Users/Philip/Clarivate/testData/testOutput";

    }

}
