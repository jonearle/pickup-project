import { useState } from 'react';
import './css/Create.css'
import { useNavigate } from 'react-router-dom';
import { createGame } from './services/gameAPI'

function Header() {
    const navigate = useNavigate();
  
    return (
      <header>
        <div className='site-name'>Hoop Helper</div>
        <button className='guest-login' onClick={() => navigate('/login')}>Login</button>
      </header>
    );
}

export default function Create() {
    const navigate = useNavigate()

    // State variables
    const [format, setFormat] = useState("")
    const [courtAddress, setAddress] = useState("")
    const [dateTime, setTime] = useState("")

    // handleSubmit function for POST request
    async function handleSubmit(event: React.FormEvent) {
        event.preventDefault(); // stops browsers default page reload
        
        try {
            const newGame = await createGame(format, courtAddress, dateTime);
            // Log game creation
            console.log('Created game:', newGame);
            // Navigate to games page
            navigate('/games');
        } catch(error) {
            console.error('Error creating game:', error);
        }
    }

    return (
        <div>
            <Header />
            <hr />
            <h1 className="main-header">Create a game.</h1>
            <div className="game-create-page">
                <div className="game-form-container">
                    <form className="game-form" 
                        action="create-game"
                        onSubmit={handleSubmit}>
                        <label>Format</label>
                        <select 
                            className="format"
                            value={format}
                            onChange={(e) => setFormat(e.target.value)}>
                            <option value="1">1v1</option>
                            <option value="2">2v2</option>
                            <option value="3">3v3</option>
                            <option value="4">4v4</option>
                            <option value="5">5v5</option>
                        </select>

                        <label>Court Address</label>
                        <input 
                            type="text" 
                            className="address"
                            value={courtAddress}
                            onChange={(e) => setAddress(e.target.value)}/>

                        <label className="date-time">Date and Time</label>
                        <input 
                            type="datetime-local" 
                            className="time"
                            value={dateTime}
                            onChange={(e) => setTime(e.target.value)}/>

                        <button className="confirm-create" type="submit">Create!</button>
                    </form>
                </div>
            </div>
        </div>
    );
}