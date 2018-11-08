package PrecisionR;

import java.util.HashMap;

public class Pairwise {

    public static void print(Object o) {System.out.println(o);}

    // Compare two row to see if the pair is tp, fp, fn, or tn
    public static String compare(String[] row1, String[] row2) {
        String id1 = row1[1]; String id2 = row2[1];
        String label1 = row1[2]; String label2 = row2[2];
        if (id1.equals(id2) && label1.equals(label2)) {
            return "tp";
        } else if (!id1.equals(id2) && label1.equals(label2)) {
            return "fp";
        } else if (id1.equals(id2) && !label1.equals(label2)) {
            return "fn";
        } else {
            return "tn";
        }
    }


    // Return all pairing result (e.g. ["tp", "tp", "tn", "fn", ...])
    public static String[] pair(String[][] data) {
        String[] rtn = new String[Utils.n_choose_k(Utils.n_rows(data), 2)];

        int i = 0;
        for (int j = 0; j < Utils.n_rows(data); j++) {
            for (int k = j + 1; k < Utils.n_rows(data); k++) {
                rtn[i] = compare(Utils.row(j, data), Utils.row(k, data));
                i++;
            }
        }
        return rtn;
    }

    // Calculation of precision and recall
    public static double[] precisionRecall(String[][] data) {
        String[] pair_result = pair(data);
        HashMap<String, Double> confusion = new HashMap<>();
        for (String el : pair_result) {
            if (confusion.containsKey(el)) {
                double val = confusion.get(el);
                confusion.put(el, val + 1);
            } else {
                confusion.put(el, 1.0);
            }
        }

        double precision = confusion.get("tp") / (confusion.get("tp") + confusion.get("fp"));
        double recall = confusion.get("tp") / (confusion.get("tp") + confusion.get("fn"));
        return new double[] {precision, recall};
    }


}
