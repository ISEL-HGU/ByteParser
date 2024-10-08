package hgu.isel.structure.attribute.type.module;

public class Requires {
    private byte[] requiresIndex; // u2
    private byte[] requiresFlags; // u2
    private byte[] requiresVersionIndex; // u2

    public byte[] getRequiresIndex() {
        return requiresIndex;
    }

    public void setRequiresIndex(byte[] requiresIndex) {
        this.requiresIndex = requiresIndex;
    }

    public byte[] getRequiresFlags() {
        return requiresFlags;
    }

    public void setRequiresFlags(byte[] requiresFlags) {
        this.requiresFlags = requiresFlags;
    }

    public byte[] getRequiresVersionIndex() {
        return requiresVersionIndex;
    }

    public void setRequiresVersionIndex(byte[] requiresVersionIndex) {
        this.requiresVersionIndex = requiresVersionIndex;
    }

    public Requires(byte[] requiresIndex, byte[] requiresFlags, byte[] requiresVersionIndex) {
        this.requiresIndex = requiresIndex;
        this.requiresFlags = requiresFlags;
        this.requiresVersionIndex = requiresVersionIndex;
    }
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(byte b : requiresIndex) {
            stringBuilder.append(String.format("%02X", b));
        }

        for(byte b : requiresFlags) {
            stringBuilder.append(String.format("%02X", b));
        }

        for(byte b : requiresVersionIndex) {
            stringBuilder.append(String.format("%02X", b));
        }

        return stringBuilder.toString();
    }
}
