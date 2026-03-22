package com.example.desafiopagamentos.model;

import com.example.desafiopagamentos.model.enums.MetodoPagamento;
import com.example.desafiopagamentos.model.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer codigoDebito;

    private String cpfCnpj;

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;

    private String numeroCartao;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status = StatusPagamento.PENDENTE_PROCESSAMENTO;

    private Boolean ativo = true;
}
