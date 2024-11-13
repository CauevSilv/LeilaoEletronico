package com.caue.lpII.service;

import com.caue.lpII.entity.dto.ClienteDTO;
import com.caue.lpII.entity.Cliente;
import com.caue.lpII.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO registrarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getEmail(), clienteDTO.getTelefone());
        return clienteRepository.save(cliente).toDTO();
    }

    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll().stream().map(Cliente::toDTO).toList();
    }

    public ClienteDTO atualizarCliente(int idCliente, ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setNome(clienteDTO.getNome());
            cliente.setCpf(clienteDTO.getCpf());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setTelefone(clienteDTO.getTelefone());
            return clienteRepository.save(cliente).toDTO();
        }
        return null;
    }

    public void removerCliente(int idCliente) {
        clienteRepository.deleteById(idCliente);
    }
}
