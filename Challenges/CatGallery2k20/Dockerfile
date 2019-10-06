FROM php:7.0-apache

COPY src/ /var/www/html/
RUN useradd -g www-data nopermissions
RUN chown nopermissions:www-data /var/www/html -R
RUN chmod 750 /var/www/html -R
RUN chmod 777 /var/www/html/cats
