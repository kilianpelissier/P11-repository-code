FROM node:14-alpine
WORKDIR /app
COPY ./Frontend/medhead/package*.json ./
RUN npm install
COPY ./Frontend/medhead ./
CMD ["npm", "start"]