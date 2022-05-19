package com.gdp.service.auth.security.core.clientdetails;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        String resourceIds = "";
        String scope = "all";
        String authorizedGrantTypes = "password,sms_code, mobile, refresh_token";
        String authorities = "ROLE_APP";
        String webServerRedirectUri = "http://www.baidu.com";

        BaseClientDetails clientDetails = new BaseClientDetails(
                clientId,
                resourceIds,
                scope,
                authorizedGrantTypes,
                authorities,
                webServerRedirectUri
        );
        clientDetails.setClientSecret("{noop}" + "LKg7icFR/nbIWATvAoqYEyyoO4nBUf52");
        clientDetails.setAccessTokenValiditySeconds(1209600);
        clientDetails.setRefreshTokenValiditySeconds(2592000);
        return clientDetails;
    }
}
