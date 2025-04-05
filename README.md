# Relatório de Testes de Software

Repositório do projeto: [GitHub - AssessmentTesteSoftware](https://github.com/Abrahao02/AssementTesteSoftware)

## 📌 Exercício 1 - Teste Exploratório e Análise de Comportamento Esperado

### 1. Introdução
O sistema de Cálculo de IMC permite ao usuário inserir seu peso e altura para calcular o Índice de Massa Corporal (IMC) e obter uma classificação correspondente com base nos valores definidos pela Organização Mundial da Saúde (OMS).

### 2. Funcionalidades
- Solicitar ao usuário a entrada de peso em quilogramas.  
- Solicitar ao usuário a entrada de altura em metros.  
- Calcular o IMC usando a fórmula: `IMC = peso / (altura * altura)`.  
- Exibir o valor do IMC calculado.  
- Exibir a classificação do IMC com base nos valores definidos pela OMS.  

### 3. Regras de Negócio
- A altura deve ser informada em metros, com ponto decimal (.) como separador.  
- O peso deve ser informado em quilogramas.  
- O sistema deve aceitar apenas valores positivos e não nulos para peso e altura.  

### 4. Casos de Teste

| ID   | Peso (kg) | Altura (m) | IMC Esperado | Classificação Esperada   |
|------|------------|------------|---------------|---------------------------|
| CT01 | 45         | 1.60       | 17.58         | Magreza leve              |
| CT02 | 60         | 1.70       | 20.76         | Saudável                  |
| CT03 | 80         | 1.75       | 26.12         | Sobrepeso                 |
| CT04 | 100        | 1.80       | 30.86         | Obesidade Grau I          |
| CT05 | 120        | 1.90       | 33.24         | Obesidade Grau I          |
| CT06 | -50        | 1.75       | Erro          | Entrada inválida          |
| CT07 | 70         | -1.60      | Erro          | Entrada inválida          |
| CT08 | 0          | 1.65       | Erro          | Entrada inválida          |
| CT09 | 75         | 0          | Erro          | Entrada inválida          |
| CT10 | 85         | 1,75       | Erro          | Formato de altura inválido|

### 5. Justificativa para Escolha dos Testes
- **Análise de valor limite**: testes com valores próximos às mudanças de classificação do IMC.  
- **Partições de equivalência**: divisão em grupos distintos (magreza, saudável, sobrepeso, obesidade).  
- **Testes de entrada inválida**: valores negativos, nulos e formatos incorretos.  

### 6. Observações
- O sistema atualmente não aceita vírgula (`,`) para altura.  
- É recomendável validar as entradas antes de calcular o IMC para evitar erros e melhorar a experiência do usuário.  

---

## 📌 Exercício 2 - Testes Baseados em Propriedades e Simulação de Dependências

### 1. Introdução
Testes realizados utilizando a biblioteca **Jqwik**, com foco em validar os métodos da classe `MathFunctions`. Foram aplicados testes baseados em propriedades para garantir o comportamento correto da classe.

### 2. Testes Realizados

#### 2.1 MultiplyByTwo
- **Teste**: `multiplyByTwoShouldAlwaysReturnEven`  
- **Validação**: o resultado é sempre par (`n * 2` é sempre par).

#### 2.2 GenerateMultiplicationTable
- **Validação**: todos os elementos devem ser múltiplos do número original.  
- **Correção aplicada**: `table[i] = number * (i + 1)`

#### 2.3 IsPrime
- **Validação**: números primos não têm divisores além de 1 e ele mesmo.

#### 2.4 CalculateAverage
- **Validação**: a média calculada deve estar entre o menor e maior valor do array.

### 3. Refatoração para Injeção de Dependência
- A classe `MathFunctions` foi refatorada para aceitar uma interface `MathLogger` injetada via construtor.  
- Isso permite o uso de mocks para testes isolados, sem necessidade de uma implementação real.

### 4. Melhorias e Correções
- Correção no teste `generateMultiplicationTableShouldReturnCorrectMultiples`, ajustando a multiplicação para `number * (i + 1)`.

---

## 📌 Exercício 3 - Teste de API: Funcionalidade, Robustez e Estratégia de Teste

### 1. Tabela de Decisão

| Entrada | UF Válida | UF Inválida | Cidade c/ Acento | Cidade s/ Acento | Logradouro Válido | Logradouro Inexistente |
|---------|-----------|-------------|------------------|------------------|--------------------|--------------------------|
| 1       | Não       | Não         | Não              | Não              | Não                | Não                      |
| 2       | Não       | Não         | Não              | Não              | Não                | Não                      |
| 3       | Sim       | Não         | Não              | Não              | Sim                | Não                      |
| 4       | Sim       | Não         | Sim              | Sim              | Sim                | Não                      |
| 5       | Não       | Sim         | Sim              | Sim              | Sim                | Não                      |
| 6       | Sim       | Não         | Sim              | Não              | Não                | Sim                      |
| 7       | Sim       | Não         | Não              | Sim              | Não                | Sim                      |

### 2. Partição de Equivalência
- **UF Válida**: SP, RJ, MG  
- **UF Inválida**: ZZ, XXX, 123  
- **Cidade com acento**: São Paulo  
- **Cidade sem acento**: Sao Paulo  
- **Logradouro válido**: Avenida Paulista  
- **Logradouro inexistente**: Rua Fictícia  

### 3. Análise de Valor Limite
- **CEP**: de `00000-00` a `99999-99`  
- **UF**: A até Z, testar com símbolos  
- **Cidade**: nome mais curto (ex: "SP") e mais longo (>100 caracteres)  
- **Logradouro**: nomes mínimos e máximos  

### 4. Estratégia de Teste
- **Funcionalidade**: entradas válidas e inválidas  
- **Robustez**: entradas corrompidas (letras em CEP, etc.)  
- **Limites**: entradas mínimas e máximas  

---

## 📌 Exercício 4 - Automação de Testes Web com Selenium

### Atividades
- Automatizar com Selenium WebDriver o fluxo de **cadastro de um novo usuário** e **login com credenciais válidas**.  
- Automatizar tentativas de login com **credenciais inválidas** e verificar se as mensagens de erro são apropriadas.  
- Aplicar o padrão **Page Object Model** para organizar o código dos testes.  
- Capturar **screenshots em caso de falhas** nos testes.  
- Integrar os testes com um framework como **JUnit ou TestNG**, com setup automatizado via **WebDriverManager**.  

---


## 📌 Exercício 5 - Análise Estrutural de Código

### 🎯 Objetivo
Avaliar a **cobertura** e **robustez** dos métodos de ordenação da classe `InsertionSort` com testes unitários usando **JUnit 5**.

---

### ✅ Casos de Teste Executados

| Método                         | Descrição                                            | Resultado |
|--------------------------------|------------------------------------------------------|-----------|
| `testNormalSort`               | Ordenação correta com múltiplos elementos           | ✅ Passou |
| `testSortWithEmptyArray`      | Comportamento com array vazio                       | ✅ Passou |
| `testSortWithSingleElement`   | Ordenação com um único elemento                     | ✅ Passou |
| `testSortWithRange`           | Ordenação parcial em um intervalo específico        | ✅ Passou |
| `testSentinelSort`            | Ordenação completa com estratégia de sentinela      | ✅ Passou |
| `testSentinelSortSingleElement` | Sentinela com array unitário                      | ✅ Passou |
| `testSentinelSortNull`        | Tratamento de `null` no método `sentinelSort`       | ✅ Passou |
| `testFindMinIsCorrect`        | Identificação do menor elemento                     | ✅ Passou |
| `testIsSortedTrue`            | Confirma que o array está ordenado                  | ✅ Passou |
| `testIsSortedFalse`           | Confirma que o array está desordenado               | ✅ Passou |
| `testSwapWorksCorrectly`      | Teste do método de troca (`swap`)                   | ✅ Passou |
| `testLessAndGreater`          | Validação dos métodos `less` e `greater`            | ✅ Passou |

---

### ⚠️ Teste Ignorado

| Método                    | Motivo da Desativação                                      |
|---------------------------|------------------------------------------------------------|
| `testSortWithNullArray`  | ❌ Gera `NullPointerException`. Refatoração necessária para tratar `null` corretamente. |

---

### 📈 Conclusão

A suíte de testes cobre:

- ✅ Casos comuns e de borda (array vazio, único elemento)  
- ✅ Estratégias alternativas como `sentinelSort`  
- ✅ Métodos utilitários de ordenação (`swap`, `less`, `greater`, `isSorted`)  

### 💡 Recomendação

Refatorar o método `sort` para tratar entradas `null` de forma segura, adicionando:
- Uma **checagem inicial** (`if (array == null)`)  
- Ou lançando uma **exceção personalizada** com mensagem informativa

---

