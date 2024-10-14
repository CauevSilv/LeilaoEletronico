package com.fatec.leilao.controller;

import com.fatec.leilao.dto.ClienteDTO;
import com.fatec.leilao.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> registrarCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO novoCliente = clienteService.registrarCliente(clienteDTO);
        return ResponseEntity.ok(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable int idCliente, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteAtualizado = clienteService.atualizarCliente(idCliente, clienteDTO);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> removerCliente(@PathVariable int idCliente) {
        clienteService.removerCliente(idCliente);
        return ResponseEntity.noContent().build();
    }
}

