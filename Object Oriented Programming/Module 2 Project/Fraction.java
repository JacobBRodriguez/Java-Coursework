public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if(denominator == 0)
            throw new IllegalArgumentException("denominator is zero");
        if(denominator < 0) {
            this.numerator = numerator * -1;
            this.denominator = denominator * -1;
        }
        else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    public Fraction(int numerator) {
        this(numerator, 1);
    }

    public Fraction() {
        this(0, 1);
    }

    // Methods

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public String toString() {
        return ""+numerator+"/"+denominator+"";
    } // end toString

    public double toDouble() {
        double value = numerator / denominator;
        return value;
    }// end toDouble

    public Fraction add(Fraction other) {
        Fraction result = math(other, '+');
        return result;

    }// end add

    public Fraction subtract(Fraction other) {
        Fraction result = math(other, '-');
        return result;
    }// end subtract

    public Fraction multiply(Fraction other) {
        Fraction result = math(other, '*');
        return result;
    }// end  multiply

    public Fraction divide(Fraction other) {
        if(other.numerator == 0 || this.numerator == 0)
            throw new IllegalArgumentException("Divide by 0");
        Fraction result = math(other, '/');
        return result;
    }// end divide

    public boolean equals(Fraction other) {
        double computedThis = this.toDouble();
        double computedThat = other.toDouble();
        if(computedThis == computedThat)
            return true;
        return false;
    } //end equals override

    public void toLowestTerms() {
        if(this.numerator == 0) return;
        int divisor = this.gcd(this.numerator, this.denominator);
        this.numerator = this.numerator / divisor;
        this.denominator = this.denominator / divisor;
    } // end toLowestTerms

    public static int gcd(int num, int den) {
        int remainder;
        while(num != 0 && den != 0) {
            remainder = num % den;
            num = den;
            den = remainder;
        }
        return num;
    }// end gcd

    // Helper Functions ********
    private Fraction math(Fraction other, char operation) {
        int greatestCommon, newNominator, newDenominator, thisNewNum, thatNewNum;
        Fraction result;

        if(this.denominator == other.denominator) {
            greatestCommon = this.denominator;
            thisNewNum = this.numerator;
            thatNewNum = other.numerator;
        }

        else {
            greatestCommon = this.denominator * other.denominator;
            thisNewNum = other.denominator * this.numerator;
            thatNewNum = this.denominator * other.numerator;
        }

        if(operation == '+') {
            newNominator = thisNewNum + thatNewNum;
            newDenominator = greatestCommon;
        }
        else if(operation == '-') {
            newNominator = thisNewNum - thatNewNum;
            newDenominator = greatestCommon;
        }
        else if(operation == '*')
        {
            newNominator = this.numerator * other.numerator;
            newDenominator = this.denominator * other.denominator;
        }
        else
        {
            newNominator = this.numerator * other.denominator;
            newDenominator = this.denominator * other.numerator;
        }

        if(newNominator == 0)
            result = new Fraction();
        else
            result = new Fraction(newNominator, newDenominator);
        return result;
    } // end math
} //end class
