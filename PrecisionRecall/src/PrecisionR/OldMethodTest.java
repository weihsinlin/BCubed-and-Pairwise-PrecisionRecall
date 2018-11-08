package PrecisionR;

import java.text.DecimalFormat;
import java.util.Arrays;

public class OldMethodTest {
    public static void main(String[] args) {

        String gold_path = "/Users/Philip/Clarivate/Command/PDE_Gold.2columnpure.txt";
        String output_path = "/Users/Philip/Clarivate/Command/output/output-2-col.txt";

        String testGoldPath = "/Users/Philip/Clarivate/testData/testGold";
        String testOutputPath = "/Users/Philip/Clarivate/testData/testOutput";

        String[][] df = ReadData.readTo2DStringArray(output_path, gold_path);

        System.out.println("For number of rows = " + df.length + " : " + "\n");

//        long startTime = System.currentTimeMillis();
//        double[] pairwise = Pairwise.precisionRecall(df);
//        long stopTime = System.currentTimeMillis();
//        System.out.println("Elapsed time for naive pairwise was " + (stopTime - startTime) + " miliseconds.");

        long startTime1 = System.currentTimeMillis();
        double[] improvedPairwise = PairwiseImproved.precisionRecall(df);
        long stopTime1 = System.currentTimeMillis();
        System.out.println("Elapsed time improved pairwise was " + (stopTime1 - startTime1) + " miliseconds.");

//        long startTime2 = System.currentTimeMillis();
//        double[] b3 = BCubed.precisionRecall(df);
//        long stopTime2 = System.currentTimeMillis();
//        System.out.println("Elapsed time naive B3 was " + (stopTime2 - startTime2) + " miliseconds.");

        long startTime3 = System.currentTimeMillis();
        double[] improvedB3 = BCubedImproved.precisionRecall(df);
        long stopTime3 = System.currentTimeMillis();
        System.out.println("Elapsed time improved B3 was " + (stopTime3 - startTime3) + " miliseconds.");



        System.out.println();
//        DecimalFormat format = new DecimalFormat("#.######");
//        System.out.println("PrecisionR.Pairwise Precision:          " + format.format(pairwise[0]) + "  PrecisionR.Pairwise Recall:          " + format.format(pairwise[1]));
//        System.out.println("Improved PrecisionR.Pairwise Precision: " + format.format(improvedPairwise[0]) + "  Improved PrecisionR.Pairwise Recall: " + format.format(improvedPairwise[1]));
//        System.out.println("B3 Precision:                " + format.format(b3[0]) + "  B3 Recall:                " + format.format(b3[1]));
//        System.out.println("Improved B3 Precision:       " + format.format(improvedB3[0]) + "  Improved B3 Recall:       " + format.format(improvedB3[1]));
        System.out.println("Pairwise PR: " + Arrays.toString(improvedPairwise));
        System.out.println("BCubed PR: " + Arrays.toString(improvedB3));
    }
}
