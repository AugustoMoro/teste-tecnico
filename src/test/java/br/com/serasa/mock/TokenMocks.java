package br.com.serasa.mock;

import br.com.serasa.dto.TokenDTO;

public class TokenMocks {
    public static TokenDTO createTokenDtoMock() {
        return new TokenDTO("Test");
    }
}
