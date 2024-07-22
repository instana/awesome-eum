#/bin/bash

docker run -d -v /root/images/build:/var/www/html -p 3380:80 --name sourcemap_test php:7.2-apache