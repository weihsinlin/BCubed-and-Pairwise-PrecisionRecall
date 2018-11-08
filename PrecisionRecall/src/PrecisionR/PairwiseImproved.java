package PrecisionR;

import java.util.Arrays;
import java.util.HashMap;

public class PairwiseImproved {

    // Compare two row to see if the pair is tp, fp, fn, or tn
    public static String compare(String id1, String id2, String label1, String label2) {
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



    public static HashMap<PairIdLabel, String> pairCompare(String[][] data) {
        //group by goldid and label agg sum
        HashMap<IdLabel, Integer> grouped = Utils.groupBy(data);

        Object[] arr_obj = grouped.keySet().toArray();
        IdLabel[] arr = Arrays.copyOf(arr_obj, arr_obj.length, IdLabel[].class);

        HashMap<PairIdLabel, String> result = new HashMap<>();

        for (int j = 0; j < arr.length; j++) {
            for (int k = j + 1; k < arr.length; k++) {
                IdLabel il1 = arr[j]; IdLabel il2 = arr[k];
                String id1 = il1.getId(); String id2 = il2.getId();
                String label1 = il1.getLabel(); String label2 = il2.getLabel();
                result.put(new PairIdLabel(il1, il2), compare(id1, id2, label1, label2));
            }
        }
        return result;
    }

    // Calculation of precision and recall
    public static double[] precisionRecall(String[][] data) {
        HashMap<IdLabel, Integer> grouped = Utils.groupBy(data);
        HashMap<PairIdLabel, String> paired = pairCompare(data);

        HashMap<String, Double> confusion = new HashMap<>();
        for (PairIdLabel pair : paired.keySet()) {
            IdLabel il1 = pair.il1;
            IdLabel il2 = pair.il2;
            double item_pair = grouped.get(il1) * grouped.get(il2);
            String res = paired.get(pair);

            if (confusion.containsKey(res)) {
                double val = confusion.get(res) + item_pair;
                confusion.put(res, val);
            } else {
                confusion.put(res, item_pair);
            }
        }
        double tp = 0;
        for (int val : grouped.values()) {
            tp += Utils.n_choose_k(val, 2);
        }
        confusion.put("tp", tp);

        String[] elem = new String[] {"tp", "fp", "tn", "fn"};
        for (String el : elem) {
            if (!confusion.containsKey(el)) confusion.put(el, 0.0);
        }
        double precision = confusion.get("tp") / (confusion.get("tp") + confusion.get("fp"));
        double recall = confusion.get("tp") / (confusion.get("tp") + confusion.get("fn"));
        return new double[] {precision, recall};
    }


}
