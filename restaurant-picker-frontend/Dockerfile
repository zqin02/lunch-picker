FROM node:14-alpine AS build

WORKDIR /app
 
RUN apk add --no-cache git
RUN git clone <REPO_URL> src

WORKDIR /app/src
 
RUN npm install
RUN npm run build --prod

FROM httpd:alpine

/usr/local/apache2/htdocs/

EXPOSE 80

CMD ["httpd-foreground"]
