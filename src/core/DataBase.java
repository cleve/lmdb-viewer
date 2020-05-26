package core;

import org.lmdbjava.CursorIterator;
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
        System.out.println(this.env.stat());
        System.out.println(this.env.info());
    }

    public String GetDbNames() {
        if (db == null) {
            return "";
        }
        return "String.valueOf(.getName())";
    }

    public ArrayList<KeyValue> GetData() {
        final Txn<ByteBuffer> rtx = env.txnRead();
        byte [] bName = null;
        ArrayList<KeyValue> results = new ArrayList<>();
        db = env.openDbi(bName);
        CursorIterator<ByteBuffer> cursor = db.iterate(rtx);
        for(CursorIterator.KeyVal<ByteBuffer> kv : cursor.iterable()) {
            // Storing in the data class
            results.add(new KeyValue(kv.key(), kv.val()));
        }
        return results;
    }

    public ArrayList<KeyValue> searchData(String keyValue) {
        final Txn<ByteBuffer> rtx = env.txnRead();
        // To byte
        byte [] bName = null;
        ArrayList<KeyValue> results = new ArrayList<>();
        db = env.openDbi(bName);
        CursorIterator<ByteBuffer> cursor = db.iterate(rtx);
        for(CursorIterator.KeyVal<ByteBuffer> kv : cursor.iterable()) {
            // Storing in the data class
            byte[] keyByte = new byte[kv.key().remaining()];
            kv.key().get(keyByte);
            String dbString = new String(keyByte);
            System.out.println(dbString);
            if (dbString.contains(keyValue)) {
                results.add(new KeyValue(kv.key(), kv.val()));
            }

        }
        return results;
    }


}
