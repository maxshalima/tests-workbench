import { KeycloakService } from 'keycloak-angular';

export function initializeKeycloak(keycloak: KeycloakService) {
    return () =>
        keycloak.init({
            config: {
                url: 'http://localhost:9080', // Keycloak server URL
                realm: 'jhipster',
                clientId: 'web_app',
            },
            initOptions: {
                pkceMethod: 'S256',
                onLoad: 'login-required',
                silentCheckSsoRedirectUri: window.location.origin + '/assets/silent-check-sso.html',
            },
            loadUserProfileAtStartUp: true,
        });
}
