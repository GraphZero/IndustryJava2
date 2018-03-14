import org.junit.jupiter.api.Test;
import parser.GenerateTransactionCommand;
import utility.Tuple;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionGeneratorTest {
    private TransactionGenerator transactionGenerator;

    @Test
    void shouldGenerateTransactions()  throws IOException{
        // given
        transactionGenerator = new TransactionGenerator();
        transactionGenerator.generateTransactions(new GenerateTransactionCommand(
                        new Tuple<>(1,5),
                        new Tuple<>(LocalDateTime.now(), LocalDateTime.of(2018, 2, 2,2,2)),
                        new Tuple<>(1,5),
                        new Tuple<>(1,50),
                        5,
                        "testItems.csv",
                        "D:\\Java Produkcyjna\\Lab 2\\w2Test\\generator\\src\\main\\resources\\output"
                ));
        // when
        // then
    }

}