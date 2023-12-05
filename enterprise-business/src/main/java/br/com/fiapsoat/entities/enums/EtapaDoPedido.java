package br.com.fiapsoat.entities.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EtapaDoPedido {
    RECEBIDO("Recebido") {
        @Override
        public EtapaDoPedido proximaEtapa() {
            return EM_PREPARACAO;
        }
    }, EM_PREPARACAO("Em preparação") {
        @Override
        public EtapaDoPedido proximaEtapa() {
            return PRONTO;
        }
    }, PRONTO("Pronto") {
        @Override
        public EtapaDoPedido proximaEtapa() {
            return FINALIZADO;
        }
    }, FINALIZADO("Finalizado") {
        @Override
        public EtapaDoPedido proximaEtapa() {
            return FINALIZADO;
        }
    };

    private final String etapa;
    public abstract EtapaDoPedido proximaEtapa();

    EtapaDoPedido(String etapa) {
        this.etapa = etapa;
    }

    @JsonValue
    public String getEtapa(){
        return this.etapa;
    }

}
