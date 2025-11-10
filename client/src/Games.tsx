import './css/Games.css'
import Map from './Map.tsx'
import { useNavigate } from 'react-router-dom';
import type { Game } from './types/pickupTypes.ts'
import { getGames } from './services/gameAPI.ts'
import { useEffect, useState } from 'react';

function Header() {
  const navigate = useNavigate();

  return (
    <header>
      <div className='site-name'>Hoop Helper</div>
      <button className='guest-login' onClick={() => navigate('/login')}>Login</button>
    </header>
  );
}

function Footer() {
  const navigate = useNavigate();

  return (
    <button className="create-game-button" onClick={() => navigate('/games/create')}>Create a Game</button>
  )
}

// For the game page
// ** null host is temporary, missing map **
function renderGame(game: Game) {
  const navigate = useNavigate();

  return(
  <>
    <div className="game">
      <h2>{game.format} on {game.format} Game</h2>
      <h3>Host: {game.host?.username ?? 'No host yet'}</h3>
      <h3>Players:</h3>
      <ul>
        {/* Mapping through the list of players in the JSON file */}
        {game.players.map(player => (<li key={player.id}>{player.username}</li>))}
      </ul>
      <h3>Location:</h3>
      <Map key={game.id} coords={game.address}/>
      <h3>Time: {game.time}</h3>
      <button className="join-game">Join Game</button>
    </div>
  </>
  )
}

export default function App() {
    // games is the state value; setGames is how you update it
    const [games, setGames] = useState<Game[]>([]);

    // To connect with the api
    useEffect(() => {
      getGames()
        .then(setGames)
    }, [])

    // Render
    return (
    <>
        <Header />
        <hr />
        <h1 className="headermain">Check out some games near you.</h1>
        <div className="game-list">
        {games.map(game => (
          <div key={game.id}>
            {renderGame(game)}
          </div>
        ))}
        </div>
        <Footer />
    </>
    )
}