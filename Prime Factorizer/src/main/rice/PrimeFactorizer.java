package main.rice;

/**
 * This class implements a relatively simple algorithm for computing the prime factors
 * of a number.  At initialization, a list of primes is computed. Given a number, this
 * list is used to efficiently compute the prime factors of that number.
 */
public class PrimeFactorizer {

    private int maxNumToFactorize;
    private int[] allPrimes;

    /**
     * Helper function that adds a number to an array.
     *
     * @param inputArray array of length n that needs a number added to it
     * @param numToAdd number to be added to inputArray
     * @return a new array of length n + 1 with numToAdd in the nth index
     */
    private int[] arrayAppend(int[] inputArray, int numToAdd) {
        // declares new array
        int[] newArray = new int[inputArray.length + 1];
        // copy contents of inputArray into newArray
        for (int i = 0; i < inputArray.length; i++) {
            newArray[i] = inputArray[i];
        }
        // adds numToAdd into final index in newArray
        newArray[inputArray.length] = numToAdd;
        return newArray;
    }

    /**
     * Constructor for a PrimeFactorizer object.
     * Builds an array primeCandidates that contains all integers between 2
     * and the square root of maxNumToFactorize, inclusive. Iterates through numbers in prime candidates, crossing
     * out numbers that are multiples of previous numbers.
     *
     * @param maxNumToFactorize integer representing the maximum number that the function is capable of factorizing
     */
    public PrimeFactorizer(int maxNumToFactorize) {

        this.maxNumToFactorize = maxNumToFactorize;
        int maxPrime = (int) Math.ceil(Math.sqrt(this.maxNumToFactorize));

        int[] primeCandidates = new int[maxPrime - 1];

        // populates primeCandidates
        for ( int i = 0; i <= maxPrime - 2; i++ ) {
            primeCandidates[i] = i + 2;
        }

        // "crosses out" (makes 0) non-prime numbers in primeCandidates
        int numsRemoved = 0;
        for ( int i = 0; i <= maxPrime-2; i++ ) {
            int num1 = primeCandidates[i];
            if ( num1 != 0 ) {
                for ( int j = i + 1; j <= maxPrime - 2; j++ ) {
                    int num2 = primeCandidates[j];
                    if ( num2 % num1 == 0 && num2 != 0) {
                        primeCandidates[j] = 0;
                        numsRemoved++;
                    }
                }
            }
        }
        // declares and populates allPrimes
        this.allPrimes = new int[primeCandidates.length - numsRemoved];
        int i = 0;
        for ( int num : primeCandidates ) {
            if ( num != 0 ) {
                this.allPrimes[i] = num;
                i++;
            }
        }
    }

    /**
     * Computes and returns the prime factorization of an input number in the form of an array of prime integers sorted
     * in ascending order.
     *
     * @param numToFactorize number to be factorized
     * @return an array in ascending order representing prime factorization of numToFactorize
     */
    public int[] computePrimeFactorization(int numToFactorize) {

        // checks if input is out of bounds
        if (numToFactorize < 2 || numToFactorize > this.maxNumToFactorize) {
            return null;
        }

        int dividend = numToFactorize;
        int[] ans = new int[]{};

        // iterates over each number in allPrimes
        for (int divisor : this.allPrimes) {
            // if the current dividend is divisible by divisor, divides out the divisor and adds divisor to the answer
            while ( dividend % divisor == 0 ) {
                dividend = dividend/divisor;
                ans = arrayAppend(ans, divisor);
            }
        }
        if (dividend != 1) {
            ans = arrayAppend(ans, dividend);
        }
        return ans;
    }

//    public static void main(String[] args) {
//        PrimeFactorizer testFactorizer = new PrimeFactorizer(100000);
//        int[] ans = testFactorizer.computePrimeFactorization(99975);
//        for (int num : ans) {
//            System.out.println(num);
//        }
//    }
}