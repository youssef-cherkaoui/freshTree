package com.zalarfresh.ZalarFresh.DTO.response;

import com.zalarfresh.ZalarFresh.Model.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO

{
    private String token;
    private Admin user;
}
