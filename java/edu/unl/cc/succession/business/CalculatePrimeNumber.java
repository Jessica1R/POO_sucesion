package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * Autores:
 * Steeven Pardo
 * Juan Calopino
 * Mark Gonzalez
 * Jostin Vásquez
 * Ana Panamito
 * Jessica Rivas
 */

public class CalculatePrimeNumber implements Successionable {

    private Integer nTerms; // Número total de términos a calcular
    private Integer currentTerm; // Último número primo generado (se inicializa en uno menos que el primer primo)
    private final StringBuilder printableTerms; // Cadena para almacenar la sucesión en formato legible

    // Constructor que inicia desde el número primo 2
    public CalculatePrimeNumber(Integer nTerms) {
        this(2, nTerms); // Llama al constructor principal con inicio en 2
    }

    /**
     * @param start  valor inicial desde donde comenzar a buscar números primos
     * @param nTerms cantidad de términos a calcular
     */
    public CalculatePrimeNumber(Integer start, Integer nTerms) {
        if (start.intValue() < 2) {
            throw new IllegalArgumentException("El número primo inicial debe ser mayor o igual a 2.");
        }
        if (nTerms.intValue() <= 0) {
            throw new IllegalArgumentException("El número de términos a calcular debe ser mayor a 0.");
        }
        this.currentTerm = start - 1; // Se resta 1 para que el primer número buscado sea el 'start'
        setLimit(nTerms);
        this.printableTerms = new StringBuilder("S = "); // Inicializa la cadena de salida
    }

    /**
     * Método que obtiene el siguiente número primo después de currentTerm
     * @param currentTerm número actual desde el que se busca el siguiente primo
     * @return siguiente número primo
     */
    @Override
    public Number nextTerm(Number currentTerm) {
        int num = currentTerm.intValue() + 1;
        while (!isPrime(num)) {
            num++; // Sigue buscando hasta encontrar un número primo
        }
        return num;
    }

    /**
     * Verifica si un número dado es primo
     * @param num número a verificar
     * @return true si es primo, false si no lo es
     */
    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    /**
     * Define la cantidad de términos que se desean generar
     * @param limit número de términos
     */
    @Override
    public void setLimit(Number limit) {
        int lim = limit.intValue();
        this.nTerms = lim;
    }

    /**
     * Realiza el cálculo de la suma de los cubos de los primeros n números primos
     * @return resultado de la suma
     */
    @Override
    public Number calculate() {
        int count = 0;
        int result = 0;
        int nextPrimeNumber = this.currentTerm;

        while (count < nTerms) {
            nextPrimeNumber = nextTerm(nextPrimeNumber).intValue(); // Obtiene siguiente primo
            int cubo = (int) Math.pow(nextPrimeNumber, 3); // Calcula el cubo
            result += cubo; // Suma al resultado total

            // Agrega término a la cadena de salida
            printableTerms.append(nextPrimeNumber).append("^3");
            if (count < nTerms - 1) printableTerms.append(" + ");
            count++;
        }

        return result;
    }

    /**
     * Devuelve la cadena con la serie de términos en formato legible
     * @return cadena representando la serie
     */
    @Override
    public String print() {
        return printableTerms.toString();
    }

    /**
     * Representación textual detallada del objeto con información útil
     * @return descripción del objeto
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CalculatePrimeNumber{ ");
        sb.append("\nCantidad de términos ingresados: ").append(nTerms);
        sb.append("\nTérmino inicial: ").append(currentTerm);
        sb.append("\nSerie de números: ").append(printableTerms);
        sb.append('}');
        return sb.toString();
    }
}
