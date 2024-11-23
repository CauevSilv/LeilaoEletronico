package com.caue.lpII.controller;

import com.caue.lpII.config.ExporterConfig;
import com.caue.lpII.entity.dto.LeilaoDETDTO;
import com.caue.lpII.entity.dto.LeilaoDTO;
import com.caue.lpII.entity.dto.LoteDTO;
import com.caue.lpII.service.LeilaoService;
import com.caue.lpII.service.LoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/leiloes")
public class LeilaoController {

    private final LeilaoService leilaoService;
    private final LoteService loteService;

    public LeilaoController(LeilaoService leilaoService, LoteService loteService) {
        this.leilaoService = leilaoService;
        this.loteService = loteService;
    }

    @PostMapping
    @Operation(summary = "Registrar um novo leilão",
            description = "Adiciona um novo leilão ao sistema com as informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Instituição registrada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados fornecidos."),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado."),
            @ApiResponse(responseCode = "408", description = "Tempo de resposta excedido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao tentar registrar a instituição.")
    })
    public ResponseEntity<LeilaoDTO> registrarLeilao(@RequestBody LeilaoDTO leilaoDTO) {
        LeilaoDTO novoLeilao = leilaoService.registrarLeilao(leilaoDTO);
        return ResponseEntity.status(201).body(novoLeilao);
    }

    @GetMapping
    @Operation(summary = "Listar todos os leilões",
            description = "Retorna uma lista de todos os leilões registrados no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de leilões obtida com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao obter a lista de leilões.")
    })
    public ResponseEntity<List<LeilaoDTO>> listarLeiloes() {
        List<LeilaoDTO> leiloes = leilaoService.listarLeiloes();
        return ResponseEntity.ok(leiloes);
    }

    @PutMapping("/{idLeilao}")
    @Operation(summary = "Atualizar um leilão existente",
            description = "Atualiza as informações de um leilão específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leilão atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados fornecidos."),
            @ApiResponse(responseCode = "404", description = "Leilão não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao atualizar o leilão.")
    })
    public ResponseEntity<LeilaoDTO> atualizarLeilao(@PathVariable int idLeilao, @RequestBody LeilaoDTO leilaoDTO) {
        LeilaoDTO leilaoAtualizado = leilaoService.atualizarLeilao(idLeilao, leilaoDTO);
        return ResponseEntity.ok(leilaoAtualizado);
    }

    @DeleteMapping("/{idLeilao}")
    @Operation(summary = "Remover um leilão",
            description = "Remove um leilão do sistema com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Leilão removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Leilão não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao remover o leilão.")
    })
    public ResponseEntity<Void> removerLeilao(@PathVariable int idLeilao) {
        leilaoService.removerLeilao(idLeilao);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detailed/{idLeilao}")
    @Operation(summary = "Listar um leilão de forma detalhada",
            description = "Lista um leilão do sistema com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Leilão listado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Leilão não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao remover o leilão.")
    })
    public ResponseEntity<Object> getLeilaoDetailed(@PathVariable int idLeilao) {
        return ResponseEntity.ok(leilaoService.getLeilaoDetalhado(idLeilao));
    }

    @GetMapping("/{lanceMin}/{lanceMax}/{idLeilao}")
    @Operation(summary = "Listar Lotes por faixa de lance inicial no leilão",
            description = "Lista os lotes dentro de um intervalo de valores de lance inicial para um leilão específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lotes listados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum lote encontrado no intervalo especificado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<List<LoteDTO>> listarLotesPorLanceInicialNoLeilao(
            @PathVariable double lanceMin,
            @PathVariable double lanceMax,
            @PathVariable int idLeilao) {
        List<LoteDTO> lotes = loteService.listarLotesEntreLances(lanceMin, lanceMax, idLeilao);
        return ResponseEntity.ok(lotes);
    }

    @GetMapping("/total/{idLeilao}/{min}/{max}")
    @Operation(summary = "Listar Lotes por faixa de valores totais no leilão",
            description = "Lista os lotes dentro de uma faixa de valores totais para um leilão específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lotes listados com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum lote encontrado no intervalo especificado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<List<LoteDTO>> buscarLotesPorFaixa(
            @PathVariable double min,
            @PathVariable double max,
            @PathVariable int idLeilao) {
        List<LoteDTO> lotes = loteService.listarLotesEntreLancesTotais(min, max, idLeilao);
        return ResponseEntity.ok(lotes);
    }

    @GetMapping("/exportar/{id}")
    @Operation(summary = "Exportar informações detalhadas de um leilão",
            description = "Gera um arquivo no formato DET com as informações detalhadas de um leilão específico, com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo exportado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Leilão não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao exportar o arquivo.")
    })
    public ResponseEntity<String> exportarLeilao(@PathVariable Long id) {
        try {
            LeilaoDETDTO leilaoDETDTO = leilaoService.montarLeilaoDet(id);

            String projectPath = System.getProperty("user.dir");

            String filePath = projectPath + "/src/main/resources/det/leilao_" + id + ".DET";

            File directory = new File(projectPath + "/src/main/resources/det");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            ExporterConfig.exportLeilaoExportacaoToDetFile(leilaoDETDTO, filePath);

            return ResponseEntity.ok("Arquivo exportado com sucesso para: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        } catch (RuntimeException e) {
            return ResponseEntity.ok("Leilão não encontrado: " + e.getMessage());
        }
    }
}

