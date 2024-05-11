import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  build: {
    outDir: 'dist',  // Explicitly specifying the build output directory
  },
  server: {
    port: 5000  // Ensuring the development server port
  }
});
