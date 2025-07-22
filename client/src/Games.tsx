import './css/Games.css'
import Map from './Map.tsx'
import { useNavigate } from 'react-router-dom';
import type { Game } from './types/pickupTypes.ts'
import exuser from './data/exuser.json';
import exgame from './data/exgame.json';

function Header() {
  const navigate = useNavigate();

  return (
    <header>
      <div className='site-name'>Hoop Helper</div>
      <button className='guest-login' onClick={() => navigate('/login')}>Login</button>
    </header>
  );
}

// For the game page
function renderGame(game: Game) {
  return(
  <>
    <div className="game">
      <h2>{game.format} on {game.format} Game</h2>
      <h3>Host: {game.host.username}</h3>
      <h3>Players:</h3>
      <ul>
        {/* Mapping through the list of players in the JSON file */}
        {game.players.map(player => (<li key={player.id}>{player.username}</li>))}
      </ul>
      <h3>Location:</h3>
      <Map />
      <h3>Time: {game.time}</h3>
    </div>
  </>
  )
}

export default function App() {
    {/* This is so that location can be recognized as a tuple not an array */}
    const game = renderGame({
    ...exgame,
    location: exgame.location as [number, number] 
    });

    return (
    <>
        <Header />
        <hr />
        <h1 className="headermain">Check out some games near you.</h1>
        <div className="game-list">
          {game}
          {game}
          {game}
        </div>
    </>
    )
}