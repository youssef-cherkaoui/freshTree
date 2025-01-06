package com.zalarfresh.ZalarFresh.Controller;


import com.zalarfresh.ZalarFresh.DTO.request.ChampRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ChampResponseDTO;
import com.zalarfresh.ZalarFresh.Repository.ChampRepository;
import com.zalarfresh.ZalarFresh.Service.ChampService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/champs")
@Tag(name = "Champs", description = "Endpoints gestion des champs")
@RequiredArgsConstructor
public class ChampController {

    private final ChampService champService;

    @PostMapping("/CreateChamp")
    @Operation(summary = "Créer un nouveau champ" , description = "Créer un nouveau champ avec les détails spécifiés")
    public ResponseEntity<ChampResponseDTO> createChamp(@RequestBody @Valid ChampRequestDTO champDTO) {
        ChampResponseDTO createChamp = champService.creationChamp(champDTO);
        return ResponseEntity.ok(createChamp);
    }

    @GetMapping("/GetChamps")
    public ResponseEntity<List<ChampResponseDTO>> getAllChamps() {
        return ResponseEntity.ok(champService.getAllChamps());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampResponseDTO> getChampById(@PathVariable Long id) {
        ChampResponseDTO champ = champService.getChampById(id);
        return ResponseEntity.ok(champ);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChampResponseDTO> updateChamp(@PathVariable Long id, @RequestBody @Valid ChampRequestDTO champDTO) {
        ChampResponseDTO updatechamp = champService.updateChamp(id, champDTO);
        return ResponseEntity.ok(updatechamp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ChampResponseDTO> deleteChamp(@PathVariable Long id) {
        ChampResponseDTO deletechamp = champService.deleteChamp(id);
        return ResponseEntity.ok(deletechamp);
    }
}
