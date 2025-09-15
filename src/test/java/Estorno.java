import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import com.example.DigitalWallet;

class Estorno {
    private DigitalWallet wallet;

    static Stream<Arguments> refundValues() {
        return Stream.of(
            Arguments.of(100.0, 10.0, 110.0),
            Arguments.of(0.0, 5.0, 5.0),
            Arguments.of(50.0, 0.01, 50.01)
        );
    }

    @ParameterizedTest
    @MethodSource("refundValues")
    void refundComCarteiraValida(double initialBalance, double refundAmount, double expectedBalance) {
        wallet = new DigitalWallet("Antonio", initialBalance);
        wallet.verify();
        wallet.unlock();

        assumeTrue(wallet.isVerified());
        assumeFalse(wallet.isLocked());

        wallet.refund(refundAmount);
        assertEquals(expectedBalance, wallet.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-100.0, 0.0})
    void deveLancarExcecaoParaRefundInvalido(double refundAmount) {
        wallet = new DigitalWallet("Antonio", 1000.0);
        wallet.verify();
        wallet.unlock();

        assumeTrue(wallet.isVerified());
        assumeFalse(wallet.isLocked());

        assertThrows(IllegalArgumentException.class, () -> wallet.refund(refundAmount));
    }

    @Test
    void deveLancarSeNaoVerificadaOuBloqueada() {
        wallet = new DigitalWallet("Antonio", 9000.0);
        assertThrows(IllegalStateException.class, () -> wallet.refund(10.0));
    }
}