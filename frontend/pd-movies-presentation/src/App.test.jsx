import React from 'react';
import '@testing-library/jest-dom';
import {render, screen} from '@testing-library/react';
import App from './App.jsx';

// Mock fetch for movies
global.fetch = jest.fn(() =>
    Promise.resolve({
        json: () => Promise.resolve([]),
    })
);
describe('App', () => {
    it('renders App component', () => {
        render(<App />);
        expect(screen.getByText('Add Movie')).toBeInTheDocument();
    });
});