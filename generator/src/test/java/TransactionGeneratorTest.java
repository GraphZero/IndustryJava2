import org.junit.jupiter.api.Test;
import parser.GenerateTransactionCommand;
import utility.Tuple;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionGeneratorTest {
    private TransactionGenerator transactionGenerator;
    @Test
    void generateTransactions()  throws IOException{
        // given
        transactionGenerator = new TransactionGenerator();
        transactionGenerator.generateTransactions(new GenerateTransactionCommand(
                        new Tuple<>(1,5),
                        new Tuple<>(LocalDateTime.now(), LocalDateTime.of(2018, 2, 2,2,2)),
                        new Tuple<>(1,5),
                        new Tuple<>(1,50),
                        5,
                        "D:\\Java Produkcyjna\\Lab 2\\w2Test\\generator\\src\\main\\resources\\items.txt",
                        "D:\\Java Produkcyjna\\Lab 2\\w2Test\\generator\\src\\main\\resources\\output.json"
                ));
        // when
        // then
    }

    @Test
    void generateSingleTransaction() throws IOException {
        // given
        transactionGenerator = new TransactionGenerator();
        transactionGenerator.generateSingleTransaction(new GenerateTransactionCommand(
                new Tuple<>(1,5),
                new Tuple<>(LocalDateTime.now(), LocalDateTime.of(2018, 2, 2,2,2)),
                new Tuple<>(1,5),
                new Tuple<>(1,50),
                5,
                "D:\\Java Produkcyjna\\Lab 2\\w2Test\\generator\\src\\main\\resources\\items.txt",
                "D:\\Java Produkcyjna\\Lab 2\\w2Test\\generator\\src\\main\\resources\\output.json"
        ),
                1);
        // when
        // then
    }
}