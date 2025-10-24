// ** API calling script **

import type { Game } from "../types/pickupTypes";

// helper: geocoding address function

export async function geocodeAddress(address: string) {
    // Using Nominatim API to geocode addresses
    const url = new URL('https://nominatim.openstreetmap.org/search');

    // Set url params
    url.searchParams.set('q', address);
    url.searchParams.set('format', 'jsonv2');
    url.searchParams.set('limit', '1');
    url.searchParams.set('email', 'jonearle05@gmail.com');

    // Make call
    const res = await fetch(url.toString());
    if (!res.ok) {
        throw new Error(`Geocoding failed: ${res.status}`);
    }

    // Parse information
    const result = await res.json() as Array<{lat: string, lon: string, display_name: string}>
    if (!result.length) { // If address is undefined
        throw new Error('No results found')
    }
    const { lat, lon, display_name } = result[0];
    const coords = {lat: parseFloat(lat), lon: parseFloat(lon), label: display_name};
    console.log("Printing Nominatim URL:", url.toString()); // Test
    return coords;
}

// GET User
async function getUser(id: number) {
    // Establish headers
    const headers: Headers = new Headers()
    headers.set("Content-Type", "application/json")
    headers.set("Accept", "application/json")

    const request: RequestInfo = new Request(`http://localhost:8080/user/${id}`, {
        method: 'GET',
        headers: headers
    })

    const res = await fetch(request);
    const user = await res.json();
    return user;
}

// POST Register User
export async function registerUser(email: string, username: string, password: string) {
    // Define url/headers
    const url = `http://localhost:8080/signup`;
    const headers: Headers = new Headers();
    headers.set("Content-Type", "application/json");
    headers.set("Accept", "application/json");

    // Make request object for fetch() to accept
    const request: RequestInfo = new Request(url, {
        method: 'POST',
        headers: headers,
        body: JSON.stringify({email, username, password})
    });

    // Fetch data
    const response = await fetch(request);

    // Error check
    if (!response.ok) {
        throw new Error(`HTTP ERROR ${response.status}`);
    }

    return response.json();
}

// GET Load Games
export async function getGames(pageNumber: number = 0, gamesOnPage: number = 16) {
    const headers: Headers = new Headers()
    headers.set("Content-Type", "application/json")
    headers.set("Accept", "application/json")

    const url = `http://localhost:8080/games?pageNumber=${pageNumber}&gamesOnPage=${gamesOnPage}`;

    const request: RequestInfo = new Request(url, {
        method: 'GET',
        headers: headers
    })

    const res = await fetch(request);
    const games: Game[] = await res.json();
    return games;
}

// GET one game

// POST create game
export async function createGame(format: string, address: string, time: string) {
    // Geocode address into coordinates
    console.log("Geocoding address: ", address);
    const coords = await geocodeAddress(address);
    
    // Define url/headers
    const url = `http://localhost:8080/games`;
    const headers: Headers = new Headers();
    headers.set("Content-Type", "application/json");
    headers.set("Accept", "application/json");

    // Make request object for fetch() to accept
    const request: RequestInfo = new Request(url, {
        method: 'POST',
        headers: headers,
        body: JSON.stringify({
            format: parseInt(format, 10),
            address: {x: coords.lon, y:coords.lat},
            time: time})
    });

    // Fetch data
    const response = await fetch(request);

    // Error check
    if (!response.ok) {
        throw new Error(`HTTP ERROR ${response.status}`);
    }

    const data = await response.json();
    return data;
}

// POST sign up for a game
export function joinGame(game: Game) {
    
}

// DELETE delete a game

// leave a game