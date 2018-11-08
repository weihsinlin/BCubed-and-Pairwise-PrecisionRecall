package PrecisionR;

public class IdLabel {
    String id;
    String label;

    public IdLabel(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof IdLabel) {
            IdLabel that = (IdLabel) obj;
            if (this.id.equals(that.id) && this.label.equals(that.label)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(id: " + id + " label: " + label + ")";
    }

    @Override
    public int hashCode() {
        return id.hashCode() + label.hashCode();
    }

}
