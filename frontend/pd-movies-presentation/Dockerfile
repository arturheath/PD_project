# Use the official Node.js 20 image as a base
FROM node:20-alpine

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install project dependencies
RUN npm install

# Copy the rest of the project
COPY . .

# Build the project
RUN npm run build

# Expose port 5000
EXPOSE 5000

# Run the application
CMD [ "npm", "run", "serve" ]