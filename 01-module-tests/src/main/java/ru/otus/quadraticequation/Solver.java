package ru.otus.quadraticequation;

public class Solver {

    public double[] solve(double a, double b, double c) {
        if (!(isNumber(a) && isNumber(b) && isNumber(c)))
            throw new IllegalArgumentException("Some coefficients are Nan");

        if (isZero(a)) throw new IllegalArgumentException("Coefficient A can't be equal to zero");

        double discriminant = Math.pow(b, 2) - 4 * a * c;
        System.out.println(discriminant);
        if (discriminant < 0) return new double[]{};
        if (isZero(discriminant)) return new double[] {(-1) * b / 2 / a};
        return new double[] {((-1) * b + Math.sqrt(discriminant)) / 2 / a,
                ((-1) * b - Math.sqrt(discriminant)) / 2 / a};
    }

    private static boolean isNumber(double number) {
        return !Double.isNaN(number) && Double.isFinite(number);
    }

    private static boolean isZero(double value, double threshold){
        return value >= -threshold && value <= threshold;
    }

    private static boolean isZero(double value){
        return isZero(value, .000001);
    }
}
