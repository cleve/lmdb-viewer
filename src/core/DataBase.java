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

    public DataBase(String dbPath) {
        pathLmdb = dbPath;
        File dbDirectory = new File(dbPath);
        env = Env.create()
                .open(dbDirectory);

        for (byte[] obj:env.getDbiNames()) {
            System.out.println(new String(obj));
        }
        System.out.println(env.getDbiNames());
        System.out.println(env.stat());


    }

    public String GetDbNames() {
        if (db == null) {
            return "";
        }
        return "String.valueOf(.getName())";
    }

    public ArrayList<DataStructure> GetData() {
        ArrayList<DataStructure> results = null;
        try(Txn<ByteBuffer> txn = env.txnRead()) {
            CursorIterator<ByteBuffer> cursor = db.iterate(txn);

            for(CursorIterator.KeyVal<ByteBuffer> kv : cursor.iterable()) {
                ByteBuffer value = kv.val();
                byte[] bytes = new byte[value.remaining()];
                value.get(bytes);
                System.out.println(bytes);
            }
        }
        return results;
    }


}
