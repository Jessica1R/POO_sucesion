package edu.unl.cc.succession.business;

import edu.unl.cc.succession.domain.Successionable;

public class CalculatePrimeNumber implements Successionable {

    private int terms;
    private final StringBuilder printableTerms;

    public CalculatePrimeNumber(int terms) {
        if (terms <= 0) {
            throw new IllegalArgumentException("El número de términos debe ser mayor a 0");
        }
        this.terms = terms;
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

    @Override
    public void setLimit(Number limit) {
        this.terms = limit.intValue();
    }

    @Override
    public Number calculate() {
        int count = 0;
        int currentPrime = 1;
        long sum = 0;

        while (count < terms) {
            currentPrime = nextTerm(currentPrime).intValue();
            long cubed = (long) Math.pow(currentPrime, 3);
            sum += cubed;
            printableTerms.append(currentPrime).append("^3 + ");
            count++;
        }

        return sum;
    }

    @Override
    public String print() {
        return printableTerms.toString().replaceAll("\\+ $", "");
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}