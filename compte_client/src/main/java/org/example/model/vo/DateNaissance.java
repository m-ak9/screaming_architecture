package org.example.model.vo;

import java.time.LocalDate;
import java.util.Objects;

public class DateNaissance {
    private final LocalDate value;

    private DateNaissance(LocalDate value){
        this.value = value;
    }

    public static DateNaissance of(LocalDate dateNaissance){
        if(dateNaissanceEstValide(dateNaissance)) {
            return new DateNaissance(dateNaissance);
        }
        else {
            throw new IllegalArgumentException("La date de naissance saisie doit être inférieure à la date actuelle");
        }
    }

    private static boolean dateNaissanceEstValide(LocalDate dateNaissance) {
        return dateNaissance.isBefore(LocalDate.now());
    }

    public LocalDate getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateNaissance dateNaissance1 = (DateNaissance) o;
        return Objects.equals(value, dateNaissance1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
