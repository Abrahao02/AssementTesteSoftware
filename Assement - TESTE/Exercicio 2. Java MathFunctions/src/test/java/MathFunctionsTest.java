import net.jqwik.api.*;
import static org.assertj.core.api.Assertions.*;

public class MathFunctionsTest {

    // Mock da interface MathLogger
    static class MockLogger implements MathLogger {
        @Override
        public void log(String operation, int[] inputs) {
            // Simula o log (não faz nada neste caso)
        }
    }

    // Instanciando a classe com a dependência injetada
    private final MathFunctions mathFunctions = new MathFunctions(new MockLogger());

    // Teste para verificar se o número multiplicado por 2 é sempre par
    @Property
    void multiplyByTwoShouldAlwaysReturnEven(@ForAll int number) {
        int result = mathFunctions.multiplyByTwo(number);
        assertThat(result).isEven();
    }

    // Teste para verificar se a tabela de multiplicação retorna os múltiplos corretos
    @Property
    void generateMultiplicationTableShouldReturnCorrectMultiples(@ForAll int number, @ForAll int limit) {
        // Limitar o limite para um valor razoável para evitar alocação excessiva
        if (limit < 0) {
            limit = 0;
        }
        if (limit > 1000) {  // Adicionando um limite máximo para evitar uso excessivo de memória
            limit = 1000;
        }

        int[] table = mathFunctions.generateMultiplicationTable(number, limit);
        for (int i = 0; i < table.length; i++) {
            assertThat(table[i]).isEqualTo(number * (i + 1));  // Verifica o valor correto para cada multiplicação
        }
    }


    // Teste para verificar se apenas números primos retornam true
    @Property
    void isPrimeShouldOnlyReturnTrueForPrimeNumbers(@ForAll int number) {
        boolean result = mathFunctions.isPrime(number);
        if (result) {
            // Verifica se o número realmente é primo
            for (int i = 2; i <= Math.sqrt(number); i++) {
                assertThat(number % i).isNotEqualTo(0);  // Garantir que não é divisível por nenhum número menor que a raiz quadrada
            }
        } else {
            assertThat(result).isFalse();
        }
    }

    // Teste para verificar se a média está dentro do intervalo entre o valor mínimo e máximo
    @Property
    void calculateAverageShouldBeBetweenMinAndMax(@ForAll int[] numbers) {
        if (numbers.length == 0) return; // Ignorar arrays vazios

        double average = mathFunctions.calculateAverage(numbers);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : numbers) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // Verifica se a média calculada está entre o valor mínimo e máximo
        assertThat(average).isBetween((double) min, (double) max);
    }
}
