package br.com.api.mock;

import br.com.api.dto.TokenDTO;

public class TokenMocks {
    public static TokenDTO createTokenDtoMock() {
        return new TokenDTO("Test");
    }
}
