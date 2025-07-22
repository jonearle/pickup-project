export interface Player {
    "id": number;
    "username": string;
    "ranking": number;
}

export interface Host extends Player {
    email: string;
}

export interface Game {
    id: number;
    host: Host;
    players: Player[];
    location: [number, number];
    format: number;
    time: string;
}