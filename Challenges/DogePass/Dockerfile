FROM php:7.0-apache

COPY src/ /var/www/html/
# Custom for each service
# Commands
RUN chmod 775 /var/www/ -R
RUN chown www-data /var/www/ -R
RUN service apache2 restart