FROM node:14-alpine
WORKDIR /app
COPY ./medhead/package*.json ./
RUN npm install
COPY ./medhead ./
CMD ["npm", "start"]