package PrecisionR;

public class PairIdLabel {
    IdLabel il1;
    IdLabel il2;

    public PairIdLabel(IdLabel il1, IdLabel il2) {
        this.il1 = il1;
        this.il2 = il2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PairIdLabel)) return false;
        PairIdLabel that = (PairIdLabel) obj;
        return (this.il1.equals(that.il1) && this.il2.equals(that.il2)) ||
                (this.il1.equals(that.il2) && this.il2.equals(that.il1));

    }

    @Override
    public int hashCode() {
        return il1.hashCode() + il2.hashCode();
    }

    @Override
    public String toString() {
        return "Pair{" + il1.toString() + ", " + il2.toString() + "}";
    }
}
