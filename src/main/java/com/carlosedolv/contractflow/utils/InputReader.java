    package com.carlosedolv.contractflow.utils;

    import java.math.BigDecimal;
    import java.time.LocalDate;
    import java.time.format.DateTimeParseException;
    import java.util.InputMismatchException;
    import java.util.Scanner;

    import com.carlosedolv.contractflow.exceptions.InvalidDateException;
    import com.carlosedolv.contractflow.exceptions.InvalidBigDecimalException;
    import com.carlosedolv.contractflow.exceptions.InvalidIntegerException;

    public final class InputReader {
        private InputReader() {
            throw new UnsupportedOperationException("Utility class");
        }

        public static int readPositiveInteger(Scanner sc) {
            try {
                int value = sc.nextInt();
                sc.nextLine();

                if (value <= 0) {
                    throw new InvalidIntegerException("The value must be greater than zero.");
                }

                return value;
            } catch (InputMismatchException e) {
                sc.nextLine();
                throw new InvalidIntegerException("Invalid input! Please enter an integer.");
            }

        }

        public static BigDecimal readPositiveBigDecimal(Scanner sc) {
            try {
                BigDecimal value = sc.nextBigDecimal();
                sc.nextLine();

                if (value.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new InvalidBigDecimalException("The value must be greater than zero.");
                }

                return value;
            } catch (InputMismatchException e) {
                sc.nextLine();
                throw new InvalidBigDecimalException("Invalid input! Please enter an double.");
            }

        }

        public static LocalDate readDate(Scanner sc) {
            try {
                String dateStr = sc.nextLine();
                return DateFormatter.parseDate(dateStr);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException("Invalid input! Please enter format (dd/MM/yyyy)");
            }
        }
    }
