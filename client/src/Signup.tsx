// Clone of Login Page (more or less)
import './css/Signup.css'
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { registerUser } from './services/gameAPI'
// navigate?


export default function Signup() {
    const navigate = useNavigate();

    // State variables
    const [email, setEmail] = useState("")
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    // Handle Submit (override default browser button onsubmit action)
    async function handleSubmit(event: React.FormEvent) {
        event.preventDefault(); // stops browsers default page reload
        
        try {
            const newUser = await registerUser(email, username, password);
            // Log user creation
            console.log('Created user:', newUser);
            navigate('/games');
        } catch(error) { // Basic safety
            console.error('Error registering user:', error);
        }
    }

    return (
      <div className="main-wrapper">
        <div className='content'>
          <h1 className="headermain">Welcome to Hoop Helper!</h1>
          <div className="form-container">
            <form className="hpgauth" action="create-user" onSubmit={handleSubmit}>
              <label htmlFor="username">Email Address</label> 
              <input type="text" className="email" value={email} onChange={(e) => setEmail(e.target.value)}/> 
              <label htmlFor="username">Username</label> 
              <input type="text" className="username" value={username} onChange={(e) => setUsername(e.target.value)}/> 
              <label htmlFor="password">Password</label>
              <input type="text" className="password" value={password} onChange={(e) => setPassword(e.target.value)}/>
              <button className="signup-button">Create Account!</button>
            </form>
          </div>
        </div>
      </div>
    );
  }