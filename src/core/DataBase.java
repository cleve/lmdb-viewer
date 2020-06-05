package core;

import org.lmdbjava.CursorIterable;
import org.lmdbjava.Dbi;
import org.lmdbjava.Env;
import org.lmdbjava.Txn;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class DataBase {
    private String pathLmdb = null;
    private Dbi<ByteBuffer> db;
    private Env<ByteBuffer> env;
    private File dbDirectory;

    private File FilterDbPath(String fullPath) {
        return new File(fullPath);
    }

    public DataBase(File dbDirectory) {
        this.dbDirectory = dbDirectory;
        this.env = Env.create()
                .setMaxDbs(0)
                .open(this.dbDirectory);

        for (byte[] obj:this.env.getDbiNames()) {
            System.out.println(new String(obj));
        }
    }

    public String GetDbNames() {
        if (db == null) {
            return "";
        }
        return "String.valueOf(.getName())";
    }

    public ArrayList<KeyValue> GetData() {
        final Txn<ByteBuffer> rtx = env.txnRead();
        ArrayList<KeyValue> results = new ArrayList<>();
        db = env.openDbi((byte[]) null);

        CursorIterable<ByteBuffer> cursor = db.iterate(rtx);
        for (CursorIterable.KeyVal<ByteBuffer> kv : cursor) {
            // Storing in the data class
            results.add(new KeyValue(kv.key(), kv.val()));
        }
        return results;
    }

    public ArrayList<KeyValue> searchData(String keyValue, Boolean valueSearch) {
        final Txn<ByteBuffer> rtx = env.txnRead();
        // To byte
        ArrayList<KeyValue> results = new ArrayList<>();
        db = env.openDbi((byte[]) null);
        CursorIterable<ByteBuffer> cursor = db.iterate(rtx);
        for (CursorIterable.KeyVal<ByteBuffer> kv : cursor) {
            // copy of key
            int size = kv.key().remaining();
            byte[] keyByte = new byte[size];
            kv.key().get(keyByte);
            String dbString = new String(keyByte);
            // copy of value
            int sizeValue = kv.val().remaining();
            byte[] valueByte = new byte[sizeValue];
            kv.val().get(valueByte);
            String dbStringValue = new String(valueByte);
            if (valueSearch && dbStringValue.contains(keyValue)) {
                results.add(new KeyValue(ByteBuffer.wrap(dbString.getBytes()), ByteBuffer.wrap(dbStringValue.getBytes())));
            } else if (!valueSearch && dbString.contains(keyValue)) {
                results.add(new KeyValue(ByteBuffer.wrap(dbString.getBytes()), ByteBuffer.wrap(dbStringValue.getBytes())));
            }
        }
        return results;
    }


}
