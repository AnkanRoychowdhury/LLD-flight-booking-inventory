package space.ankanroychowdhury.models;

import lombok.Getter;
import space.ankanroychowdhury.exceptions.InsufficientFundsException;
import space.ankanroychowdhury.exceptions.InvalidAmountException;

import java.util.Objects;

@Getter
public final class User {
    private final String id;
    private final String name;
    private double wallet;

    private User(Builder builder) {
        this.id = Objects.requireNonNull(builder.id);
        this.name = Objects.requireNonNull(builder.name);
        this.wallet = builder.wallet;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public synchronized void addFunds(double amount) {
        if (amount < 0) {
            throw new InvalidAmountException("Amount to be added should be non-negative");
        }
        this.wallet += amount;
    }
    public synchronized void deductFunds(double amount) {
        if (amount < 0) {
            throw new InvalidAmountException("Amount to be deducted should be non-negative");
        }
        if (this.wallet < amount) {
            throw new InsufficientFundsException("Insufficient funds in wallet");
        }
        this.wallet -= amount;
    }
    public static class Builder {
        private String id;
        private String name;
        private double wallet;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder wallet(double wallet) {
            this.wallet = wallet;
            return this;
        }
        public User build() {
            return new User(this);
        }
    }
    public String toString(){
        return "<"+id+", "+name+", "+wallet+">";
    }
}
