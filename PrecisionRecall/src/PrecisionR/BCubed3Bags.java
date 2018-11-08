package PrecisionR;

import org.apache.commons.collections.Bag;

import java.util.HashMap;
import java.util.Map;

public class BCubed3Bags {
    public static double[] itemPR(Bag intersect, Bag goldIds, Bag clusterIds, String idPair) {

        double tp = intersect.getCount(idPair);

        int k = idPair.indexOf('~');
        String goldId = idPair.substring(0, k);
        String clusterId = idPair.substring(k + 1);
        double tp_fp = clusterIds.getCount(clusterId);
        double tp_fn = goldIds.getCount(goldId);

        return new double[] {tp / tp_fp, tp / tp_fn};
    }

    public static double[] precisionRecall(Bag intersect, Bag goldIds, Bag clusterIds) {
        long nrows = intersect.size();
        Map<String, double[]> idPairToPR = new HashMap<>();

        // Calculate precision and recall for each (goldId, clusterId) pair
        for (Object o : intersect.uniqueSet()) {
            String idPair = o.toString();
            double[] pr = itemPR(intersect, goldIds, clusterIds, idPair);
            pr[0] *= intersect.getCount(idPair);
            pr[1] *= intersect.getCount(idPair);
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
