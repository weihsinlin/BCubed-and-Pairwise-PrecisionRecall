package PrecisionR;

public class BCubed {

    // Calculate an item's precision
    public static double itemPrecision(String[] row, String[][] data) {
        double tp_fp = 0;
        double tp = 0;

        for (int i = 0; i < Utils.n_rows(data); i++) {
            String[] temp_row = Utils.row(i, data);
            if (temp_row[2].equals(row[2])) {
                tp_fp++;
                if (temp_row[1].equals(row[1])) tp++;
            }
        }
        return tp / tp_fp;
    }

    // Calculate an item's recall
    public static double itemRecall(String[] row, String[][] data) {
        double tp = 0;
        double tp_fn = 0;
        for (int i = 0; i < Utils.n_rows(data); i++) {
            String[] temp_row = Utils.row(i, data);
            if (temp_row[1].equals(row[1])) {
                tp_fn++;
                if (temp_row[2].equals(row[2])) tp++;
            }

        }
        return tp / tp_fn;
    }

    // Calculate overall precision and recall
    public static double[] precisionRecall(String[][] data) {
        double n_row = Utils.n_rows(data);
        double prec_acc = 0;
        double recall_acc = 0;
        for (int i = 0; i < Utils.n_rows(data); i++) {
            String[] r = Utils.row(i, data);
            prec_acc += itemPrecision(r, data);
            recall_acc += itemRecall(r, data);
        }
        return new double[] {prec_acc / n_row, recall_acc / n_row};
    }

}
