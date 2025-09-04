FROM node:22.19.0-alpine AS builder
ARG TARGET
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

FROM nginx:alpine
EXPOSE 80
COPY --from=builder /app/dist/sakai-ng/ /usr/share/nginx/html/
COPY default.conf /etc/nginx/conf.d/default.conf
