package com.gdp.service.auth.security.core.clientdetails;

import com.gdp.service.admin.api.OAuthClientFeignClient;
import com.gdp.service.admin.dto.AuthClientDTO;
import com.gdp.service.auth.common.enums.PasswordEncoderTypeEnum;
import com.gdp.service.common.core.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private OAuthClientFeignClient oAuthClientFeignClient;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        try {
            Result<AuthClientDTO> result = oAuthClientFeignClient.getOAuth2ClientById(clientId);
            if (Result.isSuccess(result)){
                AuthClientDTO client = result.getData();

                BaseClientDetails clientDetails = new BaseClientDetails(
                        client.getClientId(),
                        client.getResourceIds(),
                        client.getScope(),
                        client.getAuthorizedGrantTypes(),
                        client.getAuthorities(),
                        client.getWebServerRedirectUri()
                );
                clientDetails.setClientSecret(PasswordEncoderTypeEnum.NOOP.getPrefix() + client.getClientSecret());
                clientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
                clientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
                return clientDetails;
            }
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }catch (Exception e){
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }
    }
}
