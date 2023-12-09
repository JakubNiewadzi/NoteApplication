FROM node:alpine as builder

WORKDIR /app
COPY ../../frontend/package.json ./
COPY ../../frontend/package-lock.json ./
COPY ../../frontend ./
RUN npm ci

CMD ["npm", "start"]