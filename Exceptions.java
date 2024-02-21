package Seminar6;

public class Exceptions {

    public void dataLength(String input, int n) throws InvalidArgumentCountException {
        String[] data = input.split("\\s+");
        if (data.length != n) {
            throw new InvalidArgumentCountException();
        }
    }

    public ResultWrapper parseInput(String input) throws InvalidPhoneNumberException, InvalidNameException {
        String[] data = input.split("\\s+");
        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(data[3]);

        } catch (NumberFormatException e) {
            throw new InvalidPhoneNumberException();
        }
        if (lastName.matches(".*\\d.*") || firstName.matches(".*\\d.*") || middleName.matches(".*\\d.*")) {
            throw new InvalidNameException();
        }
        return new ResultWrapper(new String[] { lastName, firstName, middleName }, phoneNumber);
    }

    static class ResultWrapper {
        private String[] result;
        private Long phoneNumber;

        public ResultWrapper(String[] result, Long phoneNumber) {
            this.result = result;
            this.phoneNumber = phoneNumber;
        }

        public String[] getResult() {
            return result;
        }

        public Long getPhone() {
            return phoneNumber;
        }
    }

    static class InvalidPhoneNumberException extends Exception {
        public InvalidPhoneNumberException() {
            super("Ошибка: Неверный формат номера телефона (должны быть только цифры)");
        }
    }

    static class InvalidNameException extends Exception {
        public InvalidNameException() {
            super("Ошибка: Неверный формат ФИО (должны быть только буквы)");
        }
    }

    static class InvalidArgumentCountException extends Exception {
        public InvalidArgumentCountException() {
            super("Ошибка: Неверное количество аргументов");
        }
    }
}
