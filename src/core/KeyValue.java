package core;

import javafx.beans.property.SimpleStringProperty;

import java.nio.ByteBuffer;

public class KeyValue {
    private SimpleStringProperty keyName = null;
    private SimpleStringProperty valueName = null;

    public KeyValue(ByteBuffer keyName, ByteBuffer valueName) {
        setKeyName(keyName);
        setValueName(valueName);
    }

    public String getKeyName() {
        return this.keyName.get();
    }

    public void setKeyName(ByteBuffer keyName) {
        byte[] keyByte = new byte[keyName.remaining()];
        keyName.get(keyByte);
        this.keyName = new SimpleStringProperty(new String(keyByte));
    }

    public String getValueName() {
        return this.valueName.get();
    }

    public void setValueName(ByteBuffer valueName) {
        byte[] valueByte = new byte[valueName.remaining()];
        valueName.get(valueByte);
        this.valueName = new SimpleStringProperty(new String(valueByte));
    }
}
