package hgu.isel.structure.constant.type;

import hgu.isel.structure.constant.ConstantPoolInformation;

public class ModuleInformation implements ConstantPoolInformation {
    private byte tag;
    private byte[] nameIndex; // u2

    public byte getTag() {
        return tag;
    }

    public void setTag(byte tag) {
        this.tag = tag;
    }

    public byte[] getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(byte[] nameIndex) {
        this.nameIndex = nameIndex;
    }

    public ModuleInformation(byte tag, byte[] nameIndex) {
        this.tag = tag;
        this.nameIndex = nameIndex;
    }
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\nModuleInformation: ");
        stringBuilder.append(String.format("%02X", tag));

        for(byte b : nameIndex) {
            stringBuilder.append(String.format("%02X", b));
        }

        return stringBuilder.toString();
    }
}
