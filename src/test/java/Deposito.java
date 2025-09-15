import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.DigitalWallet;

class Deposito {
    @ParameterizedTest
    @ValueSource(doubles = {0.01, 10.0, 20.5, 999.99})
    void deveDepositarValoresValidos(double depositAmount) {
        DigitalWallet wallet = new DigitalWallet("Antonio", 0.0);
        wallet.deposit(depositAmount);
        assertEquals(depositAmount, wallet.getBalance(), 0.0001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0, -20.0})
    void deveLancarExcecaoParaDepositoInvalido(double depositAmount) {
        DigitalWallet wallet = new DigitalWallet("Antonio", 0.0);
        assertThrows(IllegalArgumentException.class, () -> wallet.deposit(depositAmount));
    }
}