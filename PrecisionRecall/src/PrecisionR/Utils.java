package PrecisionR;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Utils {
    static int df_size = 5000;

    public static void printHashMap(HashMap map) {
        System.out.println("-----Start Printing HashMap-----");
        for (Object name: map.keySet()){
            String key = name.toString();
            String value = map.get(name).toString();
            System.out.println(key + ": " + value);
        }
        System.out.println("------End Printing HashMap------");
    }

    public static int n_choose_k(int n, int k) {
        if (k == 0) {
            return 1;
        } else {
            return (n * n_choose_k(n-1, k-1) / k);
        }
    }

    public static HashMap<IdLabel, Integer> groupBy(String[][] data) {
        HashMap<IdLabel, Integer> result = new HashMap<>();
        for (int i = 0; i < n_rows(data); i++) {
            String[] r = row(i, data);
            IdLabel idLabel = new IdLabel(r[1], r[2]);

            if (result.containsKey(idLabel)) {
                int val = result.get(idLabel);
                result.put(idLabel, val + 1);
            } else {
                result.put(idLabel, 1);
            }
        }
        return result;
    }




    public static String[][] make_toy_df1() {
        String[] goldid = new String[] {"A", "A", "B", "B", "B", "B"};
        String[] label  = new String[] {"1", "1", "1", "2", "2", "2"};
        String[] item   = new String[label.length];
        for (int i = 0; i < item.length; i++) item[i] = "Item" + Integer.toString(i + 1);
//        String[][] df = new String[][] {item, goldid, label};

        String[][] df = new String[goldid.length][3];
        for (int i = 0; i < item.length; i++) {
            df[i][0] = item[i];
            df[i][1] = goldid[i];
            df[i][2] = label[i];
        }
        return df;
    }

    public static String[][] make_toy_df2() {
        String[] goldid = new String[] {"A", "A", "B", "B", "B", "B", "C", "C", "C", "C"};
        String[] label  = new String[] {"1", "1", "1", "2", "2", "2", "2", "3", "3", "3"};
        String[] item   = new String[label.length];
        for (int i = 0; i < item.length; i++) item[i] = "Item" + Integer.toString(i + 1);
        String[][] df = new String[][] {item, goldid, label};
        return df;
    }

    public static String[][] make_toy_df3() {
        Random rand = new Random();
        int size = df_size;
        String[] label = rand.ints(size, 1, 6).mapToObj(String::valueOf).toArray(String[]::new);
        String[] goldid = new String[size];
        String[] item   = new String[size];
        for (int i = 0; i < size; i++) {
            char c = (char) (rand.nextInt(3) + 'A');
            goldid[i] = String.valueOf(c);
            item[i] = "Item" + Integer.toString(i + 1);
        }
//        String[][] df = new String[][] {item, goldid, label};
        String[][] df = new String[goldid.length][3];
        for (int i = 0; i < item.length; i++) {
            df[i][0] = item[i];
            df[i][1] = goldid[i];
            df[i][2] = label[i];
        }

        return df;
    }


    public static String[] row(int n, String[][] data) {
        return data[n];
    }

    public static int n_rows(String[][] data) {
        return data.length;
    }



    public static void main(String[] args) {
        String[][] d = make_toy_df1();
//        toUpper(d, 1);
        System.out.println(Arrays.deepToString(d));
    }

}
