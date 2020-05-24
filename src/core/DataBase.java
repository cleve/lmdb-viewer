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
        ArrayList<KeyValue> results = null;
        db = env.openDbi(bName);
        CursorIterator<ByteBuffer> cursor = db.iterate(rtx);
        for(CursorIterator.KeyVal<ByteBuffer> kv : cursor.iterable()) {
            // Storing in the data class
            KeyValue keyValue = new KeyValue();
            keyValue.setKey(kv.key());
            keyValue.setValue(kv.val());
            results.add(keyValue);
        }
        return results;
    }


}
