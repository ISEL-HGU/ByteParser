package hgu.isel.structure.attribute.type.packages;

public class PackageIndex {
    private byte[] packageIndex; // u2

    public byte[] getPackageIndex() {
        return packageIndex;
    }

    public void setPackageIndex(byte[] packageIndex) {
        this.packageIndex = packageIndex;
    }

    public PackageIndex(byte[] packageIndex) {
        this.packageIndex = packageIndex;
    }
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(byte b : packageIndex) {
            stringBuilder.append(String.format("%02X", b));
        }


        return stringBuilder.toString();
    }
}
