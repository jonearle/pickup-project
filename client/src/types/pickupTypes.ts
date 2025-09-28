export interface User {
    id: number;
    username: string;
    password: string;
    ranking: number;
}

export interface Game {
    id: number;
    host: User;
    players: User[];
    address: { x: number; y: number }
    format: number;
    time: string;
    status: number;
    // 0 = Waiting for players
    // 1 = full
    // 2 = complete
}