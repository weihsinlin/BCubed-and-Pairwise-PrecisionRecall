package PrecisionR;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.bag.HashBag;

import java.util.HashMap;
import java.util.Map;

public class BCubedImproved {


    public static double itemPrecision(IdLabel il, HashMap<IdLabel, Integer> grouped) {
        double tp_fp = 0;
        double tp = grouped.get(il);

        for (IdLabel key : grouped.keySet()){
            if (key.getLabel().equals(il.getLabel())) {
                tp_fp += grouped.get(key);
            }
        }
        return tp / tp_fp;
    }



    // Calculate an item's recall
    public static double itemRecall(IdLabel il, HashMap<IdLabel, Integer> grouped) {
        double tp = grouped.get(il);
        double tp_fn = 0;
        for (IdLabel key : grouped.keySet()){
            if (key.getId().equals(il.getId())) {
                tp_fn += grouped.get(key);
            }
        }

        return tp / tp_fn;
    }

    public static double[] precisionRecall(String[][] data) {
        double n_row = Utils.n_rows(data);
        HashMap<IdLabel, Integer> grouped = Utils.groupBy(data);

        double prec_acc = 0;
        double recall_acc = 0;

        for (IdLabel il : grouped.keySet()) {
            double p = itemPrecision(il, grouped);
            double r = itemRecall(il, grouped);
            prec_acc += (p * grouped.get(il));
            recall_acc += (r * grouped.get(il));
        }
        return new double[] {prec_acc / n_row, recall_acc / n_row};
    }



}
