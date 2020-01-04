FROM docker:dind
# Expose ports
EXPOSE 1000 2000 80 443
# Copy files
COPY infrastructure /home/infrastructure
COPY challenges /home/challenges
# Copy run.sh
COPY run.sh /home/run.sh
# Permissions
RUN chmod 777 -R /home