package PrecisionR;

import org.apache.commons.collections.Bag;

import java.util.HashMap;
import java.util.Map;

public class BCubedBag {
    public static double[] itemPR(Bag intersect, String idPair) {
        double tp_fp = 0;
        double tp_fn = 0;
        double tp = intersect.getCount(idPair);

        int k = idPair.indexOf('~');
        String goldId = idPair.substring(0, k);
        String clusterId = idPair.substring(k + 1);

        for (Object item : intersect.uniqueSet()){
            String pair = item.toString();
            int l = idPair.indexOf('~');
            String itemGoldId = pair.substring(0, l);
            String itemClusterId = pair.substring(l + 1);

            if (itemGoldId.equals(goldId)) { // clusterId same
                tp_fn += intersect.getCount(item);
            }

            if (itemClusterId.equals(clusterId)) { //goldId same
                tp_fp += intersect.getCount(item);
            }
        }

        return new double[] {tp / tp_fp, tp / tp_fn};
    }

    public static double[] precisionRecall(Bag intersect) {
        long nrows = intersect.size();
        Map<String, double[]> idPairToPR = new HashMap<>();

        // Calculate precision and recall for each (goldId, clusterId) pair
        for (Object o : intersect.uniqueSet()) {
            String idPair = o.toString();
            double[] pr = itemPR(intersect, idPair);
            pr[0] *= intersect.getCount(o);
            pr[1] *= intersect.getCount(o);
            idPairToPR.put(idPair, pr);
        }

        // Calculate overall PR
        double overallP = 0.0;
        double overallR = 0.0;
        for (double[] PR : idPairToPR.values()) {
            overallP += PR[0];
            overallR += PR[1];
        }

        overallP = overallP / nrows;
        overallR = overallR / nrows;

        return new double[] {overallP, overallR};

    }
}
