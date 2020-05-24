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

    public ArrayList<DataStructure> GetData() {
        final Txn<ByteBuffer> rtx = env.txnRead();
        byte [] bName = null;
        ArrayList<DataStructure> results = null;
        db = env.openDbi(bName);

            CursorIterator<ByteBuffer> cursor = db.iterate(rtx);

            for(CursorIterator.KeyVal<ByteBuffer> kv : cursor.iterable()) {
                ByteBuffer key = kv.key();
                ByteBuffer value = kv.val();
                byte[] key_bytes = new byte[key.remaining()];
                byte[] bytes = new byte[value.remaining()];
                value.get(bytes);
                key.get(key_bytes);
                System.out.println(new String(key_bytes));
                System.out.println(new String(bytes));
            }

        return results;
    }


}
