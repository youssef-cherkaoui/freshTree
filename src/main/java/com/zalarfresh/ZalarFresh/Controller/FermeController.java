package com.zalarfresh.ZalarFresh.Controller;


import com.zalarfresh.ZalarFresh.DTO.request.FermeCriteresRechercheDTO;
import com.zalarfresh.ZalarFresh.DTO.request.FermeRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.FermeResponseDTO;
import com.zalarfresh.ZalarFresh.Service.FermeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/fermes")
//@RequiredArgsConstructor
@Tag(name = "Fermes", description = "Endpoints pour gestion des fermes" )
public class FermeController {

    @Autowired
    private  FermeService fermeService;

    @PostMapping("/CreateFerme")
    @Operation(summary = "Creation nouvelle ferme" , description = "Créer une nouvelle ferme avec les détails spécifiés")
    public ResponseEntity<FermeResponseDTO> creationFerme(@RequestBody @Valid FermeRequestDTO fermeRequestDTO) {
        FermeResponseDTO fermeResponseDTO = fermeService.creationFerme(fermeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(fermeResponseDTO);
    }


    @GetMapping("/GetFermes")
    @Operation(summary = "Get all fermes" , description = "Récupérer une liste de toutes les fermes")
    public ResponseEntity<Page<FermeResponseDTO>> getAllFermes(Pageable pageable) {
        return ResponseEntity.ok(fermeService.getAllFermes(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "get ferme avec id" , description = "Récupérer une ferme par son id")
    public ResponseEntity<FermeResponseDTO> getFerme(@PathVariable Long id) {
        return ResponseEntity.ok(fermeService.getFermeById(id));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update ferme" , description = "Mettre à jour la ferme avec l'ID spécifié")
    public ResponseEntity<FermeResponseDTO> updateFerme(@PathVariable Long id,@RequestBody @Valid FermeRequestDTO fermeDTO) {
        return ResponseEntity.ok(fermeService.updateFerme(id, fermeDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "supprimer ferme" , description = "Supprimer la ferme avec l'ID spécifié")
    public ResponseEntity<Void> deleteFerme(@PathVariable Long id) {
        fermeService.deleteFerme(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    @Operation(summary = "Search ferme" , description = "Recherche du ferme avec Critere")
    public ResponseEntity<List<FermeResponseDTO>> searchFerme(@RequestBody FermeCriteresRechercheDTO critere){
        return ResponseEntity.ok(fermeService.searchFerme(critere));
    }
}
