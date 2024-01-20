import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class XIRR {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); 
        List<Double> payments = new ArrayList<>();
        List<Date> dates = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            double payment = scanner.nextDouble();
            String dateString = scanner.next();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            try {
                Date date = dateFormat.parse(dateString);
                dates.add(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            payments.add(payment);
        }

        double rateOfReturn = calculateXIRR(payments, dates);
        System.out.printf("%.2f\n", rateOfReturn);

        scanner.close();
    }

    private static double calculateXIRR(List<Double> payments, List<Date> dates) {
        double guess = 0.1;
        double tolerance = 0.0001; 

        double x0 = 0.0;
        double x1 = guess;
        double f0 = calculateXIRRF(x0, payments, dates);
        double f1 = calculateXIRRF(x1, payments, dates);

        while (Math.abs(f1) > tolerance) {
            double x2 = x1 - f1 * ((x1 - x0) / (f1 - f0));
            x0 = x1;
            x1 = x2;
            f0 = f1;
            f1 = calculateXIRRF(x1, payments, dates);
        }

        return x1 * 100;
    }

    private static double calculateXIRRF(double rate, List<Double> payments, List<Date> dates) {
        double result = 0.0;

        for (int i = 0; i < payments.size(); i++) {
            result += payments.get(i) / Math.pow(1 + rate, daysBetween(dates.get(i), dates.get(0)) / 365.0);
        }

        return result;
    }

    private static long daysBetween(Date startDate, Date endDate) {
        long difference = endDate.getTime() - startDate.getTime();
        return difference / (24 * 60 * 60 * 1000);
    }
}

