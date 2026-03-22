package com.example.desafiopagamentos.service;

import com.example.desafiopagamentos.model.Pagamento;
import com.example.desafiopagamentos.model.enums.StatusPagamento;
import com.example.desafiopagamentos.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;


    public Pagamento receberPagamento(Pagamento pagamento) {
        pagamento.setStatus(StatusPagamento.PENDENTE_PROCESSAMENTO);
        pagamento.setAtivo(true);

        return repository.save(pagamento);
    }


    public Pagamento atualizarStatus(Long id, StatusPagamento novoStatus) {

        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        StatusPagamento statusAtual = pagamento.getStatus();


        if (statusAtual == StatusPagamento.PROCESSADO_SUCESSO) {
            throw new RuntimeException("Um pagamento processado com sucesso não pode ter seu status alterado.");
        }


        if (statusAtual == StatusPagamento.PROCESSADO_FALHA && novoStatus != StatusPagamento.PENDENTE_PROCESSAMENTO) {
            throw new RuntimeException("Um pagamento com falha só pode retornar para o status PENDENTE_PROCESSAMENTO.");
        }


        pagamento.setStatus(novoStatus);
        return repository.save(pagamento);
    }


    public List<Pagamento> listarPagamentos(Integer codigoDebito, String cpfCnpj, StatusPagamento status) {

        Pagamento filtro = new Pagamento();
        filtro.setCodigoDebito(codigoDebito);
        filtro.setCpfCnpj(cpfCnpj);
        filtro.setStatus(status);

        Example<Pagamento> example = Example.of(filtro);
        return repository.findAll(example);
    }


    public Pagamento inativarPagamento(Long id) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));


        if (pagamento.getStatus() != StatusPagamento.PENDENTE_PROCESSAMENTO) {
            throw new RuntimeException("Só é possível inativar pagamentos com status PENDENTE_PROCESSAMENTO.");
        }

        pagamento.setAtivo(false);
        return repository.save(pagamento);
    }
}