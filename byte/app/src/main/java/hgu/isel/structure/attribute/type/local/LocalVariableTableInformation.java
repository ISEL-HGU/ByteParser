package hgu.isel.structure.attribute.type.local;

import hgu.isel.structure.attribute.type.annotation.ElementValuePairs;

public class LocalVariableTableInformation {
    private byte[] startPC; // u2
    private byte[] length; // u2
    private byte[] nameIndex; // u2
    private byte[] descriptorIndex; // u2
    private byte[] index; // u2

    public byte[] getStartPC() {
        return startPC;
    }

    public void setStartPC(byte[] startPC) {
        this.startPC = startPC;
    }

    public byte[] getLength() {
        return length;
    }

    public void setLength(byte[] length) {
        this.length = length;
    }

    public byte[] getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(byte[] nameIndex) {
        this.nameIndex = nameIndex;
    }

    public byte[] getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(byte[] descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public byte[] getIndex() {
        return index;
    }

    public void setIndex(byte[] index) {
        this.index = index;
    }

    public LocalVariableTableInformation(byte[] startPC, byte[] length, byte[] nameIndex, byte[] descriptorIndex, byte[] index) {
        this.startPC = startPC;
        this.length = length;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.index = index;
    }
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(byte b : startPC) {
            stringBuilder.append(String.format("%02X", b));
        }

        for(byte b : length) {
            stringBuilder.append(String.format("%02X", b));
        }

        for(byte b : nameIndex) {
            stringBuilder.append(String.format("%02X", b));
        }

        for(byte b : descriptorIndex) {
            stringBuilder.append(String.format("%02X", b));
        }

        for(byte b : index) {
            stringBuilder.append(String.format("%02X", b));
        }

        return stringBuilder.toString();
    }
}
