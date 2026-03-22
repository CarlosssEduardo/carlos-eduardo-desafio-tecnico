package com.example.desafiopagamentos.controller;

import com.example.desafiopagamentos.model.Pagamento;
import com.example.desafiopagamentos.model.enums.StatusPagamento;
import com.example.desafiopagamentos.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;


    @PostMapping
    public ResponseEntity<Pagamento> receberPagamento(@RequestBody Pagamento pagamento) {
        Pagamento novoPagamento = pagamentoService.receberPagamento(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPagamento);
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<Pagamento> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusPagamento novoStatus) {

        Pagamento pagamentoAtualizado = pagamentoService.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(pagamentoAtualizado);
    }

    @GetMapping
    public ResponseEntity<List<Pagamento>> listarPagamentos(
            @RequestParam(required = false) Integer codigoDebito,
            @RequestParam(required = false) String cpfCnpj,
            @RequestParam(required = false) StatusPagamento status) {

        List<Pagamento> pagamentos = pagamentoService.listarPagamentos(codigoDebito, cpfCnpj, status);
        return ResponseEntity.ok(pagamentos);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Pagamento> inativarPagamento(@PathVariable Long id) {
        Pagamento pagamentoInativado = pagamentoService.inativarPagamento(id);
        return ResponseEntity.ok(pagamentoInativado);
    }
}