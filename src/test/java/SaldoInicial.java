import org.junit.jupiter.api.Test;

import com.example.DigitalWallet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SaldoInicial {
    @Test
    void deveConfigurarSaldoInicialCorreto() {
        DigitalWallet account = new DigitalWallet("Giullya", 100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    public void deveLancarExcecaoParaSaldoInicialNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new DigitalWallet("Giullya", -20.0));
    }
}