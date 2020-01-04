FROM docker:docker
# Working directory
WORKDIR /home
# Expose ports
EXPOSE 1000
EXPOSE 2000
EXPOSE 80
EXPOSE 443
# Copy files
COPY infrastructure ./infrastructure
COPY challenges ./challenges
# Copy startup script
COPY configuration/startup.sh /bin/startup
# Permissions
RUN chmod 777 /bin/startup
# Entrypoint command
CMD "/bin/startup"