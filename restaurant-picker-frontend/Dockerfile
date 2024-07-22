FROM node:20.15-buster AS build

WORKDIR /app
COPY . /app
RUN npm install
RUN npm run build

FROM httpd:alpine

COPY --from=build /app/dist/restaurant-picker-frontend/browser /usr/local/apache2/htdocs/


EXPOSE 80

CMD ["httpd-foreground"]
