FROM php:7.3-apache
# Enable mods
RUN a2enmod headers ssl
# Copy configuration
COPY configurations/000-default.conf /etc/apache2/sites-available/000-default.conf
# Copy certificates
COPY certificates /home/
# Copy WebApp to /var/www/html
COPY src /var/www/html
# Change ownership of /var/www
RUN chown www-data /var/www/ -R
# Change permissions of /var/www
RUN chmod 775 /var/www/ -R