package writers;

import generators.Transaction;

import java.util.ArrayList;

public interface IFileWriter {
    void writeValue(String filePath, ArrayList<Transaction> transactionsToSave);
}
