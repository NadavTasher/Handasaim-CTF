FROM debian:buster-slim
# Expose ports
EXPOSE 1000
EXPOSE 2000
EXPOSE 80
EXPOSE 443
# Install Docker
RUN apt-get update
RUN apt-get -y install supervisor
RUN apt-get -y install apt-transport-https ca-certificates curl gnupg2 software-properties-common
RUN curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -
RUN add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/debian $(lsb_release -cs) stable"
RUN apt-get update
RUN apt-get -y install docker-ce
RUN systemctl enable docker
# Supervisor
COPY configuration/supervisord.conf /etc/supervisor/conf.d/supervisord.conf
# Copy files
COPY infrastructure /home/infrastructure
COPY challenges /home/challenges
# Copy startup script
COPY configuration/startup.sh /bin/startup
# Permissions
RUN chmod 777 /bin/startup
# Entrypoint command
CMD ["supervisord"]