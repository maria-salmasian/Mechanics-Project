public class FT {
    public class ComplexNumbers {

        public double real_part = 0.0;
        public double imaginary_part = 0.0;

        public ComplexNumbers() {
        }

        public double getReal_part() {
            return real_part;
        }

        public void setReal_part(double real_part) {
            this.real_part = real_part;
        }

        public double getImaginary_part() {
            return imaginary_part;
        }

        public void setImaginary_part(double imaginary_part) {
            this.imaginary_part = imaginary_part;
        }

        public double mod() {
            if (real_part != 0 || imaginary_part != 0) {
                return Math.sqrt(real_part * real_part + imaginary_part * imaginary_part);
            } else {
                return 0d;
            }
        }

        public ComplexNumbers plus(ComplexNumbers w) {
            return new ComplexNumbers(real_part + w.getReal_part(), imaginary_part + w.getImaginary_part());
        }

        public ComplexNumbers minus(ComplexNumbers w) {
            return new ComplexNumbers(real_part - w.getReal_part(), imaginary_part - w.getImaginary_part());
        }


        public ComplexNumbers times(ComplexNumbers w) {
            return new ComplexNumbers(real_part * w.getReal_part() - imaginary_part * w.getImaginary_part(), real_part * w.getImaginary_part() + imaginary_part * w.getReal_part());
        }


        public ComplexNumbers div(ComplexNumbers w) {
            double den = Math.pow(w.mod(), 2);
            return new ComplexNumbers((real_part * w.getReal_part() + imaginary_part * w.getImaginary_part()) / den, (imaginary_part * w.getReal_part() - real_part * w.getImaginary_part()) / den);
        }


        public ComplexNumbers(double real_part, double imaginary_part) {
            this.real_part = real_part;
            this.imaginary_part = imaginary_part;
        }


        public ComplexNumbers polar(final double rho, final double theta) {
            return (new FT.ComplexNumbers(rho * Math.cos(theta), rho * Math.sin(theta)));
        }
    }

    public ComplexNumbers[] fft(ComplexNumbers[] x) {
        int N = x.length;

        if (N == 1)
            return new ComplexNumbers[]{x[0]};
        if (N % 2 != 0) {
            throw new RuntimeException("N is not a power of 2");
        }

        ComplexNumbers[] even = new ComplexNumbers[N / 2];
        for (int k = 0; k < N / 2; k++) {
            even[k] = x[2 * k];
        }
        ComplexNumbers[] q = fft(even);

        ComplexNumbers[] odd = even;
        for (int k = 0; k < N / 2; k++) {
            odd[k] = x[2 * k + 1];
        }
        ComplexNumbers[] r = fft(odd);

        ComplexNumbers[] y = new ComplexNumbers[N];
        for (int k = 0; k < N / 2; k++) {
            double kth = -2 * k * Math.PI / N;
            ComplexNumbers wk = new FT.ComplexNumbers(Math.cos(kth), Math.sin(kth));
            y[k] = q[k].plus(wk.times(r[k]));
            y[k + N / 2] = q[k].minus(wk.times(r[k]));
        }
        return y;
    }


    public static void show(ComplexNumbers[] x, String title) {
        System.out.println(title);
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
        System.out.println();
    }


}