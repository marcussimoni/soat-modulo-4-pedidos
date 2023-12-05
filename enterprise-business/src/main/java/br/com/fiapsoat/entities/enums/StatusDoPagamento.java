package br.com.fiapsoat.entities.enums;

public enum StatusDoPagamento {
    AGUARDANDO_PAGAMENTO("Aguardando pagamento"),
    AGUARDANDO_CONFIRMACAO("Aguardando confirmação do pagamento pela instituição financeira"),
    CONFIRMADO("Pagamento confirmado"),
    RECUSADO("Pagamento recusado pela instituição financeira")
    ;

    private final String status;

    StatusDoPagamento(String status) {
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

}
