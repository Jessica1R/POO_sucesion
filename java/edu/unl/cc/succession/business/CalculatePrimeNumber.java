package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

/**
 * @author
 * Steeven Pardo
 * Juan Calopino
 * Mark Gonzalez
 * Jostin Vásquez
 * Ana Panamito
 * Jessica Rivas
 */


public class CalculatePrimeNumber implements Successionable {

    private Integer nTerms;
    private Integer currentTerm;
    private final StringBuilder printableTerms;

    public CalculatePrimeNumber(Integer nTerms) {
        this(2, nTerms);
    }

    /**
     *
     * @param start
     * @param nTerms
     */
    public CalculatePrimeNumber(Integer start, Integer nTerms) {
        if (start.intValue() < 2) {
            throw new IllegalArgumentException("El numero primo inicial debe ser mayor o igual a 2.");
        }
        if (nTerms.intValue() <= 0) {
            throw new IllegalArgumentException("El número de términos a calcular debe ser mayor a 0.");
        }
        this.currentTerm = start - 1;
        setLimit(nTerms);
        this.printableTerms = new StringBuilder("S = ");
    }

    @Override
    public Number nextTerm(Number currentTerm) {
        int num = currentTerm.intValue() + 1;
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    @Override
    public void setLimit(Number limit) {
        int lim = limit.intValue();
        this.nTerms = lim;
    }

    @Override
    public Number calculate() {
        int count = 0;
        int result = 0;
        int nextPrimeNumber = this.currentTerm;
        while (count < nTerms) {
            nextPrimeNumber = nextTerm(nextPrimeNumber).intValue();
            int cubo = (int) Math.pow(nextPrimeNumber, 3);
            result += cubo;
            printableTerms.append(nextPrimeNumber).append("^3");
            if (count < nTerms - 1) printableTerms.append(" + ");
            count++;
        }
        return result;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CalculatePrimeNumber{ ");
        sb.append("\nCantidad de terminos ingresados: ").append(nTerms);
        sb.append("\nTermino inicial: ").append(currentTerm);
        sb.append("\nSerie de numeros: ").append(printableTerms);
        sb.append('}');
        return sb.toString();
    }
}
