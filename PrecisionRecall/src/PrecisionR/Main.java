package PrecisionR;

import org.apache.commons.collections.Bag;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        String gold_path = "/Users/Philip/Clarivate/Command/PDE_Gold.2columnpure.txt";
        String output_path = "/Users/Philip/Clarivate/Command/output/output-2-col.txt";

        String testGoldPath = "/Users/Philip/Clarivate/testData/testGold";
        String testOutputPath = "/Users/Philip/Clarivate/testData/testOutput";

        String g = gold_path;
        String o = output_path;

        Bag intersect = ReadData.readAndJoin(o, g);
        Bag[] bags = ReadData.readAndJoin1(o, g);

        String[] g_arr = g.split("/");
        String[] o_arr = o.split("/");
        System.out.println("Using gold file: " + g_arr[g_arr.length - 1]);
        System.out.println("Using outpur file: " + o_arr[o_arr.length - 1]);

        System.out.println("---\nintersect: " + intersect + "\n---");

        System.out.println("There are " + bags[0].size() + " rows. ");
        System.out.println("There are " + bags[0].uniqueSet().size() + " pairs. ");
        System.out.println("\nStart calculating\n");

//        long startTime = System.currentTimeMillis();
//        double[] pr = BCubedBag.precisionRecall(intersect);
//        long stopTime = System.currentTimeMillis();
//        System.out.println("Elapsed time one-bag BCubed " + (stopTime - startTime) + " miliseconds.");

        long startTime1 = System.currentTimeMillis();
        double[] pr1 = BCubed3Bags.precisionRecall(bags[0], bags[1], bags[2]);
        long stopTime1 = System.currentTimeMillis();
        System.out.println("Elapsed time three-bag BCubed " + (stopTime1 - startTime1) + " miliseconds.");


//        System.out.println("\nOne-bag BCubed PR " + Arrays.toString(pr));
        System.out.println("Three-bag BCubed PR " + Arrays.toString(pr1));

        // Calculating pairwise PR
        String[][] df = ReadData.readTo2DStringArray(o, g);
        double[] pairwisePR = PairwiseImproved.precisionRecall(df);
        System.out.println("Pairwise PR " + Arrays.toString(pairwisePR));



    }

}