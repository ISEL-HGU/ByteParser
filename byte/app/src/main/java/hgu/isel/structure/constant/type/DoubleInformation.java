package hgu.isel.structure.constant.type;

import hgu.isel.structure.constant.ConstantPoolInformation;

public class DoubleInformation implements ConstantPoolInformation {
    private byte tag;
    private byte[] highBytes; // u4
    private byte[] lowBytes; // u4

    public byte getTag() {
        return tag;
    }

    public void setTag(byte tag) {
        this.tag = tag;
    }

    public byte[] getHighBytes() {
        return highBytes;
    }

    public void setHighBytes(byte[] highBytes) {
        this.highBytes = highBytes;
    }

    public byte[] getLowBytes() {
        return lowBytes;
    }

    public void setLowBytes(byte[] lowBytes) {
        this.lowBytes = lowBytes;
    }

    public DoubleInformation(byte tag, byte[] highBytes, byte[] lowBytes) {
        this.tag = tag;
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
    }
}
