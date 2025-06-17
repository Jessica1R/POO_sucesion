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

    private int terms; // Número de términos de la serie
    private final StringBuilder printableTerms; // Representación textual de la serie

    // Constructor: inicializa los términos y valida que sean mayores a 0
    public CalculatePrimeNumber(int terms) {
        if (terms <= 0) {
            throw new IllegalArgumentException("El número de términos debe ser mayor a 0");
        }
        this.terms = terms;
        this.printableTerms = new StringBuilder("S = ");
    }

    // Encuentra el siguiente número primo después del término actual
    @Override
    public Number nextTerm(Number currentTerm) {
        int num = currentTerm.intValue() + 1;
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }

    // Establece un nuevo límite de términos
    @Override
    public void setLimit(Number limit) {
        this.terms = limit.intValue();
    }

    // Calcula la suma de la serie de números primos elevados al cubo
    @Override
    public Number calculate() {
        int count = 0; // Contador de términos calculados
        int currentPrime = 1; // Número primo actual
        long sum = 0; // Suma acumulada de la serie

        while (count < terms) {
            currentPrime = nextTerm(currentPrime).intValue(); // Obtiene el siguiente primo
            long cubed = (long) Math.pow(currentPrime, 3); // Eleva el primo al cubo
            sum += cubed; // Suma el cubo al total
            printableTerms.append(currentPrime).append("^3 + "); // Agrega el término a la representación textual
            count++;
        }

        return sum; // Devuelve la suma total
    }

    // Devuelve la representación textual de la serie
    @Override
    public String print() {
        return printableTerms.toString().replaceAll("\\+ $", ""); // Elimina el último '+'
    }

    // Verifica si un número es primo
    private boolean isPrime(int num) {
        if (num < 2) return false; // Los números menores a 2 no son primos
        for (int i = 2; i <= Math.sqrt(num); i++) { // Verifica divisibilidad hasta la raíz cuadrada
            if (num % i == 0) return false; // Si es divisible, no es primo
        }
        return true; // Si no es divisible, es primo
    }
}
