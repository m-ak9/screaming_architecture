package model.compteClient.vo;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String email) {
        if(emailEstValide(email)) {
            return new Email(email);
        }
        else {
            throw new IllegalArgumentException("Le mail saisi n'est pas un mail");
        }
    }

    private static boolean emailEstValide(String email) {
        final String regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(email);

        while (matcher.find()) {
            return true;
        }
        return false;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(value, email1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
