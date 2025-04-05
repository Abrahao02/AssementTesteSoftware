public class MathFunctions {

    private MathLogger logger;

    // Construtor para injeção de dependência
    public MathFunctions(MathLogger logger) {
        this.logger = logger;
    }

    // Método que multiplica o número por 2
    public int multiplyByTwo(int number) {
        int result = number * 2;
        logger.log("multiplyByTwo", new int[]{number, result});
        return result;
    }

    // Método que gera uma tabela de multiplicação
    public int[] generateMultiplicationTable(int number, int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Limit must be non-negative");
        }
        int[] table = new int[limit];
        for (int i = 0; i < limit; i++) {
            table[i] = number * (i + 1);  // Corrigido o cálculo para gerar os múltiplos corretos
        }
        return table;
    }

    // Método que verifica se um número é primo
    public boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number == 2) return true;  // 2 é o único número primo par
        if (number % 2 == 0) return false; // Elimina números pares imediatamente
        for (int i = 3; i <= Math.sqrt(number); i += 2) {  // Verificação apenas para ímpares
            if (number % i == 0) return false;
        }
        logger.log("isPrime", new int[]{number});
        return true;
    }

    // Método que calcula a média de um array de números
    public double calculateAverage(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }

        double sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        double average = sum / numbers.length;
        logger.log("calculateAverage", numbers);
        return average;
    }
}
