package com.fatec.leilao.service;

import com.fatec.leilao.dto.ClienteDTO;
import com.fatec.leilao.entity.Cliente;
import com.fatec.leilao.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO registrarCliente(ClienteDTO clienteDTO) {
        ClienteDTO cliente = new ClienteDTO(clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getEmail(), clienteDTO.getTelefone());
        return modelMapper.map(clienteRepository.save(modelMapper.map(cliente, Cliente.class)), ClienteDTO.class);
    }

    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll().stream().map((element) -> modelMapper.map(element, ClienteDTO.class)).collect(Collectors.toList());
    }

    public ClienteDTO atualizarCliente(int idCliente, ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setNome(clienteDTO.getNome());
            cliente.setCpf(clienteDTO.getCpf());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setTelefone(clienteDTO.getTelefone());
            return modelMapper.map(clienteRepository.save(cliente), ClienteDTO.class);
        }
        return null; // Ou lançar exceção
    }

    public void removerCliente(int idCliente) {
        clienteRepository.deleteById(idCliente);
    }
}
