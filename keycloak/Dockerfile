# Use official Keycloak base image
FROM quay.io/keycloak/keycloak:26.1.4

# Set environment variables
ENV KC_DB=dev-file \
    KEYCLOAK_ADMIN=admin \
    KEYCLOAK_ADMIN_PASSWORD=admin \
    KC_FEATURES=scripts \
    KC_HTTP_PORT=9080 \
    KC_HTTPS_PORT=9443 \
    KC_HTTP_MANAGEMENT_PORT=9990

# Copy realm config and health check script into the image
COPY ./realm-config /opt/keycloak/data/import

# Set the default command
CMD ["start-dev", "--import-realm"]

