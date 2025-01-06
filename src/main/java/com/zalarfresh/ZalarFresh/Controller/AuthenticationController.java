package com.zalarfresh.ZalarFresh.Controller;


import com.zalarfresh.ZalarFresh.DTO.request.AuthRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.request.RegisterRequest;
import com.zalarfresh.ZalarFresh.DTO.response.AuthResponseDTO;
import com.zalarfresh.ZalarFresh.Model.Admin;
import com.zalarfresh.ZalarFresh.Service.Impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping("/Admin/RegisterAdmin")
    public ResponseEntity<AuthResponseDTO> registerAdmin(
            @RequestBody RegisterRequest request) {
        AuthResponseDTO responseDTO = authenticationService.registerAdmin(request);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @PostMapping("/authenticate/M")
    public ResponseEntity<AuthResponseDTO> authenticate(
            @RequestBody AuthRequestDTO request
    ) {
        AuthResponseDTO responseDTO = authenticationService.authenticate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
