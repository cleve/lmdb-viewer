package core;

import java.nio.ByteBuffer;

public class KeyValue {
    private String key = null;
    private String value = null;

    public String getKey() {
        return key;
    }

    public void setKey(ByteBuffer key) {
        byte[] keyByte = new byte[key.remaining()];
        key.get(keyByte);
        this.key = new String(keyByte);
    }

    public String getValue() {
        return value;
    }

    public void setValue(ByteBuffer value) {
        byte[] valueByte = new byte[value.remaining()];
        value.get(valueByte);
        this.value = new String(valueByte);
    }
}
