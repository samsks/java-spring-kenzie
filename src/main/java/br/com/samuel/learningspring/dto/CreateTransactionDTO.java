package br.com.samuel.learningspring.dto;

import br.com.samuel.learningspring.model.User;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class CreateTransactionDTO {

    @NotNull(message = "Payer is required")
    private long payer_id;

    @NotNull(message = "Payee is required")
    private long payee_id;

    @NotNull(message = "Value is required")
    @DecimalMin(value = "0.01", message = "Value must be greater than 0")
    private float value;

    public long getPayer_id() {
        return payer_id;
    }

    public void setPayer_id(long payer_id) {
        this.payer_id = payer_id;
    }

    public long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(long payee_id) {
        this.payee_id = payee_id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
