package com.es.segurosinseguros.utils;

import com.es.segurosinseguros.dto.UsuarioDTO;
import com.es.segurosinseguros.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO entityToDto (Usuario u) {

        String[] roles = u.getRoles().split(",");
        return new UsuarioDTO(
                u.getUsername(),
                u.getPassword(),
                roles
        );
    }

}
