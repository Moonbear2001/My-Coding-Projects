package test.rice;

import main.rice.PrimeFactorizer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * A test suite for the PrimeFactorizer class. Every method with the annotation "@Test"
 * will be called when running the test with JUnit.
 */
public class PrimeFactorizerTest {

    /**
     * A prime factorizer with an upper bound of 100.
     */
    private static PrimeFactorizer factorizer100 = new PrimeFactorizer(100);

    /**
     * Tests that attempting factorization of a negative number rightfully returns null.
     */
    @Test
    void testFactorizeNegative() {
        int[] actual = factorizer100.computePrimeFactorization(-1);
        assertNull(actual);
    }

    /**
     * Tests that attempting factorization of 0 rightfully returns null.
     */
    @Test
    void testFactorize0() {
        int[] actual = factorizer100.computePrimeFactorization(0);
        assertNull(actual);
    }

    /**
     * Tests that attempting factorization of 1 rightfully returns null.
     */
    @Test
    void testFactorize1() {
        int[] actual = factorizer100.computePrimeFactorization(1);
        assertNull(actual);
    }

    /**
     * Tests factorization of a prime that can be factorized.
     */
    @Test
    void testFactorize7() {
        int[] actual = factorizer100.computePrimeFactorization(7);
        int[] expected = new int[]{7};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of a non-prime that can be factorized.
     */
    @Test
    void testFactorize15() {
        int[] actual = factorizer100.computePrimeFactorization(15);
        int[] expected = new int[]{3, 5};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of an input that includes more than one distinct prime.
     */
    @Test
    void testFactorize95() {
        int[] actual = factorizer100.computePrimeFactorization(95);
        int[] expected = new int[]{5, 19};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of an input that includes repeats.
     */
    @Test
    void testFactorize98() {
        int[] actual = factorizer100.computePrimeFactorization(98);
        int[] expected = new int[]{2, 7, 7};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of an input (prime) that is too large to be factorized.
     */
    @Test
    void testFactorize101() {
        int[] actual = factorizer100.computePrimeFactorization(101);
        assertNull(actual);
    }

    /**
     * Tests factorization of an input (composite) that is too large to be factorized.
     */
    @Test
    void testFactorize102() {
        int[] actual = factorizer100.computePrimeFactorization(102);
        assertNull(actual);
    }

    /**
     * A prime factorizer with an upper bound of 10,000.
     */
    private static PrimeFactorizer factorizer10000 = new PrimeFactorizer(10000);

    /**
     * Tests factorization of a very large (<10000) input (prime) that can be factorized.
     */
    @Test
    void testFactorize7919() {
        int[] actual = factorizer10000.computePrimeFactorization(7919);
        int[] expected = new int[]{7919};
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests factorization of a very large (<10000) input (composite) that can be factorized.
     */
    @Test
    void testFactorize9996() {
        int[] actual = factorizer10000.computePrimeFactorization(9996);
        int[] expected = new int[]{2, 2, 3, 7, 7, 17};
        assertArrayEquals(expected, actual);
    }
}